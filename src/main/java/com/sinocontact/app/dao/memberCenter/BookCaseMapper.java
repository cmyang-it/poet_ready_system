package com.sinocontact.app.dao.memberCenter;

import com.sinocontact.app.entity.BookCaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书架
 * @author caoMingYang
 * @since 2019/4/14 21:44
 */
@Mapper
public interface BookCaseMapper {

    /**
     * 获取用户书架信息
     * @param userId
     * @return bookCaseList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/14 22:13
     */
    List<BookCaseInfo> queryBookCaseList(@Param("userId")Integer userId)throws Exception;

    /**
     * 删除书架小说信息
     * @param userId 用户id
     * @param novelId 小说id
     * @return int
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/14 23:38
     */
    Integer deleteNovelById(@Param("userId")Integer userId,
                            @Param("novelId")Integer novelId)throws Exception;
}
