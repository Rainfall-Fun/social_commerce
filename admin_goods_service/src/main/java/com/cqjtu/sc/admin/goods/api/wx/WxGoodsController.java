package com.cqjtu.sc.admin.goods.api.wx;

import com.cqjtu.sc.admin.goods.db.domain.AllGoodsInfo;
import com.cqjtu.sc.admin.goods.db.service.*;
import com.cqjtu.sc.admin.goods.dto.BriefGoods;
import com.cqjtu.sc.admin.goods.util.ResponseUtil;
import com.cqjtu.sc.admin.goods.validator.Order;
import com.cqjtu.sc.admin.goods.validator.Sort;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 商品服务
 */
@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController {


    @Autowired
    BriefGoodsService briefGoodsService;
    @Autowired
    AllGoodsInfoService allGoodsInfoService;
    @Autowired
    GoodsSpecifiAttValService goodsSpecifiAttValService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    GoodsImgService goodsImgService;
	private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(9);
//
	private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();
//
	private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);
//
	/**
	 * 商品详情
	 * <p>
	 * 用户可以不登录。
	 * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
	 *
	 * @param userId 用户ID
	 * @param id     商品ID
	 * @return 商品详情
	 */
	@GetMapping("detail")
	public Object detail(Integer userId, @NotNull Integer id) {
        // 商品信息
        AllGoodsInfo info = allGoodsInfoService.findById(id);
        info.setGallery(goodsImgService.getGallery(id));

//        // 商品属性
//        Callable<List> goodsAttributeListCallable = () -> goodsAttributeService.queryByGid(id);

        // 商品规格 返回的是定制的GoodsSpecificationVo
        Callable<Object> objectCallable = () -> goodsSpecificationService.getSpecificationVoList(id);

        // 商品规格对应的数量和价格
        Callable<List> productListCallable = () ->goodsSpecifiAttValService.queryByGoodsId(id);

//        // 商品问题，这里是一些通用问题
//        Callable<List> issueCallable = () -> goodsIssueService.querySelective("", 1, 4, "", "");


//
//        // 评论
//        Callable<Map> commentsCallable = () -> {
//            List<LitemallComment> comments = commentService.queryGoodsByGid(id, 0, 2);
//            List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
//            long commentCount = PageInfo.of(comments).getTotal();
//            for (LitemallComment comment : comments) {
//                Map<String, Object> c = new HashMap<>();
//                c.put("id", comment.getId());
//                c.put("addTime", comment.getAddTime());
//                c.put("content", comment.getContent());
//                LitemallUser user = userService.findById(comment.getUserId());
//                c.put("nickname", user == null ? "" : user.getNickname());
//                c.put("avatar", user == null ? "" : user.getAvatar());
//                c.put("picList", comment.getPicUrls());
//                commentsVo.add(c);
//            }
//            Map<String, Object> commentList = new HashMap<>();
//            commentList.put("count", commentCount);
//            commentList.put("data", commentsVo);
//            return commentList;
//        };
//
//        //团购信息
//        Callable<List> grouponRulesCallable = () ->rulesService.queryByGoodsId(id);
//
//        // 用户收藏
//        int userHasCollect = 0;
//        if (userId != null) {
//            userHasCollect = collectService.count(userId, id);
//        }
//
//        // 记录用户的足迹 异步处理
//        if (userId != null) {
//            executorService.execute(()->{
//                LitemallFootprint footprint = new LitemallFootprint();
//                footprint.setUserId(userId);
//                footprint.setGoodsId(id);
//                footprintService.add(footprint);
//            });
//        }
//        FutureTask<List> goodsAttributeListTask = new FutureTask<>(goodsAttributeListCallable);
        FutureTask<Object> objectCallableTask = new FutureTask<>(objectCallable);
        FutureTask<List> productListCallableTask = new FutureTask<>(productListCallable);
//        FutureTask<List> issueCallableTask = new FutureTask<>(issueCallable);
//        FutureTask<Map> commentsCallableTsk = new FutureTask<>(commentsCallable);
//        FutureTask<List> grouponRulesCallableTask = new FutureTask<>(grouponRulesCallable);

//        executorService.submit(goodsAttributeListTask);
        executorService.submit(objectCallableTask);
        executorService.submit(productListCallableTask);
//        executorService.submit(issueCallableTask);
//        executorService.submit(commentsCallableTsk);
//        executorService.submit(grouponRulesCallableTask);

        Map<String, Object> data = new HashMap<>();

        try {
            data.put("info", info);
//            data.put("userHasCollect", userHasCollect);
//            data.put("issue", issueCallableTask.get());
//            data.put("comment", commentsCallableTsk.get());
            data.put("specificationList", objectCallableTask.get());
            data.put("productList", productListCallableTask.get());
//            data.put("attribute", goodsAttributeListTask.get());
//            data.put("groupon", grouponRulesCallableTask.get());
            //SystemConfig.isAutoCreateShareImage()
//            data.put("share", SystemConfig.isAutoCreateShareImage());

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //商品分享图片地址
//        data.put("shareImage", info.getShareUrl());
		return ResponseUtil.ok(data);
	}
//
//	/**
//	 * 商品分类类目
//	 *
//	 * @param id 分类类目ID
//	 * @return 商品分类类目
//	 */
//	@GetMapping("category")
//	public Object category(@NotNull Integer id) {
//		return ResponseUtil.ok(data);
//	}
//
//	/**
//	 * 根据条件搜素商品
//	 * <p>
//	 * 1. 这里的前五个参数都是可选的，甚至都是空
//	 * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
//	 *
//	 * @param categoryId 分类类目ID，可选
//	 * @param brandId    品牌商ID，可选
//	 * @param keyword    关键字，可选
//	 * @param isNew      是否新品，可选
//	 * @param isHot      是否热买，可选
//	 * @param userId     用户ID
//	 * @param page       分页页数
//	 * @param limit       分页大小
//	 * @param sort       排序方式，支持"add_time", "retail_price"或"name"
//	 * @param order      排序类型，顺序或者降序
//	 * @return 根据条件搜素的商品详情
//	 */
//	@GetMapping("list")
//	public Object list(
//		Integer categoryId,
//		Integer brandId,
//		String keyword,
//		Boolean isNew,
//		Boolean isHot,
//		Integer userId,
//		@RequestParam(defaultValue = "1") Integer page,
//		@RequestParam(defaultValue = "10") Integer limit,
//		@Sort(accepts = {"add_time", "retail_price", "name"}) @RequestParam(defaultValue = "add_time") String sort,
//		@Order @RequestParam(defaultValue = "desc") String order) {
//
//
//
//		Map<String, Object> entity = new HashMap<>();
//		entity.put("list", goodsList);
//		entity.put("total", pagedList.getTotal());
//		entity.put("page", pagedList.getPageNum());
//		entity.put("limit", pagedList.getPageSize());
//		entity.put("pages", pagedList.getPages());
//		entity.put("filterCategoryList", categoryList);
//
//		// 因为这里需要返回额外的filterCategoryList参数，因此不能方便使用ResponseUtil.okList
//		return ResponseUtil.ok(entity);
//	}
//
//
//
//	/**
//	 * 在售的商品总数
//	 *
//	 * @return 在售的商品总数
//	 */
//	@GetMapping("count")
//	public Object count() {
//		return ResponseUtil.ok(goodsCount);
//	}


    /**
     * 获取商品的概要信息用于在主页等地方展示
     * @param goodsIds
     * @return
     */
    @RequestMapping("getBriefGoods")
    public Object getBriefGoods(@RequestBody List<Integer> goodsIds){
        List<BriefGoods> briefGoods = briefGoodsService.getBriefGoods(goodsIds);
        for (BriefGoods datum : briefGoods) {
            datum.setCounterPrice(datum.getRetailPrice().multiply(BigDecimal.valueOf(1.5)));
            datum.setPicUrl("http://localhost:8777/"+datum.getPicUrl());
        }
        return briefGoods;
    }

}