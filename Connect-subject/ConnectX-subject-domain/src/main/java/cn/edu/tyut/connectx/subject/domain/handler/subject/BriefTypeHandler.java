package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
public class BriefTypeHandler implements SubjectTypeHandler {
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public int add(SubjectInfoBO subjectInfoBo) {
        return 0;
    }
}
