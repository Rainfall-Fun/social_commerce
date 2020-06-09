package com.cqjtu.sc.gateway.web.service;

import com.cqjtu.sc.gateway.dao.entity.admin.AdminUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="auth-service")
public interface AuthService {
    @GetMapping("admin/role/list")
    Object querySelective(@RequestParam("name") String name,
                          @RequestParam("page") Integer page,
                          @RequestParam("limit") Integer limit,
                          @RequestParam("sort") String sort,
                          @RequestParam("order") String order);

    @PostMapping(value = "admin/role/create",consumes = "application/json")
    Object createRole(@RequestBody String body);

    @PostMapping(value = "admin/role/update",consumes = "application/json")
    Object updateRole(@RequestBody String role);

    @PostMapping(value = "admin/role/delete",consumes = "application/json")
    Object deleteRole(@RequestBody String role);
    @PostMapping(value = "admin/role/permissions",consumes = "application/json")
    Object updateRolePermission(@RequestBody String body);
    @GetMapping("admin/admin/list")
    Object adminList(@RequestParam("username") String username,
                     @RequestParam("page") Integer page,
                     @RequestParam("limit") Integer limit,
                     @RequestParam("sort") String sort,
                     @RequestParam("order") String order);

    @PostMapping(value = "admin/admin/create",consumes = "application/json")
    Object adminCreate(@RequestBody String admin,@RequestParam("id") Integer id);
    @GetMapping("admin/admin/read")
    Object adminRead(@RequestParam("id") Integer id);
    @PostMapping(value = "admin/admin/update",consumes = "application/json")
    Object adminUpdate(@RequestBody String admin, @RequestParam("id") int operatorId);
    @PostMapping(value = "admin/admin/delete",consumes = "application/json")
    Object adminDelete(@RequestBody String admin, @RequestParam("id") int operatorId);

    @GetMapping("admin/role/options")
    Object roleOption();
    @GetMapping("admin/admin/supplier")
    Object adminSupplier();

    @PostMapping(value = "admin/admin/permissions",consumes = "application/json")
    Object adminPermissionUpdate(@RequestBody String body, @RequestParam("id")Integer id);
}
