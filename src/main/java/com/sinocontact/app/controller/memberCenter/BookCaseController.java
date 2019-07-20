package com.sinocontact.app.controller.memberCenter;

import com.alibaba.fastjson.JSONObject;
import com.sinocontact.app.controller.BaseController;
import com.sinocontact.app.entity.BookCaseInfo;
import com.sinocontact.app.service.memberCenter.BookCaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 书架
 * @author caoMingYang
 * @since 2019/4/14 21:07
 */
@Controller
@RequestMapping("/bookCase")
public class BookCaseController extends BaseController {

    private static Logger logger = Logger.getLogger(BookCaseController.class);

    @Autowired
    private BookCaseService service;


    /**
     * 书架首页
     * @author caoMingYang
     * @since 2019/4/14 21:09
     */
    @RequestMapping("/index")
    public String index(){
        Integer userId = this.getUserInfo().getUserId();
        List<BookCaseInfo> bookCaseInfoList = service.queryBookCaseList(userId);
        this.putObject("data",bookCaseInfoList);
        return "views/memberCenter/bookCase";
    }

    /**
     * 删除书架中小说
     * @param novelId 小说id
     * @author caoMingYang
     * @since 2019/4/15 10:41
     */
    @ResponseBody
    @RequestMapping("/deleteNovel")
    public String deleteNovel(@RequestParam("novelId")Integer novelId){
        JSONObject job = new JSONObject();
        boolean flag = service.deleteNovel(this.getUserInfo().getUserId(),novelId);
        if (flag){
            job.put("code",0);
            job.put("msg","success");
        }else{
            job.put("code",-1);
            job.put("msg","fail");
        }
        String jsonText = job.toJSONString();
        logger.info("deleteNovel>>>"+jsonText);
        return jsonText;
    }
}
