package com.sinocontact.app.service.systemSetting;

import com.sinocontact.app.dao.systemSetting.ClassifyManageMapper;
import com.sinocontact.app.entity.NovelType;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.lang.model.type.NoType;
import java.util.List;

/**
 * 分类管理
 * @author Cao
 * @since 2019/3/16 16:54
 */
@Service
public class ClassifyManageService {
    private static Logger logger = Logger.getLogger(ClassifyManageService.class);

    @Autowired
    private ClassifyManageMapper mapper;

    /**
     * 获取所有的分类信息
     * @author Cao
     * @param
     * @since 2019/3/16 18:14
     * @return
     */
    public List<NovelType> queryNovelTypeList(){
        List<NovelType> novelTypeList = mapper.selectNovelTypeList();
        //处理时间格式
        dealWithDate(novelTypeList);
        return novelTypeList;
    }

    /**
     * 获取分类的总条数
     * @author Cao
     * @param
     * @since 2019/3/16 19:28
     * @return 分类条数
     */
    public Integer queryNovelTypeCount(){
        return mapper.selectNovelTypeCount();
    }

    /**
     * 通过ID删除分类信息
     * @author Cao
     * @param id 分类ID
     * @since 2019/3/16 18:16
     * @return false 删除失败 true 删除成功
     */
    public boolean deleteNovelTypeById(Integer id){
        Integer num = mapper.deleteNovelTypeById(id);
        return num > 0 ? true : false;
    }

    /**
     * 更新分类信息
     * @author Cao
     * @param typeName 分类名称
     * @param typeId 分类ID
     * @since 2019/3/16 18:23
     * @return false 更新失败 true 更新成功
     */
    public boolean updateNovelType(String  typeName, Integer typeId){
        NovelType novelType = new NovelType();
        if (StringUtils.isEmpty(typeName)){
            return false;
        }
        novelType.setTypeId(typeId);
        novelType.setTypeName(typeName);
        Integer num = mapper.updateNovelType(novelType);
        return num > 0 ? true : false;
    }

    /**
     * 添加分类信息
     * @author Cao
     * @param typeName 分类信息
     * @since 2019/3/16 18:25
     * @return false 添加失败  true 添加成功
     */
    public boolean addNovelType(String  typeName){
        Integer num = mapper.addNovelType(typeName);
        return num > 0 ? true : false;
    }

    /**
     * 处理时间格式
     * @author Cao
     * @param novelTypeList 分类列表
     * @since 2019/3/16 20:12
     * @return
     */
    private void dealWithDate(List<NovelType> novelTypeList){
        for (NovelType novelType : novelTypeList){
            if (null != novelType.getCreateTime()){
                novelType.setCreateTime(novelType.getCreateTime().replace(".0",""));
            }
            if (null != novelType.getUpdateTime()){
                novelType.setUpdateTime(novelType.getUpdateTime().replace(".0",""));
            }
        }
    }


}
