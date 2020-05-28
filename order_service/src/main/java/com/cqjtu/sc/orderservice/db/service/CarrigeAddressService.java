package com.cqjtu.sc.orderservice.db.service;

import org.springframework.stereotype.Service;

import com.cqjtu.sc.orderservice.db.dao.AllCarrigeAddressMapper;
import com.cqjtu.sc.orderservice.db.dao.AllRegionMapper;
import com.cqjtu.sc.orderservice.db.domain.AllCarrigeAddress;
import com.cqjtu.sc.orderservice.db.domain.AllRegion;
import com.cqjtu.sc.orderservice.db.domain.AllRegionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarrigeAddressService {
    @Resource
    AllCarrigeAddressMapper mapper;
    @Resource
    AllRegionMapper regionMapper;

    /**
     * 返回用户收货地址字符串
     * @param addressId
     * @return
     */
    public String addressString(int addressId){
        AllCarrigeAddress address = mapper.selectByPrimaryKey(addressId);
        List<Integer> regionIds=new ArrayList<>();
        regionIds.add(address.getProvince());
        regionIds.add(address.getCity());
        regionIds.add(address.getCounty());

        AllRegionExample example=new AllRegionExample();
        AllRegionExample.Criteria criteria = example.createCriteria();
        criteria.andRegionIdIn(regionIds);
        List<AllRegion> allRegions = regionMapper.selectByExample(example);
        Map<Integer,String> regionNameMap=new HashMap<>();
        for (AllRegion allRegion : allRegions) {
            regionNameMap.put(allRegion.getRegionId(),allRegion.getRegionName());
        }

        StringBuilder areaAddress=new StringBuilder();
        for (Integer regionId : regionIds) {
            areaAddress.append(regionNameMap.get(regionId)+" ");
        }

        return areaAddress+address.getAddress()+","+address.getConsignee()+","+address.getTel();

    }
}
