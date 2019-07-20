package com.sinocontact.app.dao.library;

import com.sinocontact.app.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书库
 * @author caoMingYang
 * @since 2019/4/19 16:52
 */
@Mapper
public interface LibraryMapper {

    /**
     * 获取全部作品页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param novelType 类型
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/19 17:28
     */
    List<Novel> queryTotalNovelList(@Param("page")Integer page,
                                    @Param("pageSize")Integer pageSize,
                                    @Param("novelType")String novelType,
                                    @Param("sortFlag")Integer sortFlag,
                                    @Param("start")Integer startWords,
                                    @Param("end")Integer endWords,
                                    @Param("novelStatus")Integer novelStatus,
                                    @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取小说总条数
     * @param novelInfo 书名、作者
     * @param novelType 类型
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/19 17:28
     */
    Integer getNovelCount(@Param("novelType")String novelType,
                          @Param("sortFlag")Integer sortFlag,
                          @Param("start")Integer startWords,
                          @Param("end")Integer endWords,
                          @Param("novelStatus")Integer novelStatus,
                          @Param("novelInfo")String novelInfo)throws Exception;


    /**
     * 获取都市小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:17
     */
    List<Novel> queryTotalCityNovelList(@Param("page")Integer page,
                                    @Param("pageSize")Integer pageSize,
                                    @Param("sortFlag")Integer sortFlag,
                                    @Param("start")Integer startWords,
                                    @Param("end")Integer endWords,
                                    @Param("novelStatus")Integer novelStatus,
                                    @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取都市小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:17
     */
    Integer getNovelCityCount(@Param("sortFlag")Integer sortFlag,
                              @Param("start")Integer startWords,
                              @Param("end")Integer endWords,
                              @Param("novelStatus")Integer novelStatus,
                              @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取都市热门小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23  23:32
     */
    List<Novel> queryCityHotNovel()throws Exception;


    /**
     * 玄幻热门小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryFantasyHotNovel()throws Exception;

    /**
     * 获取玄幻小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalFantasyNovelList(@Param("page")Integer page,
                                           @Param("pageSize")Integer pageSize,
                                           @Param("sortFlag")Integer sortFlag,
                                           @Param("start")Integer startWords,
                                           @Param("end")Integer endWords,
                                           @Param("novelStatus")Integer novelStatus,
                                           @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取玄幻小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelFantasyCount(@Param("sortFlag")Integer sortFlag,
                                 @Param("start")Integer startWords,
                                 @Param("end")Integer endWords,
                                 @Param("novelStatus")Integer novelStatus,
                                 @Param("novelInfo")String novelInfo)throws Exception;


    /**
     * 仙侠热门小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryFairyHotNovel()throws Exception;

    /**
     * 获取仙侠小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalFairyNovelList(@Param("page")Integer page,
                                         @Param("pageSize")Integer pageSize,
                                         @Param("sortFlag")Integer sortFlag,
                                         @Param("start")Integer startWords,
                                         @Param("end")Integer endWords,
                                         @Param("novelStatus")Integer novelStatus,
                                         @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取仙侠小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelFairyCount(@Param("sortFlag")Integer sortFlag,
                               @Param("start")Integer startWords,
                               @Param("end")Integer endWords,
                               @Param("novelStatus")Integer novelStatus,
                               @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 科幻热门小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryScienceFictionHotNovel()throws Exception;

    /**
     * 获取科幻小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalScienceFictionNovelList(@Param("page")Integer page,
                                         @Param("pageSize")Integer pageSize,
                                         @Param("sortFlag")Integer sortFlag,
                                         @Param("start")Integer startWords,
                                         @Param("end")Integer endWords,
                                         @Param("novelStatus")Integer novelStatus,
                                         @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取科幻小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelScienceFictionCount(@Param("sortFlag")Integer sortFlag,
                               @Param("start")Integer startWords,
                               @Param("end")Integer endWords,
                               @Param("novelStatus")Integer novelStatus,
                               @Param("novelInfo")String novelInfo)throws Exception;


    /**
     * 历史军事热门小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryMilitaryHistoryHotNovel()throws Exception;

    /**
     * 获取历史军事小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalMilitaryHistoryNovelList(@Param("page")Integer page,
                                                  @Param("pageSize")Integer pageSize,
                                                  @Param("sortFlag")Integer sortFlag,
                                                  @Param("start")Integer startWords,
                                                  @Param("end")Integer endWords,
                                                  @Param("novelStatus")Integer novelStatus,
                                                  @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取历史军事小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelMilitaryHistoryCount(@Param("sortFlag")Integer sortFlag,
                                        @Param("start")Integer startWords,
                                        @Param("end")Integer endWords,
                                        @Param("novelStatus")Integer novelStatus,
                                        @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 武侠小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryDragonHotNovel()throws Exception;

    /**
     * 获取武侠小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalDragonNovelList(@Param("page")Integer page,
                                         @Param("pageSize")Integer pageSize,
                                         @Param("sortFlag")Integer sortFlag,
                                         @Param("start")Integer startWords,
                                         @Param("end")Integer endWords,
                                         @Param("novelStatus")Integer novelStatus,
                                         @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取武侠小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelDragonCount(@Param("sortFlag")Integer sortFlag,
                               @Param("start")Integer startWords,
                               @Param("end")Integer endWords,
                               @Param("novelStatus")Integer novelStatus,
                               @Param("novelInfo")String novelInfo)throws Exception;


    /**
     * 网游竞技小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryAthleticHotNovel()throws Exception;

    /**
     * 获取网游竞技小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalAthleticNovelList(@Param("page")Integer page,
                                                   @Param("pageSize")Integer pageSize,
                                                   @Param("sortFlag")Integer sortFlag,
                                                   @Param("start")Integer startWords,
                                                   @Param("end")Integer endWords,
                                                   @Param("novelStatus")Integer novelStatus,
                                                   @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取网游竞技小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelAthleticCount(@Param("sortFlag")Integer sortFlag,
                                         @Param("start")Integer startWords,
                                         @Param("end")Integer endWords,
                                         @Param("novelStatus")Integer novelStatus,
                                         @Param("novelInfo")String novelInfo)throws Exception;


    /**
     * 其他小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> querySuspenseParanormalHotNovel()throws Exception;

    /**
     * 获取其他小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalSuspenseParanormalNovelList(@Param("page")Integer page,
                                            @Param("pageSize")Integer pageSize,
                                            @Param("sortFlag")Integer sortFlag,
                                            @Param("start")Integer startWords,
                                            @Param("end")Integer endWords,
                                            @Param("novelStatus")Integer novelStatus,
                                            @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取其他小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelSuspenseParanormalCount(@Param("sortFlag")Integer sortFlag,
                                  @Param("start")Integer startWords,
                                  @Param("end")Integer endWords,
                                  @Param("novelStatus")Integer novelStatus,
                                  @Param("novelInfo")String novelInfo)throws Exception;


    /**
     * 其他小说
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryOtherHotNovel()throws Exception;

    /**
     * 获取其他小说页面数据
     * @param page 页码
     * @param pageSize 页数据条数
     * @param sortFlag 检索标记
     * @param startWords 开始字数
     * @param endWords 结束字数
     * @param novelStatus 状态
     * @return novelList
     * @throws Exception
     * @author caoMingYang
     * @since 2019/4/23 23:50
     */
    List<Novel> queryTotalOtherNovelList(@Param("page")Integer page,
                                            @Param("pageSize")Integer pageSize,
                                            @Param("sortFlag")Integer sortFlag,
                                            @Param("start")Integer startWords,
                                            @Param("end")Integer endWords,
                                            @Param("novelStatus")Integer novelStatus,
                                            @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 获取其他小说总条数
     * @param novelInfo 书名、作者
     * @param endWords 结束字数
     * @param novelStatus  状态
     * @param sortFlag 排序
     * @param startWords 开始字数
     * @return java.lang.Integer
     * @author caoMingYang
     * @since 2019/4/23 23:51
     */
    Integer getNovelOtherCount(@Param("sortFlag")Integer sortFlag,
                                  @Param("start")Integer startWords,
                                  @Param("end")Integer endWords,
                                  @Param("novelStatus")Integer novelStatus,
                                  @Param("novelInfo")String novelInfo)throws Exception;

}
