package cn.edu.tyut.connectx.subject.infra.basic.service;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务接口
 *
 * @author makejava
 * @since 2024-05-28 17:38:17
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);

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
     * @param multipleList 插入的批量
     * @return 受影响行数
     */
    int batchInsert(List<SubjectMultiple> multipleList);

    /**
     * 根据subjectId查询对应的多选题答案
     *
     * @param subjectId 题目id
     * @return 查询到的多选题答案
     */
    List<SubjectMultiple> queryBySubjectId(Long subjectId);

    /**
     * 根据subjectId查询出正确的答案
     *
     * @param subjectId 题目ID
     * @return 查询出的正确答案
     */
    List<String> queryBySubjectIdCorrect(Long subjectId);
}
