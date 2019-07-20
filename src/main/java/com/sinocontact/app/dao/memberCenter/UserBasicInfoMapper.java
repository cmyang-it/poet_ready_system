package com.sinocontact.app.dao.memberCenter;

import com.sinocontact.app.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author caoMingYang
 * @since 2019/4/11 23:20
 */
@Mapper
public interface UserBasicInfoMapper {

    /**
     * 通过用户名获取用户信息
     * @param account 用户名
     * @return 数据条数
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/11 23:21
     */
    User userInfoByAccount(@Param("account")String account)throws Exception;


    /**
     * 更新用户信息
     * @param account 用户名
     * @param nickname 昵称
     * @param userId 用户id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/12 15:03
     */
    Integer updateUserInfo(@Param("account")String account,
                           @Param("nickname")String nickname,
                           @Param("userId")Integer userId)throws Exception;

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return User
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/12 16:57
     */
    User getUser(@Param("userId")Integer userId)throws Exception;

    /**
     * 申请成为作者
     * @param userId 用户id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/12 19:44
     */
    Integer applyForAuthor(@Param("userId")Integer userId)throws Exception;


    /**
     * 更改用户申请状态
     * @param userId 用户id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/12 20:59
     */
    Integer updateApplyStatus(@Param("userId")Integer userId)throws Exception;


    /**
     * 更新用户密码
     * @param password 密码
     * @param userId 用户id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/14 12:19
     */
    Integer updatePassword(@Param("password")String password,
                           @Param("userId")Integer userId) throws Exception;
}
