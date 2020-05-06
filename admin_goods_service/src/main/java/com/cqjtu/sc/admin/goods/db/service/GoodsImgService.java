package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsSpecifiImgMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiImg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsImgService {
    @Resource
    AllGoodsSpecifiImgMapper mapper;
    public void batchAdd(List<AllGoodsSpecifiImg> imgs){
        mapper.batchInsertImg(imgs);
    }
}
