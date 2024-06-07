package cn.edu.tyut.connectx.subject.domain.service.impl;

import cn.edu.tyut.connectx.subject.common.enums.CategoryTypeEnum;
import cn.edu.tyut.connectx.subject.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.subject.domain.convert.SubjectLabelConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectLabelDomainService;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectCategory;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectLabel;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMapping;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectLabelService;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectMappingService;
import cn.edu.tyut.connectx.subject.infra.basic.service.impl.SubjectCategoryServiceImpl;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Slf4j
@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {
    private SubjectLabelService subjectLabelService;
    private SubjectLabelConvert subjectLabelConvert;
    private SubjectMappingService subjectMappingService;
    private SubjectCategoryServiceImpl subjectCategoryService;

    @Autowired
    public void setSubjectMappingService(SubjectMappingService subjectMappingService) {
        this.subjectMappingService = subjectMappingService;
    }

    @Autowired
    public void setSubjectLabelConvert(SubjectLabelConvert subjectLabelConvert) {
        this.subjectLabelConvert = subjectLabelConvert;
    }

    @Autowired
    public void setSubjectLabelService(SubjectLabelService subjectLabelService) {
        this.subjectLabelService = subjectLabelService;
    }

    @Autowired
    public void setSubjectCategoryService(SubjectCategoryServiceImpl subjectCategoryService) {
        this.subjectCategoryService = subjectCategoryService;
    }

    @Override
    public Integer add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.subjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = subjectLabelConvert.convertSubjectLabelBoToSubjectLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(0);
        return subjectLabelService.add(subjectLabel);
    }

    @Override
    public boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.subjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = subjectLabelConvert.convertSubjectLabelBoToSubjectLabel(subjectLabelBO);
        return subjectLabelService.update(subjectLabel);
    }

    @Override
    public boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.delete.subjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = subjectLabelConvert.convertSubjectLabelBoToSubjectLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLabelService.update(subjectLabel);
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(@NotNull SubjectLabelBO subjectLabelBO) {
        // 如果当前分类为一级分类，则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if (CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()) {
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectLabelBO.getCategoryId());
            subjectLabel.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
            List<SubjectLabel> subjectLabelList = subjectLabelService.queryByCondition(subjectLabel);
            // subjectLabel -> subjectLabelBO
            return subjectLabelConvert.convertSubjectLabelListToSubjectLabelBoList(subjectLabelList);
        }
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        List<Long> categoryIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(categoryIdList);
        return subjectLabelConvert.convertSubjectLabelListToSubjectLabelBoList(subjectLabelList);
    }
}
