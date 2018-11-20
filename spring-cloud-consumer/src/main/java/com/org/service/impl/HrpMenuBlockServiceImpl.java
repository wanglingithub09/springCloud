package com.org.service.impl;

import com.org.entity.HrpMenuBlock;
import com.org.mapper.HrpMenuBlockMapper;
import com.org.service.HrpMenuBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Author: WangLin
* @Description: 
* @Date: 2018/11/16 10:03
*/
@Service
public class HrpMenuBlockServiceImpl implements HrpMenuBlockService {

    @Autowired
    private HrpMenuBlockMapper hrpMenuBlockMapper;

    @Override
    public List<HrpMenuBlock> getHrpMenuBlockByCode(String crcHrpBlockCode) {
        return hrpMenuBlockMapper.getHrpMenuBlockByCode(crcHrpBlockCode);
    }
}
