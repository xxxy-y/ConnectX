package cn.edu.tyut.connectx.subject.infra.basic.service;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectRadio;

import java.util.List;

/**
 * 单选题信息表(SubjectRadio)表服务接口
 *
 * @author makejava
 * @since 2024-05-29 20:09:14
 */
public interface SubjectRadioService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio insert(SubjectRadio subjectRadio);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio update(SubjectRadio subjectRadio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量新增数据
     *
     * @param subjectRadioList 需要新增的数据列表
     * @return 受影响的行数
     */
    int batchInsert(List<SubjectRadio> subjectRadioList);

    /**
     * 根据subjectId来查询到对应的SubjectRadio（单选题答案）数据
     *
     * @param subjectId subjectId
     * @return 查询到的列表，其中包含SubjectRadio
     */
    List<SubjectRadio> queryBySubjectId(Long subjectId);

    /**
     * 条件查询
     *
     * @param subjectRadio 条件查询条件
     * @return 条件查询结果
     */
    List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio);
}
