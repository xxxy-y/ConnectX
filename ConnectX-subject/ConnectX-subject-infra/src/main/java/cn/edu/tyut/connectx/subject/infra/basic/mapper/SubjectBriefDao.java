package cn.edu.tyut.connectx.subject.infra.basic.mapper;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectBrief;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 简答题(SubjectBrief)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-28 17:36:17
 */
public interface SubjectBriefDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 统计总行数
     *
     * @param subjectBrief 查询条件
     * @return 总行数
     */
    long count(SubjectBrief subjectBrief);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 影响行数
     */
    int insert(SubjectBrief subjectBrief);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectBrief> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectBrief> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectBrief> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectBrief> entities);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 影响行数
     */
    int update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据subjectId来查询答案
     *
     * @param subjectId 传入的subjectId
     * @return 查询到的与之对应的答案
     */
    SubjectBrief queryBySubjectId(long subjectId);

    /**
     * 根据subjectBrief查询
     *
     * @param subjectBrief 传入的查询条件
     * @return 查询到的结果
     */
    SubjectBrief queryAllByLimit(SubjectBrief subjectBrief);
}

