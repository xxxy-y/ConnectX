package cn.edu.tyut.connectx.subject.application.convert;

import cn.edu.tyut.connectx.subject.application.dto.SubjectLabelDTO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Mapper(componentModel = "spring")
public interface SubjectLabelDtoConvert {
    /**
     * 将 LabelDTO 转换为 BO
     *
     * @param subjectLabelDTO 需要转换的DTO
     * @return 转换后的BO
     */
    SubjectLabelBO convertSubjectLabelDtoToSubjectLabelBo(SubjectLabelDTO subjectLabelDTO);

    /**
     * 将 BO 转换为 DTO
     * @param subjectLabelBOList 需要转换的BO
     * @return 转换后的DTO
     */
    List<SubjectLabelDTO> convertSubjectLabelBoListToSubjectLabelDtoList(List<SubjectLabelBO> subjectLabelBOList);
}
