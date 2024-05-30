package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Mapper(componentModel = "spring")
public interface RadioSubjectConvert {
    /**
     * 将 BO 转换为 Radio
     *
     * @param subjectAnswerBO BO
     * @return Radio
     */
    SubjectRadio convertSubjectAnswerBoToSubjectRadio(SubjectAnswerBO subjectAnswerBO);
}
