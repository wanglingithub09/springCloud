package com.org.mapper1;

import com.org.entity.HrpMenuBlock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Author: WangLin
* @Description: 
* @Date: 2018/11/16 9:52
*/
public interface HrpMenuBlockMapper {

    public List<HrpMenuBlock> getHrpMenuBlockByCode(@Param("crcHrpBlockCode") String crcHrpBlockCode);
}
