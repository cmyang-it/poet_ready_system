package com.sinocontact.app.service.library;

import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.dao.library.LibraryMapper;
import com.sinocontact.app.entity.Novel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 书库
 * @author caoMingYang
 * @since 2019/4/19 16:51
 */
@Service
public class LibraryService {
    private static final Logger logger = Logger.getLogger(LibraryService.class);

    @Autowired
    private LibraryMapper mapper;

    /**
     * 全部作品页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelType 类型
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 17:33
     */
    public List<Novel> queryTotalNovel(Integer page,Integer sortFlag,String novelType,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalNovelList(currentPage,pageSize,novelType,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("全部作品页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取小说总条数
     * @param sortFlag 排序
     * @param novelType 类型
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/20 9:51
     */
    public Integer getNovelCount(Integer sortFlag,String novelType,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelCount(novelType,sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取小说总条数异常",e);
        }
        return -1;
    }


    /**
     * 都市小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalCityNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalCityNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("都市小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取都市热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryCityHotNovel(){
        try {
            return mapper.queryCityHotNovel();
        }catch (Exception e){
            logger.error("获取都市热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取都市小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelCityCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelCityCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取都市小说总条数异常",e);
        }
        return -1;
    }


    /**
     * 玄幻小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalFantasyNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalFantasyNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("玄幻小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取玄幻热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryFantasyHotNovel(){
        try {
            return mapper.queryFantasyHotNovel();
        }catch (Exception e){
            logger.error("获取玄幻热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取玄幻小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelFantasyCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelFantasyCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取玄幻小说总条数异常",e);
        }
        return -1;
    }

    /**
     * 仙侠小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalFairyNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalFairyNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("仙侠小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取仙侠热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryFairyHotNovel(){
        try {
            return mapper.queryFairyHotNovel();
        }catch (Exception e){
            logger.error("获取仙侠热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取仙侠小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelFairyCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelFairyCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取仙侠小说总条数异常",e);
        }
        return -1;
    }

    /**
     * 科幻小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalScienceFictionNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalScienceFictionNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("科幻小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取科幻热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryScienceFictionHotNovel(){
        try {
            return mapper.queryScienceFictionHotNovel();
        }catch (Exception e){
            logger.error("获取科幻热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取科幻小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelScienceFictionCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelScienceFictionCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取科幻小说总条数异常",e);
        }
        return -1;
    }


    /**
     * 历史军事小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalMilitaryHistoryNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalMilitaryHistoryNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("历史军事小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取历史军事热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryMilitaryHistoryHotNovel(){
        try {
            return mapper.queryMilitaryHistoryHotNovel();
        }catch (Exception e){
            logger.error("获取历史军事热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取历史军事小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelMilitaryHistoryCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelMilitaryHistoryCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取历史军事小说总条数异常",e);
        }
        return -1;
    }

    /**
     * 网游武侠小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalDragonNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalDragonNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("武侠小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取武侠热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryDragonHotNovel(){
        try {
            return mapper.queryDragonHotNovel();
        }catch (Exception e){
            logger.error("获取武侠热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取武侠小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelDragonCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelDragonCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取武侠小说总条数异常",e);
        }
        return -1;
    }

    /**
     * 网游竞技小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalAthleticNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalAthleticNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("网游竞技小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取网游竞技热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryAthleticHotNovel(){
        try {
            return mapper.queryAthleticHotNovel();
        }catch (Exception e){
            logger.error("获取网游竞技热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取网游竞技小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelAthleticCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelAthleticCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取网游竞技小说总条数异常",e);
        }
        return -1;
    }

    /**
     * 悬疑灵异小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalSuspenseParanormalNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalSuspenseParanormalNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("悬疑灵异小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取悬疑灵异热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> querySuspenseParanormalHotNovel(){
        try {
            return mapper.querySuspenseParanormalHotNovel();
        }catch (Exception e){
            logger.error("获取悬疑灵异热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取悬疑灵异小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelSuspenseParanormalCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelSuspenseParanormalCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取悬疑灵异小说总条数异常",e);
        }
        return -1;
    }

    /**
     * 其他小说页面数据
     * @param page 页码
     * @param sortFlag 检索标记
     * @param novelStatus 状态
     * @param novelWords 字数
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:15
     */
    public List<Novel> queryTotalOtherNovel(Integer page,Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer pageSize = CommonConstant.PAGE_SIZE;
        Integer currentPage = (page - 1) * pageSize;
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            List<Novel> novelList = mapper.queryTotalOtherNovelList(currentPage,pageSize,sortFlag,startWords,endWords,novelStatus,novelInfo);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("其他小说页面数据异常",e);
        }
        return new ArrayList<Novel>();
    }


    /**
     * 获取其他热门小说
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/23 23:34
     */
    public List<Novel> queryOtherHotNovel(){
        try {
            return mapper.queryOtherHotNovel();
        }catch (Exception e){
            logger.error("获取其他热门小说异常",e);
        }
        return new ArrayList<Novel>();
    }

    /**
     * 获取其他小说总条数
     * @param sortFlag 排序
     * @param novelStatus 状态
     * @param novelWords 字数
     * @param novelInfo 信息
     * @return 条数
     * @author caoMingYang
     * @since 2019/4/23 23:16
     */
    public Integer getNovelOtherCount(Integer sortFlag,Integer novelStatus,String novelWords,String novelInfo){
        Integer startWords = this.getNovelWords(novelWords,"start");
        Integer endWords = this.getNovelWords(novelWords,"end");
        try{
            return mapper.getNovelOtherCount(sortFlag,startWords,endWords,novelStatus,novelInfo);
        }catch (Exception e){
            logger.error("获取其他小说总条数异常",e);
        }
        return -1;
    }

    /**
     * 获取字数范围
     * @param novelWords 范围描述
     * @param info start 或 end
     * @return 字数
     * @author caoMingYang
     * @since 2019/4/19 17:43
     */
    private Integer getNovelWords(String novelWords,String info){
        if (!StringUtils.isEmpty(novelWords)){
            if ("30万字以下".equals(novelWords)){
                if ("start".equals(info)){
                    return 0;
                }else{
                    return 300000;
                }
            }
            if ("30-50万字".equals(novelWords)){
                if ("start".equals(info)){
                    return 300000;
                }else{
                    return 500000;
                }
            }
            if ("50-100万字".equals(novelWords)){
                if ("start".equals(info)){
                    return 500000;
                }else{
                    return 1000000;
                }
            }
            if ("100-200万字".equals(novelWords)){
                if ("start".equals(info)){
                    return 1000000;
                }else{
                    return 2000000;
                }
            }
            if ("200万字以上".equals(novelWords)){
                if ("start".equals(info)){
                    return 2000000;
                }else{
                    return 100000000;
                }
            }
        }else {
            if ("start".equals(info)) {
                return 0;
            } else {
                return 100000000;
            }
        }
        return 0;
    }


    /**
     * 格式化更新时间
     * @param novelList 小说集合
     * @author caoMingYang
     * @since 2019/4/19 17:50
     */
    private void formatDate(List<Novel> novelList){
        if (null != novelList && novelList.size() > 0){
            for (Novel novel : novelList){
                if (!StringUtils.isEmpty(novel.getLastChapterUpdate())){
                    novel.setLastChapterUpdate(novel.getLastChapterUpdate().replace(".0",""));
                }
            }
        }
    }


}
