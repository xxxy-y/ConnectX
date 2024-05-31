package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectOptionBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;

import java.util.List;

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

    /**
     * 将subjectInfo List转换为subjectInfoBo List
     *
     * @param subjectInfoList 参数
     * @return 转换后的
     */
    List<SubjectInfoBO> convertSubjectInfoListToSubjectInfoBoList(List<SubjectInfo> subjectInfoList);

    /**
     * 将subjectInfo转换为subjectInfoBo
     *
     * @param subjectInfo 传入的实体类
     * @param subjectOptionBO 传入的实体类
     * @return 转换后的subjectInfoBO
     */
    SubjectInfoBO convertSubjectOptionAndSubjecyInfoToSubjectInfoBO(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);
}
