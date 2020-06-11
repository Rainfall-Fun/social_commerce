package com.cqjtu.sc.admin.goods.api.admin;

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
     * @param barcode
     * @param goodsName
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/list")
    public Object list(
            @RequestParam(value = "goodsSn", defaultValue = "defaultGoodsSn") String barcode,
            @RequestParam(value = "name", defaultValue = "defaultName") String goodsName,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "sort", defaultValue = "add_time") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order
    ) {
        System.out.println("list");
        goodsService.list(barcode, goodsName, page, limit, sort, order);
        return null;
    }

    @GetMapping("/catAndBrand")
    public Object catAndBrand() {
        System.out.println("catAndBrand");
        return goodsService.catAndBrand();
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
    public Object create(@RequestBody GoodsAllinone finalGoods, Integer supplierId) {
        long l = System.currentTimeMillis();
        Object o = goodsService.addGoods(finalGoods, supplierId);
        System.out.println(System.currentTimeMillis() - l);
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
