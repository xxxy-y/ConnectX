package cn.edu.tyut.connectx.subject.application.controller;

import cn.edu.tyut.connectx.subject.application.convert.SubjectCategoryDtoConvert;
import cn.edu.tyut.connectx.subject.application.dto.SubjectCategoryDTO;
import cn.edu.tyut.connectx.subject.common.entity.Result;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectCategoryDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {
    private SubjectCategoryDomainService subjectCategoryDomainService;
    private SubjectCategoryDtoConvert subjectCategoryDtoConvert;

    @Autowired
    public void setSubjectCategoryDtoConvert(SubjectCategoryDtoConvert subjectCategoryDtoConvert) {
        this.subjectCategoryDtoConvert = subjectCategoryDtoConvert;
    }

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
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父类ID不能为空");
            SubjectCategoryBO subjectCategoryBO = subjectCategoryDtoConvert.convertSubjectCategoryDtoToSubjectCategoryBo(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.info("subjectCategoryController.add.error: {}", e.getMessage());
            return Result.fail("新增分类失败");
        }
    }

    @GetMapping("/queryPrimaryCategory")
    public @NotNull Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            SubjectCategoryBO subjectCategoryBO = subjectCategoryDtoConvert.convertSubjectCategoryDtoToSubjectCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = subjectCategoryDtoConvert.convertSubjectCategoryBoListToSubjectCategoryDtoList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("subjectCategoryController.queryPrimaryCategory.error: {}", e.getMessage());
            return Result.fail();
        }
    }

    @PostMapping("/queryCategoryByPrimary")
    public @NotNull Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryByPrimary.subjectCategoryDTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类ID不能为空");
            SubjectCategoryBO subjectCategoryBO = subjectCategoryDtoConvert.convertSubjectCategoryDtoToSubjectCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = subjectCategoryDtoConvert.convertSubjectCategoryBoListToSubjectCategoryDtoList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("subjectCategoryController.queryCategoryByPrimary.error: {}", e.getMessage());
            return Result.fail();
        }
    }

    /**
     * 更新分类
     *
     * @param subjectCategoryDTO 更新后的数据
     * @return 是否成功更新
     */
    @PostMapping("/update")
    public @NotNull Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.update.subjectCategoryDTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "ID不能为空");
            SubjectCategoryBO subjectCategoryBO = subjectCategoryDtoConvert.convertSubjectCategoryDtoToSubjectCategoryBo(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("subjectCategoryController.update.error: {}", e.getMessage());
            return Result.fail(false);
        }
    }

    /**
     * 删除分类
     *
     * @param subjectCategoryDTO 分类id
     * @return 是否删除成功
     */
    @PostMapping("/delete")
    public @NotNull Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.delete.subjectCategoryDTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "ID不能为空");
            SubjectCategoryBO subjectCategoryBO = subjectCategoryDtoConvert.convertSubjectCategoryDtoToSubjectCategoryBo(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("subjectCategoryController.delete.error: {}", e.getMessage());
            return Result.fail(false);
        }
    }
}
