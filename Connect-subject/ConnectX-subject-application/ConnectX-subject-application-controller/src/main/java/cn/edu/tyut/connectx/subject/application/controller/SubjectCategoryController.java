package cn.edu.tyut.connectx.subject.application.controller;

import cn.edu.tyut.connectx.subject.application.convert.SubjectCategoryDTOConvert;
import cn.edu.tyut.connectx.subject.application.dto.SubjectCategoryDTO;
import cn.edu.tyut.connectx.subject.common.entity.Result;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectCategoryDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @Autowired
    public void setSubjectCategoryDomainService(SubjectCategoryDomainService subjectCategoryDomainService) {
        this.subjectCategoryDomainService = subjectCategoryDomainService;
    }

    @PostMapping("/add")
    public @NotNull Result<Object> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            // 如果没有这行判断：即使日志级别没有设置为INFO级别，或者日志记录器没有启用INFO级别的日志，也要执行JSON序列化操作，那么便会浪费性能，在高并发的情况下，性能消耗更加明显，所以加上判断，可以优化性能（这是日志优化的一种形式，还有一种形式为异步日志）
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.subjectCategoryDTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isEmpty(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父类ID不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert.INSTANCE.subjectCategoryBoToSubjectCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.info("subjectCategoryController.add.error: {}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }
}
