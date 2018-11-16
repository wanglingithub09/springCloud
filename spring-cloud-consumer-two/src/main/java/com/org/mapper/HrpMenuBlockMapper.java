package com.org.mapper;

import com.org.configure.DS;
import com.org.configure.DatabaseType;
import com.org.entity.HrpMenuBlock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Author: WangLin
* @Description: 
* @Date: 2018/11/16 9:52
*/
public interface HrpMenuBlockMapper {
    @DS(DatabaseType.oracleDb)
    public List<HrpMenuBlock> getHrpMenuBlockByCode(@Param("crcHrpBlockCode") String crcHrpBlockCode);
}
