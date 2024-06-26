package cn.edu.tyut.connectx.subject.infra.basic.service;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-05-28 17:34:42
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 受影响行数
     */
    int insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 条数
     *
     * @param subjectInfo 1
     * @param categoryId  2
     * @param labelId     3
     * @return 返回条数
     */
    int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);

    /**
     * 当前页面查到的数据
     *
     * @param subjectInfo 1
     * @param categoryId  2
     * @param labelId     3
     * @param start       4
     * @param pageSize    5
     * @return 返回当前页面查出来的数据
     */
    List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize);
}
