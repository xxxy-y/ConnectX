package cn.edu.tyut.connectx.subject.domain.service.impl;

import cn.edu.tyut.connectx.subject.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.subject.domain.convert.SubjectCategoryConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectCategoryDomainService;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectCategory;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectLabel;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMapping;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectCategoryService;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectLabelService;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectMappingService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Slf4j
@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    private SubjectCategoryService subjectCategoryService;
    private SubjectCategoryConvert subjectCategoryConvert;
    private SubjectMappingService subjectMappingService;
    private SubjectLabelService subjectLabelService;

    @Autowired
    public void setSubjectLabelService(SubjectLabelService subjectLabelService) {
        this.subjectLabelService = subjectLabelService;
    }

    @Autowired
    public void setSubjectMappingService(SubjectMappingService subjectMappingService) {
        this.subjectMappingService = subjectMappingService;
    }

    @Autowired
    public void setSubjectConvert(SubjectCategoryConvert subjectCategoryConvert) {
        this.subjectCategoryConvert = subjectCategoryConvert;
    }

    @Autowired
    public void setSubjectCategoryService(SubjectCategoryService subjectCategoryService) {
        this.subjectCategoryService = subjectCategoryService;
    }

    @Override
    public void add(SubjectCategoryBO subjectCategoryBo) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.subjectCategoryBo:{}", JSON.toJSONString(subjectCategoryBo));
        }
        SubjectCategory subjectCategory = subjectCategoryConvert.convertSubjectCategoryBoToSubjectCategory(subjectCategoryBo);
        subjectCategory.setIsDeleted(0);
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        // 在这一层进行业务性的修改
        SubjectCategory subjectCategory = subjectCategoryConvert.convertSubjectCategoryBoToSubjectCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryConvert.convertSubjectCategoryListToSubjectCategoryBOList(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.subjectCategoryBOList:{}", JSON.toJSONString(subjectCategoryBOList));
        }
        subjectCategoryBOList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });
        return subjectCategoryBOList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = subjectCategoryConvert.convertSubjectCategoryBoToSubjectCategory(subjectCategoryBO);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.update.subjectCategoryBO:{}", JSON.toJSONString(subjectCategoryBO));
        }
        return subjectCategoryService.update(subjectCategory);
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = subjectCategoryConvert.convertSubjectCategoryBoToSubjectCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.delete.subjectCategoryBO:{}", JSON.toJSONString(subjectCategoryBO));
        }
        return subjectCategoryService.delete(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(@NotNull SubjectCategoryBO subjectCategoryBO) {
        // 查询当前大类下所有分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryCategoryAndLabel.subjectCategoryBO:{}", JSON.toJSONString(subjectCategoryBO));
        }
        // 一次获取标签信息
        List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryConvert.convertSubjectCategoryListToSubjectCategoryBOList(subjectCategoryList);
        subjectCategoryBOList.forEach(category -> {
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setCategoryId(category.getId());
            List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
            if (CollectionUtils.isEmpty(subjectMappingList)) {
                return;
            }
            List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
            List<SubjectLabelBO> labelBOList = new LinkedList<>();
            subjectLabelList.forEach(label -> {
                SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
                subjectLabelBO.setId(label.getId());
                subjectLabelBO.setLabelName(label.getLabelName());
                subjectLabelBO.setCategoryId(label.getCategoryId());
                subjectLabelBO.setSortNum(label.getSortNum());
                labelBOList.add(subjectLabelBO);
            });
            category.setLabelBoList(labelBOList);
        });
        return subjectCategoryBOList;
    }
}
