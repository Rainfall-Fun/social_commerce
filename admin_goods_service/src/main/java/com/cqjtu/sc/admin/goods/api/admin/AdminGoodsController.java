package com.cqjtu.sc.admin.goods.api.admin;

import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecification;
import com.cqjtu.sc.admin.goods.dto.GoodsAllinone;
import com.cqjtu.sc.admin.goods.service.GoodsService;
import com.cqjtu.sc.admin.goods.validator.Order;
import com.cqjtu.sc.admin.goods.validator.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {


    @Autowired
    GoodsService goodsService;
    /**
     * 查询商品
     *
     * @param goodsId
     * @param goodsSn
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/list")
    public Object list(Integer goodsId, String goodsSn, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        System.out.println("jinr");
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
    @PostMapping("/update")
    public Object update(@RequestBody GoodsAllinone goodsAllinone) {
        return null;
    }

    /**
     * 删除商品
     *
     * @param goods
     * @return
     */
//    @PostMapping("/delete")
//    public Object delete(@RequestBody LitemallGoods goods) {
//        return adminGoodsService.delete(goods);
//    }

    /**
     * 添加商品
     *
     * @param finalGoods
     * @return
     */
    @PostMapping("/create")
    public Object create(@RequestBody GoodsAllinone finalGoods) {
        long l = System.currentTimeMillis();
        Object o = goodsService.addGoods(finalGoods);
        System.out.println(System.currentTimeMillis()-l);
        return o;

    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return null;

    }

}
