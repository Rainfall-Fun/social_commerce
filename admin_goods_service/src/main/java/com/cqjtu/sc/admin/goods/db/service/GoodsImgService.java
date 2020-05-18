package com.cqjtu.sc.admin.goods.db.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsSpecifiImgMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiImg;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsSpecifiImgExample;
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

    public String[] getGallery(int goodsId){
        AllGoodsSpecifiImgExample example=new AllGoodsSpecifiImgExample();
        AllGoodsSpecifiImgExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId).andTypeEqualTo(1);
        List<AllGoodsSpecifiImg> imgs = mapper.selectByExample(example);
        String gallery[] = new String[imgs.size()];
        for (int i = 0; i < imgs.size(); i++) {
            gallery[i]="http://localhost:8777/"+imgs.get(i).getUrl();
        }
        return gallery;
    }
}
