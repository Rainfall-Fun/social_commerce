package com.cqjtu.sc.authservice.db.service;

import com.cqjtu.sc.authservice.db.dao.AllSupplierMapper;
import com.cqjtu.sc.authservice.db.domain.AllSupplier;
import com.cqjtu.sc.authservice.db.domain.AllSupplierExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierService {
    @Resource
    AllSupplierMapper mapper;
    public List<AllSupplier> queryAll(){
        return mapper.selectByExample(new AllSupplierExample());
    }
}
