package com.sinocontact.app.service.oss;

import com.sinocontact.app.utils.OSSUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * oss 上传
 * @author caoMingYang
 * @Description TODO
 * @since 2019/5/24 15:28
 **/
@Service
public class OSSService {
    private static Logger logger = Logger.getLogger(OSSService.class);


    /**
     * 上传图片
     * @param file
     * @return 图片路径
     * @author caoMingYang
     * @since 2019/5/24 15:42
     */
    public String uploadImg(MultipartFile file){
        try{
            return OSSUtils.uploadImg(file);
        }catch (Exception e){
            logger.error("上传图片异常",e);
        }
        return null;
    }



}
