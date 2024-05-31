package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.convert.BriefSubjectConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectOptionBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectBrief;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectBriefService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public int add(@NotNull SubjectInfoBO subjectInfoBo) {
        SubjectBrief subjectBrief = briefSubjectConvert.convertSubjectInfoBoToSubjectBrief(subjectInfoBo);
        subjectBrief.setSubjectId(subjectInfoBo.getId());
        return subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
