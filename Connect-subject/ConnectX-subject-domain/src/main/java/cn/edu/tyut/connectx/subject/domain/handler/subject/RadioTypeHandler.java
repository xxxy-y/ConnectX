package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.convert.RadioSubjectConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectOptionBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectRadio;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectRadioService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler {
    private RadioSubjectConvert radioSubjectConvert;
    private SubjectRadioService subjectRadioService;

    @Autowired
    public void setSubjectRadioService(SubjectRadioService subjectRadioService) {
        this.subjectRadioService = subjectRadioService;
    }

    @Autowired
    public void setRadioSubjectConvert(RadioSubjectConvert radioSubjectConvert) {
        this.radioSubjectConvert = radioSubjectConvert;
    }

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(@NotNull SubjectInfoBO subjectInfoBo) {
        List<SubjectRadio> subjectRadioList = new ArrayList<>();
        subjectInfoBo.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = radioSubjectConvert.convertSubjectAnswerBoToSubjectRadio(option);
            subjectRadio.setSubjectId(subjectInfoBo.getId());
            subjectRadioList.add(subjectRadio);
        });
        return subjectRadioService.batchInsert(subjectRadioList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(subjectId);
        subjectRadio.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectRadio> result = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBoList = radioSubjectConvert.convertSubjectRadioListToSubjectAnswerBOList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBoList);
        return subjectOptionBO;
    }
}
