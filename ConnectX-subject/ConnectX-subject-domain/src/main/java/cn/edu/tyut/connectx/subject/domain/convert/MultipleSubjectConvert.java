package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/30
 */
@Mapper(componentModel = "spring")
public interface MultipleSubjectConvert {
    /**
     * 将 answerBo 放入到 SubjectMultiple 表中
     *
     * @param subjectAnswerBO AnswerBo
     * @return 返回subjectMultiple
     */
    SubjectMultiple convertSubjectAnswerBoToSubjectMultiple(SubjectAnswerBO subjectAnswerBO);

    /**
     * 将 answerBo 放入到 SubjectMultiple 表中
     *
     * @param subjectMultipleList subjectMultipleList
     * @return 返回SubjectAnswerBo
     */
    List<SubjectAnswerBO> convertSubjectMultipleListToSubjectAnswerBo(List<SubjectMultiple> subjectMultipleList);
}
