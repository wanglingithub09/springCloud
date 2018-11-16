package com.org.mapper;

import com.org.entity.UserDevice;
import org.apache.ibatis.annotations.Param;

/**
* @Author: WangLin
* @Description:
* @Date: 2018/11/15 17:10
*/
public interface UserDeviceMapper {
    /**
     * 根据ID获取userDevice
     * @param id
     * @return
     */
    UserDevice getUserDeviceById(@Param("id") Long id);
}
