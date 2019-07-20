package com.sinocontact.app.service.memberCenter;

import com.sinocontact.app.dao.memberCenter.UserBasicInfoMapper;
import com.sinocontact.app.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息
 * @author caoMingYang
 * @since 2019/4/11 23:26
 */
@Service
public class UserBasicInfoService {
    private static Logger logger = Logger.getLogger(UserBasicInfoService.class);

    @Autowired
    private UserBasicInfoMapper mapper;

    /**
     * 获取用户
     * @param userId 用户id
     * @return User
     * @author caoMingYang
     * @since 2019/4/12 17:01
     */
    public User getUser(Integer userId){
        User user = null;
        try {
            user = mapper.getUser(userId);
        }catch (Exception e){
            logger.error("获取用户信息异常",e);
        }
        return user;
    }

    /**
     * 通过用户名获取用户数据条数
     * @param account 用户名
     * @return false 用户名不存在，true 用户名存在
     * @author caoMingYang
     * @since 2019/4/11 23:28
     */
    public boolean checkAccount(String account,Integer userId){
        try {
            User user = mapper.userInfoByAccount(account);
            //用户信息为空或用户id和session中的相同
            if (null == user || user.getUserId() == userId){
                return true;
            }
        }catch (Exception e){
            logger.error("通过用户名获取用户数据条数异常",e);
        }
        return false;
    }

    /**
     * 更新用户信息
     * @param account 账号
     * @param nickname 昵称
     * @param userId 用户id
     * @return true 成功，false 失败
     * @author caoMingYang
     * @since 2019/4/12 15:09
     */
    public boolean updateUserInfo(String account,String nickname,Integer userId){
        try{
            Integer num = mapper.updateUserInfo(account,nickname,userId);
            return num > 0;
        }catch (Exception e){
            logger.error("更新用户信息异常",e);
        }
        return false;
    }

    /**
     * 申请成为作者
     * @param userId 用户id
     * @return true 成功，false 失败
     * @author caoMingYang
     * @since 2019/4/12 19:50
     */
    @Transactional
    public boolean applyForAuthor(Integer userId){
        try{
            Integer num = mapper.applyForAuthor(userId);
            Integer count = mapper.updateApplyStatus(userId);
            return num > 0 && count > 0;
        }catch (Exception e){
            logger.error("申请成为作者异常",e);
        }
        return false;
    }

    /**
     * 更新用户密码
     * @param password 密码
     * @param userId 用户id
     * @return true 成功  false 失败
     * @author caoMingYang
     * @since 2019/4/14 12:21
     */
    public boolean updatePassword(String password,Integer userId){
        try{
            Integer num = mapper.updatePassword(password,userId);
            return num > 0;
        }catch (Exception e){
            logger.error("更新用户密码异常",e);
        }
        return false;
    }
}
