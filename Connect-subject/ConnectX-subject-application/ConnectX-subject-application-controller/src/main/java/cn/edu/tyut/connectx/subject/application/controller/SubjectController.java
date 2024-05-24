package cn.edu.tyut.connectx.subject.application.controller;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectCategory;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刷题controller
 *
 * @Author 吴庆涛
 * @DATE 2024/5/23
 */
@RestController
public class SubjectController {
    private SubjectCategoryService categoryService;

    @Autowired
    public void setCategoryService(SubjectCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/test")
    public String test() {
        SubjectCategory subjectCategory = categoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }
}
