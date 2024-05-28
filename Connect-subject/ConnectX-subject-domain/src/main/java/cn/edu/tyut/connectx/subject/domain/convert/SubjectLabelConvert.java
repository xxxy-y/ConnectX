package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Mapper(componentModel = "spring")
public interface SubjectLabelConvert {
    /**
     * 将BO转换为DO
     *
     * @param subjectLabelBO BO
     * @return DO
     */
    SubjectLabel convertSubjectLabelBoToSubjectLabel(SubjectLabelBO subjectLabelBO);

    /**
     * 将DO转换为BO
     *
     * @param subjectLabel DO
     * @return BO
     */
    List<SubjectLabelBO> convertSubjectLabelListToSubjectLabelBoList(List<SubjectLabel> subjectLabel);

    /**
     * 将DO转换为BO
     *
     * @param subjectLabel 传入的DO
     * @return 返回的BO
     */
    SubjectLabelBO convertSubjectLabelToSubjectLabelBo(SubjectLabel subjectLabel);
}
