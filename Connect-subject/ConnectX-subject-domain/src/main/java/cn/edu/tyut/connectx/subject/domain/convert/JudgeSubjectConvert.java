package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/30
 */
@Mapper(componentModel = "spring")
public interface JudgeSubjectConvert {
    /**
     * 将SubjectJudgeList 转换为 SubjectAnswerBO
     *
     * @param subjectJudgeList JudgeList
     * @return SubjectAnswerBO
     */
    List<SubjectAnswerBO> convertSubjectJudgeListToSubjectAnswerBoList(List<SubjectJudge> subjectJudgeList);
}
