package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.domain.*;
import com.cqjtu.sc.admin.goods.db.service.AllGoodsInfoService;
import com.cqjtu.sc.admin.goods.db.service.GoodsImgService;
import com.cqjtu.sc.admin.goods.db.service.ProductService;
import com.cqjtu.sc.admin.goods.db.service.SpecificationService;
import com.cqjtu.sc.admin.goods.dto.GoodsAllinone;
import com.cqjtu.sc.admin.goods.util.ResponseUtil;
import com.cqjtu.sc.admin.goods.vo.CatVo;
import com.cqjtu.sc.admin.goods.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired
    AllGoodsInfoService allGoodsInfoService;
    @Autowired
    GoodsImgService imgService;
    @Autowired
    SpecificationService specificationService;
    @Autowired
    ProductService productService;
    @Autowired
    GoodsModulesService goodsModulesService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    GoodsBrandService goodsBrandService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    PaymentWayService paymentWayService;
    @Autowired
    FreightRuleService freightRuleService;

    @Transactional
    public Object addGoods(GoodsAllinone goodsAllinone, Integer supplierId) {
        System.out.println(supplierId);
        if (supplierId == null)
            return ResponseUtil.fail(101, "该用户不能添加商品");
        List<AllGoodsSpecifiImg> imgs = new ArrayList<>();
        AllGoodsInfo goods = goodsAllinone.getGoods();
//        goods.setPaymentId(1);
//        goods.setCategoryId(1);
        goods.setGoodsAddTime(LocalDateTime.now());
        goods.setGoodsLastModifyedTime(LocalDateTime.now());
        goods.setGoodsLastRecommandTime(LocalDateTime.now());
        goods.setGoodsLastSetTime(LocalDateTime.now());
        goods.setSupplierId(supplierId);
        int goodsId = allGoodsInfoService.add(goods);
        String[] gallery = goods.getGallery();
        for (int i = 0; i < gallery.length; i++) {
            AllGoodsSpecifiImg img = new AllGoodsSpecifiImg();
            img.setGoodsId(goodsId);
            img.setType(1);//1为商品展示图
            imgs.add(img);
        }

        AllGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        Map<String, Integer> specifiIdMap = new HashMap<>();
        for (int i = 0; i < specifications.length; i++) {
            specifications[i].setGoodsId(goodsId);
            int specificationId = specificationService.add(specifications[i]);
            AllGoodsSpecifiImg img = new AllGoodsSpecifiImg();
            img.setGoodsId(goodsId);
            img.setId(specificationId);
            img.setUrl(specifications[i].getUrl() + "");
            img.setType(2);//2为规格图
            imgs.add(img);
            specifiIdMap.put(specifications[i].getSpecifiValue(), specificationId);
        }
        AllGoodsSpecifiImg img = new AllGoodsSpecifiImg();
        img.setGoodsId(goodsId);
        img.setType(3);//三为封面图
        img.setUrl(goods.getCoverImg());
        if (goods.getCoverImg() == null || goods.getCoverImg().equals(""))
            img.setUrl("default.jpg");
        imgs.add(img);
        imgService.batchAdd(imgs);

        AllGoodsSpecifiAttValue[] products = goodsAllinone.getProducts();
        List<AllGoodsSpecifiAttValue> ps = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            AllGoodsSpecifiAttValue product = products[i];
            String[] specifications1 = product.getSpecifications();
            product.setSpecifications(specifications1);
            product.setGoodsId(goodsId);
            ps.add(product);
        }
        productService.batchInsert(ps);
        return ResponseUtil.ok();
    }

    public Object catAndBrand() {
        //商品所属板块
        List<AllGoodsBoard> allGoodsModules = goodsModulesService.getAllGoodsModules();
        List<CatVo> catVoListGoodsModules = new ArrayList<>(allGoodsModules.size());
        for (AllGoodsBoard gm :
                allGoodsModules) {
            CatVo goodsModule = new CatVo();
            goodsModule.setLabel(gm.getBoard());
            goodsModule.setValue(gm.getBoardId());
            catVoListGoodsModules.add(goodsModule);
        }

        //管理员设置“所属分类”
        List<AllGoodsCategory> categoryL1 = categoryService.getGoodsL1Categories();
        List<CatVo> catVoListL1 = new ArrayList<>(categoryL1.size());

        //添加一级分类
        for (AllGoodsCategory goodsCategoryL1 :
                categoryL1) {
            CatVo l1CatVo = new CatVo();
            l1CatVo.setValue(goodsCategoryL1.getCategoryId());
            l1CatVo.setLabel(goodsCategoryL1.getCategoryName());
            //添加二级分类
            List<AllGoodsCategory> categoryL2 = categoryService.getGoodsL2CategoriesBySuperiorCategoryId(goodsCategoryL1.getCategoryId());
            List<CatVo> catVoListL2 = new ArrayList<>(categoryL2.size());
            for (AllGoodsCategory goodsCategoryL2 :
                    categoryL2) {
                CatVo l2CatVo = new CatVo();
                l2CatVo.setValue(goodsCategoryL2.getCategoryId());
                l2CatVo.setLabel(goodsCategoryL2.getCategoryName());
                //添加三级分类
                List<AllGoodsCategory> categoryL3 = categoryService.getGoodsL3CategoriesBySuperiorCategoryId(goodsCategoryL2.getCategoryId());
                List<CatVo> catVoListL3 = new ArrayList<>(categoryL3.size());
                for (AllGoodsCategory goodsCategoryL3 :
                        categoryL3) {
                    CatVo l3CateVo = new CatVo();
                    l3CateVo.setValue(goodsCategoryL3.getCategoryId());
                    l3CateVo.setLabel(goodsCategoryL3.getCategoryName());
                    catVoListL3.add(l3CateVo);
                }
                l2CatVo.setChildren(catVoListL3);
                catVoListL2.add(l2CatVo);
            }
            l1CatVo.setChildren(catVoListL2);
            catVoListL1.add(l1CatVo);
        }

        // 管理员设置“所属品牌商”
        List<AllGoodsBrand> list = goodsBrandService.getAllGoodsBrand();
        List<Map<String, Object>> brandList = new ArrayList<>(list.size());
        for (AllGoodsBrand brand : list) {
            Map<String, Object> b = new HashMap<>(2);
            b.put("value", brand.getBrandId());//商品品牌编码
            b.put("label", brand.getBrandName());//品牌名称
            brandList.add(b);
        }

        //所属供应商
        List<AllSupplier> allSupplier = supplierService.getAllSupplier();
        List<Map<String, Object>> supplierList = new ArrayList<>(allSupplier.size());
        for (AllSupplier supplier :
                allSupplier) {
            Map<String, Object> s = new HashMap<>(2);
            s.put("value", supplier.getSupplierId() + "");
            s.put("label", supplier.getSupplierName());
            supplierList.add(s);
        }
        //支付方式
        List<AllPayment> allPaymentWays = paymentWayService.getAllPaymentWays();
        List<Map<String, Object>> paymentWayList = new ArrayList<>(allPaymentWays.size());
        for (AllPayment paymentWay :
                allPaymentWays) {
            Map<String, Object> s = new HashMap<>(2);
            s.put("value", paymentWay.getPaymentId());
            s.put("label", paymentWay.getPayment());
            paymentWayList.add(s);
        }
        //运费规则
        List<AllCarrigeRule> freightRules = freightRuleService.getAllFreightRule();
        List<Map<String, Object>> freightRuleList = new ArrayList<>(freightRules.size());
        for (AllCarrigeRule frieightRule :
                freightRules) {
            Map<String, Object> fr = new HashMap<>(2);
            fr.put("value", frieightRule.getCarriageRuleId());
            fr.put("label", frieightRule.getCarriageRule());
            freightRuleList.add(fr);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", catVoListL1);//分类list
        data.put("brandList", brandList);//品牌list
        data.put("supplierList", supplierList);//所属供应商list
        data.put("boardList", catVoListGoodsModules);//所属板块list
        data.put("paymentWayList", paymentWayList);//支付方式List
        data.put("carriageRuleList", freightRuleList);//运费规则list
        return ResponseUtil.ok(data);
    }

    public Object list(String goodsSn, String name, Integer page, Integer limit, String sort, String order) {
        List<AllGoodsInfo> goodsList = allGoodsInfoService.querySelective(goodsSn, name, page, limit, sort, order);
        List<GoodsVo> goodsVos = new ArrayList<>(goodsList.size());
        for (AllGoodsInfo goods : goodsList) {
            GoodsVo goodsInfo = new GoodsVo();
// TODO 图片

//            String goodsPicture = goodsAndProductPictureService.getGoodsPicture(goods.getSpbm());
            goodsInfo.setGoodsId(goods.getGoodsId());
            goodsInfo.setName(goods.getGoodsName());
            goodsInfo.setRetailPrice(goods.getPrice().doubleValue());
            goodsInfo.setTaxCost(goods.getTaxes().doubleValue());
            goodsInfo.setIsRecommendation(goods.getIsRecommand());
            goodsInfo.setIsOnSale(goods.getIsOnSale());
//            goodsInfo.setPicUrl(uploadAddress + goodsPicture);
            goodsInfo.setUnit(goods.getUnit());
            goodsInfo.setBrandId(goods.getBrandId());
            goodsInfo.setSupplierId(goods.getSupplierId() + "");
//            goodsInfo.setBelongModule(goods.getSsbkbm());
            goodsInfo.setDetail(goods.getGoodsDesc());
            goodsInfo.setCategoryId(goods.getCategoryId() + "");
            goodsInfo.setPaymentWay(goods.getPaymentId());
            goodsInfo.setFreightRule(goods.getCarriageRuleId());
            goodsVos.add(goodsInfo);
        }
        return ResponseUtil.okList(goodsVos);
    }

}
