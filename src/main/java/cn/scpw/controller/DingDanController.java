package cn.scpw.controller;

import cn.scpw.pojo.Dingdan;
import cn.scpw.service.DingDanService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Author: lyh
 * Version: V1.0
 * Package: cn.scpw.controller
 * Date:  2019/8/28
 * Description:
 */
@Controller
public class DingDanController {

    @Autowired
    DingDanService dingDanService;

    /**
     * 通过用户自己的ID去查询自己所以的订单包括自己的历史订单，订单里面也包括了车次等信息
     *
     * @return 用户订单所有信息集合
     */
    @RequestMapping(value = "/selectMyAllDinDanByUserId", method = RequestMethod.POST)
    @ResponseBody
    public List<Dingdan> getMyAllDinDanByUserId(@RequestBody Map<String, String> map) {
//        "f47ce6a3de0c4b52ac7c41a0ff936b39"
        System.out.println(map.get("userId"));
        return dingDanService.selectAllMyDingDanByUserId(map.get("userId"));
    }


    /**
     * 通过用户ID与用户选择的火车类型去查询订单信息
     * @param map 前端传入的json数据,封装的有车辆类型与用户ID
     * @return 订单对象集合
     */
    @RequestMapping(value = "/selectMyDinDanByLeiXing", method = RequestMethod.POST)
    @ResponseBody
    public List<Dingdan> getMyDinDanByLeiXing(@RequestBody Map<String,String> map) {
        return dingDanService.selectAllMyDingDanByCheCiLeiXing(map.get("leixing"),map.get("userId"));
    }
}