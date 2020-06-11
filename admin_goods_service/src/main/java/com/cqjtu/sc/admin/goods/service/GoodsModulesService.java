package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsBoardMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsBoard;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsBoardExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsModulesService {

    @Autowired
    AllGoodsBoardMapper goodsModulesMapper;

    /**
     * 得到所有的商品板块信息
     *
     * @return
     */
    public List<AllGoodsBoard> getAllGoodsModules() {
        return goodsModulesMapper.selectByExample(new AllGoodsBoardExample());
    }
}