package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.convert.BriefSubjectConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectBrief;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {
    private BriefSubjectConvert briefSubjectConvert;
    private SubjectBriefService subjectBriefService;

    @Autowired
    public void setSubjectBriefService(SubjectBriefService subjectBriefService) {
        this.subjectBriefService = subjectBriefService;
    }

    @Autowired
    public void setBriefSubjectConvert(BriefSubjectConvert briefSubjectConvert) {
        this.briefSubjectConvert = briefSubjectConvert;
    }

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public int add(SubjectInfoBO subjectInfoBo) {
        List<SubjectBrief> subjectBriefList = new ArrayList<>();
        subjectInfoBo.getOptionList().forEach(option -> {
            SubjectBrief subjectBrief = briefSubjectConvert.convertSubjectAnswerBoToSubjectBrief(option);
            subjectBrief.setSubjectId(subjectInfoBo.getId());
            subjectBriefList.add(subjectBrief);
        });
        return subjectBriefService.batchInsert(subjectBriefList);
    }
}
