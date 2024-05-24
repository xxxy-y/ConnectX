package cn.edu.tyut.connectx.subject.application.convert;

import cn.edu.tyut.connectx.subject.application.dto.SubjectCategoryDTO;
import cn.edu.tyut.connectx.subject.domain.convert.SubjectCategoryConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Mapper(componentModel = "spring")
public interface SubjectCategoryDTOConvert {
    SubjectCategoryDTOConvert INSTANCE = Mappers.getMapper(SubjectCategoryDTOConvert.class);

    /**
     * 将 SubjectCategoryDTO 对象转换为 SubjectCategoryBO
     * @param subjectCategoryDTO 转换前
     * @return 转换后的对象
     */
    SubjectCategoryBO subjectCategoryBoToSubjectCategory(SubjectCategoryDTO subjectCategoryDTO);
}
