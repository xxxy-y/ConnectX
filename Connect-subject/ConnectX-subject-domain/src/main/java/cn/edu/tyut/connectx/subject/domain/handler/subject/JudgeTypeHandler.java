package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.convert.JudgeSubjectConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectJudge;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler {
    private JudgeSubjectConvert judgeSubjectConvert;
    private SubjectJudgeService subjectJudgeService;

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
    public int add(SubjectInfoBO subjectInfoBo) {
        List<SubjectJudge> subjectJudgeList = new ArrayList<>();
        subjectInfoBo.getOptionList().forEach(option -> {
            SubjectJudge subjectJudge = judgeSubjectConvert.convertSubjectAnswerBoToSubjectJudge(option);
            subjectJudge.setSubjectId(subjectInfoBo.getId());
            subjectJudgeList.add(subjectJudge);
        });
       return subjectJudgeService.batchInsert(subjectJudgeList);
    }
}
