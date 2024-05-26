package cn.edu.tyut.connectx.subject.domain.convert;

import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Mapper(componentModel = "spring")
public interface SubjectCategoryConvert {
    SubjectCategoryConvert INSTANCE = Mappers.getMapper(SubjectCategoryConvert.class);

    /**
     * 将 SubjectCategoryBO 对象转换为 SubjectCategory
     *
     * @param subjectCategoryBo 转换前
     * @return 转换后的对象
     */
    SubjectCategory subjectCategoryBoToSubjectCategory(SubjectCategoryBO subjectCategoryBo);

    /**
     * 将 SubjectCategory 对象转化为 SubjectCategoryBO
     *
     * @param subjectCategoryList 需要转换的对象
     * @return 转换后的对象
     */
    List<SubjectCategoryBO> subjectCategoryToSubjectCategoryBo(List<SubjectCategory> subjectCategoryList);
}
