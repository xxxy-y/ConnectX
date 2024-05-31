package cn.edu.tyut.connectx.subject.application.controller;

import cn.edu.tyut.connectx.subject.application.convert.SubjectAnswerDtoConvert;
import cn.edu.tyut.connectx.subject.application.convert.SubjectInfoDtoConvert;
import cn.edu.tyut.connectx.subject.application.dto.SubjectInfoDTO;
import cn.edu.tyut.connectx.subject.common.entity.PageResult;
import cn.edu.tyut.connectx.subject.common.entity.Result;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectAnswerBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectInfoDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 刷题controller
 *
 * @Author 吴庆涛
 * @DATE 2024/5/23
 */
@RestController
@Slf4j
@RequestMapping("/subject")
public class SubjectController {
    private SubjectInfoDtoConvert subjectInfoDtoConvert;
    private SubjectAnswerDtoConvert subjectAnswerDtoConvert;
    private SubjectInfoDomainService subjectInfoDomainService;

    @Autowired
    public void setSubjectAnswerDtoConvert(SubjectAnswerDtoConvert subjectAnswerDtoConvert) {
        this.subjectAnswerDtoConvert = subjectAnswerDtoConvert;
    }

    @Autowired
    public void setSubjectInfoDomainService(SubjectInfoDomainService subjectInfoDomainService) {
        this.subjectInfoDomainService = subjectInfoDomainService;
    }

    @Autowired
    public void setSubjectInfoDtoConvert(SubjectInfoDtoConvert subjectInfoDtoConvert) {
        this.subjectInfoDtoConvert = subjectInfoDtoConvert;
    }

    @PostMapping("/add")
    public Result<Object> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("subject.add.subjectInfoDTO:{}", subjectInfoDTO);
            }
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectName(), "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()), "题目分类不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()), "题目标签不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getOptionList()), "题目答案不能为空");
            SubjectInfoBO subjectInfoBO = subjectInfoDtoConvert.convertSubjectInfoDtoToSubjectInfoBo(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOList = subjectAnswerDtoConvert.convertSubjectAnswerDtoListToSubjectAnswerBoList(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOList);
            Boolean add = subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(add);
        } catch (Exception e) {
            log.error("SubjectController.add.subjectInfoDTO: {}", subjectInfoDTO);
            return Result.fail("新增题目失败");
        }
    }

    @PostMapping("/getSubjectPage")
    public Result<Object> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage.subjectInfoDTO:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "分类ID不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "标签ID不能为空");
            SubjectInfoBO subjectInfoBO = subjectInfoDtoConvert.convertSubjectInfoDtoToSubjectInfoBo(subjectInfoDTO);
            PageResult<SubjectInfoBO> subjectInfoBoPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(subjectInfoBoPageResult);
        } catch (Exception e) {
            log.error("SubjectController.getSubjectPage.error: {}", e.getMessage());
            return Result.fail("分页查询失败");
        }
    }

    @PostMapping("/querySubjectInfo")
    public Result<Object> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.querySubjectInfo.subjectInfoDTO:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目ID不能为空");
            SubjectInfoBO subjectInfoBO = subjectInfoDtoConvert.convertSubjectInfoDtoToSubjectInfoBo(subjectInfoDTO);
            SubjectInfoBO subjectInfoBo = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
            SubjectInfoDTO subjectInfoDto = subjectInfoDtoConvert.convertSubjectInfoBoToSubjectInfoDto(subjectInfoBo);
            return Result.ok(subjectInfoDto);
        } catch (Exception e) {
            log.error("SubjectController.querySubjectInfo.error: {}", e.getMessage(), e);
            return Result.fail("查询题目详情失败");
        }
    }
}
