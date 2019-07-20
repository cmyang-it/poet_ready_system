package com.sinocontact.app.service.systemSetting;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.dao.systemSetting.UserManageMapper;
import com.sinocontact.app.entity.CheckUser;
import com.sinocontact.app.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 * @author Cao
 * @since 2019/3/16 16:53
 */
@Service
public class UserManageService {

    private static Logger logger = Logger.getLogger(UserManageService.class);

    @Autowired
    private UserManageMapper mapper;

    /**
     * 获取所有的用户信息
     * @author todd
     * @since 2019/3/19
     */
    public List<User> getUserList(String startDate,String endDate,String userInfo,Integer role,Integer page,Integer pageSize){
        List<User> userList = null;
        try{
            Integer currentPage = (page - 1) * pageSize;
            userList = mapper.getUserList(setFormatDateStart(startDate),setFormatDateEnd(endDate),userInfo,role,currentPage,pageSize);
            //处理日期格式
            this.setFormatDate(userList);
        }catch (Exception e){
            logger.error("获取所有用户信息异常",e);
        }
        return userList;
    }

    /**
     * 获取用户总条数
     * @return 用户条数
     * @author todd
     * @since 2019/3/19
     */
    public Integer getUserCount(String startDate,String endDate,String userInfo,Integer role){
        Integer userCount = null;
        try{
            userCount = mapper.getUserCount(setFormatDateStart(startDate),setFormatDateEnd(endDate),userInfo,role);
        }catch (Exception e){
            logger.error("获取用户总条数异常",e);
        }
        return userCount;
    }

    /**
     * 获取所有审核信息
     * @author todd
     * @since 2019/3/21
     */
    public List<CheckUser> getCheckUserList(Integer checkStatus,Integer page,Integer pageSize){
        List<CheckUser> checkUserList = null;
        try{
            Integer currentPage = (page - 1) * pageSize;
            checkUserList = mapper.getCheckUserList(checkStatus, currentPage, pageSize);
            //格式化日期
            this.formatDate(checkUserList);
        }catch (Exception e){
            logger.error("获取所有审核信息异常",e);
        }
        return checkUserList;
    }

    /**
     * 获取所有审核信息总条数
     * @param checkStatus 审核状态
     * @return 总条数
     * @author todd
     * @since 2019/3/21
     */
    public Integer getCheckUserCount(Integer checkStatus){
        Integer count = null;
        try{
            count = mapper.getCheckUserCount(checkStatus);
        }catch (Exception e){
            logger.error("获取审核信息总条数异常",e);
        }
        return count;
    }

    /**
     * 获取用户近一个月登录情况的json数据
     * @author todd
     * @since 2019/3/19
     */
    public String userLoginInfo(){
        JSONArray EChartsDataJson = new JSONArray();
        JSONObject loginJson = new JSONObject();
        JSONObject noLoginJson = new JSONObject();
        //获取所有用户的数量
        Integer userCount = null;
        //获取近一个月登录的用户
        Integer loginCount = null;
        try{
            //总数
            userCount = mapper.getUserNum();
            //登陆过人数
            loginCount = this.getLoginNums();
        }catch (Exception e){
            logger.error("获取近一个月用户登录信息异常",e);
            return null;
        }
        loginJson.put("value",loginCount);
        loginJson.put("name", CommonConstant.ONE_MONTH_LOGIN);
        noLoginJson.put("value",(userCount - loginCount));
        noLoginJson.put("name",CommonConstant.ONE_MONTH_NO_LOGIN);
        EChartsDataJson.add(loginJson);
        EChartsDataJson.add(noLoginJson);
        return EChartsDataJson.toJSONString();
    }
    
    /**
     * 审核通过
     * @author todd
     * @since 2019/3/22
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer checkUserPass(String idArray,String userIdArray)throws Exception{
        List<Integer> idList = this.array2List(idArray);
        List<Integer> userIdList = this.array2List(userIdArray);
        Integer num = mapper.checkUserPass(idList);
        if (num > 0){
            return mapper.updateUserRole(userIdList);
        }
        return -1;
    }

    /**
     * 审核不通过
     * @author todd
     * @since 2019/3/22
     */
    public Integer checkUserNoPass(String idStr){
        List<Integer> idList = this.array2List(idStr);
        try {
            return mapper.checkUserNoPass(idList);
        }catch (Exception e){
            logger.error("审核不通过异常",e);
        }
        return -1;
    }



    /**
     * 获取一个月时间段登录的用户条数
     * @return 登录条数（0，失败）
     * @author todd
     * @since 2019/3/5
     */
    private Integer getLoginNums() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //当前时间
        String endDate = format.format(new Date());
        //当前一个月之前的时间
        String startDate = this.oneMonthBeforeDate();
        logger.info("start"+startDate+">>>>endDate"+endDate);
        return mapper.getUserLoginNum(startDate,endDate);
    }


    /**
     * 获取当前时间一个月之前的日期时间
     * @author todd
     * @since 2019/3/5
     */
    private String oneMonthBeforeDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        String oneMonthAgoDate = "";
        try {
            c.setTime(new Date());
            c.add(Calendar.MONTH, -1);
            Date agoDate = c.getTime();
            oneMonthAgoDate = format.format(agoDate);
        }catch (Exception e){
            logger.error("时间获取异常",e);
        }
        return oneMonthAgoDate;
    }



    /**
     * 处理日期格式 （去除时间后的.0）
     * @author todd
     * @since 2019/3/19
     */
    private void setFormatDate(List<User> userList){
        for (User user : userList){
            if(!StringUtils.isEmpty(user.getUpdateTime())){
                user.setUpdateTime(user.getUpdateTime().replace(".0",""));
            }
            user.setCreateTime(user.getCreateTime().replace(".0",""));
        }
    }

    /**
     * 处理日期格式（去掉时间后面的.0）
     * @author todd
     * @since 2019/3/21
     */
    private void formatDate(List<CheckUser> checkUserList){
        for (CheckUser checkUser : checkUserList){
            if (!StringUtils.isEmpty(checkUser.getCheckTime())){
                checkUser.setCheckTime(checkUser.getCreateTime().replace(".0",""));
            }
            checkUser.setCreateTime(checkUser.getCreateTime().replace(".0",""));
        }
    }

    /**
     * 开始时间
     * @author todd
     * @since 2019/3/19
     */
    private String setFormatDateStart(String val){
        if (!StringUtils.isEmpty(val)){
            return val+" 00:00:00";
        }
        return val;
    }

    /**
     * 结束时间
     * @author todd
     * @since 2019/3/19
     */
    private String setFormatDateEnd(String val){
        if (!StringUtils.isEmpty(val)){
            return val+" 23:59:59";
        }
        return val;
    }

    /**
     * id数组转换成List
     * @author todd
     * @since 2019/3/22
     */
    private List<Integer> array2List(String idArray){
        List<Integer> idList = new ArrayList<Integer>();
        try {
            JSONArray jsonIdArray = JSONArray.parseArray(idArray);
            for (int i=0;i<jsonIdArray.size();i++){
                idList.add(jsonIdArray.getIntValue(i));
            }
        }catch (Exception e){
            logger.error("类型转换异常",e);
        }
        return idList;
    }


}
