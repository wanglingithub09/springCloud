package com.org.controller;

import com.alibaba.fastjson.JSONObject;
import com.org.base.vo.FromData;
import com.org.base.vo.PageVo;
import com.org.base.vo.ServerData;
import com.org.entity.HrpMenuBlock;
import com.org.service.HrpMenuBlockService;
import com.org.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HrpMenuBlockController extends BasicController{

    private Logger log = LoggerFactory.getLogger(HrpMenuBlockController.class);

    private final static String redis_key = "saveRedisUserDevice_1"+HrpMenuBlockController.class.getName();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private HrpMenuBlockService hrpMenuBlockService;

    @GetMapping(value = "/getHrpMenuBlock/{crcHrpBlockCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<List<HrpMenuBlock>> getUserDeviceById(@PathVariable("crcHrpBlockCode") String crcHrpBlockCode){
        List<HrpMenuBlock> hrpMenuBlockList = hrpMenuBlockService.getHrpMenuBlockByCode(crcHrpBlockCode);
        return new ServerData<>(hrpMenuBlockList);
    }

    @PostMapping(value = "/getHrpMenuBlock",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<PageVo> getUserDevice(@RequestBody FromData<HrpMenuBlock> from){
        List<HrpMenuBlock> hrpMenuBlockList = hrpMenuBlockService.getUserDevice(from);
        PageVo vo = new PageVo(hrpMenuBlockList);
        vo.setCount(hrpMenuBlockList.size());
        vo.setPage(from.getPage());
        vo.setPageSize(from.getSize());
        return new ServerData<>(vo);
    }

    @PostMapping(value = "/saveRedisUserDevice",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<String> saveRedisUserDevice(@RequestBody FromData<HrpMenuBlock> from){
        log.info("redis key:"+redis_key,from);
        redisUtil.set(redis_key,from.getBo());
        return new ServerData<>("redis key:"+redis_key+"保存成功");
    }

    @PostMapping(value = "/getRedisUserDevice",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<Object> getRedisUserDevice(@RequestBody FromData<HrpMenuBlock> from){
        System.out.println("入参:"+JSONObject.toJSONString(from));
        return new ServerData<>(redisUtil.get(redis_key));
    }
}
