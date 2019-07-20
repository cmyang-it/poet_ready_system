package com.sinocontact.app.controller.systemSetting;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.entity.CheckUser;
import com.sinocontact.app.entity.User;
import com.sinocontact.app.service.systemSetting.UserManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 * @author Cao
 * @since 2019/3/16 16:57
 */
@Controller
@RequestMapping("/userManage")
public class UserManageController {
    private static Logger logger = Logger.getLogger(UserManageController.class);

    @Autowired
    private UserManageService service;


    /**
     * 用户首页
     * @author todd
     * @since 2019/3/19
     */
    @RequestMapping("/index")
    public String index(){
        return "views/systemSetting/userManage/userList";
    }

    /**
     * 获取所有的用户信息
     * @author todd
     * @since 2019/3/19
     */
    @ResponseBody
    @RequestMapping("/userList")
    public String userList(@RequestParam(value = "startDate",defaultValue = "")String startDate,
                           @RequestParam(value = "endDate",defaultValue = "")String endDate,
                           @RequestParam(value = "userInfo",defaultValue = "")String userInfo,
                           @RequestParam(value = "role",defaultValue = "0")Integer role,
                           @RequestParam(value = "page",defaultValue = "1")Integer page,
                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        logger.info("startDate>>>>"+startDate+"  ,endDate>>>>"+endDate+"  ,userInfo>>>>"+userInfo+"  ,role>>>>"+role+"  ,page>>>>"+page+"  ,pageSize>>>>"+pageSize);
        //所有用户信息
        List<User> userList = service.getUserList(startDate,endDate,userInfo,role,page,pageSize);
        //用户总条数
        Integer userCount = service.getUserCount(startDate,endDate,userInfo,role);
        JSONObject job = new JSONObject();
        job.put("code",0);
        job.put("msg","success");
        job.put("count",userCount);
        job.put("data",userList);
        String jsonText = job.toJSONString();
        logger.info("获取所有用户信息》》》》》》"+jsonText);
        return jsonText;
    }

    /**
     * 获取用户审核信息列表
     * @author todd
     * @since 2019/3/21
     */
    @ResponseBody
    @RequestMapping(value = "/userCheckList",produces = "application/json;charset=utf-8")
    public String userCheckList(@RequestParam(value = "checkStatus",defaultValue = "0")Integer checkStatus,
                                @RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        logger.info("用户审核信息列表》》》 checkStatus>>>"+checkStatus+",page>>>"+page+",pageSize>>>"+pageSize);
        //获取审核信息
        List<CheckUser> checkUserList = service.getCheckUserList(checkStatus,page,pageSize);
        //获取总条数
        Integer count = service.getCheckUserCount(checkStatus);
        JSONObject job = new JSONObject();
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",checkUserList);
        String jsonText = job.toJSONString();
        logger.info("获取所有用户审核信息》》》》"+jsonText);
        return jsonText;
    }

    /**
     * 审核用户权限申请
     * @author todd
     * @since 2019/3/22
     */
    @ResponseBody
    @RequestMapping(value = "/checkUser",produces = "application/json;charset=utf-8")
    public String checkUser(@RequestParam("checkIdArray") String checkIdArray,
                            @RequestParam("userIdArray")String userIds){
        logger.info("审核通过id》》》"+checkIdArray);
        Integer code = 0;
        try {
            code = service.checkUserPass(checkIdArray,userIds);
        }catch (Exception e){
            logger.error("审核通过异常",e);
        }
        JSONObject job = new JSONObject();
        if (code < 1){
            job.put("code",-1);
            job.put("msg","fail");
        }else{
            job.put("code",0);
            job.put("msg","success");
        }
        String jsonText = job.toJSONString();
        logger.info("审核用户权限为通过》》》》"+jsonText);
        return jsonText;
    }

    /**
     * 审核用户权限申请不通过
     * @author todd
     * @since 2019/3/22
     */
    @ResponseBody
    @RequestMapping(value = "checkUserNoPass",produces = "application/json;charset=utf-8")
    public String checkUserNoPass(@RequestParam("checkIdArray")String checkIdArray){
        logger.info("审核不通过id>>>>"+checkIdArray);
        Integer code = service.checkUserNoPass(checkIdArray);
        JSONObject job = new JSONObject();
        if (code < 1){
            job.put("code",-1);
            job.put("msg","fail");
        }else{
            job.put("code",0);
            job.put("msg","success");
        }
        String jsonText = job.toJSONString();
        logger.info("审核用户权限为不通过》》》》"+jsonText);
        return jsonText;
    }


    /**
     * 获取用户近一个月登录情况
     * @author todd
     * @since 2019/3/19
     */
    @ResponseBody
    @RequestMapping(value = "/userLoginInfo",method = RequestMethod.POST ,produces = "application/json;charset=utf-8")
    public String userLoginInfo(){
        String eChartData = service.userLoginInfo();
        JSONObject job = new JSONObject();
        if (null == eChartData){
            job.put("code",-1);
            job.put("msg","fail");
        }else{
            job.put("code",0);
            job.put("msg","success");
            job.put("data",eChartData);
        }
        String jsonText = job.toJSONString();
        logger.info("获取近一个月的用户登录情况》》》》》》》"+jsonText);
        return jsonText;
    }
}
