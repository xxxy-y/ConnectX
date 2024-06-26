package cn.edu.tyut.connectx.subject.infra.basic.service;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务接口
 *
 * @author makejava
 * @since 2024-05-23 20:40:30
 */
public interface SubjectCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectCategory queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     */
    void insert(SubjectCategory subjectCategory);

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 是否更新成功
     */
    Boolean update(SubjectCategory subjectCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询岗位大类
     *
     * @param subjectCategory 岗位大类的数据
     * @return 返回查询出来的数据
     */
    List<SubjectCategory> queryCategory(SubjectCategory subjectCategory);

    /**
     * 删除分类（修改其中的is_delete）
     *
     * @param subjectCategory 需要修改的数据
     * @return 删除是否成功
     */
    Boolean delete(SubjectCategory subjectCategory);

    /**
     * 查询该分类下的题目数量
     *
     * @param id 分类id
     * @return 返回题目数量
     */
    Integer querySubjectCount(Long id);
}
