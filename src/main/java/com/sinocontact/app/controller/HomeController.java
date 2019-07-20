package com.sinocontact.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.Comment;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.entity.User;
import com.sinocontact.app.service.HomeService;
import com.sun.org.apache.bcel.internal.generic.NOP;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 公共的控制类
 * @author caoMingYang
 * @since 2019/4/25 16:10
 **/
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
    private static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private HomeService service;

    /**
     * 跳转到小说详情页面
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/4/25 16:17
     */
    @RequestMapping("/novelInfo")
    public String novelInfo(@RequestParam("novelId")Integer novelId){
        Novel novel = service.getNovelById(novelId);
        List<Chapter> chapterList = service.queryChapterList(novelId);
        this.putObject("novel",novel);
        this.putObject("list",chapterList);
        return "views/home/novelInfo";
    }

    /**
     * 通过小说id获取小说最新的章节
     * @param novelId 小说id
     * @return chapterList
     * @author caoMingYang
     * @since 2019/4/27 12:27
     */
    @ResponseBody
    @RequestMapping("/getNewChapter")
    public String getNewChapter(@RequestParam("novelId")Integer novelId){
        JSONObject job = new JSONObject();
        List<Chapter> chapterList = service.queryFiveChapter(novelId);
        if (null == chapterList){
            job.put("code",-1);
            job.put("msg","fail");
        }else{
            job.put("code",0);
            job.put("msg","success");
            job.put("data",chapterList);
        }
        return job.toJSONString();
    }

    /**
     * 增加点击
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/4/26 0:13
     */
    @ResponseBody
    @RequestMapping("/addClick")
    public String addClick(@RequestParam("novelId")Integer novelId){
        JSONObject job = new JSONObject();
        boolean flag = service.addClick(novelId);
        if (flag) {
            job.put("code", 0);
            job.put("msg", "success");
        }
        return job.toJSONString();
    }

    /**
     * 推荐小说
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/4/28 20:33
     */
    @ResponseBody
    @RequestMapping("/recommend")
    public String recommend(@RequestParam("novelId")Integer novelId){
        JSONObject job = new JSONObject();
        User user = this.getUserInfo();
        Integer num = service.updateRecommend(novelId,user);
        if (num == 0){
            job.put("code",0);
            job.put("msg","success");
        }else if (num == -1){
            job.put("code",-1);
            job.put("msg","推荐失败，系统异常！");
        }else if (num == -2){
            job.put("code",-1);
            job.put("msg","今日已推荐三次，请明天再来！");
        }
        return job.toJSONString();
    }

    /**
     * 通过小说id将小说加入书架
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/4/29 22:19
     */
    @ResponseBody
    @RequestMapping("/addBookCase")
    public String addBookCase(@RequestParam("novelId")Integer novelId,
                              @RequestParam(value = "chapterId",defaultValue = "0")Integer chapterId){
        JSONObject job = new JSONObject();
        Integer num = service.addBookCase(novelId,this.getUserInfo(),chapterId);
        if (num == -1){
            job.put("code",-1);
            job.put("msg","fail");
        }else {
            job.put("code",0);
            job.put("msg","success");
        }
        return job.toJSONString();
    }

    /**
     * 获取小说评论
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/4/27 17:35
     */
    @ResponseBody
    @RequestMapping("/getNovelComment")
    public String getNovelComment(@RequestParam("novelId")Integer novelId){
        JSONObject job = new JSONObject();
        List<Comment> commentList = service.getNovelComment(novelId);
        if (null != commentList){
            job.put("code",0);
            job.put("msg","success");
            job.put("data",commentList);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 通过id获取小说章节详情
     * @param novelId 小说id
     * @param chapterId 章节id
     * @author caoMingYang
     * @since 2019/5/11 13:23
     */
    @ResponseBody
    @RequestMapping("/getChapterInfo")
    public String getChapterInfo(@RequestParam("novelId")Integer novelId,
                                 @RequestParam("chapterId")Integer chapterId){
        JSONObject job = new JSONObject();
        Chapter chapter = service.getChapter(novelId, chapterId);
        if (null != chapter){
            job.put("code",0);
            job.put("msg","success");
            job.put("data",chapter);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 获取章节内容
     * @param novelId 小说id
     * @param chapterId 章节id
     * @author caoMingYang
     * @since 2019/5/11 18:30
     */
    @ResponseBody
    @RequestMapping("/previousChapter")
    public String previousChapter(@RequestParam("novelId")Integer novelId,
                                  @RequestParam("chapterId")Integer chapterId){
        JSONObject job = new JSONObject();
        Chapter chapter = service.previousChapter(novelId, chapterId);
        if (null != chapter){
            if (chapter.getChapterId() != -1){
                job.put("code",0);
                job.put("msg","success");
                job.put("data",chapter);
            }else {
                job.put("code",1);
                job.put("msg","success");
            }
        }else {
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 获取章节内容
     * @param novelId 小说id
     * @param chapterId 章节id
     * @author caoMingYang
     * @since 2019/5/11 18:30
     */
    @ResponseBody
    @RequestMapping("/nextChapter")
    public String nextChapter(@RequestParam("novelId")Integer novelId,
                              @RequestParam("chapterId")Integer chapterId){
        JSONObject job = new JSONObject();
        Chapter chapter = service.nextChapter(novelId, chapterId);
        if (null != chapter){
            if (chapter.getChapterId() != -1){
                job.put("code",0);
                job.put("msg","success");
                job.put("data",chapter);
            }else {
                job.put("code",1);
                job.put("msg","success");
            }
        }else {
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 保存用户评论
     * @param novelId 小说id
     * @param comment 评论
     * @author caoMingYang
     * @since 2019/5/25 13:53
     */
    @ResponseBody
    @RequestMapping("/saveComment")
    public String saveComment(@RequestParam("novelId")Integer novelId,
                              @RequestParam(value = "comment",defaultValue = "")String comment){
        JSONObject job = new JSONObject();
        User user = (User) this.getSession().getAttribute(CommonConstant.SESSION_KEY);
        boolean flag = service.saveComment(user.getUserId(),novelId,comment);
        if (flag){
            job.put("code",0);
            job.put("msg","success");
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 更新用户推荐次数
     * @author caoMingYang
     * @since 2019/5/18 0:18
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateUserRecommendTimes(){
        logger.info("推荐次数更新开始>>>>>>>>>>>>>>>>>>>>>>>>>");
        Integer num = service.updateRecommendTimes();
        if (num == -1 || num == 0){
            logger.error("更新用户每日推荐次数异常");
        }else {
            logger.info("更新了【"+num+"】人的每日推荐次数！");
        }
        logger.info("推荐次数更新结束<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

}
