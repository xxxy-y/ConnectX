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
     * @param subjectCategoryBO 传入的岗位
     * @return 查询出来的信息
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     *
     * @param subjectCategoryBO 传入更新后的数据
     * @return 是否更新成功
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类
     *
     * @param subjectCategoryBO 根据其中id删除分类
     * @return 是否删除成功
     */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
