package com.cqjtu.sc.admin.goods.api.wx;

import com.cqjtu.sc.admin.goods.util.ResponseUtil;
import com.cqjtu.sc.admin.goods.validator.Order;
import com.cqjtu.sc.admin.goods.validator.Sort;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
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


//	private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(9);
//
//	private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();
//
//	private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);
//
//	/**
//	 * 商品详情
//	 * <p>
//	 * 用户可以不登录。
//	 * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
//	 *
//	 * @param userId 用户ID
//	 * @param id     商品ID
//	 * @return 商品详情
//	 */
//	@GetMapping("detail")
//	public Object detail(Integer userId, @NotNull Integer id) {
//		return ResponseUtil.ok(data);
//	}
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



}