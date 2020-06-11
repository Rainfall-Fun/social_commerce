package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.dao.AllPaymentMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllPayment;
import com.cqjtu.sc.admin.goods.db.domain.AllPaymentExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PaymentWayService {
    /**
     * 得到所有支付方式
     */
    @Autowired
    AllPaymentMapper paymentWayMapper;

    public List<AllPayment> getAllPaymentWays() {
        return paymentWayMapper.selectByExample(new AllPaymentExample());
    }


    /**
     * 查询出符合条件的支付方式
     *
     * @param paymentWayId
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllPayment> querySelective(String paymentWayId, String name, Integer page, Integer size, String sort, String order) {
        AllPaymentExample paymentWayExample = new AllPaymentExample();
        AllPaymentExample.Criteria criteria = paymentWayExample.createCriteria();

        if (!StringUtils.isEmpty(paymentWayId)) {
            criteria.andPaymentIdEqualTo(Integer.valueOf(paymentWayId));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andPaymentLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            paymentWayExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return paymentWayMapper.selectByExample(paymentWayExample);
    }

    /**
     * 判断支付方式名称是否存在，存在返回true
     *
     * @param paymentWayName
     * @return
     */
    public boolean checkPaymentWay(String paymentWayName) {
        AllPaymentExample paymentWayExample = new AllPaymentExample();
        AllPaymentExample.Criteria criteria = paymentWayExample.createCriteria();
        criteria.andPaymentEqualTo(paymentWayName);
        List<AllPayment> AllPayment = paymentWayMapper.selectByExample(paymentWayExample);
        if (AllPayment.size() != 0)
            return true;
        else
            return false;
    }

    /**
     * 添加支付方式，成功返回添加记录的主键，不成功返回0
     *
     * @param paymentWay
     * @return
     */
    public int addPaymentWay(AllPayment paymentWay) {
        try {
            paymentWayMapper.insertSelective(paymentWay);
            return paymentWay.getPaymentId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除支付方式，成功返回true
     *
     * @param paymentId
     * @return
     */
    public boolean deletePayment(int paymentId) {
        try {
            int i = paymentWayMapper.deleteByPrimaryKey(paymentId);
            if (i == 1)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新支付方式，成功返回true
     *
     * @param paymentWay
     * @return
     */
    public boolean updatePaymentWay(AllPayment paymentWay) {
        try {
            int i = paymentWayMapper.updateByPrimaryKeySelective(paymentWay);
            if (i == 1)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
