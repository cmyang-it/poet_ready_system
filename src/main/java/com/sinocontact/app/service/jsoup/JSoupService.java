package com.sinocontact.app.service.jsoup;

import com.sinocontact.app.dao.jsoup.JSoupMapper;
import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.CrawlNovel;
import com.sinocontact.app.entity.DChapter;
import com.sinocontact.app.entity.Novel;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时获取小说
 * @author caoMingYang
 * @since 2019/04/22 22:08
 */
@Service
public class JSoupService {
    private static Logger logger = Logger.getLogger(JSoupService.class);

    @Autowired
    private JSoupMapper mapper;


    /**
     * 保存待爬取小说信息
     * @param novelName 名称
     * @param novelAuthor 作者
     * @param flUrl 飞卢网地址
     * @param ddUrl 顶点网地址
     * @return true 成功，false 失败
     * @author caoMingYang
     * @since 2019/5/12 17:14
     */
    public boolean saveNovelUrl(String novelName,String novelAuthor,String flUrl,String ddUrl,String ddChapterUrl){
        try{
            Integer num = mapper.saveNovelUrl(novelName, novelAuthor, flUrl, ddUrl,ddChapterUrl);
            return num > 0;
        }catch (Exception e){
            logger.error("保存待爬取小说信息异常",e);
        }
        return false;
    }

    /**
     * 获取待抓取小说信息
     * @return crawlNovelList
     * @author caoMingYang
     * @since 2019/5/12 20:02
     */
    public List<CrawlNovel> queryNovelList(){
        try{
            return mapper.queryNovelList();
        }catch (Exception e){
            logger.error("获取待抓取小说信息异常",e);
        }
        return null;
    }


    /**
     * 保存小说和小说章节信息
     * @param novel 小说
     * @param chapterList 章节集合
     * @return true 成功，false 失败
     * @author caoMingYang
     * @since 2019/5/16 22:30
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean saveNovelInfo(Novel novel,List<Chapter> chapterList,Integer crawlNovelId)throws Exception{
        this.setNovelType(novel);
        Integer n1 = mapper.saveNovelInfo(novel);
        Integer n2 = mapper.saveNovelExtInfo(novel);
        for (Chapter chapter : chapterList) {
            chapter.setNovelId(novel.getNovelId());
        }
        logger.info("chapterSize>>>>"+chapterList.size());
        Integer n3 = mapper.saveChapterInfo(chapterList);
        if (n1 > 0 && n2 > 0 && n3 > 0){
            mapper.updateCrawlNovel(crawlNovelId);
            return true;
        }
        return false;
    }


    /**
     * 设置小说类型
     * @param novel 小说
     * @author caoMingYang
     * @since 2019/5/16 22:13
     */
    private void setNovelType(Novel novel){
        if (null != novel && !StringUtils.isEmpty(novel.getNovelTypeStr())){
            if (novel.getNovelTypeStr().contains("都市")){
                novel.setNovelType(1);
            }else if (novel.getNovelTypeStr().contains("玄幻")){
                novel.setNovelType(2);
            }else if (novel.getNovelTypeStr().contains("科幻")){
                novel.setNovelType(3);
            }else if (novel.getNovelTypeStr().contains("历史") || novel.getNovelTypeStr().contains("军事")){
                novel.setNovelType(4);
            }else if (novel.getNovelTypeStr().contains("武侠")){
                novel.setNovelType(5);
            }else if (novel.getNovelTypeStr().contains("网游")){
                novel.setNovelType(6);
            }else if (novel.getNovelTypeStr().contains("悬疑") || novel.getNovelTypeStr().contains("灵异")){
                novel.setNovelType(7);
            }else if (novel.getNovelTypeStr().contains("仙侠")){
                novel.setNovelType(9);
            }else if (novel.getNovelTypeStr().contains("其他")){
                novel.setNovelType(10);
            }

        }
    }



    /**
     * 获取章节信息
     * @param dChapterList 网站章节
     * @return chapterList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/14 20:21
     */
    public List<Chapter> getChapterList(List<DChapter> dChapterList) throws Exception{
        List<Chapter> chapterList = new ArrayList<Chapter>();
        if (null != dChapterList && dChapterList.size() > 0) {
            for (int i=0;i<dChapterList.size();i++) {
                Chapter chapter = new Chapter();
                Document document = Jsoup.connect(dChapterList.get(i).getChapterUrl()).get();
                String title = document.getElementsByTag("h1").text();
                chapter.setChapterName(title);
                Element element = document.getElementById("contents");
                chapter.setChapterContent(element.text());
                chapterList.add(chapter);
            }
        }
        return chapterList;
    }

    /**
     * 获取关键字
     * @param flUrl 飞卢小说网地址
     * @return keyWord
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/14 20:35
     */
    public String getKeyWord(String flUrl) throws Exception{
        String keyWord = "";
        if (!StringUtils.isEmpty(flUrl)){
            Document flDocument = Jsoup.connect(flUrl).get();
            List<String> tagElements = flDocument.getElementsByClass("item_tag").eachText();
            keyWord = tagElements.get(0).replace("、",",");
        }
        return keyWord;
    }


    /**
     * 获取带爬取小说的详细信息
     * @param document 网页
     * @return novel
     * @author caoMingYang
     * @since 2019/5/14 0:08
     */
    public Novel getNovel(Document document){
        Novel novel = new Novel();
        //获取基础信息
        String  novelName = document.getElementsByTag("meta").get(1).attr("content");
        novel.setNovelName(novelName);
        //封面
        String novelImg = document.getElementsByTag("img").last().attr("src");
        novel.setNovelImg(novelImg);
        Element element = document.getElementById("at");
        Elements elements = element.getElementsByTag("td");
        List<String> strList = elements.eachText();
        this.setNovel(novel,strList);
        //简介
        Element elementAbstract = document.getElementsByTag("dd").last();
        Element element2 = elementAbstract.getElementsByTag("p").get(1);
        novel.setNovelInfo(element2.text());
        //最新章节
        Element element5 = elementAbstract.getElementsByTag("p").get(5).getElementsByTag("a").first();
        novel.setLastChapter(element5.text());
        logger.info(novel);
        return novel;
    }


    /**
     * 设置小说基本信息
     * @param novel 小说
     * @param strList 基本信息
     * @author caoMingYang
     * @since 2019/5/14 0:07
     */
    private void setNovel(Novel novel,List<String> strList){
        novel.setNovelTypeStr(strList.get(0));
        novel.setNovelAuthor(strList.get(1));
        if (strList.get(2).equals("连载中")){
            novel.setStatus(1);
        }else {
            novel.setStatus(0);
        }
        novel.setNovelCollect(Integer.valueOf(strList.get(3)));
        String words = strList.get(4).replace("字","");
        novel.setNovelWords(Integer.valueOf(words));
        novel.setLastChapterUpdate(strList.get(5));
        novel.setNovelClick(Integer.valueOf(strList.get(6)));
        novel.setNovelRecommend(Integer.valueOf(strList.get(9)));
    }

}
