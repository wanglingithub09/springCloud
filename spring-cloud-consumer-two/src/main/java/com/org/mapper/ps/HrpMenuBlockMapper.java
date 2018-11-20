package com.org.mapper.ps;

import com.org.configure.datasource.DS;
import com.org.configure.datasource.DatabaseType;
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
    List<HrpMenuBlock> getHrpMenuBlockByCode(@Param("crcHrpBlockCode") String crcHrpBlockCode);

    @DS(DatabaseType.oracleDb)
    List<HrpMenuBlock> getUserDevice(HrpMenuBlock hrpMenuBlock);

}