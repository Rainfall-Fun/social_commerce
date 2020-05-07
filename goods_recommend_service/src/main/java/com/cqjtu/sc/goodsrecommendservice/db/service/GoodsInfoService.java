package com.cqjtu.sc.goodsrecommendservice.db.service;

import com.cqjtu.sc.goodsrecommendservice.db.dao.AllGoodsInfoMapper;
import com.cqjtu.sc.goodsrecommendservice.db.dao.GoodsMapper;
import com.cqjtu.sc.goodsrecommendservice.db.domain.AllGoodsInfo;
import com.cqjtu.sc.goodsrecommendservice.db.domain.AllGoodsInfoExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GoodsInfoService {
    AllGoodsInfo.Column[] columns = new AllGoodsInfo.Column[]{AllGoodsInfo.Column.goodsId};
    @Resource
    AllGoodsInfoMapper goodsInfoMapper;
    @Resource
    GoodsMapper goodsMapper;

    /**
     * 根据浏览量来取出商品
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<Integer> queryByBrowsingTimes(int offset, int limit) {
        List<Integer> result=new ArrayList<>();
        AllGoodsInfoExample example = new AllGoodsInfoExample();
        example.or().andIsOnSaleEqualTo(1);
        example.setOrderByClause("browsing_times desc");
        PageHelper.startPage(offset, limit);
        List<AllGoodsInfo> allGoodsInfos = goodsInfoMapper.selectByExampleSelective(example, columns);
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
    public List<Integer> queryBySale(){
        List<Integer> integers = goodsMapper.queryBySale(3);
        return integers;
    }

    /**
     * 获取该用户对应的区域所销售的前三的商品
     * @return
     */
    public List<Integer> queryByRegion(Integer userId){
        List<Integer> integers = goodsMapper.queryByRegion(3,userId);
        return integers;
    }




}
