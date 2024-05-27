package cn.edu.tyut.connectx.subject.application.convert;

import cn.edu.tyut.connectx.subject.application.dto.SubjectLabelDTO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Mapper(componentModel = "spring")
public interface SubjectLabelDTOConvert {
    /**
     * 将 LabelDTO 转换为 BO
     *
     * @param subjectLabelDTO 需要转换的DTO
     * @return 转换后的BO
     */
    SubjectLabelBO convertSubjectLabelDTOToSubjectLabelBO(SubjectLabelDTO subjectLabelDTO);
}
