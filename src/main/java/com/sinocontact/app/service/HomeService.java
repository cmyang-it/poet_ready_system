package com.sinocontact.app.service;

import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.dao.HomeMapper;
import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.Comment;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 公共业务处理
 * @author caoMingYang
 * @since 2019/4/25 16:12
 **/
@Service
public class HomeService {
    private static Logger logger = Logger.getLogger(HomeService.class);

    @Autowired
    private HomeMapper mapper;

    /**
     * 通过id获取小说信息
     * @param novelId 小说id
     * @return novel
     * @author caoMingYang
     * @since 2019/4/25 16:27
     */
    public Novel getNovelById(Integer novelId){
       try{
           Novel novel = mapper.getNovelById(novelId);
           this.formatNovelDate(novel);
           return novel;
       }catch (Exception e){
           logger.error("通过id获取小说信息异常",e);
       }
       return null;
    }

    /**
     * 增加点击
     * @param novelId 小说id
     * @return true 成功，false 失败
     * @author caoMingYang
     * @since 2019/4/26 0:26
     */
    @Transactional
    public boolean addClick(Integer novelId){
        try{
            Integer novelClick = mapper.getNovelClick(novelId);
            Integer num = mapper.addClick(novelId,novelClick+1);
            return num > 0 ? true : true;
        }catch (Exception e){
            logger.error("添加点击失败",e);
        }
        return false;
    }

    /**
     * 通过小说id获取小说最新的五个章节
     * @param novelId 小说id
     * @return chapterList
     * @author caoMingYang
     * @since 2019/4/27 12:33
     */
    public List<Chapter> queryFiveChapter(Integer novelId){
        try{
            return mapper.queryFiveChapter(novelId);
        }catch (Exception e){
            logger.error("通过小说id获取小说最新的五个章节异常",e);
        }
        return null;
    }

    /**
     * 通过小说id获取小说评论
     * @param novelId 小说id
     * @return commentList
     * @author caoMingYang
     * @since 2019/4/27 17:42
     */
    public List<Comment> getNovelComment(Integer novelId){
        List<Comment> commentList = null;
        try{
            commentList = mapper.getNovelComment(novelId);
            this.setCommentUserName(commentList);
        }catch (Exception e){
            logger.error("通过小说id获取用户评论异常",e);
        }
        return commentList;
    }

    /**
     * 获取小说章节信息
     * @param novelId 小说id
     * @return chapterList
     * @author caoMingYang
     * @since 2019/4/27 22:36
     */
    public List<Chapter> queryChapterList(Integer novelId){
        List<Chapter> chapterList = null;
        try{
            chapterList = mapper.queryChapterList(novelId);
        }catch (Exception e){
            logger.error("获取小说章节异常",e);
        }
        return chapterList;
    }

    /**
     * 通过id获取章节信息
     * @param novelId 小说id
     * @param chapterId 章节id
     * @return chapter
     * @author caoMingYang
     * @since 2019/5/11 13:35
     */
    public Chapter getChapter(Integer novelId,Integer chapterId){
        try{
            Chapter chapter =  mapper.getChapterInfo(novelId,chapterId);
            this.setChapterContent(chapter);
            return chapter;
        }catch (Exception e){
            logger.error("获取章节信息异常",e);
        }
        return null;
    }

    /**
     * 用户投推荐票
     * @param novelId 小说id
     * @param user 用户
     * @return -2  推荐次数不足 ，-1 推荐失败，系统异常，0 推荐成功
     * @author caoMingYang
     * @since 2019/4/28 20:48
     */
    @Transactional
    public Integer updateRecommend(Integer novelId, User user) {
        Integer recommendNum = 0;
        try{
            //获取用户当前剩余推荐次数
            recommendNum = mapper.queryRecommendNumById(user.getUserId());
        }catch (Exception e){
            logger.error("",e);
        }
        //推荐次数大于0
        if (recommendNum > 0) {
            //推荐
            Integer num = mapper.updateRecommendById(novelId);
            Integer userNum = mapper.updateUserRecommendNum(user.getUserId());
            if (num > 0 && userNum > 0) {
                return 0;
            }
        }else{
            logger.error("今日推荐次数不足");
            return -2;
        }
        return -1;

    }

    /**
     * 保存小说到用户书架中
     * @param novelId 小说id
     * @param user 用户信息
     * @return -1 保存失败，0：小说已存在书架，1 保存成功
     */
    public Integer addBookCase(Integer novelId,User user,Integer chapterId){
        try {
            Integer count = mapper.checkBookCase(novelId,user.getUserId(),chapterId);
            if (count == 0) {
                 return mapper.addBookCase(novelId,user.getUserId(),chapterId);
            }else{
                 return mapper.updateBookCase(novelId,user.getUserId(),chapterId);
            }
        }catch (Exception e){
            logger.error("保存小说到书架异常",e);
        }
        return -1;
    }


    /**
     * 获取上一章信息
     * @param novelId 小说id
     * @param chapterId 当前章id
     * @return chapter
     * @author caoMingYang
     * @since 2019/5/11 18:42
     */
    public Chapter previousChapter(Integer novelId,Integer chapterId){
        try{
            Integer num = mapper.previousChapterCount(novelId, chapterId);
            if (num <= 0){
                Chapter chapter = new Chapter();
                chapter.setChapterId(-1);
                return chapter;
            }
            Chapter chapter = mapper.previousChapter(novelId, chapterId);
            this.setChapterContent(chapter);
            return chapter;
        }catch (Exception e){
            logger.error("获取上一章章节信息异常",e);
        }
        return null;
    }

    /**
     * 获取下一章信息
     * @param novelId
     * @param chapterId
     * @return
     */
    public Chapter nextChapter(Integer novelId,Integer chapterId){
        try {
            Integer num = mapper.nextChapterCount(novelId, chapterId);
            if (num <= 0){
                Chapter chapter = new Chapter();
                chapter.setChapterId(-1);
                return chapter;
            }
            Chapter chapter = mapper.nextChapter(novelId, chapterId);
            this.setChapterContent(chapter);
            return chapter;
        }catch (Exception e){
            logger.error("获取下一章章节信息异常",e);
        }
        return null;
    }

    /**
     * 保存评论
     * @param userId 用户id
     * @param novelId 小说id
     * @param comment 评论
     * @return false 失败，true 成功
     * @author caoMingYang
     * @since 2019/5/25 13:55
     */
    public boolean saveComment(Integer userId,Integer novelId,String comment){
        try {
            Integer num = mapper.saveComment(novelId,userId,comment);
            return num > 0 ? true : false;
        }catch (Exception e){
            logger.error("",e);
        }
        return false;
    }

    /**
     * 设置章节内容格式
     * @param chapter 章节信息
     * @author caoMingYang
     * @since 2019/5/11 18:40
     */
    private void setChapterContent(Chapter chapter){
        if (null != chapter){
            String chapterContent = chapter.getChapterContent();
            if (!StringUtils.isEmpty(chapterContent)){
                chapter.setChapterContent(chapterContent.replace(" ","<br><br>&nbsp;&nbsp;&nbsp;&nbsp;"));
            }
        }
    }


    /**
     * 当评论人昵称为空时，设置默认名称
     * @param commentList 评论
     * @author caoMingYang
     * @since 2019/4/27 17:42
     */
    private void setCommentUserName(List<Comment> commentList)throws Exception{
        if (null != commentList){
            for (Comment comment : commentList){
                if (StringUtils.isEmpty(comment.getNickname())){
                    comment.setNickname(CommonConstant.NICKNAME_IS_NULL);
                }
                String createTime = comment.getUserCommentTime().replace(".0","");
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
                String result = new SimpleDateFormat("MM月dd日 HH:mm").format(date);
                comment.setUserCommentTime(result);
            }
        }
    }

    /**
     * 格式化小说罪行章节更新时间
     * @param novel 小说
     * @author caoMingYang
     * @since 2019/4/25 16:26
     */
    private void formatNovelDate(Novel novel)throws Exception{
        if (null != novel){
            if (null != novel.getLastChapterUpdate()){
                String updateTime = novel.getLastChapterUpdate().replace(".0","");
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateTime);
                String result = new SimpleDateFormat("MM月dd日 HH:mm").format(date);
                novel.setLastChapterUpdate(result);
            }
        }
    }

    /**
     * 更新用户每日推荐次数
     * @author caoMingYang
     * @since 2019/5/18 0:22
     */
    public Integer updateRecommendTimes(){
        try {
            return mapper.updateRecommendTimes();
        }catch (Exception e){
            logger.error("更新用户每日推荐次数异常",e);
        }
        return -1;
    }
}


