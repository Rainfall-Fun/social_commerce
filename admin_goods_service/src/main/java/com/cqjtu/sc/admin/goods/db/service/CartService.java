package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllCartMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllCart;
import com.cqjtu.sc.admin.goods.db.domain.AllCartExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartService {
    @Resource
    AllCartMapper mapper;
    public int add(AllCart cart){
        mapper.insert(cart);
        return cart.getCartId();
    }

    public long countByUserInfoId(int userInfoId){
        AllCartExample example=new AllCartExample();
        AllCartExample.Criteria criteria = example.createCriteria();
        criteria.andUserInfoIdEqualTo(userInfoId);
        long l = mapper.countByExample(example);
        return l;
    }

    public AllCart existByProductIdAndUserId(int id,int userId){
        AllCartExample example=new AllCartExample();
        AllCartExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id).andUserInfoIdEqualTo(userId);
        List<AllCart> allCarts = mapper.selectByExample(example);
        if (allCarts.size()==0)
            return null;
        else
            return allCarts.get(0);
    }

    public int update(AllCart cart){
        int i = mapper.updateByPrimaryKeySelective(cart);
        return i;
    }

    public List<AllCart> queryByUserInfoId(int userInfoId){
        AllCartExample example=new AllCartExample();
        AllCartExample.Criteria criteria = example.createCriteria();
        criteria.andUserInfoIdEqualTo(userInfoId);
        return mapper.selectByExample(example);
    }
}
