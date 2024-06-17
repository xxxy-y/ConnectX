package cn.edu.tyut.connectx.auth.application.controller;

import cn.edu.tyut.connectx.auth.application.convert.AuthRolePermissionDtoConvert;
import cn.edu.tyut.connectx.auth.application.dto.AuthRolePermissionDto;
import cn.edu.tyut.connectx.auth.common.entity.Result;
import cn.edu.tyut.connectx.auth.domain.entity.AuthRolePermissionBo;
import cn.edu.tyut.connectx.auth.domain.service.AuthRolePermissionDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
@RequestMapping("/rolePermission/")
public class RolePermissionController {
    private AuthRolePermissionDtoConvert authRolePermissionDtoConvert;
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    @Autowired
    public void setAuthRolePermissionDomainService(AuthRolePermissionDomainService authRolePermissionDomainService) {
        this.authRolePermissionDomainService = authRolePermissionDomainService;
    }

    @Autowired
    public void setAuthRolePermissionDtoConvert(AuthRolePermissionDtoConvert authRolePermissionDtoConvert) {
        this.authRolePermissionDtoConvert = authRolePermissionDtoConvert;
    }

    @PostMapping("add")
    public Result<Object> add(@RequestBody AuthRolePermissionDto authRolePermissionDto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("auth.RolePermissionController.add.authRolePermissionDto:{}", JSON.toJSONString(authRolePermissionDto));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDto.getPermissionIdList()),"权限List不能为空");
            Preconditions.checkNotNull(authRolePermissionDto.getRoleId(),"角色不能为空");
            AuthRolePermissionBo authRolePermissionBo = authRolePermissionDtoConvert.convertAuthRolePermissionDtoToAuthRolePermissionBo(authRolePermissionDto);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBo));
        } catch (Exception e) {
            log.error("auth.RolePermissionController.add.authRolePermissionDto.error: {}", authRolePermissionDto);
            return Result.fail("新增角色权限失败");
        }
    }
}
