package com.sinocontact.app.service.systemSetting;

import com.alibaba.fastjson.JSONArray;
import com.sinocontact.app.dao.systemSetting.NovelManageMapper;
import com.sinocontact.app.entity.Novel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 小说管理
 * @author Cao
 * @since 2019/3/16 16:52
 */
@Service
public class NovelManageService {
    private static Logger logger = Logger.getLogger(NovelManageService.class);

    @Autowired
    private NovelManageMapper mapper;

    /**
     * 获取分类信息
     * @return typeMap
     * @author Cao
     * @since 2019/3/24 1:10:34
     */
    public List<String> getNovelType(){
       List<String> typeList = null;
        try{
            typeList = mapper.getNovelType();
        }catch (Exception e){
            logger.error("获取所有分类信息异常",e);
        }
        return typeList;
    }

    /**
     * 获取所有小说信息
     * @param novelStatus 状态
     * @param novelType 类型
     * @param novelInfo 书名、作者
     * @param page 当前页
     * @param pageSize 页数据条数
     * @return novelList
     * @author caoMingYang
     * @since 2019/3/26 22:54
     */
    public List<Novel> getNovelList(Integer novelStatus,String novelType,String novelInfo,Integer page,Integer pageSize){
        Integer currentPage = (page - 1) * pageSize;
        try{
            List<Novel> novelList = mapper.getNovelList(novelStatus, novelType, novelInfo, currentPage, pageSize);
            //处理小说的显示时间格式
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("获取所有小说信息异常",e);
        }
        return null;
    }

    /**
     * 获取小说总条数
     * @return -1 获取失败；
     * @author caoMingYang
     * @since 2019/3/26 22:59
     */
    public Integer getNovelCount(Integer novelStatus,String novelType,String novelInfo){
        try{
            return mapper.getNovelCount(novelStatus, novelType, novelInfo);
        }catch (Exception e){
            logger.error("获取总条数异常",e);
        }
        return -1;
    }

    /**
     * 和谐小说
     * @param idArray id
     * @return -1 失败
     * @author caoMingYang
     * @since 2019/3/29 20:43
     */
    public Integer checkNovelNoPass(String idArray,String reason){
        List<Integer> idList = this.array2List(idArray);
        try {
            return mapper.updateNovelNoPass(idList, reason);
        }catch (Exception e){
            logger.error("和谐小说异常",e);
        }
        return -1;
    }

    /**
     * 上架小说
     * @param idArray id
     * @return -1 失败
     * @author caoMingYang
     * @since 2019/3/29 20:43
     */
    public Integer checkNovelPass(String idArray,String reason){
        List<Integer> idList = this.array2List(idArray);
        try{
            return mapper.updateNovelPass(idList,reason);
        }catch (Exception e){
            logger.error("上架小说异常",e);
        }
        return -1;
    }



    /**
     * id数组转换成List
     * @author todd
     * @since 2019/3/22
     */
    private List<Integer> array2List(String idArray){
        List<Integer> idList = new ArrayList<Integer>();
        try {
            JSONArray jsonIdArray = JSONArray.parseArray(idArray);
            for (int i=0;i<jsonIdArray.size();i++){
                idList.add(jsonIdArray.getIntValue(i));
            }
        }catch (Exception e){
            logger.error("类型转换异常",e);
        }
        return idList;
    }


    /**
     * 处理显示时间格式（yyyy-MM-dd HH:mm:ss）
     * @param novelList 小说集合
     * @return
     * @author caoMingYang
     * @since 2019/3/30 10:03
     */
    private void formatDate(List<Novel> novelList){
        for (Novel novel: novelList){
            if (!StringUtils.isEmpty(novel.getCheckTime())){
                novel.setCheckTime(novel.getCheckTime().replace(".0",""));
            }
            if (!StringUtils.isEmpty(novel.getLastChapterUpdate())){
                novel.setLastChapterUpdate(novel.getLastChapterUpdate().replace(".0",""));
            }
        }
    }
}
