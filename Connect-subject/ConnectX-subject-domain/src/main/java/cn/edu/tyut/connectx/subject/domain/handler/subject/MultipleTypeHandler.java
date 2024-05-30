package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.convert.MultipleSubjectConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMultiple;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {
    private MultipleSubjectConvert multipleSubjectConvert;
    private SubjectMultipleService subjectMultipleService;

    @Autowired
    public void setSubjectMultipleService(SubjectMultipleService subjectMultipleService) {
        this.subjectMultipleService = subjectMultipleService;
    }

    @Autowired
    public void setMultipleSubjectConvert(MultipleSubjectConvert multipleSubjectConvert) {
        this.multipleSubjectConvert = multipleSubjectConvert;
    }

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public int add(SubjectInfoBO subjectInfoBo) {
        // TODO 这里可以进行多选题特有的检验
        List<SubjectMultiple> multipleList = new ArrayList<>();
        subjectInfoBo.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = multipleSubjectConvert.convertSubjectAnswerBoToSubjectMultiple(option);
            subjectMultiple.setSubjectId(subjectInfoBo.getId());
            multipleList.add(subjectMultiple);
        });
        return subjectMultipleService.batchInsert(multipleList);
    }
}
