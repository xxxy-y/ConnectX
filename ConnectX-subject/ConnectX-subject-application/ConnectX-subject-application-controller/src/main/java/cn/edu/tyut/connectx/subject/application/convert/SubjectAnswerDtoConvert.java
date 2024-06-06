package cn.edu.tyut.connectx.subject.application.convert;

import cn.edu.tyut.connectx.subject.application.dto.SubjectAnswerDTO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
@Mapper(componentModel = "spring")
public interface SubjectAnswerDtoConvert {
    /**
     * List DTO TO List BO
     *
     * @param subjectAnswerDTO 传入的dto
     * @return 返回的Bo
     */
    List<SubjectAnswerBO> convertSubjectAnswerDtoListToSubjectAnswerBoList(List<SubjectAnswerDTO> subjectAnswerDTO);
}
