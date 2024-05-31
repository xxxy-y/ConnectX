package cn.edu.tyut.connectx.subject.infra.basic.service;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectBrief;

import java.util.List;

/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author makejava
 * @since 2024-05-28 17:36:18
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    int insert(SubjectBrief subjectBrief);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量插入
     *
     * @param subjectBriefList 列表
     * @return 受影响行数
     */
    int batchInsert(List<SubjectBrief> subjectBriefList);

    /**
     * 根据题目ID查询答案
     *
     * @param subjectId 题目ID
     * @return 查询到的答案
     */
    SubjectBrief queryBySubjectId(long subjectId);
}
