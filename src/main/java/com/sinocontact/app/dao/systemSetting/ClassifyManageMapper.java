package com.sinocontact.app.dao.systemSetting;

import com.sinocontact.app.entity.NovelType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类管理
 * @author Cao
 * @since 2019/3/16 16:57
 */
@Mapper
public interface ClassifyManageMapper {

    /**
     * 获取所有的小说类型
     * @author Cao
     * @param
     * @since 2019/3/16 18:09
     * @return List<NovelType>
     */
    List<NovelType> selectNovelTypeList();

    /**
     * 获取分类的总条数
     * @author Cao
     * @param
     * @since 2019/3/16 19:26
     * @return 条数
     */
    Integer selectNovelTypeCount();

    /**
     * 更新分类信息
     * @author Cao
     * @param novelType 分类信息
     * @since 2019/3/16 18:27
     * @return
     */
    Integer updateNovelType(NovelType novelType);

    /**
     * 通过id删除分类信息
     * @author Cao
     * @param typeId 分类ID
     * @since 2019/3/16 18:28
     * @return
     */
    Integer deleteNovelTypeById(Integer typeId);

    /**
     * 添加分类信息
     * @author Cao
     * @param typeName 分类名称
     * @since 2019/3/17 14:32
     * @return
     */
    Integer addNovelType(String typeName);
}
