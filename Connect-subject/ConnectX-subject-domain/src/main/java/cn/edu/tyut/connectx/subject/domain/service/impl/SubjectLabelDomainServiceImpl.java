package cn.edu.tyut.connectx.subject.domain.service.impl;

import cn.edu.tyut.connectx.subject.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.subject.domain.convert.SubjectLabelConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectLabelDomainService;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectLabel;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectLabelService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Slf4j
@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {
    private SubjectLabelService subjectLabelService;
    private SubjectLabelConvert subjectLabelConvert;

    @Autowired
    public void setSubjectLabelConvert(SubjectLabelConvert subjectLabelConvert) {
        this.subjectLabelConvert = subjectLabelConvert;
    }

    @Autowired
    public void setSubjectLabelService(SubjectLabelService subjectLabelService) {
        this.subjectLabelService = subjectLabelService;
    }

    @Override
    public Integer add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.subjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = subjectLabelConvert.convertSubjectLabelBOToSubjectLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(0);
        return subjectLabelService.add(subjectLabel);
    }

    @Override
    public boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.subjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = subjectLabelConvert.convertSubjectLabelBOToSubjectLabel(subjectLabelBO);
        return subjectLabelService.update(subjectLabel);
    }

    @Override
    public boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.delete.subjectLabelBO:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = subjectLabelConvert.convertSubjectLabelBOToSubjectLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLabelService.update(subjectLabel);
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = subjectLabelConvert.convertSubjectLabelBOToSubjectLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectLabel> subjectLabelList = subjectLabelService.queryLabelByCategoryId(subjectLabel);
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.queryLabelByCategoryId.subjectLabelList:{}", JSON.toJSONString(subjectLabelList));
        }
        return subjectLabelConvert.convertSubjectLabelToSubjectLabelBO(subjectLabelList);
    }
}
