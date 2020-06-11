package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsInfoMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsInfo;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsInfoExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AllGoodsInfoService {
    @Resource
    AllGoodsInfoMapper mapper;

    public int add(AllGoodsInfo allGoodsInfo) {
        mapper.insertSelective(allGoodsInfo);
        return allGoodsInfo.getGoodsId();
    }

    public AllGoodsInfo findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<AllGoodsInfo> querySelective(String goodsSn, String name, Integer page, Integer size, String sort, String order) {
        AllGoodsInfoExample goodsExample = new AllGoodsInfoExample();
        AllGoodsInfoExample.Criteria criteria = goodsExample.createCriteria();

        if (!goodsSn.equals("defaultGoodsSn")) {
            criteria.andBarcodeEqualTo(goodsSn);
        }
        if (!name.equals("defaultName")) {
            criteria.andGoodsNameLike("%" + name + "%");
        }

        //是否删除
//        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            goodsExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mapper.selectByExample(goodsExample);
    }
}
