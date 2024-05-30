package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/30
 */
@Mapper(componentModel = "spring")
public interface JudgeSubjectConvert {
    /**
     * 将answer转换为Judge
     *
     * @param subjectAnswerBO subjectAnswerBo
     * @return 返回SubjectJudge
     */
    SubjectJudge convertSubjectAnswerBoToSubjectJudge(SubjectAnswerBO subjectAnswerBO);
}
