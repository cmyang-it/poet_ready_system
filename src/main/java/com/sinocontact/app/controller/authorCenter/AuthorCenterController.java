package com.sinocontact.app.controller.authorCenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.entity.User;
import com.sinocontact.app.service.authorCenter.AuthorCenterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * 作者中心
 * @author caoMingYang
 * @Description TODO
 * @since 2019/5/20 14:03
 **/
@Controller
@RequestMapping("/author")
public class AuthorCenterController extends BaseController {

    private static Logger logger = Logger.getLogger(AuthorCenterController.class);

    @Autowired
    private AuthorCenterService service;

    /**
     * 跳转到作者所有作品页面
     */
    @RequestMapping("/index")
    public String index(){
        return "views/authorCenter/index";
    }


    /**
     * 获取作者写的所有小说
     * @param page 页码
     * @param pageSize 一页数据条数
     * @author caoMingYang
     * @since 2019/5/20 22:51
     */
    @ResponseBody
    @RequestMapping("novelList")
    public String novelList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        User user = (User) this.getSession().getAttribute(CommonConstant.SESSION_KEY);
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryNovelList(user.getUserId(),page,pageSize);
        Integer count = service.queryNovelCount(user.getUserId());
        job.put("code",0);
        job.put("msg","success");
        job.put("data",novelList);
        job.put("count",count);
        return job.toJSONString();
    }

    /**
     * 通过id获取小说信息
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/5/21 21:27
     */
    @ResponseBody
    @RequestMapping("/novelInfo")
    public String novelInfoById(@RequestParam("novelId")Integer novelId){
        Novel novel = service.queryNovelInfo(novelId);
        JSONObject job = new JSONObject();
        if (null != novel){
            job.put("code",0);
            job.put("msg","success");
            job.put("data",novel);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 校验小说名称是否重复
     * @param novelId 小说id
     * @param novelName 小说名称
     * @author caoMingYang
     * @since 2019/5/24 21:28
     */
    @ResponseBody
    @RequestMapping("/checkNovelName")
    public String checkNovelName(@RequestParam("novelId")Integer novelId,
                                 @RequestParam(value = "novelName",defaultValue = "")String novelName){
        JSONObject job = new JSONObject();
        boolean flag = service.checkNovelName(novelId, novelName);
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
     * 更新小说基本信息
     * @param novelId 小说id
     * @param novelName 小说名称
     * @param novelTypeStr 类型
     * @param novelInfo 简介
     * @param novelImg 封面
     * @param keyword 关键字
     * @param status 状态
     * @author caoMingYang
     * @since 2019/5/24 21:41
     */
    @ResponseBody
    @RequestMapping("/updateNovelInfo")
    public String updateNovelInfo(@RequestParam("novelId")Integer novelId,
                                  @RequestParam(value = "novelName",defaultValue = "")String novelName,
                                  @RequestParam(value = "novelTypeStr",defaultValue = "")String novelTypeStr,
                                  @RequestParam("novelInfo")String novelInfo,
                                  @RequestParam(value = "novelImg",defaultValue = "")String novelImg,
                                  @RequestParam("keyword")String keyword,
                                  @RequestParam(value = "status",defaultValue = "1")Integer status){
        JSONObject job = new JSONObject();
        boolean flag = service.updateNovel(novelId,novelName,novelImg,novelInfo,novelTypeStr,keyword,status);
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
     * 跳转到上传页面
     */
    @RequestMapping("/uploadNovel")
    public String uploadNovel(){
        return "views/authorCenter/upload";
    }


    /**
     * 保存小说基本信息
     * @param novelName 小说名称
     * @param novelTypeStr 类型
     * @param novelInfo 简介
     * @param novelImg 封面
     * @param keyword 关键字
     * @param status 状态
     * @author caoMingYang
     * @since 2019/5/24 21:41
     */
    @ResponseBody
    @RequestMapping("/saveNovelInfo")
    public String saveNovelInfo(@RequestParam(value = "novelName",defaultValue = "")String novelName,
                                @RequestParam(value = "novelTypeStr",defaultValue = "")String novelTypeStr,
                                @RequestParam("novelInfo")String novelInfo,
                                @RequestParam(value = "novelImg",defaultValue = "")String novelImg,
                                @RequestParam("keyword")String keyword,
                                @RequestParam(value = "status",defaultValue = "1")Integer status){
        JSONObject job = new JSONObject();
        User user = (User) this.getSession().getAttribute(CommonConstant.SESSION_KEY);
        Integer novelId = 0;
        try {
            novelId = service.saveNovel(user, novelName, novelImg, novelInfo, novelTypeStr, keyword, status);
        }catch (Exception e){
            logger.error("保存小说信息异常",e);
        }
        if (novelId > 0){
            job.put("code",0);
            job.put("msg","success");
            job.put("data",novelId);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 保存小说章节
     * @param chapterTitle 标题
     * @param chapterContent 章节内容
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/5/25 1:22
     */
    @ResponseBody
    @RequestMapping("/uploadChapter")
    public String uploadChapter(@RequestParam("chapterTitle")String chapterTitle,
                                @RequestParam("chapterNum")String chapterNumStr,
                                @RequestParam("chapterContent")String chapterContent,
                                @RequestParam("novelId")Integer novelId){
        JSONObject job = new JSONObject();
        Integer chapterNum = 0;
        try {
            chapterNum = service.saveChapter(chapterNumStr, chapterTitle, chapterContent, novelId);
        }catch (Exception e){
            logger.error("保存章节信息异常",e);
        }
        if (chapterNum > 0){
            job.put("code",0);
            job.put("msg","success");
            job.put("data",chapterNum);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/queryChapterNum")
    public String queryChapterNum(@RequestParam("novelId")Integer novelId){
        JSONObject job = new JSONObject();
        Integer num = service.queryChapterNum(novelId);
        if (num > 0){
            job.put("code",0);
            job.put("msg","success");
            job.put("data",num);
        }else {
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }


}
