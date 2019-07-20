package com.sinocontact.app.service.authorCenter;

import com.sinocontact.app.dao.authorCenter.AuthorCenterMapper;
import com.sinocontact.app.entity.Chapter;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.entity.User;
import com.sinocontact.app.utils.PropertyUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 作者中心
 * @author caoMingYang
 * @Description TODO
 * @since 2019/5/20 14:04
 **/
@Service
public class AuthorCenterService {

    private static Logger logger = Logger.getLogger(AuthorCenterService.class);

    private static String DEFAULT_NOVEL_COVER = PropertyUtils.getProperty("default_novel_cover");

    @Autowired
    private AuthorCenterMapper mapper;

    /**
     * 获取小说集合
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 一页数据条数
     * @return novelList
     * @author caoMingYang
     * @since 2019/5/20 23:08
     */
    public List<Novel> queryNovelList(Integer userId,Integer page,Integer pageSize){
        Integer currentPage = (page - 1) * pageSize;
        try{
            List<Novel> novelList =  mapper.queryNovelList(userId,currentPage,pageSize);
            this.formatDate(novelList);
            return novelList;
        }catch (Exception e){
            logger.error("获取用户创作小说列表异常",e);
        }
        return null;
    }

    /**
     * 获取小说创作总条数
     * @param userId 用户id
     * @return 条数
     * @author caoMingYang
     * @since 2019/5/20 23:09
     */
    public Integer queryNovelCount(Integer userId){
        try{
            return mapper.queryNovelCount(userId);
        }catch (Exception e){
            logger.error("获取用户创作小说总条数异常",e);
        }
        return 0;
    }

    /**
     * 通过id获取小说信息
     * @param novelId 小说id
     * @return novel
     * @author caoMingYang
     * @since 2019/5/21 21:32
     */
    public Novel queryNovelInfo(Integer novelId){
        try{
            return mapper.queryNovelInfo(novelId);
        }catch (Exception e){
            logger.error("通过id获取小说信息异常",e);
        }
         return null;
    }


    /**
     * 校验小说名称是否重复
     * @param novelId 小说id
     * @param novelName 小说名称
     * @return true 不重复， false 重复
     * @author caoMingYang
     * @since 2019/5/24 21:30
     */
    public boolean checkNovelName(Integer novelId,String novelName){
        try{
            Integer num = mapper.checkNovelName(novelId, novelName);
            if (num == 0){
                return true;
            }
        }catch (Exception e){
            logger.error("校验小说名称异常",e);
        }
        return false;
    }


    /**
     * 更新小说基本信息
     * @param novelId 小说id
     * @param novelName 小说名称
     * @param novelTypeStr 类型
     * @param novelInfo 简介
     * @param novelImg 封面
     * @param keyword 关键字
     * @param status 状态
     * @author caoMingYang
     * @since 2019/5/24 21:41
     */
    public boolean updateNovel(Integer novelId,String novelName,String novelImg,String novelInfo,String novelTypeStr,String keyword,Integer status){
        Novel novel = this.getNovel(novelId,novelImg,novelName,novelInfo,novelTypeStr,keyword,status);
        try{
            Integer num =  mapper.updateNovel(novel);
            if (num > 0){
                return true;
            }
        }catch (Exception e){
            logger.error("更新小说信息异常",e);
        }
        return false;
    }

    /**
     * 保存小说信息
     * @param user 用户
     * @param novelName 小说名称
     * @param novelImg 封面
     * @param novelInfo 简介
     * @param novelTypeStr 类型
     * @param keyword 关键字
     * @param status 状态
     * @return 大于0 成功，失败
     * @author caoMingYang
     * @since 2019/5/25 0:01
     */
    @Transactional(rollbackFor=Exception.class)
    public Integer saveNovel(User user, String novelName, String novelImg, String novelInfo, String novelTypeStr, String keyword, Integer status)throws Exception{
        Novel novel = this.getNovel(0,novelImg,novelName,novelInfo,novelTypeStr,keyword,status);
        novel.setNovelAuthor(user.getNickname());
        //保存小说信息
        Integer n1 = mapper.saveNovel(novel);
        //保存小说扩展信息
        Integer n2 = mapper.saveNovelExt(novel.getNovelId());
        //保存作者创作小说信息
        Integer n3 = mapper.saveAuthorNovel(novel.getNovelId(),user.getUserId());
        if (n1 > 0 && n2 > 0 && n3 > 0){
            return novel.getNovelId();
        }
        return 0;
    }

    /**
     * 保存章节信息
     * @author caoMingYang
     * @since 2019/5/25 1:36
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer saveChapter(String chapterNum,String chapterTitle,String chapterContent,Integer novelId)throws Exception{
        Chapter chapter = this.getChapter(chapterNum,chapterTitle,chapterContent,novelId);
        Integer n1 = mapper.saveChapter(chapter);
        Integer n2 = mapper.queryNovelWords(novelId);
        Integer novelWords = n2 + this.getWords(chapterContent);
        Integer n3 = mapper.updateLastChapter(chapter,novelId,novelWords);
        if (n1 > 0 && n3 > 0){
            return Integer.valueOf(chapterNum.trim().substring(1,chapterNum.trim().indexOf("章")))+1;
        }
        return 0;
    }

    public Integer queryChapterNum(Integer novelId){
        try{
            String chapterNumStr = mapper.queryChapterNum(novelId);
            return Integer.valueOf(chapterNumStr.trim().substring(1,chapterNumStr.trim().indexOf("章")))+1;
        }catch (Exception e){
            logger.error("",e);
        }
        return 0;
    }

    private Chapter getChapter(String chapterNum,String chapterTitle,String chapterContent,Integer novelId){
        Chapter chapter = new Chapter();
        chapter.setChapterName(chapterNum+"  "+chapterTitle);
        chapter.setNovelId(novelId);
        chapter.setChapterContent(chapterContent);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        chapter.setCreateTime(format.format(new Date()));
        return chapter;
    }

    private Integer getWords(String chapterContent){
        if (!StringUtils.isEmpty(chapterContent)){
            String content1 = chapterContent.replace(".","");
            content1 = content1.replace("。","");
            content1 = content1.replace(",","");
            content1 = content1.replace("，","");
            return content1.trim().length();
        }
        return 0;
    }

    /**
     * 设置小说参数
     * @param novelId 小说id
     * @param novelName 小说名称
     * @param novelTypeStr 类型
     * @param novelInfo 简介
     * @param novelImg 封面
     * @param keyword 关键字
     * @param status 状态
     * @return novel
     * @author caoMingYang
     * @since 2019/5/24 21:51
     */
    private Novel getNovel(Integer novelId,String novelImg,String novelName,String novelInfo,String novelTypeStr,String keyword,Integer status){
        Novel novel = new Novel();
        if (novelId > 0) {
            novel.setNovelId(novelId);
        }
        if (!StringUtils.isEmpty(novelImg)){
            novel.setNovelImg(novelImg);
        }else{
            novel.setNovelImg(DEFAULT_NOVEL_COVER);
        }
        novel.setNovelName(novelName);
        novel.setNovelInfo(novelInfo);
        novel.setKeyword(keyword);
        novel.setStatus(status);
        novel.setNovelTypeStr(novelTypeStr);
        this.setNovelType(novel);
        return novel;
    }


    /**
     * 格式化时间
     * @param novelList 小说集合
     * @author caoMingYang
     * @since 2019/5/20 23:07
     */
    private void formatDate(List<Novel> novelList){
        if (null != novelList){
            for (Novel novel : novelList){
                if (!StringUtils.isEmpty(novel.getLastChapterUpdate())){
                    novel.setLastChapterUpdate(novel.getLastChapterUpdate().replace(".0",""));
                }
                novel.setCreateTime(novel.getCreateTime().replace(".0",""));
            }
        }
    }

    /**
     * 设置小说类型
     * @param novel 小说
     * @author caoMingYang
     * @since 2019/5/16 22:13
     */
    private void setNovelType(Novel novel){
        if (null != novel && !StringUtils.isEmpty(novel.getNovelTypeStr())){
            if (novel.getNovelTypeStr().contains("都市")){
                novel.setNovelType(1);
            }else if (novel.getNovelTypeStr().contains("玄幻")){
                novel.setNovelType(2);
            }else if (novel.getNovelTypeStr().contains("科幻")){
                novel.setNovelType(3);
            }else if (novel.getNovelTypeStr().contains("历史") || novel.getNovelTypeStr().contains("军事")){
                novel.setNovelType(4);
            }else if (novel.getNovelTypeStr().contains("武侠")){
                novel.setNovelType(5);
            }else if (novel.getNovelTypeStr().contains("网游")){
                novel.setNovelType(6);
            }else if (novel.getNovelTypeStr().contains("悬疑") || novel.getNovelTypeStr().contains("灵异")){
                novel.setNovelType(7);
            }else if (novel.getNovelTypeStr().contains("仙侠")){
                novel.setNovelType(9);
            }else if (novel.getNovelTypeStr().contains("其他")){
                novel.setNovelType(10);
            }

        }
    }
}
