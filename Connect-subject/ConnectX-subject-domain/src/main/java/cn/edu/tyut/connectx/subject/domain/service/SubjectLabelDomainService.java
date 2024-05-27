package cn.edu.tyut.connectx.subject.domain.service;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
public interface SubjectLabelDomainService {
    Integer add(SubjectLabelBO subjectLabelBO);

    boolean update(SubjectLabelBO subjectLabelBO);

    boolean delete(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
