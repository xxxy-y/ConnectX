package cn.edu.tyut.connectx.subject.infra.basic.mapper;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectRadio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单选题信息表(SubjectRadio)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-29 20:09:14
 */
public interface SubjectRadioDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectRadio 查询条件
     * @return 总行数
     */
    long count(SubjectRadio subjectRadio);

    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 影响行数
     */
    int insert(SubjectRadio subjectRadio);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectRadio> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectRadio> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectRadio> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectRadio> entities);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 影响行数
     */
    int update(SubjectRadio subjectRadio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据subjectId来查询
     *
     * @param subjectId subjectId
     * @return 返回列表中包含SubjectRadio答案
     */
    List<SubjectRadio> queryBySubjectId(Long subjectId);

    /**
     * 根据SubjectId查询出当前题目的正确答案内容
     *
     * @param subjectId 题目ID
     * @return 返回查询到的答案(标识a ， b ， c ， d)
     */
    String querySubjectAnswerBySubjectId(Long subjectId);

    /**
     * 条件查询结果
     *
     * @param subjectRadio 条件查询的条件
     * @return 条件查询的结果
     */
    List<SubjectRadio> queryAllByLimit(SubjectRadio subjectRadio);
}

