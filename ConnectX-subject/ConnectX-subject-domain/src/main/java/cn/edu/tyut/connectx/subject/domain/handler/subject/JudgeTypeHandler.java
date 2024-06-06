package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.convert.JudgeSubjectConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectOptionBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectJudge;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectJudgeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler {
    private SubjectJudgeService subjectJudgeService;
    private JudgeSubjectConvert judgeSubjectConvert;

    @Autowired
    public void setSubjectJudgeService(SubjectJudgeService subjectJudgeService) {
        this.subjectJudgeService = subjectJudgeService;
    }

    @Autowired
    public void setJudgeSubjectConvert(JudgeSubjectConvert judgeSubjectConvert) {
        this.judgeSubjectConvert = judgeSubjectConvert;
    }

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public int add(@NotNull SubjectInfoBO subjectInfoBo) {
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBo.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBo.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        return subjectJudgeService.insert(subjectJudge);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBoList = judgeSubjectConvert.convertSubjectJudgeListToSubjectAnswerBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBoList);
        return subjectOptionBO;
    }
}
