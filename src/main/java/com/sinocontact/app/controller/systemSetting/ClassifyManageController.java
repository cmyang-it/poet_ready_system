package com.sinocontact.app.controller.systemSetting;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.entity.NovelType;
import com.sinocontact.app.service.systemSetting.ClassifyManageService;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 分类管理
 * @author Cao
 * @since 2019/3/16 16:58
 */
@Controller
@RequestMapping("/classifyManage")
public class ClassifyManageController {
    private static Logger logger = Logger.getLogger(ClassifyManageService.class);

    @Autowired
    private ClassifyManageService service;

    /**
     * 跳转到分类列表页
     */
    @RequestMapping("/classifyList")
    public String classifyList(){
        return "views/systemSetting/classifyManage/classifyList";
    }

    /**
     * 获取分类列表
     * @author Cao
     * @param
     * @since 2019/3/16 18:47
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/novelTypeList",produces = "application/json;charset=utf-8")
    public String getNovelTypes(){
        JSONObject job = new JSONObject();
        //分类列表
        List<NovelType> novelTypes = service.queryNovelTypeList();
        //分类条数
        Integer total = service.queryNovelTypeCount();
        job.put("code",0);
        job.put("msg","success");
        job.put("count",total);
        job.put("data",novelTypes);
        String jsonText = job.toJSONString();
        logger.info("分类列表获取成功>>>>>>>>"+jsonText);
        return jsonText;
    }

    /**
     * 删除分类信息
     * @author Cao
     * @param typeId 分类id
     * @since 2019/3/17 13:47
     * @return
     */
    @ResponseBody
    @RequestMapping("/delType")
    public String delNovelType(@RequestParam("typeId")String typeId){
        JSONObject job = new JSONObject();
        Integer id = Integer.valueOf(typeId);
        boolean flag = service.deleteNovelTypeById(id);
        String jsonText = setJson(job,flag);
        logger.info("删除分类信息>>>>>>"+jsonText);
        return jsonText;
    }

    /**
     * 添加分类信息
     * @author Cao
     * @param typeName 分类名称
     * @since 2019/3/17 13:47
     * @return
     */
    @ResponseBody
    @RequestMapping("/addType")
    public String addNovelType(@RequestParam("typeName")String typeName){
        JSONObject job = new JSONObject();
        boolean flag = service.addNovelType(typeName);
        String jsonText = setJson(job,flag);
        logger.info("添加分类信息>>>>>>"+jsonText);
        return jsonText;
    }

    /**
     * 更新分类信息
     * @author Cao
     * @param typeName 分类名称
     * @since 2019/3/17 13:47
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateType")
    public String addNovelType(@RequestParam(value = "typeName",defaultValue = "")String typeName,@RequestParam(value = "typeId",defaultValue = "0")Integer typeId){
        JSONObject job = new JSONObject();
        boolean flag = service.updateNovelType(typeName,typeId);
        String jsonText = setJson(job,flag);
        logger.info("更新分类信息>>>>>>"+jsonText);
        return jsonText;
    }


    /**
     * 获取json字符串
     * @author Cao
     * @param job jsonObject  flag true or false
     * @since 2019/3/17 14:41
     * @return
     */
    private String setJson(JSONObject job,boolean flag){
        if (!flag){
            job.put("code",-1);
            job.put("msg","fail");
        }
        job.put("code",0);
        job.put("msg","success");
        return job.toJSONString();
    }
}
