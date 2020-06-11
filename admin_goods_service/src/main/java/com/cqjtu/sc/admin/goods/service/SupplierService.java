package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.dao.AllSupplierMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllSupplier;
import com.cqjtu.sc.admin.goods.db.domain.AllSupplierExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    AllSupplierMapper supplierMapper;

    /**
     * 得到所有的供应商信息
     *
     * @return
     */
    public List<AllSupplier> getAllSupplier() {
        AllSupplierExample supplierExample = new AllSupplierExample();
        return supplierMapper.selectByExample(supplierExample);
    }

    public List<AllSupplier> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        AllSupplierExample supplierExample = new AllSupplierExample();
        AllSupplierExample.Criteria criteria = supplierExample.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andSupplierIdEqualTo(new Integer(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andSupplierNameEqualTo("%" + name + "%");
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            supplierExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return supplierMapper.selectByExample(supplierExample);
    }

    /**
     * 添加供应商
     *
     * @param supplier
     * @return
     */
    public boolean addSupplier(AllSupplier supplier) {
        try {
            supplierMapper.insertSelective(supplier);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ;
            return false;
        }
    }

    /**
     * 删除供应商
     *
     * @param supplier
     * @return
     */
    public boolean deleteSupplier(AllSupplier supplier) {
        try {
            supplierMapper.deleteByPrimaryKey(supplier.getSupplierId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新供应商
     *
     * @param supplier
     * @return
     */
    public int updateSupplier(AllSupplier supplier) {
        try {
            return supplierMapper.updateByPrimaryKeySelective(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}