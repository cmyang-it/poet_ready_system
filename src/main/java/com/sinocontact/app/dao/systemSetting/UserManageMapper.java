package com.sinocontact.app.dao.systemSetting;

import com.sinocontact.app.entity.CheckUser;
import com.sinocontact.app.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户管理
 * @author Cao
 * @Date 2019/3/10 18:37
 */
@Mapper
public interface UserManageMapper {

    //获取所有的用户信息
    List<User> getUserList(@Param("startDate")String startDate,
                           @Param("endDate")String endDate,
                           @Param("userInfo")String userInfo,
                           @Param("role")Integer role,
                           @Param("page")Integer page,
                           @Param("pageSize")Integer pageSize) throws Exception;

    //获取所有用户总条数
    Integer getUserCount(@Param("startDate")String startDate,
                         @Param("endDate")String endDate,
                         @Param("userInfo")String userInfo,
                         @Param("role")Integer role) throws Exception;

    //获取所有用户
    Integer getUserNum()throws Exception;

    //获取近一个月登录的用户数量
    Integer getUserLoginNum(@Param("startDate")String startDate,
                            @Param("endDate")String endDate) throws Exception;

    //获取所有用户审核信息
    List<CheckUser> getCheckUserList(@Param("checkStatus")Integer checkStatus,
                                     @Param("page")Integer page,
                                     @Param("pageSize")Integer pageSize) throws Exception;

    //获取所有用户审核信息总条数
    Integer getCheckUserCount(@Param("checkStatus")Integer checkStatus)throws Exception;

    //审核通过
    Integer checkUserPass(@Param("list")List<Integer> idList)throws Exception;

    //更新用户权限
    Integer updateUserRole(@Param("list")List<Integer> idList) throws Exception;

    //审核不通过
    Integer checkUserNoPass(@Param("list")List<Integer> idList) throws Exception;

}
