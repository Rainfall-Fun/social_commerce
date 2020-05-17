package com.cqjtu.sc.goodsrecommendservice.db.service;

import com.cqjtu.sc.goodsrecommendservice.db.dao.AllGoodsInfoMapper;
import com.cqjtu.sc.goodsrecommendservice.db.dao.RecommendMapper;
import com.cqjtu.sc.goodsrecommendservice.db.domain.AllGoodsInfo;
import com.cqjtu.sc.goodsrecommendservice.db.domain.AllGoodsInfoExample;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RecommendService {
    AllGoodsInfo.Column[] columns = new AllGoodsInfo.Column[]{AllGoodsInfo.Column.goodsId};
    @Resource
    AllGoodsInfoMapper goodsInfoMapper;
    @Resource
    RecommendMapper recommendMapper;

    /**
     * 根据浏览量来取出商品
     *
     * @param offset
     * @param limit
     * @return
     */
    public Page<Integer> queryByBrowsingTimes(int offset, int limit) {
        Page<Integer> result=new Page<>();
        AllGoodsInfoExample example = new AllGoodsInfoExample();
        example.or().andIsOnSaleEqualTo(1);
        example.setOrderByClause("browsing_times desc");
        PageHelper.startPage(offset, limit);
        List<AllGoodsInfo> allGoodsInfos = goodsInfoMapper.selectByExampleSelective(example, columns);
        if (allGoodsInfos instanceof Page) {
            Page page = (Page) allGoodsInfos;
            result.setPages(page.getPages());
        }
        for (AllGoodsInfo allGoodsInfo : allGoodsInfos) {
            result.add(allGoodsInfo.getGoodsId());
        }
        return result;
    }

    /**
     * 查询推荐的商品id
     * @param offset
     * @param limit
     * @return
     */
    public List<Integer> queryByRecommend(int offset, int limit, Set<Integer> goodsIds){
        List<Integer> result=new ArrayList<>();
        AllGoodsInfoExample example = new AllGoodsInfoExample();
        AllGoodsInfoExample.Criteria criteria = example.or().andIsOnSaleEqualTo(1).andIsRecommandEqualTo(1);
        for (Integer goodsId : goodsIds) {
            criteria.andGoodsIdNotEqualTo(goodsId);
        }
        example.setOrderByClause("goods_last_recommand_time desc");
        PageHelper.startPage(offset, limit);
        List<AllGoodsInfo> allGoodsInfos = goodsInfoMapper.selectByExampleSelective(example, columns);
        for (AllGoodsInfo allGoodsInfo : allGoodsInfos) {
            result.add(allGoodsInfo.getGoodsId());
        }
        return result;
    }

    /**
     * 根据销量查询销售量前三的商品
     * @return
     */
    public List<Integer> queryBySale(Integer page,Integer limit){
        List<Integer> integers = recommendMapper.queryBySale((page-1)*limit,limit);
        return integers;
    }

    /**
     * 获取该用户对应的区域所销售的前三的商品
     * @return
     */
    public List<Integer> queryByRegion(Integer page,Integer userId,Integer limit){
        List<Integer> integers = recommendMapper.queryByRegion((page-1)*limit,limit,userId);
        return integers;
    }

    /**
     * 获取该用户对应的区域所销售的前三的商品
     * @return
     */
    public List<Integer> queryByRatio(Integer page,Integer limit){
        List<Integer> integers = recommendMapper.queryByRatio((page-1)*limit,limit);
        return integers;
    }


    public int countByRatio(int limit){
        return recommendMapper.countByRatio(limit);
    }
    public int countBySale(int limit){
        return recommendMapper.countBySale(limit);
    }
    public int countByRegion(int limit,int userId){
        return recommendMapper.countByRegion(limit,userId);
    }


}
