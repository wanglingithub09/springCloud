package com.org.service;

import com.org.entity.HrpMenuBlock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Author: WangLin
* @Description:
* @Date: 2018/11/16 9:58
*/
public interface HrpMenuBlockService {
    public List<HrpMenuBlock> getHrpMenuBlockByCode(@Param("crcHrpBlockCode") String crcHrpBlockCode);
}
