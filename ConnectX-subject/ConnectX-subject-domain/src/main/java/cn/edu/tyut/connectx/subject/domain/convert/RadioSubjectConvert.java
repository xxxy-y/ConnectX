package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;

import java.util.List;

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

    /**
     * 将List subjectRadio答案中的数据简化，转换为SubjectAnswerBO
     *
     * @param subjectRadioList SubjectRadioList
     * @return 返回简化后的数据
     */
    List<SubjectAnswerBO> convertSubjectRadioListToSubjectAnswerBOList(List<SubjectRadio> subjectRadioList);
}
