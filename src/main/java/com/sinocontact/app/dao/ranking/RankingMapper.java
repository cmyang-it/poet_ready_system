package com.sinocontact.app.dao.ranking;

import com.sinocontact.app.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 排行榜
 * @author caoMingYang
 * @since 2019/4/15 13:58
 */
@Mapper
public interface RankingMapper {

    /**
     * 分页查询点击排行榜数据
     * @param page 页码
     * @param pageSize 一页数据条数
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/16 13:48
     */
    List<Novel> queryClickNovelLimit(@Param("page")Integer page,
                                     @Param("pageSize")Integer pageSize) throws Exception;

    /**
     * 获取收藏榜数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/18 23:40
     */
    List<Novel> queryCollectList(@Param("page")Integer page,
                                 @Param("pageSize")Integer pageSize) throws Exception;

    /**
     * 获取总排行榜数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    List<Novel> queryTotalNumber(@Param("page")Integer page,
                                 @Param("pageSize")Integer pageSize)throws Exception;

    /**
     * 获取总推荐榜数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    List<Novel> queryRecommendList(@Param("page")Integer page,
                                   @Param("pageSize")Integer pageSize)throws Exception;


    /**
     * 最近更新
     * @param page 页码
     * @param pageSize 页数据条数
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    List<Novel> queryNowUpdate(@Param("page")Integer page,
                               @Param("pageSize")Integer pageSize)throws Exception;



    /**
     * 最新入库
     * @param page 页码
     * @param pageSize 页数据条数
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    List<Novel> queryNowPutStorage(@Param("page")Integer page,
                                   @Param("pageSize")Integer pageSize)throws Exception;



    /**
     * 字数排行
     * @param page 页码
     * @param pageSize 页数据条数
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/19 11:25
     */
    List<Novel> queryWordsList(@Param("page")Integer page,
                               @Param("pageSize")Integer pageSize)throws Exception;



    /**
     * 获取小说总条数
     * @return 总条数
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/17 0:09
     */
    int queryNovelCount()throws Exception;
}
