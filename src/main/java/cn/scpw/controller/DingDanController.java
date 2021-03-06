package cn.scpw.controller;

import cn.scpw.pojo.Dingdan;
import cn.scpw.service.DingDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        System.out.println(map.get("userId"));
        return dingDanService.selectAllMyDingDanByUserId(map.get("userid"));
    }


    /**
     * 通过用户ID与用户选择的火车类型去查询订单信息
     *
     * @param map 前端传入的json数据,封装的有车辆类型与用户ID
     * @return 订单对象集合
     */
    @RequestMapping(value = "/selectMyDinDanByLeiXing", method = RequestMethod.POST)
    @ResponseBody
    public List<Dingdan> getMyDinDanByLeiXing(@RequestBody Map<String, String> map) {
        return dingDanService.selectAllMyDingDanByCheCiLeiXing(map.get("leixing"), map.get("userid"));
    }

    /**
     * 用户根据支付状态来查询自己的所有订单
     *
     * @param map 前端传入的json数据 userid ——用户id 与 paystate ——支付状态（0表示未支付，1表示支付）
     * @return 用户订单集合
     */
    @RequestMapping(value = "/selectMyDinDanByPayState", method = RequestMethod.POST)
    @ResponseBody
    public List<Dingdan> getMyDinDanByPayState(@RequestBody Map<String, String> map) {
        return dingDanService.selectDingDanByUserIdAndPayState(map.get("userid"), map.get("paystate"));
    }

    @RequestMapping(value = "/yudingchepiao", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void yuDing(@RequestBody Map<String, String> map) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        dingDanService.insertCheCi(map, uuid);
        dingDanService.insertDingDan(map, uuid);
    }

    /**
     * 根据用户订单ID去更改用户支付状态
     * @param map 前端传入的订单ID
     */
    @RequestMapping(value = "/updatePaySate", method = RequestMethod.POST)
    public void updatePaySate(@RequestBody Map<String,String> map) {
        System.out.println(map.get("out_trade_no"));
        //通过订单ID去修改支付状态
        dingDanService.updateDingDanPayState(map.get("out_trade_no"));
    }
}
