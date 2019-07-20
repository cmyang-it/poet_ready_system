package com.sinocontact.app.controller.systemSetting;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.entity.Comment;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.service.systemSetting.CommentManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStreamReader;
import java.util.List;

/**
 * 评论管理
 * @author Cao
 * @since 2019/3/16 16:59
 */
@Controller
@RequestMapping("/commentManage")
public class CommentManageController {
    private static final Logger logger = Logger.getLogger(CommentManageController.class);

    @Autowired
    private CommentManageService service;

    /**
     * 跳转到评论管理首页
     * @return java.lang.String
     * @author caoMingYang
     * @since 2019/3/30 14:34
     */
    @RequestMapping("/index")
    public String index(){
        return "views/systemSetting/commentManage/commentList";
    }


    /**
     * 获取小说列表
     * @param novelInfo 作者或书名
     * @param novelType 类型
     * @param page 页码
     * @param pageSize 当前页数据条数
     * @return java.lang.String
     * @author caoMingYang
     * @since 2019/3/30 14:46
     */
    @ResponseBody
    @RequestMapping("/novelList")
    public String novelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                            @RequestParam(value = "novelType",defaultValue = "")String novelType,
                            @RequestParam(value = "page",defaultValue = "1")Integer page,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        JSONObject job = new JSONObject();
        //获取小说信息
        List<Novel> novelList = service.getNovelList(novelInfo, novelType, page, pageSize);
        //获取小说总条数
        Integer count = service.getNovelCount(novelInfo, novelType);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info("获取小说列表>>>>>"+jsonText);
        return jsonText;
    }

    /**
     * 获取小说的评论列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param commentInfo 评论
     * @param page 页码
     * @param pageSize 当前页最大数据
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/4/5 16:04
     */
    @ResponseBody
    @RequestMapping("/commentList")
    public String commentList(@RequestParam(value = "startDate",defaultValue = "")String startDate,
                              @RequestParam(value = "endDate",defaultValue = "")String endDate,
                              @RequestParam(value = "commentInfo",defaultValue = "")String commentInfo,
                              @RequestParam(value = "page",defaultValue = "1")Integer page,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                              @RequestParam(value = "novelId")Integer novelId){
        JSONObject job = new JSONObject();
        //评论
        List<Comment> commentList = service.getCommentList(startDate, endDate, commentInfo,novelId, page, pageSize);
        //总数
        Integer count = service.getCommentCount(startDate, endDate, commentInfo, novelId);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",commentList);
        String jsonText = job.toJSONString();
        logger.info("获取小说评论列表>>>>"+jsonText);
        return jsonText;
    }


    /**
     * 和谐小说
     * @param novelId 小说id
     * @param  commentId 评论id
     * @author caoMingYang
     * @since 2019/4/5 18:43
     */
    @ResponseBody
    @RequestMapping("/accordNovel")
    public String accordNovel(@RequestParam(value = "novelId")Integer novelId,
                              @RequestParam(value = "commentId")Integer commentId,
                              @RequestParam(value = "accordReason",defaultValue = "")String accordReason){
        JSONObject job = new JSONObject();
        boolean flag = service.accordReason(novelId,commentId,accordReason);
        if (flag){
            job.put("code",0);
            job.put("msg","success");
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        String jsonText = job.toJSONString();
        logger.info("和谐小说>>>>"+jsonText);
        return jsonText;
    }


}
