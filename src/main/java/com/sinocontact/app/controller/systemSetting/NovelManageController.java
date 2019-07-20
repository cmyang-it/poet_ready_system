package com.sinocontact.app.controller.systemSetting;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.Novel;
import com.sinocontact.app.service.systemSetting.NovelManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小说管理
 * @author Cao
 * @since 2019/3/16 12:19
 */
@Controller
@RequestMapping("/novelManage")
public class NovelManageController extends BaseController {
    private static Logger logger = Logger.getLogger(NovelManageController.class);

    @Autowired
    private NovelManageService service;

    /**
     * 跳转到小说管理页面
     * @author cao
     * @since 2019/03/23 17:28:45
     */
    @RequestMapping("/index")
    public String index(){
        return "views/systemSetting/novelManage/novelList";
    }


    /**
     * 获取分类下拉数据
     * @author cao
     * @since 2019/03/24 1:01:23
     */
    @ResponseBody
    @RequestMapping(value = "/selectData",produces = "application/json;charset=utf-8")
    public String getSelectData(){
        JSONObject job = new JSONObject();
        List<String> typeList = service.getNovelType();
        if (null == typeList){
            job.put("code",-1);
            job.put("msg","fail");
        }else{
            job.put("code",0);
            job.put("msg","success");
            job.put("data",typeList);
        }
        String jsonText = job.toJSONString();
        logger.info("获取分类下拉框数据》》》》"+jsonText);
        return jsonText;
    }


    /**
     * 获取所有小说信息
     * @param novelStatus 状态
     * @param novelType 类型
     * @param novelInfo 作者或书名
     * @author cao
     * @since 2019/03/24 22:24:44
     */
    @ResponseBody
    @RequestMapping("/novelList")
    public String novelList(@RequestParam(value = "novelStatus",defaultValue = "0")Integer novelStatus,
                            @RequestParam(value = "novelType",defaultValue = "")String novelType,
                            @RequestParam(value = "novelInfo",defaultValue = "")String novelInfo,
                            @RequestParam(value = "page",defaultValue = "1")Integer page,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        logger.info("获取所有小说信息》》》novelStatus>>"+novelStatus+",novelType>>"+novelType+",novelInfo>>"+novelInfo+",page>>"+page+",pageSize>>"+pageSize);
        //获取所有小说信息
        List<Novel> novels = service.getNovelList(novelStatus,novelType,novelInfo,page,pageSize);
        //获取总条数
        Integer count = service.getNovelCount(novelStatus,novelType,novelInfo);
        JSONObject job = new JSONObject();
        job.put("code",0);
        job.put("msg","success");
        job.put("count",count);
        job.put("data",novels);
        String jsonText = job.toJSONString();
        logger.info(jsonText);
        return jsonText;
    }

    /**
     * 上架小说
     * @param novelIdArray 小说id
     * @return java.lang.String
     * @author caoMingYang
     * @since 2019/3/27 22:40
     */
    @ResponseBody
    @RequestMapping(value = "checkNovelPass",produces = "application/json;charset=utf-8")
    public String checkNovelPass(@RequestParam("novelIdArray")String novelIdArray,@RequestParam("reason")String reason){
        Integer result = service.checkNovelPass(novelIdArray,reason);
        JSONObject jsonObject = new JSONObject();
        if (result == -1){
            jsonObject.put("code",-1);
            jsonObject.put("msg","fail");
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
        }
        String jsonText = jsonObject.toJSONString();
        logger.info("上架小说>>>>>>>"+jsonText);
        return jsonText;
    }

    /**
     * 和谐小说
     * @param novelIdArray 小说id
     * @return java.lang.String
     * @author caoMingYang
     * @since 2019/3/29 20:40
     */
    @ResponseBody
    @RequestMapping(value = "/checkNovelNoPass",produces = "application/json;charset=utf-8")
    public String checkNovelNoPass(@RequestParam("novelIdArray")String novelIdArray,@RequestParam("reason")String reason){
        Integer result = service.checkNovelNoPass(novelIdArray,reason);
        JSONObject jsonObject = new JSONObject();
        if (result == -1){
            jsonObject.put("code",-1);
            jsonObject.put("msg","fail");
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
        }
        String jsonText = jsonObject.toJSONString();
        logger.info("和谐小说>>>>>>>"+jsonText);
        return jsonText;
    }
}
