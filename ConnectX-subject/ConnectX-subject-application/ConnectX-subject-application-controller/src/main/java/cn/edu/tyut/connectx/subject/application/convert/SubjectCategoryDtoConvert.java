package cn.edu.tyut.connectx.subject.application.convert;

import cn.edu.tyut.connectx.subject.application.dto.SubjectCategoryDTO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Mapper(componentModel = "spring")
public interface SubjectCategoryDtoConvert {
    /**
     * 将 SubjectCategoryBO 对象转换为 SubjectCategoryDTO
     *
     * @param subjectCategoryBOList 传入的 BOList 对象
     * @return 转换后的 SubjectCategoryDTO 列表
     */
    List<SubjectCategoryDTO> convertSubjectCategoryBoListToSubjectCategoryDtoList(List<SubjectCategoryBO> subjectCategoryBOList);

    /**
     * 将 SubjectCategoryDTO 转换为 SubjectCategoryBO
     *
     * @param subjectCategoryDTO 传入的DTO对象
     * @return 转换后的BO对象
     */
    SubjectCategoryBO convertSubjectCategoryDtoToSubjectCategoryBo(SubjectCategoryDTO subjectCategoryDTO);
}
