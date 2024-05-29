package cn.edu.tyut.connectx.subject.domain.handler.subject;

import cn.edu.tyut.connectx.subject.common.enums.SubjectInfoTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目工厂类
 * 使用工厂+策略模式来增加代码的健壮性
 * 继承InitializingBean实现afterPropertiesSet方法是为了在创建Bean的最后给handlerMap赋值。
 *
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Slf4j
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {
    private final Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    /**
     * 自动注入一个列表，会将在Spring容器中注册的实现该泛型（SubjectTypeHandler）接口的Bean都自动注入到该列表中
     *
     * @param subjectTypeHandlerList 参数
     */
    @Autowired
    public void setSubjectTypeHandlerList(List<SubjectTypeHandler> subjectTypeHandlerList) {
        this.subjectTypeHandlerList = subjectTypeHandlerList;
    }

    /**
     * 根据传入的题目类型数字返回对应的题目handler类
     *
     * @param subjectType 题目类型数字
     * @return 对应的题目handler类
     */
    public SubjectTypeHandler getHandler(int subjectType) {
        SubjectInfoTypeEnum subjectInfoTypeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        log.error("subjectTypeHandlerList: {}", subjectTypeHandlerList);
        return handlerMap.get(subjectInfoTypeEnum);
    }

    /**
     * 在创建该Bean的最后执行的操作
     * 给handlerMap赋值。
     */
    @Override
    public void afterPropertiesSet() {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlerList) {
            handlerMap.put(subjectTypeHandler.getHandlerType(), subjectTypeHandler);
        }
    }
}
