package cn.edu.tyut.connectx.subject.domain.service;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
public interface SubjectLabelDomainService {
    /**
     * 添加标签
     *
     * @param subjectLabelBO 要添加的数据
     * @return 影响的行数
     */
    Integer add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     *
     * @param subjectLabelBO 要更新的数据
     * @return 影响的行数
     */
    boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     *
     * @param subjectLabelBO 要删除的数据
     * @return 影响的行数
     */
    boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 查询分类下标签
     *
     * @param subjectLabelBO 传入的参数
     * @return 返回的查询结果
     */
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
