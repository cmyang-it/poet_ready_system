package com.sinocontact.app.controller.library;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.service.library.LibraryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 书库
 * @author caoMingYang
 * @since 2019/4/19 16:49
 */
@Controller
@RequestMapping("/library")
public class LibraryController extends BaseController {
    private static final Logger logger = Logger.getLogger(LibraryController.class);

    @Autowired
    private LibraryService service;



    /**
     * 跳转到全部作品页面
     * @author caoMingYang
     * @since 2019/4/19 17:17
     */
    @RequestMapping("/index")
    public String libraryIndex(){
        return "views/library/index";
    }

    /**
     * 跳转到都市小说页面
     * @author caoMingYang
     * @since 2019/4/23 21:01
     */
    @RequestMapping("/cityIndex")
    public String cityIndex(){
        List<Novel> hotNovelList = service.queryCityHotNovel();
        putObject("hotNovelList",hotNovelList);
        return "views/library/cityIndex";
    }

    /**
     * 跳转到玄幻小说页面
     * @author caoMingYang
     * @since 2019/4/23 23:48
     */
    @RequestMapping("/fantasy")
    public String fantasyPage(){
        List<Novel> hotNovelList = service.queryFantasyHotNovel();
        putObject("hotNovelList",hotNovelList);
        return "views/library/fantasy";
    }


    /**
     * 跳转到仙侠小说页面
     * @author caoMingYang
     * @since 2019/4/24 20:24
     */
    @RequestMapping("/fairySpiderman")
    public String fairySpidermanPage(){
        List<Novel> novelList = service.queryFairyHotNovel();
        putObject("hotNovelList",novelList);
        return "views/library/fairy";
    }

    /**
     * 跳转到科幻小说页面
     * @author caoMingYang
     * @since 2019/4/24 20:47
     */
    @RequestMapping("/scienceFiction")
    public String scienceFiction(){
        List<Novel> novelList = service.queryScienceFictionHotNovel();
        putObject("hotNovelList",novelList);
        return "views/library/scienceFiction";
    }

    /**
     * 跳转到历史军事小说页面
     * @author caoMingYang
     * @since 2019/4/24 23:36
     */
    @RequestMapping("/militaryHistory")
    public String militaryHistory(){
        List<Novel> novelList = service.queryMilitaryHistoryHotNovel();
        putObject("hotNovelList",novelList);
        return "views/library/militaryHistory";
    }

    /**
     * 跳转到武侠小说页面
     * @author caoMingYang
     * @since 2019/4/24 23:39
     */
    @RequestMapping("/dragon")
    public String dragon(){
        List<Novel> novelList = service.queryDragonHotNovel();
        putObject("hotNovelList",novelList);
        return "views/library/dragon";
    }

    /**
     * 跳转到网游竞技页面
     * @author caoMingYang
     * @since 2019/4/24 23:40
     */
    @RequestMapping("/athletic")
    public String athletic(){
        List<Novel> novelList = service.queryAthleticHotNovel();
        putObject("hotNovelList",novelList);
        return "views/library/athletic";
    }

    /**
     * 跳转到悬疑灵异页面
     * @author caoMingYang
     * @since 2019/4/24 23:42
     */
    @RequestMapping("/suspenseParanormal")
    public String suspenseParanormal(){
        List<Novel> novelList = service.querySuspenseParanormalHotNovel();
        putObject("hotNovelList",novelList);
        return "views/library/suspenseParanormal";
    }

    /**
     * 跳转到其他小说页面
     * @author caoMingYang
     * @since 2019/4/24 23:43
     */
    @RequestMapping("/other")
    public String other(){
        List<Novel> novelList = service.queryOtherHotNovel();
        putObject("hotNovelList",novelList);
        return "views/library/other";
    }


    /**
     * 获取小说列表
     * @param novelType 小说分类
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/19 17:17
     */
    @ResponseBody
    @RequestMapping("/novelList")
    public String novelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                            @RequestParam(value = "novelType",defaultValue = "")String novelType,
                            @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                            @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                            @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                            @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalNovel(page, sortFlag, novelType, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelCount(sortFlag,novelType,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取都市小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/23 23:17
     */
    @ResponseBody
    @RequestMapping("/cityNovelList")
    public String cityNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalCityNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelCityCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取玄幻小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/23 23:57
     */
    @ResponseBody
    @RequestMapping("/fantasyNovelList")
    public String fantasyNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                   @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                   @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                   @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                   @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalFantasyNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelFantasyCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取仙侠小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/24 20:25
     */
    @ResponseBody
    @RequestMapping("/fairyNovelList")
    public String fairyNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                 @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                 @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                 @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                 @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalFairyNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelFairyCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取仙侠小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/24 20:25
     */
    @ResponseBody
    @RequestMapping("/scienceFictionNovelList")
    public String scienceFictionNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                 @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                 @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                 @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                 @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalScienceFictionNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelScienceFictionCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }


    /**
     * 获取历史军事小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/24 20:25
     */
    @ResponseBody
    @RequestMapping("/militaryHistoryNovelList")
    public String militaryHistoryNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                          @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                          @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                          @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                          @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalMilitaryHistoryNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelMilitaryHistoryCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取武侠小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/24 20:25
     */
    @ResponseBody
    @RequestMapping("/dragonNovelList")
    public String dragonNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                           @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                           @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                           @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                           @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalDragonNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelDragonCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取网游竞技小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/24 20:25
     */
    @ResponseBody
    @RequestMapping("/athleticNovelList")
    public String athleticNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                           @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                           @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                           @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                           @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalAthleticNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelAthleticCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取悬疑灵异小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/24 20:25
     */
    @ResponseBody
    @RequestMapping("/suspenseParanormalNovelList")
    public String suspenseParanormalNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                    @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                    @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                    @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                    @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalSuspenseParanormalNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelSuspenseParanormalCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 获取其他小说列表
     * @param sortFlag 排序状态（点击、收藏、推荐、总）
     * @param novelStatus 小说状态（连载、完结）
     * @param novelWords 字数
     * @param page 页码
     * @author caoMingYang
     * @since 2019/4/24 20:25
     */
    @ResponseBody
    @RequestMapping("/otherNovelList")
    public String otherNovelList(@RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                                    @RequestParam(value = "sortFlag",defaultValue = "0")Integer sortFlag,
                                    @RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                                    @RequestParam(value = "novelWords",defaultValue = "")String novelWords,
                                    @RequestParam(value = "page",defaultValue = "1")Integer page){
        JSONObject job = new JSONObject();
        List<Novel> novelList = service.queryTotalOtherNovel(page, sortFlag, novelStatus, novelWords, novelInfo);
        Integer count = service.getNovelOtherCount(sortFlag,novelStatus,novelWords,novelInfo);
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novelList);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }
}
