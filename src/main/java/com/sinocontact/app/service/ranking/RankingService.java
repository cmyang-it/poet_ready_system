package com.sinocontact.app.service.ranking;

import com.sinocontact.app.common.CommonConstant;
import com.sinocontact.app.dao.ranking.RankingMapper;
import com.sinocontact.app.entity.Novel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 排行榜
 * @author caoMingYang
 * @since 2019/4/15 13:58
 */
@Service
public class RankingService {
    private static Logger logger = Logger.getLogger(RankingService.class);

    @Autowired
    private RankingMapper mapper;


    /**
     * 分页查询点击排行榜数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/16 13:48
     */
    public List<Novel> queryNovelListByPage(Integer page){
        List<Novel> novelList = null;
        Integer pageSize = CommonConstant.PAGE_SIZE;
        try{
            Integer currentPage = (page - 1) * pageSize;
            novelList = mapper.queryClickNovelLimit(currentPage,pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("分页查询点击排行榜数据异常",e);
        }
        return novelList;
    }

    /**
     * 获取总收藏榜数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/18 23:40
     */
    public List<Novel> queryCollectNovelList(Integer page){
        List<Novel> novelList = null;
        Integer pageSize = CommonConstant.PAGE_SIZE;
        try{
            Integer currentPage = (page - 1) * pageSize;
            novelList = mapper.queryCollectList(currentPage,pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("获取总收藏榜数据异常",e);
        }
        return novelList;
    }

    /**
     * 获取总排行榜数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    public List<Novel> queryTotalNumber(Integer page){
        List<Novel> novelList = null;
        Integer pageSize = CommonConstant.PAGE_SIZE;
        try{
            Integer currentPage = (page - 1) * pageSize;
            novelList = mapper.queryTotalNumber(currentPage,pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("获取总排行榜数据异常",e);
        }
        return novelList;
    }

    /**
     * 获取总推荐榜数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    public List<Novel> queryRecommendList(Integer page){
        List<Novel> novelList = null;
        Integer pageSize = CommonConstant.PAGE_SIZE;
        try{
            Integer currentPage = (page - 1) * pageSize;
            novelList = mapper.queryRecommendList(currentPage,pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("获取总推荐榜数据异常",e);
        }
        return novelList;
    }

    /**
     * 获取最近更新数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    public List<Novel> queryNowUpdate(Integer page){
        List<Novel> novelList = null;
        Integer pageSize = CommonConstant.PAGE_SIZE;
        try{
            Integer currentPage = (page - 1) * pageSize;
            novelList = mapper.queryNowUpdate(currentPage,pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("获取最近更新数据异常",e);
        }
        return novelList;
    }

    /**
     * 获取最新入库数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    public List<Novel> queryNowPutStorage(Integer page){
        List<Novel> novelList = null;
        Integer pageSize = CommonConstant.PAGE_SIZE;
        try{
            Integer currentPage = (page - 1) * pageSize;
            novelList = mapper.queryNowPutStorage(currentPage,pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("获取最新入库数据异常",e);
        }
        return novelList;
    }

    /**
     * 获取字数排行数据
     * @param page 页码
     * @return novelList
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    public List<Novel> queryWordsList(Integer page){
        List<Novel> novelList = null;
        Integer pageSize = CommonConstant.PAGE_SIZE;
        try{
            Integer currentPage = (page - 1) * pageSize;
            novelList = mapper.queryWordsList(currentPage,pageSize);
            this.formatDate(novelList);
        }catch (Exception e){
            logger.error("获取字数排行数据异常",e);
        }
        return novelList;
    }

    /**
     * 获取小说总条数
     * @return 总条数
     * @author caoMingYang
     * @since 2019/4/17 0:09
     */
    public Integer queryNovelCount(){
        int count = 0;
        try {
            count = mapper.queryNovelCount();
        }catch (Exception e){
            logger.error("获取小说总条数异常",e);
        }
        return count;
    }

    /**
     * 格式化时间
     * @param novelList 小说集合
     * @author caoMingYang
     * @since 2019/4/16 14:44
     */
    private void formatDate(List<Novel> novelList){
        for (Novel novel : novelList){
            if (!StringUtils.isEmpty(novel.getLastChapterUpdate())){
                novel.setLastChapterUpdate(novel.getLastChapterUpdate().replace(".0",""));
            }
        }
    }
}
