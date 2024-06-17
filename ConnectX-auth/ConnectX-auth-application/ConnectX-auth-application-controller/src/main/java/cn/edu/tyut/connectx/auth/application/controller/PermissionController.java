package cn.edu.tyut.connectx.auth.application.controller;

import cn.edu.tyut.connectx.auth.application.convert.AuthPermissionDtoConvert;
import cn.edu.tyut.connectx.auth.application.dto.AuthPermissionDto;
import cn.edu.tyut.connectx.auth.common.entity.Result;
import cn.edu.tyut.connectx.auth.domain.entity.AuthPermissionBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthPermissionDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/17
 */
@RestController
@Slf4j
@RequestMapping("/permission/")
public class PermissionController {
    private AuthPermissionDtoConvert authPermissionDtoConvert;
    private AuthPermissionDomainService authPermissionDomainService;

    @Autowired
    public void setAuthPermissionDomainService(AuthPermissionDomainService authPermissionDomainService) {
        this.authPermissionDomainService = authPermissionDomainService;
    }

    @Autowired
    public void setAuthPermissionDtoConvert(AuthPermissionDtoConvert authPermissionDtoConvert) {
        this.authPermissionDtoConvert = authPermissionDtoConvert;
    }

    /**
     * 新增权限
     *
     * @param authPermissionDto 权限信息
     * @return 结果
     */
    @PostMapping("add")
    public Result<Object> add(@RequestBody AuthPermissionDto authPermissionDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.PermissionController.add.authPermissionDto:{}", JSON.toJSONString(authPermissionDto));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDto.getName()), "权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDto.getParentId(), "权限父id不为空");
            AuthPermissionBo authPermissionBo = authPermissionDtoConvert.convertAuthPermissionDtoToAuthPermissionBo(authPermissionDto);
            return Result.ok(authPermissionDomainService.add(authPermissionBo));
        } catch (Exception e) {
            log.error("auth.PermissionController.add.authPermissionDto.error: {}", authPermissionDto);
            return Result.fail("新增权限失败");
        }
    }

    /**
     * 修改权限
     *
     * @param authPermissionDto 权限信息
     * @return 结果
     */
    @PostMapping("update")
    public Result<Object> update(@RequestBody AuthPermissionDto authPermissionDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.PermissionController.update.authPermissionDto:{}", JSON.toJSONString(authPermissionDto));
            }
            Preconditions.checkNotNull(authPermissionDto.getId(), "权限ID不能为空");
            AuthPermissionBo authPermissionBo = authPermissionDtoConvert.convertAuthPermissionDtoToAuthPermissionBo(authPermissionDto);
            return Result.ok(authPermissionDomainService.update(authPermissionBo));
        } catch (Exception e) {
            log.error("auth.PermissionController.update.authPermissionDto.error: {}", authPermissionDto);
            return Result.fail("修改权限失败");
        }
    }

    /**
     * 删除权限
     *
     * @param authPermissionDto 权限
     * @return 结果
     */
    @PostMapping("delete")
    public Result<Object> delete(@RequestBody AuthPermissionDto authPermissionDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.PermissionController.delete.authPermissionDto:{}", JSON.toJSONString(authPermissionDto));
            }
            Preconditions.checkNotNull(authPermissionDto.getId(), "权限ID不能为空");
            AuthPermissionBo authPermissionBo = authPermissionDtoConvert.convertAuthPermissionDtoToAuthPermissionBo(authPermissionDto);
            return Result.ok(authPermissionDomainService.delete(authPermissionBo));
        } catch (Exception e) {
            log.error("auth.PermissionController.delete.authPermissionDto.error: {}", authPermissionDto);
            return Result.fail("删除权限失败");
        }
    }
}
