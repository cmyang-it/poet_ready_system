package com.sinocontact.app.dao.authorCenter;

import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作者中心
 */
@Mapper
public interface AuthorCenterMapper {

    /**
     * 通过用户id获取用户创作的小说
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 一页数据条数
     * @throws Exception
     */
    List<Novel> queryNovelList(@Param("userId")Integer userId,
                               @Param("page")Integer page,
                               @Param("pageSize")Integer pageSize)throws Exception;

    /**
     * 获取用户创作的小说总条数
     * @param userId 用户id
     * @return 条数
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/20 23:04
     */
    Integer queryNovelCount(@Param("userId")Integer userId) throws Exception;


    /**
     * 通过id获取小说信息
     * @param novelId 小说id
     * @return novel
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/21 21:30
     */
    Novel queryNovelInfo(@Param("novelId")Integer novelId) throws Exception;

    /**
     * 校验小说名称是否重复
     * @param novelId 小说id
     * @param novelName 小说名称
     * @return 大于0 重复
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/24 21:30
     */
    Integer checkNovelName(@Param("novelId")Integer novelId,
                           @Param("novelName")String novelName)throws Exception;

    /**
     * 更新小说信息
     * @param novel 小说
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/24 21:42
     */
    Integer updateNovel(@Param("novel") Novel novel) throws Exception;

    /**
     * 保存小说信息
     * @param novel 小说
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/24 21:42
     */
    Integer saveNovel(Novel novel) throws Exception;

    /**
     * 保存小说扩展表信息
     * @param novelId 小说id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/24 23:54
     */
    Integer saveNovelExt(@Param("novelId")Integer novelId) throws Exception;


    /**
     * 保存作者小说关联表信息
     * @param novelId 小说id
     * @param userId 用户id
     * @throws Exception
     * @author caoMingYang
     * @since 2019/5/25 0:00
     */
    Integer saveAuthorNovel(@Param("novelId")Integer novelId,
                            @Param("userId")Integer userId) throws Exception;


    Integer saveChapter(Chapter chapter) throws Exception;

    Integer queryNovelWords(@Param("novelId")Integer novelId) throws Exception;

    Integer updateLastChapter(@Param("chapter")Chapter chapter,
                              @Param("novelId")Integer novelId,
                              @Param("words")Integer words) throws Exception;


    String queryChapterNum(@Param("novelId")Integer novelId)throws Exception;

}
