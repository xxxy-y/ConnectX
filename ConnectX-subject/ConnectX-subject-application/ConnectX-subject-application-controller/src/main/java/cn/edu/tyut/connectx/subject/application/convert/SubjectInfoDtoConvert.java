package cn.edu.tyut.connectx.subject.application.convert;

import cn.edu.tyut.connectx.subject.application.dto.SubjectInfoDTO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
@Mapper(componentModel = "spring")
public interface SubjectInfoDtoConvert {
    /**
     * 将 DTO 转换为 BO
     *
     * @param subjectInfoDTO 传入的 DTO
     * @return 转换后的BO
     */
    SubjectInfoBO convertSubjectInfoDtoToSubjectInfoBo(SubjectInfoDTO subjectInfoDTO);

    /**
     * 将 Bo转换为DTO
     *
     * @param subjectInfoBO 传入的BO
     * @return 转换后的DTO
     */
    SubjectInfoDTO convertSubjectInfoBoToSubjectInfoDto(SubjectInfoBO subjectInfoBO);
}
