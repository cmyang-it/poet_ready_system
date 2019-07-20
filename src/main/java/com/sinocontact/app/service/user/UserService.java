package com.sinocontact.app.service.user;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.dao.user.UserMapper;
import com.sinocontact.app.entity.CodeInfo;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 用户登录
 * @author todd
 * @since 2019/1/21
 */
@Service("userService")
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    private static final Map<String,CodeInfo> codeInfoMap = new HashMap<String, CodeInfo>();

    @Autowired
    private UserMapper userMapper;

    /**
     * 校验用户输入的用户名和密码
     * @param username 账号
     * @param password 密码
     * @param verifyCode 用户输入的图形验证码
     * @param verCode 保存的图形验证码
     * @return -2,用户未注册；-1密码错误，1，登录成功，-3验证码有误
     * @author todd
     * @since 2019/1/23
     */
    public Integer checkUser(String username,String password,String verifyCode,String verCode) throws Exception{
        if (StringUtils.isEmpty(verifyCode) || !verifyCode.equalsIgnoreCase(verCode)){
            return -3;
        }
        User user =  userMapper.checkUser(username);
        if (null == user){
            return -2;
        }
        if (!user.getPassword().equals(password)){
            return -1;
        }
        userMapper.updateLoginTime(user.getUserId());
        return 1;
    }

    /**
     * 校验手机号，图形验证码，手机验证码
     * @param phone 手机号
     * @param verifyCode 图形验证码
     * @param sessionCode session中保存的图形验证码
     * @param phoneCode 手机验证码
     * @return -5 图形验证码错误，-4 当前手机号未获取验证码,-3 手机验证码错误，-2验证码超时，-1，当前手机号未注册，0 成功
     */
    public Integer checkForget(String phone,String verifyCode,String sessionCode,String phoneCode){
        //图形验证码错误
        if (StringUtils.isEmpty(verifyCode) || !verifyCode.equalsIgnoreCase(sessionCode)){
            return -5;
        }
        //获取保存的手机验证码
        CodeInfo codeInfo = codeInfoMap.get(phone);
        try {
            if (null != codeInfo) {
                if (phoneCode.equalsIgnoreCase(codeInfo.getCode())) {
                    if (System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(codeInfo.getSendTime()).getTime() <= 5 * 60 * 1000) {
                        //清除手机号和验证码
                        codeInfoMap.remove(phone);
                        Integer num = userMapper.checkPhone(phone);
                        return num > 0 ? 0 : -1;
                    } else {
                        logger.error("验证码超过有效时间");
                        return -2;
                    }
                } else {
                    logger.error("验证码输入有误");
                    return -3;
                }
            } else {
                logger.error("当前手机号未获取验证码！");
                return -4;
            }
        }catch (Exception e){
            logger.error("",e);
        }
        return 0;
    }

    /**
     * 更新密码
     * @param phone 手机号
     * @param password 密码
     * @return false 失败，true成功
     */
    public boolean updatePassword(String phone,String password){
        try{
            Integer num = userMapper.updatePassword(phone,password);
            if (num > 0 ){
                return true;
            }
        }catch (Exception e){
            logger.error("保存用户密码异常",e);
        }
        return false;
    }

    /**
     * 保存用户信息
     * @param username 用户名
     * @param password 密码
     * @param phone 手机号
     * @param nickname 昵称
     * @param verifyCode 手机验证码
     * @return -3 手机号码未获取验证码，-2 验证码输入有误，-1 验证码超过有效时间，0 插入用户失败，1 登录成功
     * @author caoMingYang
     * @since 2019/5/2 11:13
     */
    public Integer saveUser(String username,String password,String phone,String nickname,String verifyCode){
        CodeInfo codeInfo = codeInfoMap.get(phone);
        try {
            User user = userMapper.getUserByPhone(phone);
            if (null != user){
                return -4;
            }
            if (null != codeInfo) {
                if (verifyCode.equalsIgnoreCase(codeInfo.getCode())) {
                    if (System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(codeInfo.getSendTime()).getTime() <= 5 * 60 * 1000) {
                           Integer num = userMapper.saveUser(username, password, nickname, phone);
                           //清除手机号和验证码
                           codeInfoMap.remove(phone);
                           return num > 0 ? 1 : 0;
                    }else{
                        logger.error("验证码超过有效时间");
                        return -1;
                    }
                } else {
                    logger.error("验证码输入有误");
                    return -2;
                }
            } else {
                logger.error("此号码未发送验证码");
                return -3;
            }
        }catch (Exception e){
            logger.error("保存用户信息异常",e);
        }
        return 0;
    }

    /**
     * 通过用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     * @author todd
     * @since 2019/1/23
     */
    public User getUserByAccount(String username)throws Exception{
        return userMapper.checkUser(username);
    }


    /**
     * 发送登录验证码
     * @param phone 手机号
     * @return 0 发送成功，-1 发送失败
     * @author todd
     * @since 2019/1/29
     */
    public Integer getPhoneCode(String phone) throws Exception{
        return this.sendPhoneCodeByLogin(phone);
    }

    /**
     * 发找回密码验证码
     * @param phone 手机号
     * @author caoMingYang
     * @since 2019/5/4 9:38
     */
    public Integer forgetCode(String phone){
        return this.sendPhoneCodeByForget(phone);
    }


    /**
     * 获取热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 14:29
     */
    public List<Novel> queryHotNovel(){
        try{
            return userMapper.queryHotNovel();
        }catch (Exception e){
            logger.error("获取热门小说异常", e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取推荐榜
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 14:29
     */
    public List<Novel> queryRecommendList(){
        try{
            return userMapper.queryRecommendList();
        }catch (Exception e){
            logger.error("获取推荐榜小说异常", e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取字数榜
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 14:29
     */
    public List<Novel> queryWordsList(){
        try{
            return userMapper.queryWordsList();
        }catch (Exception e){
            logger.error("获取字数榜小说异常", e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取最近更新
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 14:29
     */
    public List<Novel> queryNowUpdate(){
        try{
            List<Novel> novelList = userMapper.queryNowUpdate();
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("获取最近更新小说异常", e);
        }
        return new ArrayList<Novel>();
    }



    /**
     * 发送登录验证码
     * @author todd
     * @since 2019/1/29
     */
    private Integer sendPhoneCodeByLogin(String phone) throws Exception{
        Integer appId = CommonConstant.APP_ID;     //短信应用SDK APPID
        String  appKey = CommonConstant.APP_KEY;    //短信应用SDK APPkey
        String[] phones = {phone};  //需要发送验证码的手机号
        Integer templateId = CommonConstant.TEMPLATE_ID;      //短信模板id
        String sign = CommonConstant.SIGN;   //签名
        String rdCode = this.getRandomCode();   //随机验证码
        codeInfoMap.put(phone,new CodeInfo(phone,rdCode,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        String[] param = {rdCode,"5"};      //参数
        try {
            SmsSingleSender sender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = sender.sendWithParam("86", phones[0], templateId, param, sign, "", "");
            if (result.getResponse().reason.equals("OK")){
                logger.info("注册手机验证码发送成功，手机号【"+phone+"】,验证码【"+rdCode+"】");
                return 0;
            }
            return -1;
        }catch (Exception e){
            logger.error("发送信息模板异常",e);
        }
        return -1;
    }


    /**
     * 发送找回密码验证码
     * @param phone 手机号
     * @return -1 失败，0 成功
     * @author caoMingYang
     * @since 2019/5/4 9:36
     */
    private Integer sendPhoneCodeByForget(String phone){
        Integer appId = CommonConstant.APP_ID;     //短信应用SDK APPID
        String  appKey = CommonConstant.APP_KEY;    //短信应用SDK APPkey
        String[] phones = {phone};  //需要发送验证码的手机号
        Integer templateId = CommonConstant.TEMPLATE_FORGET_ID;      //短信模板id
        String sign = CommonConstant.SIGN;   //签名
        String rdCode = this.getRandomCode();   //随机验证码
        codeInfoMap.put(phone,new CodeInfo(phone,rdCode,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        String[] param = {rdCode,"5"};      //参数
        try {
            SmsSingleSender sender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = sender.sendWithParam("86", phones[0], templateId, param, sign, "", "");
            if (result.getResponse().reason.equals("OK")){
                logger.info("找回密码验证码发送成功，手机号【"+phone+"】,验证码【"+rdCode+"】");
                return 0;
            }
            return -1;
        }catch (Exception e){
            logger.error("发送信息模板异常",e);
        }
        return -1;
    }

    /**
     * 获取六位验证码
     * @return 六位随机数字
     * @author todd
     * @since 2019/1/29
     */
    private String getRandomCode(){
        Random rd = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<6;i++){
            sb.append(rd.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 格式化时间
     * @param novelList 小说集合
     * @author caoMingYang
     * @since 2019/4/16 14:44
     */
    private void formatDate(List<Novel> novelList){
        for (Novel novel : novelList){
            if (!StringUtils.isEmpty(novel.getLastChapterUpdate())){
                novel.setLastChapterUpdate(novel.getLastChapterUpdate().substring(0,10));
            }
        }
    }

    public static void main(String[] args) {
        try {
            new UserService().sendPhoneCodeByLogin("18895697068");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
