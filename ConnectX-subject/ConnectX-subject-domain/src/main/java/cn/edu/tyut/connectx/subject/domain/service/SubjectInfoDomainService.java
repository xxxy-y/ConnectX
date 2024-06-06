package cn.edu.tyut.connectx.subject.domain.service;

import cn.edu.tyut.connectx.subject.common.entity.PageResult;
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

    /**
     * 分页查询题目详情
     *
     * @param subjectInfoBO 传入的Bo
     * @return 返回查询结果
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     *
     * @param subjectInfoBO 其中包含提供的信息
     * @return 返回查询到的结果
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
