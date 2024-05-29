package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
@Mapper(componentModel = "spring")
public interface SubjectInfoConvert {
    /**
     * 将 BO 转换为 实体类
     *
     * @param subjectInfoBO BO
     * @return 转换后的实体类
     */
    SubjectInfo convertSubjectInfoBoToSubjectInfo(SubjectInfoBO subjectInfoBO);
}
