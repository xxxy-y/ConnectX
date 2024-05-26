package cn.edu.tyut.connectx.subject.domain.service;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
public interface SubjectCategoryDomainService {
    /**
     * 新增数据
     *
     * @param subjectCategoryBo 实例对象
     */
    void add(SubjectCategoryBO subjectCategoryBo);

    /**
     * 查询岗位大类
     *
     * @return SubjectCategoryBO
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);
}
