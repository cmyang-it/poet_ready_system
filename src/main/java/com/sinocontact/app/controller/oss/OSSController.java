package com.sinocontact.app.controller.oss;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.service.oss.OSSService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * OSS 上传
 * @author caoMingYang
 * @Description TODO
 * @since 2019/5/24 13:50
 **/
@Controller
@RequestMapping("/oss")
public class OSSController extends BaseController {
    private static Logger logger = Logger.getLogger(OSSController.class);

    @Autowired
    private OSSService service;

    /**
     * 上传图片
     * @param file 图片文件
     * @return 图片地址
     * @author caoMingYang
     * @since 2019/5/24 15:43
     */
    @ResponseBody
    @RequestMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file){
        JSONObject job = new JSONObject();
        String imgUrl = service.uploadImg(file);
        if (null != imgUrl){
            logger.info("图片阿里云地址"+imgUrl);
            job.put("code",0);
            job.put("msg","success");
            job.put("data",imgUrl);
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        return job.toJSONString();
    }
}
