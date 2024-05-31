package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectOptionBO;
import org.springframework.stereotype.Component;

/**
 * 抽象具体类
 *
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Component
public interface SubjectTypeHandler {
    /**
     * 枚举身份的识别
     *
     * @return 返回识别到的枚举身份
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际的题目的插入
     *
     * @param subjectInfoBo 传入的题目信息
     * @return 影响行数 一道题所对应的选项有几个，那么受影响行数就有几个
     */
    int add(SubjectInfoBO subjectInfoBo);

    /**
     * 根据用户id查询用户详情
     *
     * @param subjectId 用户id
     * @return 返回查询出来的答案
     */
    SubjectOptionBO query(Long subjectId);
}
