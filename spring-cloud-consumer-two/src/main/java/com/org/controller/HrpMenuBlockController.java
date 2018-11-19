package com.org.controller;

import com.org.base.vo.FromData;
import com.org.base.vo.PageVo;
import com.org.base.vo.ServerData;
import com.org.entity.HrpMenuBlock;
import com.org.service.HrpMenuBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/getUserDevice",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<PageVo> getUserDevice(@RequestBody FromData<HrpMenuBlock> from){

        List<HrpMenuBlock> hrpMenuBlockList = hrpMenuBlockService.getUserDevice(from);
        PageVo vo = new PageVo(hrpMenuBlockList);
        vo.setCount(hrpMenuBlockList.size());
        vo.setPage(from.getPage());
        vo.setPageSize(from.getSize());
        return new ServerData<>(vo);
    }

}
