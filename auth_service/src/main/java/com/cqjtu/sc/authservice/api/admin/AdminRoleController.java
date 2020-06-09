package com.cqjtu.sc.authservice.api.admin;

import com.cqjtu.sc.authservice.db.service.AdminRolePermissionService;
import com.cqjtu.sc.authservice.db.service.AdminRoleService;
import com.cqjtu.sc.authservice.db.domain.AdminActor;
import com.cqjtu.sc.authservice.db.service.OperatorService;
import com.cqjtu.sc.authservice.util.JacksonUtil;
import com.cqjtu.sc.authservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/role")
@Validated
public class AdminRoleController {


    @Autowired
    AdminRoleService roleService;
    @Autowired
    OperatorService operatorService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;


    @GetMapping("/options")
    public Object options() {
        List<AdminActor> roleList = roleService.queryAll();

        List<Map<String, Object>> options = new ArrayList<>(roleList.size());
        for (AdminActor role : roleList) {
            Map<String, Object> option = new HashMap<>(2);
            option.put("value", role.getActorId());
            option.put("label", role.getActorName());
            options.add(option);
        }

        return ResponseUtil.okList(options);
    }
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "actor_id") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        List<AdminActor> roleList = roleService.querySelective(name, page, limit, "actor_id", order);
        return ResponseUtil.okList(roleList);
    }

    @PostMapping("/create")
    public Object create(@RequestBody AdminActor body) {
        if (!roleService.existByRoleName(body.getActorName())) {
            int i = roleService.create(body);
            body.setActorId(i);
            return ResponseUtil.ok(body);
        } else
            return ResponseUtil.fail(101, "该角色已存在");
    }

    @PostMapping("/update")
    public Object update(@RequestBody AdminActor role) {
        roleService.updateById(role);
        return ResponseUtil.ok();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody AdminActor role) {
        Integer id = role.getActorId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        int i = operatorService.countByRoleId(id);
        if (i != 0) {
            // 如果当前角色所对应管理员仍存在，则拒绝删除角色。
            return ResponseUtil.fail(101, "当前角色存在管理员，不能删除");
        }
        roleService.deleteById(id);
        return ResponseUtil.ok();
    }



    @PostMapping("/permissions")
    public Object updatePermissions(@RequestBody String body) {
        Integer roleId = JacksonUtil.parseInteger(body, "roleId");
        List<String> permissions = JacksonUtil.parseStringList(body, "permissions");
        if (roleId == null || permissions == null) {
            return ResponseUtil.badArgument();
        }
        adminRolePermissionService.addPermissions(roleId,permissions);
        return ResponseUtil.ok();
    }

}
