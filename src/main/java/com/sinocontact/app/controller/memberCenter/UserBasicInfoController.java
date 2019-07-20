package com.sinocontact.app.controller.memberCenter;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.User;
import com.sinocontact.app.service.memberCenter.UserBasicInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户基本信息
 * @author caoMingYang
 * @since 2019/4/6 11:23
 */
@Controller
@RequestMapping("/memberCenter")
public class UserBasicInfoController extends BaseController {

    private static Logger logger = Logger.getLogger(UserBasicInfoController.class);

    @Autowired
    private UserBasicInfoService service;

    /**
     * 用户信息首页
     * @author caoMingYang
     * @since 2019/4/6 11:25
     */
    @RequestMapping("/baseInfo")
    public String index(){
        User user = (User) this.getSession().getAttribute(CommonConstant.SESSION_KEY);
        putObject(CommonConstant.SESSION_KEY,user);
        return "views/memberCenter/userInfo";
    }

    /**
     * 跳转到修改密码页面
     * @author caoMingYang
     * @since 2019/4/14 11:54
     */
    @RequestMapping("/password")
    public String password(){
        User user = (User) this.getSession().getAttribute(CommonConstant.SESSION_KEY);
        putObject(CommonConstant.SESSION_KEY,user);
        return "views/memberCenter/updatePassword";
    }

    /**
     * 更新密码
     * @param password 新密码
     * @author caoMingYang
     * @since 2019/4/14 12:14
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public String updatePassword(@RequestParam(value = "password",defaultValue = "")String password){
        JSONObject job = new JSONObject();
        boolean flag = service.updatePassword(password,this.getUserInfo().getUserId());
        if (flag){
            job.put("code",0);
            job.put("msg","success");
            User user = this.getUserInfo();
            user.setPassword(password);
            this.getSession().setAttribute(CommonConstant.SESSION_KEY,user);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        String jsonText = job.toJSONString();
        logger.info("updatePassword>>>"+jsonText);
        return jsonText;
    }

    /**
     * 校验用户名是否存在
     * @param account 用户名
     * @author caoMingYang
     * @since 2019/4/11 23:32
     */
    @ResponseBody
    @RequestMapping("checkAccount")
    public String checkAccount(@RequestParam("account")String account){
        JSONObject job = new JSONObject();
        boolean flag = service.checkAccount(account,this.getUserInfo().getUserId());
        if (!flag){
            job.put("code",-1);
            job.put("msg","此用户名已存在");
        }else {
            job.put("code",0);
            job.put("msg","success");
        }
        String jsonText = job.toJSONString();
        logger.info("checkAccount>>>>"+jsonText);
        return jsonText;
    }

    /**
     * 更新用户信息
     * @param account 用户名称
     * @param nickname 昵称
     * @author caoMingYang
     * @since 2019/4/12 15:01
     */
    @ResponseBody
    @RequestMapping("updateUserInfo")
    public String updateUserInfo(@RequestParam(value = "account",defaultValue = "")String account,
                                 @RequestParam(value = "nickname",defaultValue = "")String nickname){
        JSONObject job = new JSONObject();
        boolean flag = service.updateUserInfo(account,nickname,this.getUserInfo().getUserId());
        if (flag){
            job.put("code",0);
            job.put("msg","success");
            //更新session中用户信息
           this.updateSessionUserCache(account,nickname,0);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        String jsonText = job.toJSONString();
        logger.info("updateUserInfo>>>"+jsonText);
        return jsonText;
    }

    /**
     * 申请成为作者
     * @author caoMingYang
     * @since 2019/4/12 18:36
     */
    @ResponseBody
    @RequestMapping("applyForAuthor")
    public String applyForAuthor(){
        JSONObject job = new JSONObject();
        boolean flag = service.applyForAuthor(this.getUserInfo().getUserId());
        if (flag){
            job.put("code",0);
            job.put("msg","success");
            this.updateSessionUserCache("","",1);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        String jsonText = job.toJSONString();
        logger.info("applyForAuthor>>>"+jsonText);
        return jsonText;
    }


    /**
     * 更新session中的用户信息
     * @param account 用户名
     * @param nickname 昵称
     * @param applyAuthorStatus 申请成为作者的状态
     * @author caoMingYang
     * @since 2019/4/14 11:55
     */
    private void updateSessionUserCache(String account,String nickname,Integer applyAuthorStatus){
        User user = service.getUser(this.getUserInfo().getUserId());
        if (user !=null){
            this.getSession().setAttribute(CommonConstant.SESSION_KEY,user);
        }else{
            User sessionUser = this.getUserInfo();
            if (!StringUtils.isEmpty(account)){
                sessionUser.setAccount(account);
            }
            if (!StringUtils.isEmpty(nickname)){
                sessionUser.setNickname(nickname);
            }
            if (applyAuthorStatus == 1){
                sessionUser.setApplyAuthorStatus(applyAuthorStatus);
            }
            this.getSession().setAttribute(CommonConstant.SESSION_KEY,sessionUser);
        }
    }
}
