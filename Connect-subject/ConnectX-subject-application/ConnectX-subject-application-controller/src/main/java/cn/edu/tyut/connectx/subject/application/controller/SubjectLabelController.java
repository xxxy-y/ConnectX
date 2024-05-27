package cn.edu.tyut.connectx.subject.application.controller;

import cn.edu.tyut.connectx.subject.application.convert.SubjectLabelDTOConvert;
import cn.edu.tyut.connectx.subject.application.dto.SubjectLabelDTO;
import cn.edu.tyut.connectx.subject.common.entity.Result;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectLabelBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectLabelDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签 Controller
 *
 * @Author 吴庆涛
 * @DATE 2024/5/27
 */
@Slf4j
@RestController
@RequestMapping("/subject/label")
public class SubjectLabelController {
    private SubjectLabelDomainService subjectLabelDomainService;
    private SubjectLabelDTOConvert subjectLabelDTOConvert;

    @Autowired
    public void setSubjectLabelDTOConvert(SubjectLabelDTOConvert subjectLabelDTOConvert) {
        this.subjectLabelDTOConvert = subjectLabelDTOConvert;
    }

    @Autowired
    public void setSubjectLabelDomainService(SubjectLabelDomainService subjectLabelDomainService) {
        this.subjectLabelDomainService = subjectLabelDomainService;
    }

    @PostMapping("/add")
    public @NotNull Result<Object> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()), "标签名称不能为空");
            SubjectLabelBO subjectLabelBO = subjectLabelDTOConvert.convertSubjectLabelDTOToSubjectLabelBO(subjectLabelDTO);
            subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.info("SubjectLabelController.add.error: {}", e.getMessage());
            return Result.fail("新增标签失败");
        }
    }

    /**
     * 更新标签
     *
     * @param subjectLabelDTO 更新的数据
     * @return 是否更新成功
     */
    @PostMapping("/update")
    public @NotNull Result<Object> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签ID不能为空");
            SubjectLabelBO subjectLabelBO = subjectLabelDTOConvert.convertSubjectLabelDTOToSubjectLabelBO(subjectLabelDTO);
            boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.info("SubjectLabelController.update.error: {}", e.getMessage());
            return Result.fail("更新标签失败");
        }
    }

    /**
     * 删除标签
     *
     * @param subjectLabelDTO 更新的数据
     * @return 是否删除成功
     */
    @PostMapping("/delete")
    public @NotNull Result<Object> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签ID不能为空");
            SubjectLabelBO subjectLabelBO = subjectLabelDTOConvert.convertSubjectLabelDTOToSubjectLabelBO(subjectLabelDTO);
            boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.info("SubjectLabelController.delete.error: {}", e.getMessage());
            return Result.fail("删除标签失败");
        }
    }

    /**
     * 根据分类查询标签
     *
     * @param subjectLabelDTO 包装
     * @return 返回查询是否结果
     */
    @PostMapping("/queryLabelByCategoryId")
    public @NotNull Result<Object> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类ID不能为空");
            SubjectLabelBO subjectLabelBO = subjectLabelDTOConvert.convertSubjectLabelDTOToSubjectLabelBO(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            return Result.ok(subjectLabelBOList);
        } catch (Exception e) {
            log.info("SubjectLabelController.queryLabelByCategoryId.error: {}", e.getMessage());
            return Result.fail("根据分类查询标签失败");
        }
    }
}
