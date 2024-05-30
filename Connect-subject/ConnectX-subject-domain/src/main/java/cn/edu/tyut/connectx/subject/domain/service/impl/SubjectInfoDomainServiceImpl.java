package cn.edu.tyut.connectx.subject.domain.service.impl;

import cn.edu.tyut.connectx.subject.domain.convert.SubjectInfoConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.handler.subject.SubjectTypeHandler;
import cn.edu.tyut.connectx.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import cn.edu.tyut.connectx.subject.domain.service.SubjectInfoDomainService;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectInfo;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMapping;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectInfoService;
import cn.edu.tyut.connectx.subject.infra.basic.service.impl.SubjectMappingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
@Slf4j
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    private SubjectInfoConvert subjectInfoConvert;
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    private SubjectInfoService subjectInfoService;
    private SubjectMappingServiceImpl subjectMappingService;

    @Autowired
    public void setSubjectMappingService(SubjectMappingServiceImpl subjectMappingService) {
        this.subjectMappingService = subjectMappingService;
    }

    @Autowired
    public void setSubjectInfoService(SubjectInfoService subjectInfoService) {
        this.subjectInfoService = subjectInfoService;
    }

    @Autowired
    public void setSubjectInfoConvert(SubjectInfoConvert subjectInfoConvert) {
        this.subjectInfoConvert = subjectInfoConvert;
    }

    @Autowired
    public void setSubjectTypeHandlerFactory(SubjectTypeHandlerFactory subjectTypeHandlerFactory) {
        this.subjectTypeHandlerFactory = subjectTypeHandlerFactory;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.subjectInfoBO:{}", subjectInfoBO);
        }
        // 向SubjectInfo表中插入数据
        SubjectInfo subjectInfo = subjectInfoConvert.convertSubjectInfoBoToSubjectInfo(subjectInfoBO);
        int insert = subjectInfoService.insert(subjectInfo);
        subjectInfoBO.setId(subjectInfo.getId());
        // 向题目答案表（Radio、Multiple、Judge、Brief表）中插入数据，使用的是工厂+策略模式
        Integer subjectType = subjectInfoBO.getSubjectType();
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectType);
        int add = handler.add(subjectInfoBO);
        List<SubjectMapping> subjectMappingList = getSubjectMappingList(subjectInfoBO);
        int add2 = subjectMappingService.batchInsert(subjectMappingList);
        return add > 0 && insert > 0 && add2 > 0;
    }

    private @NotNull List<SubjectMapping> getSubjectMappingList(@NotNull SubjectInfoBO subjectInfoBO) {
        List<Long> categoryIdList = subjectInfoBO.getCategoryIds();
        List<Long> labelIdList = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappingList = new ArrayList<>();
        categoryIdList.forEach(categoryId -> labelIdList.forEach(labelId -> {
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(subjectInfoBO.getId());
            subjectMapping.setCategoryId(categoryId);
            subjectMapping.setLabelId(labelId);
            subjectMappingList.add(subjectMapping);
        }));
        return subjectMappingList;
    }
}
