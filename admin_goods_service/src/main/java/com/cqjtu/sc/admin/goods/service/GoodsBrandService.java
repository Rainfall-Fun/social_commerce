package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsBrandMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsBrand;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsBrandExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsBrandService {

    @Autowired
    AllGoodsBrandMapper goodsBrandMapper;

    public List<AllGoodsBrand> getAllGoodsBrand() {
        AllGoodsBrandExample goodsBrandExample = new AllGoodsBrandExample();
        return goodsBrandMapper.selectByExample(goodsBrandExample);
    }

    /**
     * 根据条件查出商品品牌
     *
     * @param goodsSn
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllGoodsBrand> querySelective(String goodsSn, String name, Integer page, Integer size, String sort, String order) {
        AllGoodsBrandExample goodsBrandExample = new AllGoodsBrandExample();
        AllGoodsBrandExample.Criteria criteria = goodsBrandExample.createCriteria();

        if (!StringUtils.isEmpty(goodsSn)) {
            criteria.andBrandIdEqualTo(new Integer(goodsSn));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andBrandNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            goodsBrandExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return goodsBrandMapper.selectByExample(goodsBrandExample);
    }

    /**
     * 删除品牌信息，根据品牌id
     *
     * @param brandId
     * @return
     */
    public int deleteBrand(Integer brandId) {
        try {
            goodsBrandMapper.deleteByPrimaryKey(brandId);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 修改商品品牌信息
     *
     * @param brand
     * @return //返回1，更新成功
     */
    public int updateBrandInfo(AllGoodsBrand brand) {
        try {
            return goodsBrandMapper.updateByPrimaryKeySelective(brand);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 添加商品品牌信息
     *
     * @param brand
     * @return
     */
    public int addBrandInfo(AllGoodsBrand brand) {
        try {
            goodsBrandMapper.insertSelective(brand);
            return brand.getBrandId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 判断品牌名是否存在
     *
     * @param brandName
     * @return 存在返回true
     */
    public boolean checkBrandName(String brandName) {
        AllGoodsBrandExample goodsBrandExample = new AllGoodsBrandExample();
        AllGoodsBrandExample.Criteria criteria = goodsBrandExample.createCriteria();
        criteria.andBrandNameEqualTo(brandName);
        List<AllGoodsBrand> AllGoodsBrands = goodsBrandMapper.selectByExample(goodsBrandExample);
        if (AllGoodsBrands.size() != 0)
            return true;
        else
            return false;
    }

    /**
     * 根据商品品牌id得到商品品牌实体
     *
     * @param id
     * @return
     */
    public AllGoodsBrand getBrandById(int id) {
        try {
            return goodsBrandMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
