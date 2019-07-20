package com.sinocontact.app.dao.systemSetting;

import com.sinocontact.app.entity.Comment;
import com.sinocontact.app.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * 评论管理
 * @author Cao
 * @since 2019/3/16 16:56
 */
@Mapper
public interface CommentManageMapper {

    /**
     * 获取小说列表
     * @param novelInfo 书名或作者
     * @param novelType 分类
     * @param page 页码
     * @param pageSize 当前页数据条数
     * @return novelList
     * @author caoMingYang
     * @since 2019/3/30 14:48
     */
    List<Novel> getNovelList(@Param("novelInfo")String novelInfo,
                             @Param("novelType")String novelType,
                             @Param("page")Integer page,
                             @Param("pageSize")Integer pageSize)throws Exception;

    /**
     * 获取小说总条数
     * @param novelInfo 书名、作者
     * @param novelType 类型
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/3/30 14:57
     */
    Integer getNovelCount(@Param("novelInfo")String novelInfo,
                          @Param("novelType")String novelType)throws Exception;


    /**
     * 获取小说评论列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param commentInfo 评论
     * @param novelId 小说id
     * @param page 页码
     * @param pageSize 当前页数据条数
     * @return commentList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/5 16:09
     */
    List<Comment> getCommentList(@Param("startDate")String startDate,
                                 @Param("endDate")String endDate,
                                 @Param("commentInfo")String commentInfo,
                                 @Param("novelId")Integer novelId,
                                 @Param("page")Integer page,
                                 @Param("pageSize")Integer pageSize) throws  Exception;

    /**
     * 获取小说评论条数
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param commentInfo 评论
     * @param novelId 小说id
     * @return num
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/5 16:10
     */
    Integer getCommentCount(@Param("startDate")String startDate,
                            @Param("endDate")String endDate,
                            @Param("commentInfo")String commentInfo,
                            @Param("novelId")Integer novelId)throws Exception;

    /**
     * 和谐评论
     * @param commentId 评论id
     * @param novelId 小说id
     * @param accordReason 和谐理由
     * @return num
     * @author caoMingYang
     * @since 2019/4/5 18:50
     */
    Integer accordNovel(@Param("commentId")Integer commentId,
                        @Param("novelId")Integer novelId,
                        @Param("accordReason")String accordReason)throws Exception;
}
