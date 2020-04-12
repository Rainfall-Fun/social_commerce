package com.cqjtu.sc.gateway.web.admin;

import com.cqjtu.sc.gateway.annotation.RequiresPermissionsDesc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/goods")
@Validated
public class AdminGoodsController {



    /**
     * 查询商品
     *
     * @return
     */
    @RequiresPermissions("admin:goods:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(@RequestBody String data) {
        return null;
    }

    @GetMapping("/catAndBrand")
    public Object list2() {
        return null;
    }

    /**
     * 编辑商品
     *
     * @param goodsAllinone
     * @return
     */
    @RequiresPermissions("admin:goods:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody String goodsAllinone) {
        return null;
    }

    /**
     * 删除商品
     *
     * @param goods
     * @return
     */
    @RequiresPermissions("admin:goods:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody String goods) {
        return null;
    }

    /**
     * 添加商品
     *
     * @param goodsAllinone
     * @return
     */
    @RequiresPermissions("admin:goods:create")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "上架")
    @PostMapping("/create")
    public Object create(@RequestBody String goodsAllinone) {
        return null;
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:goods:read")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "详情")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return null;

    }

}
