package com.cqjtu.sc.authservice.api.admin;

import com.cqjtu.sc.authservice.db.domain.AdminActor;
import com.cqjtu.sc.authservice.db.domain.AdminActorAuthority;
import com.cqjtu.sc.authservice.db.domain.AdminOperator;
import com.cqjtu.sc.authservice.db.domain.AllSupplier;
import com.cqjtu.sc.authservice.db.service.AdminPermissionService;
import com.cqjtu.sc.authservice.db.service.AdminRolePermissionService;
import com.cqjtu.sc.authservice.db.service.AdminService;
import com.cqjtu.sc.authservice.db.service.SupplierService;
import com.cqjtu.sc.authservice.util.JacksonUtil;
import com.cqjtu.sc.authservice.util.RegexUtil;
import com.cqjtu.sc.authservice.util.ResponseUtil;

import com.cqjtu.sc.authservice.util.bcrypt.BCryptPasswordEncoder;
import com.cqjtu.sc.authservice.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/admin")
@Validated
public class AdminAdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;


    @GetMapping("/list")
    public Object list(String username,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(defaultValue = "add_time") String sort,
                       @RequestParam(defaultValue = "desc") String order) {
        List<AdminOperator> adminList = adminService.querySelective(username, page, limit, sort, order);
        return ResponseUtil.okList(adminList);
    }

    private Object validate(AdminOperator admin) {
        String name = admin.getOperatorAccount();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)) {
            return ResponseUtil.fail(101, "管理员名称不符合规定");
        }
        String password = admin.getOperatorPassword();
        if (StringUtils.isEmpty(password) || password.length() < 6) {
            return ResponseUtil.fail(101, "管理员密码长度不能小于6");
        }
        return null;
    }

    @PostMapping("/create")
    public Object create(@RequestBody AdminOperator admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }
        String username = admin.getOperatorAccount();
        List<AdminOperator> adminList = adminService.findAdmin(username);
        if (adminList.size() > 0) {
            return ResponseUtil.fail(101, "管理员已经存在");
        }
        String rawPassword = admin.getOperatorPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        admin.setOperatorPassword(encodedPassword);
        int operatorId = adminService.add(admin);
        //为此用户添加角色权限
        Integer roleId = admin.getActorId();
        List<AdminActorAuthority> byActorId = adminRolePermissionService.findByActorId(roleId);
        List<Integer> permissions=new ArrayList<>();
        for (AdminActorAuthority adminActorAuthority : byActorId) {
            permissions.add(adminActorAuthority.getAuthorityId());
        }
        adminPermissionService.addPermissionsByPid(operatorId,permissions);
        return ResponseUtil.ok(admin);
    }


    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        AdminOperator admin = adminService.findById(id);
        return ResponseUtil.ok(admin);
    }


    @PostMapping("/update")
    public Object update(@RequestBody AdminOperator admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }
        Integer anotherAdminId = admin.getOperatorId();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }
        // 不允许管理员通过编辑接口修改密码
        admin.setOperatorPassword(null);

        if (adminService.updateById(admin) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(admin);
    }


    @PostMapping("/delete")
    public Object delete(@RequestBody AdminOperator admin,Integer id) {
        Integer anotherAdminId = admin.getOperatorId();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }
        if (id.equals(anotherAdminId)) {
            return ResponseUtil.fail(101, "管理员不能删除自己账号");
        }
        adminService.deleteById(anotherAdminId);
        return ResponseUtil.ok();
    }
    @GetMapping("supplier")
    public Object supplier(){
        List<AllSupplier> suppliers = supplierService.queryAll();

        List<Map<String, Object>> options = new ArrayList<>(suppliers.size());
        for (AllSupplier supplier : suppliers) {
            Map<String, Object> option = new HashMap<>(2);
            option.put("value", supplier.getSupplierId());
            option.put("label", supplier.getSupplierName());
            options.add(option);
        }
        return ResponseUtil.okList(options);
    }

    @PostMapping("/permissions")
    public Object updatePermissions(@RequestBody String body) {
        Integer operatorId = JacksonUtil.parseInteger(body, "operatorId");
        List<String> permissions = JacksonUtil.parseStringList(body, "permissions");
        if (operatorId == null || permissions == null) {
            return ResponseUtil.badArgument();
        }
        adminPermissionService.addPermissions(operatorId,permissions);
        return ResponseUtil.ok();
    }
}
