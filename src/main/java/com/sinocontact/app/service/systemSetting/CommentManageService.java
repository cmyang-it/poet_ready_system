package com.sinocontact.app.service.systemSetting;

import com.sinocontact.app.dao.systemSetting.CommentManageMapper;
import com.sinocontact.app.entity.Comment;
import com.sinocontact.app.entity.Novel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论管理
 * @author Cao
 * @since 2019/3/16 16:53
 */
@Service
public class CommentManageService {

    private static final Logger logger = Logger.getLogger(CommentManageService.class);

    @Autowired
    private CommentManageMapper mapper;

    /**
     * 获取小说信息
     * @param novelInfo 书名、作者
     * @param novelType 类型
     * @param pageSize 一页数据条数
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/3/30 15:01
     */
    public List<Novel> getNovelList(String novelInfo,String novelType,Integer page,Integer pageSize){
        List<Novel> novelList = null;
        try {
            Integer currentPage = (page - 1) * pageSize;
            novelList =  mapper.getNovelList(novelInfo, novelType, currentPage, pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("获取小说信息异常",e);
        }
        return novelList;
    }

    /**
     * 获取总条数
     * @param novelInfo 书名、作者
     * @param novelType 类型
     * @return -1 失败
     * @author caoMingYang
     * @since 2019/3/30 15:03
     */
    public Integer getNovelCount(String novelInfo,String novelType){
        try {
            return mapper.getNovelCount(novelInfo, novelType);
        }catch (Exception e){
            logger.error("获取小说总条数异常",e);
        }
        return -1;
    }



    /**
     * 获取指定小说评论
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param commentInfo 评论
     * @param novelId 小说id
     * @param page 页码
     * @param pageSize 一页数据
     * @return commentList
     * @author caoMingYang
     * @since 2019/4/5 17:36
     */
    public List<Comment> getCommentList(String startDate,String endDate,String commentInfo,Integer novelId,Integer page,Integer pageSize){
        try{
            Integer currentPage = (page - 1) * pageSize;
            List<Comment> commentList = mapper.getCommentList(startDate, endDate, commentInfo, novelId, currentPage, pageSize);
            this.formatCommentDate(commentList);
            return commentList;
        }catch (Exception e){
            logger.error("",e);
        }
        return null;
    }

    /**
     * 获取总条数
     * @param endDate 书名、作者
     * @param startDate 类型
     * @param commentInfo 评论
     * @param novelId 小说id
     * @return -1 失败
     * @author caoMingYang
     * @since 2019/4/5 17:36
     */
    public Integer getCommentCount(String startDate,String endDate,String commentInfo,Integer novelId ){
        try {
            return mapper.getCommentCount(startDate, endDate, commentInfo, novelId);
        }catch (Exception e){
            logger.error("获取小说评论总条数异常",e);
        }
        return -1;
    }

    /**
     * 和谐小说
     * @param novelId 小说id
     * @param accordReason 审核理由
     * @param commentId 评论id
     * @return true 成功，false 失败
     * @author caoMingYang
     * @since 2019/4/5 19:17
     */
    public boolean accordReason(Integer novelId,Integer commentId,String accordReason){
        try{
            mapper.accordNovel(commentId,novelId,accordReason);
            return true;
        }catch (Exception e){
            logger.error("",e);
        }
        return false;
    }

    /**
     * 格式化时间格式
     * @param novelList novelList
     * @author caoMingYang
     * @since 2019/3/30 15:00
     */
    private void formatDate(List<Novel> novelList){
        for (Novel novel : novelList){
            if (!StringUtils.isEmpty(novel.getLastChapterUpdate())){
                novel.setLastChapterUpdate(novel.getLastChapterUpdate().replace(".0",""));
            }
        }
    }

    /**
     * 格式化时间格式
     * @param commentList novelList
     * @author caoMingYang
     * @since 2019/3/30 15:00
     */
    private void formatCommentDate(List<Comment> commentList){
        for (Comment comment : commentList){
            if (!StringUtils.isEmpty(comment.getUserCommentTime())){
                comment.setUserCommentTime(comment.getUserCommentTime().replace(".0",""));
            }
            if (!StringUtils.isEmpty(comment.getAuthorReplyTime())){
                comment.setAuthorReplyTime(comment.getAuthorReplyTime().replace(".0",""));
            }
        }
    }
}
