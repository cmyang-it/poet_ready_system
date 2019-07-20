package com.sinocontact.app.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.entity.User;
import com.sinocontact.app.service.user.UserService;
import com.sinocontact.app.utils.VerifyCodeUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.List;

/**
 * 用户登录
 * @author todd
 * @since 2019/1/21
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 跳转到用户登录界面
     * @author todd
     * @since 2019/1/23
     */
    @RequestMapping("/login")
    public String getUser(){
        return "views/user/login";
    }


    /**
     * 跳转到用户注册界面
     * @author todd
     * @since 2019/1/26
     */
    @RequestMapping("/loginReg")
    public String reg(){
        return "views/user/reg";
    }

    /**
     * 跳转到找回密码界面
     * @author todd
     * @since 2019/1/26
     */
    @RequestMapping("/loginGetPassword")
    public String getPassword(){
        return "views/user/forget";
    }


    /**
     * 检验用户登录信息
     * @author todd
     * @since 2019/1/23
     */
    @ResponseBody
    @RequestMapping(value="/loginCheck", produces = "application/json;charset=UTF-8")
    public String checkLogin(@RequestParam(value = "username")String username,
                             @RequestParam(value = "password")String password,
                             @RequestParam(value = "code",defaultValue = "")String verifyCode) throws Exception{

        String verCode = (String) this.getSession().getAttribute(CommonConstant.VERCODE_KEY);
        //校验输入的用户名、密码和图形验证码获的状态码
        Integer code = userService.checkUser(username,password,verifyCode,verCode);
        JSONObject job = new JSONObject();
        if (code == 1){ //登录成功
            this.getSession().setAttribute(CommonConstant.SESSION_KEY,userService.getUserByAccount(username));
            job.put("code",code);
            job.put("msg","登录成功！");
        }else if (code == -1){
            job.put("code",code);
            job.put("msg","密码或用户名有误，请重新输入！");
        }else if (code == -2){
            job.put("code",code);
            job.put("msg","您输入的用户名不存在！");
        }else if (code == -3){
            job.put("code",code);
            job.put("msg","验证码有误，请重新输入");
        }
        String jobText = job.toJSONString();
        logger.info(jobText);
        return jobText;
    }

    /**
     * 校验找回密码的手机号
     * @param phone 手机号
     * @param verifyCode 图形验证码
     * @param phoneCode 手机验证码
     * @author caoMingYang
     * @since 2019/5/4 10:09
     */
    @ResponseBody
    @RequestMapping("/forget")
    public String checkForget(@RequestParam("phone")String phone,
                              @RequestParam("verifyCode")String verifyCode,
                              @RequestParam("phoneCode")String phoneCode){
        JSONObject job = new JSONObject();
        String sessionCode = (String) this.getSession().getAttribute(CommonConstant.VERCODE_KEY);
        Integer num = userService.checkForget(phone,verifyCode,sessionCode,phoneCode);
        this.getSession().setAttribute(CommonConstant.PHONE,phone);
        if (num == 0){
            job.put("code",0);
            job.put("msg","success");
        }else if (num == -1){
            job.put("code",-1);
            job.put("msg","手机号未注册，请先注册！");
        }else if (num == -2){
            job.put("code",-2);
            job.put("msg","验证码超时，请重新获取！");
        }else if (num == -3){
            job.put("code",-3);
            job.put("msg","验证码输入有误！");
        }else if (num == -4){
            job.put("code",-4);
            job.put("msg","请先获取手机验证码！");
        }else if (num == -5){
            job.put("code",-5);
            job.put("msg","图形验证码输入有误！");
        }
        return job.toJSONString();
    }

    /**
     * 重置密码
     * @param password 密码
     * @author caoMingYang
     * @since 2019/5/4 15:47
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public String resetPassword(@RequestParam("password")String password){
        String phone = (String) this.getSession().getAttribute(CommonConstant.PHONE);
        boolean flag = userService.updatePassword(phone,password);
        JSONObject job = new JSONObject();
        if (flag){
            job.put("code",0);
            job.put("msg","success");
        }else{
            job.put("code",-1);
            job.put("msg","密码重置失败，请稍后再试！");
        }
        return job.toJSONString();
    }

    /**
     * 获取短信验证码
     * @author todd
     * @since 2019/1/29
     */
    @ResponseBody
    @RequestMapping(value="/loginGetCode", produces = "application/json;charset=UTF-8")
    public String getPhoneCode(@RequestParam("phone")String phone)throws Exception{
        logger.info(phone);
        Integer code = userService.getPhoneCode(phone);
        JSONObject job = new JSONObject();
        if (code == -1){//发送失败
            job.put("code",code);
            job.put("msg","验证码发送失败");
        }else{
            job.put("code",code);
            job.put("msg","验证码发送成功");
        }
        String jobText = job.toJSONString();
        logger.info(jobText);
        return jobText;
    }

    /**
     * 找回密码验证码
     * @param phone 手机号
     * @author caoMingYang
     * @since 2019/5/4 9:35
     */
    @ResponseBody
    @RequestMapping("/forgetCode")
    public String forgetCode(@RequestParam("phone")String phone){
        JSONObject job = new JSONObject();
        Integer num = userService.forgetCode(phone);
        if (num == 0){
            job.put("code",0);
            job.put("msg","success");
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 保存用户的注册信息
     * @param username 用户名
     * @param password 密码
     * @param phone 电话号码
     * @param nickname 昵称
     * @author caoMingYang
     * @since 2019/5/2 11:02
     */
    @ResponseBody
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String saveUser(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("phone")String phone,
                           @RequestParam("nickname")String nickname,
                           @RequestParam("verifyCode")String verifyCode){
        JSONObject job = new JSONObject();
        Integer num = userService.saveUser(username, password, phone, nickname,verifyCode);
        if (num == 1){
            job.put("code",0);
            job.put("msg","success");
        }else if (num == 0){
            job.put("code",-1);
            job.put("msg","系统异常，请稍后再试！");
        }else if (num == -1){
            job.put("code",-1);
            job.put("msg","验证码超时，请再次获取！");
        }else if (num == -2){
            job.put("code",-1);
            job.put("msg","验证码错误！");
        }else if (num == -3){
            job.put("code",-1);
            job.put("msg","请先获取短信验证码！");
        }else if (num == -4){
            job.put("code",-1);
            job.put("msg","此号码已注册过，请直接登录或找回密码");
        }
        return job.toJSONString();
    }


    /**
     * 获取图形验证码
     * @param session
     * @param response
     * @author caoMingYang
     * @since 2019/4/4 20:17
     */
    @RequestMapping("/verifyCode")
    public void generalVerify(HttpServletResponse response, HttpSession session){
        response.setContentType("image/jpeg");
        String verifyCode = VerifyCodeUtil.getRandomVerifyCode();
        try {
            OutputStream os = response.getOutputStream();
            session.setAttribute(CommonConstant.VERCODE_KEY, verifyCode);
            VerifyCodeUtil.outputImage(verifyCode, os);
            os.close();
        } catch (Exception e) {
            logger.error("", e);
        }
    }


    /**
     * 跳转到首页
     * @author todd
     * @since 2019/1/30
     */
    @RequestMapping("/index")
    public String toIndex(){
        User user = (User) this.getSession().getAttribute(CommonConstant.SESSION_KEY);
        putObject("user",user);
        //后台管理页面
        if (user.getRole() == 100){
            return "views/indexRear_end";
        }
        //前台网站页面
        return "views/index";
    }

    /**
     * 首页
     * @author todd
     * @since 2019/1/30
     */
    @RequestMapping("/console")
    public String console(){
        List<Novel> hotNovelList = userService.queryHotNovel();
        List<Novel> recommendList = userService.queryRecommendList();
        List<Novel> nowUpdateList = userService.queryNowUpdate();
        List<Novel> wordsList = userService.queryWordsList();

        this.putObject("hotNovelList",hotNovelList);
        this.putObject("recommendList",recommendList);
        this.putObject("nowUpdateList",nowUpdateList);
        this.putObject("wordsList",wordsList);
        return "views/home/console";
    }

    /**
     * 退出登录
     * @author todd
     * @since 2019/1/24
     */
    @RequestMapping("/loginOut")
    public String loginOut(){
        this.getSession().removeAttribute(CommonConstant.SESSION_KEY);
        return "views/user/login";
    }

}
