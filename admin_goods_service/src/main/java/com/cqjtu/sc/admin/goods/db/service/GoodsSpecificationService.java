package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsSpecificationMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiAttValue;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecification;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecificationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsSpecificationService {

    @Resource
    AllGoodsSpecificationMapper mapper;
    public List<AllGoodsSpecification> queryByGid(int goodsId){
        AllGoodsSpecificationExample example=new AllGoodsSpecificationExample();
        AllGoodsSpecificationExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        return mapper.selectByExample(example);
    }
    /**
     * [
     * {
     * name: '',
     * valueList: [ {}, {}]
     * },
     * {
     * name: '',
     * valueList: [ {}, {}]
     * }
     * ]
     *
     * @param id
     * @return
     */
    public Object getSpecificationVoList(Integer id) {
        List<AllGoodsSpecification> goodsSpecificationList = queryByGid(id);

        Map<String, VO> map = new HashMap<>();
        List<VO> specificationVoList = new ArrayList<>();

        for (AllGoodsSpecification goodsSpecification : goodsSpecificationList) {
            String specification = goodsSpecification.getSpecifiName();
            VO goodsSpecificationVo = map.get(specification);
            if (goodsSpecificationVo == null) {
                goodsSpecificationVo = new VO();
                goodsSpecificationVo.setName(specification);
                List<AllGoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVo.setValueList(valueList);
                map.put(specification, goodsSpecificationVo);
                specificationVoList.add(goodsSpecificationVo);
            } else {
                List<AllGoodsSpecification> valueList = goodsSpecificationVo.getValueList();
                valueList.add(goodsSpecification);
            }
        }

        return specificationVoList;
    }


    private class VO {
        private String name;
        private List<AllGoodsSpecification> valueList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AllGoodsSpecification> getValueList() {
            return valueList;
        }

        public void setValueList(List<AllGoodsSpecification> valueList) {
            this.valueList = valueList;
        }
    }
}
