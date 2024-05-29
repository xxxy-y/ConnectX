package cn.edu.tyut.connectx.subject.domain.service;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
public interface SubjectInfoDomainService {
    /**
     * 新增题目
     *
     * @param subjectInfoBO 传入要新增的题目
     * @return 是否新增成功
     */
    Boolean add(SubjectInfoBO subjectInfoBO);
}
