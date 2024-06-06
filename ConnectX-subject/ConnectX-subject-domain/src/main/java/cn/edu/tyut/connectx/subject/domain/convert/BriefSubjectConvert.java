package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/30
 */
@Mapper(componentModel = "spring")
public interface BriefSubjectConvert {
    /**
     * answerBo To brief
     * @param subjectInfoBO BO
     * @return 返回SubjectBrief
     */
    SubjectBrief convertSubjectInfoBoToSubjectBrief(SubjectInfoBO subjectInfoBO);
}
