package cn.edu.tyut.connectx.auth.application.controller;

import cn.edu.tyut.connectx.auth.application.convert.AuthRoleDtoConvert;
import cn.edu.tyut.connectx.auth.application.dto.AuthRoleDto;
import cn.edu.tyut.connectx.auth.common.entity.Result;
import cn.edu.tyut.connectx.auth.domain.entity.AuthRoleBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthRoleDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色controller
 *
 * @Author 吴庆涛
 * @DATE 2024/6/16
 */
@RestController("roleController")
@RequestMapping("/role/")
@Slf4j
public class RoleController {
    private AuthRoleDomainService authRoleDomainService;
    private AuthRoleDtoConvert authRoleDtoConvert;

    @Autowired
    public void setAuthRoleDomainService(AuthRoleDomainService authRoleDomainService) {
        this.authRoleDomainService = authRoleDomainService;
    }

    @Autowired
    public void setAuthRoleDtoConvert(AuthRoleDtoConvert authRoleDtoConvert) {
        this.authRoleDtoConvert = authRoleDtoConvert;
    }

    /**
     * 新增角色
     *
     * @param authRoleDto 新增角色信息
     * @return 返回新增结果
     */
    @PostMapping("add")
    public Result<Object> register(@RequestBody AuthRoleDto authRoleDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.roleController.add.authRoleDto:{}", JSON.toJSONString(authRoleDto));
            }
            AuthRoleBo authRoleBo = authRoleDtoConvert.convertAuthRoleDtoToAuthRoleBo(authRoleDto);
            checkRoleInfo(authRoleDto);
            return Result.ok(authRoleDomainService.add(authRoleBo));
        } catch (Exception e) {
            log.error("auth.roleController.add.authRoleDto.error: {}", authRoleDto);
            return Result.fail("新增角色失败");
        }
    }

    /**
     * 修改角色信息
     *
     * @param authRoleDto 角色信息
     * @return 返回修改结果
     */
    @PostMapping("update")
    public Result<Object> update(@RequestBody AuthRoleDto authRoleDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.roleController.update.authRoleDto:{}", JSON.toJSONString(authRoleDto));
            }
            AuthRoleBo authRoleBo = authRoleDtoConvert.convertAuthRoleDtoToAuthRoleBo(authRoleDto);
            Preconditions.checkNotNull(authRoleBo.getId(), "角色id不能为空");
            return Result.ok(authRoleDomainService.update(authRoleBo));
        } catch (Exception e) {
            log.error("auth.roleController.update.authRoleDto.error: {}", authRoleDto);
            return Result.fail("更新角色失败");
        }
    }

    /**
     * 删除角色信息
     *
     * @param authRoleDto 角色信息
     * @return 返回删除结果
     */
    @PostMapping("delete")
    public Result<Object> delete(@RequestBody AuthRoleDto authRoleDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.roleController.delete.authRoleDto:{}", JSON.toJSONString(authRoleDto));
            }
            AuthRoleBo authRoleBo = authRoleDtoConvert.convertAuthRoleDtoToAuthRoleBo(authRoleDto);
            Preconditions.checkNotNull(authRoleBo.getId(), "角色id不能为空");
            return Result.ok(authRoleDomainService.delete(authRoleBo));
        } catch (Exception e) {
            log.error("auth.roleController.delete.authRoleDto.error: {}", authRoleDto);
            return Result.fail("更新角色失败");
        }
    }

    @Contract(pure = true)
    private void checkRoleInfo(@NotNull AuthRoleDto authRoleDto) {
        Preconditions.checkArgument(!StringUtils.isBlank(authRoleDto.getRoleKey()), "角色Key不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authRoleDto.getRoleName()), "角色名称不能为空");
    }
}
