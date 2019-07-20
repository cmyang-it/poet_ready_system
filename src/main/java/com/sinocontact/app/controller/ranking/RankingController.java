package com.sinocontact.app.controller.ranking;

import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.service.ranking.RankingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 排行榜
 * @author caoMingYang
 * @since 2019/4/15 13:54
 */
@Controller
@RequestMapping("/ranking")
public class RankingController extends BaseController {
    private static Logger logger = Logger.getLogger(RankingController.class);

    @Autowired
    private RankingService service;

    /**
     * 跳转到点击榜页面
     * @author caoMingYang
     * @since 2019/4/15 13:55
     */
    @RequestMapping("/clickIndex")
    public String clickIndex(@RequestParam(value = "page",defaultValue = "1")Integer page){
        //获取点击榜小说
        List<Novel> novelList = service.queryNovelListByPage(page);
        //获取小说总条数
        Integer count = service.queryNovelCount();
        this.putObject("novelList",novelList);
        this.putObject("count",count);
        this.putObject("currentPage",page);
        return "views/ranking/clickIndex";
    }

    /**
     * 跳转到收藏榜页面
     * @param page 当前页码
     * @author caoMingYang
     * @since 2019/4/18 23:36
     */
    @RequestMapping("/collectIndex")
    public String collectIndex(@RequestParam(value = "page",defaultValue = "1")Integer page){
        List<Novel> novelList = service.queryCollectNovelList(page);
        Integer count = service.queryNovelCount();
        this.putObject("novelList",novelList);
        this.putObject("count",count);
        this.putObject("currentPage",page);
        return "views/ranking/collectIndex";
    }

    /**
     * 获取总排行榜数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    @RequestMapping("/totalNumber")
    public String totalNumber(@RequestParam(value = "page",defaultValue = "1")Integer page){
        List<Novel> novelList = service.queryTotalNumber(page);
        Integer count = service.queryNovelCount();
        this.putObject("novelList",novelList);
        this.putObject("count",count);
        this.putObject("currentPage",page);
        return "views/ranking/totalNumber";
    }


    /**
     * 获取总推荐榜数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    @RequestMapping("/recommendList")
    public String recommendList(@RequestParam(value = "page",defaultValue = "1")Integer page){
        List<Novel> novelList = service.queryRecommendList(page);
        Integer count = service.queryNovelCount();
        this.putObject("novelList",novelList);
        this.putObject("count",count);
        this.putObject("currentPage",page);
        return "views/ranking/recommendList";
    }

    /**
     * 获取最近更新数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    @RequestMapping("/nowUpdate")
    public String nowUpdate(@RequestParam(value = "page",defaultValue = "1")Integer page){
        List<Novel> novelList = service.queryNowUpdate(page);
        Integer count = service.queryNovelCount();
        this.putObject("novelList",novelList);
        this.putObject("count",count);
        this.putObject("currentPage",page);
        return "views/ranking/nowUpdate";
    }

    /**
     * 获取最新入库数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    @RequestMapping("/nowPutStorage")
    public String nowPutStorage(@RequestParam(value = "page",defaultValue = "1")Integer page){
        List<Novel> novelList = service.queryNowPutStorage(page);
        Integer count = service.queryNovelCount();
        this.putObject("novelList",novelList);
        this.putObject("count",count);
        this.putObject("currentPage",page);
        return "views/ranking/nowPutStorage";
    }

    /**
     * 获取字数排行数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    @RequestMapping("/wordsList")
    public String wordsList(@RequestParam(value = "page",defaultValue = "1")Integer page){
        List<Novel> novelList = service.queryWordsList(page);
        Integer count = service.queryNovelCount();
        this.putObject("novelList",novelList);
        this.putObject("count",count);
        this.putObject("currentPage",page);
        return "views/ranking/wordsList";
    }


}
