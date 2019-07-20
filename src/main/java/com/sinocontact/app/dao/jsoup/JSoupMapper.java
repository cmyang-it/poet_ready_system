package com.sinocontact.app.dao.jsoup;

import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.CrawlNovel;
import com.sinocontact.app.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 爬取小说
 * @author caoMingYang
 * @since 2019/5/12 17:14
 */
@Mapper
public interface JSoupMapper {

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
    Integer saveNovelUrl(@Param("novelName")String novelName,
                         @Param("novelAuthor")String novelAuthor,
                         @Param("flUrl")String flUrl,
                         @Param("ddUrl")String ddUrl,
                         @Param("ddChapterUrl")String ddChapterUrl) throws Exception;

    /**
     * 获取待抓取小说信息
     * @return crawlNovelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/12 20:02
     */
    List<CrawlNovel> queryNovelList() throws Exception;

    /**
     * 保存爬取的小说信息
     * @param novel 小说
     * @author caoMingYang
     * @since 2019/5/14 20:40
     */
    Integer saveNovelInfo(Novel novel)throws Exception;

    /**
     * 保存爬取小说扩展信息
     * @param novel 小说信息
     * @author caoMingYang
     * @since 2019/5/14 21:00
     */
    Integer saveNovelExtInfo(Novel novel)throws Exception;

    /**
     * 保存章节信息
     * @param chapterList 章节信息
     * @author caoMingYang
     * @since 2019/5/14 20:43
     */
    Integer saveChapterInfo(List<Chapter> chapterList)throws Exception;

    /**
     * 更新爬取成功小说的状态
     * @param novelId 小说id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/17 8:32
     */
    Integer updateCrawlNovel(@Param("id")Integer novelId)throws Exception;
}
