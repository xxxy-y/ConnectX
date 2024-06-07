package cn.edu.tyut.connectx.subject.infra.basic.service;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2024-05-27 13:54:58
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    SubjectLabel insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return true / false
     */
    boolean update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 添加数据
     *
     * @param subjectLabel 数据
     * @return 添加条数
     */
    Integer add(SubjectLabel subjectLabel);

    /**
     * 查询分类下的标签
     *
     * @param categoryIdList 分类id列表
     * @return 分类下的标签
     */
    List<SubjectLabel> batchQueryById(List<Long> categoryIdList);

    /**
     * 根据条件查询标签数据
     *
     * @param subjectLabel 条件
     * @return 返回查询到的标签数据
     */
    List<SubjectLabel> queryByCondition(SubjectLabel subjectLabel);
}
