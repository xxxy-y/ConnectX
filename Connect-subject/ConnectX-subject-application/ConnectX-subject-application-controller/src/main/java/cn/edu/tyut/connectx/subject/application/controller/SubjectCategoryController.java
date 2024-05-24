package cn.edu.tyut.connectx.subject.application.controller;

import cn.edu.tyut.connectx.subject.application.convert.SubjectCategoryDTOConvert;
import cn.edu.tyut.connectx.subject.application.dto.SubjectCategoryDTO;
import cn.edu.tyut.connectx.subject.common.entity.Result;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectCategoryDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @Autowired
    public void setSubjectCategoryDomainService(SubjectCategoryDomainService subjectCategoryDomainService) {
        this.subjectCategoryDomainService = subjectCategoryDomainService;
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert.INSTANCE.subjectCategoryBoToSubjectCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail();
        }
    }
}
