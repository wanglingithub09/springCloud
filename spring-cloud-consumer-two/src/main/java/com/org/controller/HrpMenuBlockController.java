package com.org.controller;

import com.org.base.vo.ServerData;
import com.org.entity.HrpMenuBlock;
import com.org.service.HrpMenuBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Author: WangLin
* @Description:
* @Date: 2018/11/15 17:33
*/
@RestController
@RequestMapping("/hrpMenuBlock")
public class HrpMenuBlockController {

    @Autowired
    private HrpMenuBlockService hrpMenuBlockService;

    @GetMapping("/getHrpMenuBlock/{crcHrpBlockCode}")
    public ServerData<List<HrpMenuBlock>> getUserDeviceById(@PathVariable("crcHrpBlockCode") String crcHrpBlockCode){
        List<HrpMenuBlock> hrpMenuBlockList = hrpMenuBlockService.getHrpMenuBlockByCode(crcHrpBlockCode);
        return new ServerData<>(hrpMenuBlockList);
    }


}
