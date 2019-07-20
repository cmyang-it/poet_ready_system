package com.sinocontact.app.service.memberCenter;

import com.sinocontact.app.dao.memberCenter.BookCaseMapper;
import com.sinocontact.app.entity.BookCaseInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 书架
 * @author caoMingYang
 * @since 2019/4/14 22:46
 */
@Service
public class BookCaseService {
    private static Logger logger = Logger.getLogger(BookCaseService.class);

    @Autowired
    private BookCaseMapper mapper;

    /**
     * 获取用户书架信息
     * @param userId 用户id
     * @return BookCaseInfoList
     * @author caoMingYang
     * @since 2019/4/14 22:48
     */
    public List<BookCaseInfo> queryBookCaseList(Integer userId){
        try {
            List<BookCaseInfo> bookCaseInfoList = mapper.queryBookCaseList(userId);
            this.formatDate(bookCaseInfoList);
            return bookCaseInfoList;
        }catch (Exception e){
            logger.error("获取用户书架信息异常",e);
        }
        return null;
    }

    /**
     * 删除书架中小说
     * @param userId 用户id
     * @param novelId 小说id
     * @return true 成功，false 失败
     * @author caoMingYang
     * @since 2019/4/15 10:44
     */
    public boolean deleteNovel(Integer userId,Integer novelId){
        try{
            Integer num = mapper.deleteNovelById(userId,novelId);
            return num > 0;
        }catch (Exception e){
            logger.error("删除书架中小说异常",e);
        }
        return false;
    }

    /**
     * 格式化时间
     * @param bookCaseInfoList 参数
     */
    private void formatDate(List<BookCaseInfo> bookCaseInfoList){
        for (BookCaseInfo bookCaseInfo : bookCaseInfoList){
            if (!StringUtils.isEmpty(bookCaseInfo.getUpdateTime())){
                bookCaseInfo.setUpdateTime(bookCaseInfo.getUpdateTime().replace(".0",""));
            }
            bookCaseInfo.setCreateTime(bookCaseInfo.getCreateTime().replace(".0",""));
        }
    }
}
