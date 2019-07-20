package com.sinocontact.app.dao;

import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.Comment;
import com.sinocontact.app.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公共dao
 * @author caoMingYang
 * @since 2019/4/25 16:13
 */
@Mapper
public interface HomeMapper {

    /**
     * 通过id获取小说信息
     * @param novelId 小说id
     * @return novel
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/25 16:18
     */
    Novel getNovelById(@Param("novelId")Integer novelId) throws Exception;


    /**
     * 添加点击
     * @param novelId 小说id
     * @return 影响行数
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/26 0:14
     */
    Integer addClick(@Param("novelId")Integer novelId,
                     @Param("novelClick")Integer novelClick) throws Exception;

    /**
     * 获取点击数量
     * @param novelId 小说id
     * @return 点击数量
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/26 0:23
     */
    Integer getNovelClick(@Param("novelId")Integer novelId) throws Exception;

    /**
     * 通过小说id获得最新的五条章节信息
     * @param novelId 小说id
     * @return chapterList
     * @throws Exception 异常
     * @author caoMingYang
     * @since 2019/4/27 12:28
     */
    List<Chapter> queryFiveChapter(@Param("novelId")Integer novelId)throws Exception;

    /**
     * 通过小说id获取用户评论
     * @param novelId 小说id
     * @return commentList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/27 17:38
     */
    List<Comment> getNovelComment(@Param("novelId")Integer novelId)throws Exception;

    /**
     * 通过小说id获取小说所有章节
     * @param novelId 小说 id
     * @return chapterList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/27 22:33
     */
    List<Chapter> queryChapterList(@Param("novelId")Integer novelId)throws Exception;


    /**
     * 通过用户id获取用户当前推荐次数
     * @param userId 用户id
     * @return 推荐次数
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/3 9:48
     */
    Integer queryRecommendNumById(@Param("userId")Integer userId)throws Exception;


    /**
     * 通过小说id更新小说推荐数
     * @param novelId 小说id
     * @return 影响行数
     * @author caoMingYang
     * @since 2019/4/28 20:37
     */
    Integer updateRecommendById(@Param("novelId")Integer novelId);


    /**
     * 通过id更新用户每日推荐次数
     * @param userId 用户id
     * @author caoMingYang
     * @since 2019/4/28 20:45
     */
    Integer updateUserRecommendNum(@Param("userId")Integer userId);


    /**
     * 通过小说id和用户id判断小说是否在用户书架中
     * @param novelId 小说id
     * @param userId 用户id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/30 20:19
     */
    Integer checkBookCase(@Param("novelId")Integer novelId,
                          @Param("userId")Integer userId,
                          @Param("chapterId")Integer chapterId)throws Exception;

    /**
     * 添加小说到用户书架
     * @param novelId 小说id
     * @param userId 用户id
     * @param chapterId 章节id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/30 20:19
     */
    Integer addBookCase(@Param("novelId")Integer novelId,
                        @Param("userId")Integer userId,
                        @Param(value = "chapterId")Integer chapterId)throws Exception;

    /**
     * 更新用户书架信息
     * @param novelId 小说id
     * @param userId 用户id
     * @param chapterId 章节id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/1 8:23
     */
    Integer updateBookCase(@Param("novelId")Integer novelId,
                           @Param("userId")Integer userId,
                           @Param("chapterId")Integer chapterId) throws Exception;


    /**
     * 通过id获取小说章节信息
     * @param novelId 小说id
     * @param chapterId 章节id
     * @return chapter
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/11 13:25
     */
    Chapter getChapterInfo(@Param("novelId")Integer novelId,
                           @Param("chapterId")Integer chapterId) throws Exception;

    /**
     * 通过id获取小说章节信息
     * @param novelId 小说id
     * @param chapterId 章节id
     * @return chapter
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/11 13:25
     */
    Chapter previousChapter(@Param("novelId")Integer novelId,
                            @Param("chapterId")Integer chapterId) throws Exception;

    /**
     * 获取是否存在上一章
     * @param novelId 小说id
     * @param chapterId 章节id
     * @return 0 不存在
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/11 19:00
     */
    Integer previousChapterCount(@Param("novelId")Integer novelId,
                                 @Param("chapterId")Integer chapterId)throws Exception;


    /**
     * 通过id获取小说章节信息
     * @param novelId 小说id
     * @param chapterId 章节id
     * @return chapter
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/11 13:25
     */
    Chapter nextChapter(@Param("novelId")Integer novelId,
                        @Param("chapterId")Integer chapterId) throws Exception;

    /**
     * 是否存在下一章
     * @param novelId 小说id
     * @param chapterId 章节id
     * @return 0 不存在
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/11 13:25
     */
    Integer nextChapterCount(@Param("novelId")Integer novelId,
                             @Param("chapterId")Integer chapterId) throws Exception;

    /**
     * 更新用户每日推荐次数
     * @author caoMingYang
     * @since 2019/5/18 0:23
     */
    Integer updateRecommendTimes()throws Exception;



    Integer saveComment(@Param("novelId")Integer novelId,
                        @Param("userId")Integer userId,
                        @Param("comment")String comment) throws Exception;
}
