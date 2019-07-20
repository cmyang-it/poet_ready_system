package com.sinocontact.app.dao.user;

import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * 用户登录
 * @author todd
 * @since 2019/1/21
 */
@Mapper
public interface UserMapper {

    //通过用户名查询用户信息
    User checkUser(String username)throws Exception;


    //通过手机号查询用户信息
    User getUserByPhone(String phone)throws Exception;

    //更新用户登录时间
    Integer updateLoginTime(Integer userId) throws Exception;

    /**
     * 保存用户注册信息
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @param phone 手机号
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/2 11:05
     */
    Integer saveUser(@Param("username")String username,
                     @Param("password")String password,
                     @Param("nickname")String nickname,
                     @Param("phone")String phone) throws Exception;

    /**
     * 校验此手机号是否注册
     * @param phone 手机号
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/4 10:20
     */
    Integer checkPhone(@Param("phone")String phone) throws Exception;

    /**
     * 更新用户密码
     * @param phone 手机号
     * @param password 密码
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/4 19:15
     */
    Integer updatePassword(@Param("phone")String phone,
                           @Param("password")String password) throws Exception;

    //热门小说
    List<Novel> queryHotNovel()throws Exception;

    //总推荐榜
    List<Novel> queryRecommendList()throws Exception;

    //总字数榜
    List<Novel> queryWordsList()throws Exception;

    //最新入库
    List<Novel> queryNowUpdate()throws Exception;
}
