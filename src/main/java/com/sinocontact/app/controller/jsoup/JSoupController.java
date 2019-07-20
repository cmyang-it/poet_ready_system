package com.sinocontact.app.controller.jsoup;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.CrawlNovel;
import com.sinocontact.app.entity.DChapter;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.service.jsoup.JSoupService;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @author caoMingYang
 * @Description TODO
 * @since 2019/5/12 17:08
 **/
@Controller
@EnableScheduling
@RequestMapping("/jsoup")
public class JSoupController extends BaseController {

    private static final Logger logger = Logger.getLogger(JSoupController.class);

    @Autowired
    private JSoupService service;


    /**
     * 保存需要爬取的小说信息
     * @param novelName 小说名称
     * @param novelAuthor 作者
     * @param flUrl 飞卢网地址
     * @param ddUrl 顶点网地址
     * @author caoMingYang
     * @since 2019/5/12 17:11
     */
    @ResponseBody
    @RequestMapping("/saveNovelUrl")
    public String saveNovelUrl(@RequestParam(value = "novelName",defaultValue = "")String novelName,
                               @RequestParam(value = "novelAuthor",defaultValue = "")String novelAuthor,
                               @RequestParam(value = "flUrl",defaultValue = "")String flUrl,
                               @RequestParam(value = "ddUrl",defaultValue = "")String ddUrl,
                               @RequestParam(value = "ddChapterUrl",defaultValue = "")String ddChapterUrl){
        JSONObject job = new JSONObject();
        boolean flag = service.saveNovelUrl(novelName, novelAuthor, flUrl, ddUrl,ddChapterUrl);
        if (flag) {
            job.put("code", 0);
            job.put("msg", "success");
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }

    /**
     * 定时拉取存入待爬取小说表中的小说数据
     */
    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(cron = "5 * * * * ?")
    public void crawlNovel(){
        logger.info("爬取小说开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<CrawlNovel> crawlNovelList = service.queryNovelList();
        if (null != crawlNovelList && crawlNovelList.size()>0){
            logger.info("共有"+crawlNovelList.size()+"条待抓取记录...");
            for (CrawlNovel crawlNovel : crawlNovelList){
                logger.info("开始抓取，小说【"+crawlNovel.getNovelName()+"】");
                try {
                    Document document = Jsoup.connect(crawlNovel.getDdUrl()).get();
                    //获取小说信息
                    Novel novel = service.getNovel(document);
                    //获取章节信息
                    List<DChapter> dChapters = new ArrayList<DChapter>();
                    Document chapterDocument = Jsoup.connect(crawlNovel.getDdChapterUrl()).get();
                    Elements elements = chapterDocument.getElementById("at").getElementsByTag("td");
                    for (Element element : elements){
                        DChapter dChapter = new DChapter();
                        Element a = element.getElementsByTag("a").first();
                        dChapter.setChapterName(a.text());
                        dChapter.setChapterUrl(a.attr("href"));
                        dChapters.add(dChapter);
                    }
                    //获取的章节信息
                    List<Chapter> chapterList = service.getChapterList(dChapters);
                    //获取关键字
                    String keyWord = service.getKeyWord(crawlNovel.getFlUrl());
                    novel.setKeyword(keyWord);
                    boolean flag = service.saveNovelInfo(novel,chapterList,crawlNovel.getId());
                    if (!flag){
                        logger.error("小说保存异常，名称【"+novel.getNovelName()+"】，作者【"+novel.getNovelAuthor()+"】");
                    }
                }catch (Exception e){
                    logger.error("抓取小说异常",e);
                }
            }
        }else{
            logger.error("待爬取的小说为空！");
        }
        logger.info("小说爬取结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

}
