package com.sinocontact.app.dao.systemSetting;

import com.sinocontact.app.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 小说管理
 * @author Cao
 * @since 2019/3/16 12:16
 */
@Mapper
public interface NovelManageMapper {

    /**
     * 获取所以分类信息
     * @author cao
     * @since 2019/03/24 1:00
     * @return 分类信息
     * @throws Exception
     */
    List<String> getNovelType()throws Exception;

    /**
     * 获取所有的小说信息
     * @param novelStatus 状态
     * @param novelType 类型
     * @param novelInfo 作者或书名
     * @param page 当前页
     * @param pageSize 每页数据条数
     * @return 小说集合
     * @throws Exception
     * @author caoMingYang
     * @since 2019/3/26 22:42
     */
    List<Novel> getNovelList(@Param("novelStatus")Integer novelStatus,
                             @Param("novelType")String novelType,
                             @Param("novelInfo")String novelInfo,
                             @Param("page")Integer page,
                             @Param("pageSize")Integer pageSize)throws Exception;


    /**
     * 获取小说总条数
     * @author caoMingYang
     * @since 2019/3/26 23:02
     */
    Integer getNovelCount(@Param("novelStatus")Integer novelStatus,
                          @Param("novelType")String novelType,
                          @Param("novelInfo")String novelInfo)throws Exception;

    /**
     * 批量和谐小说
     * @param reason 和谐理由
     * @param idList 小说id数组
     * @return 受影响行数
     * @author caoMingYang
     * @since 2019/3/30 9:52
     */
    Integer updateNovelNoPass(@Param("idList")List idList,@Param("reason")String reason)throws Exception;


    /**
     * 批量上架小说
     * @param reason 上架理由
     * @param idList 小说id数组
     * @return 受影响行数
     * @author caoMingYang
     * @since 2019/3/30 9:52
     */
    Integer updateNovelPass(@Param("idList")List idList,@Param("reason")String reason)throws Exception;
}
