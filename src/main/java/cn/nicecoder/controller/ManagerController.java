package cn.nicecoder.controller;

import cn.nicecoder.domain.TblDaily;
import cn.nicecoder.domain.TblTag;
import cn.nicecoder.domain.TblTagDaily;
import cn.nicecoder.domain.TblType;
import cn.nicecoder.mapper.TblDailyMapper;
import cn.nicecoder.mapper.TblTagDailyMapper;
import cn.nicecoder.mapper.TblTagMapper;
import cn.nicecoder.mapper.TblTypeMapper;
import cn.nicecoder.util.DateUtil;
import cn.nicecoder.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    TblDailyMapper tblDailyMapper;
    @Autowired
    TblTagMapper tblTagMapper;
    @Autowired
    TblTagDailyMapper tblTagDailyMapper;
    @Autowired
    TblTypeMapper tblTypeMapper;

    @GetMapping("/index.html")
    public String admin(){
        return "admin/index";
    }

    @GetMapping("/login.html")
    public String login(){
        System.out.print("----=---");
        return "login8888888888";
    }

    @GetMapping("/login-error.html")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg","登录失败，用户名或者密码错误！");
        return "login";
    }

    /**
     * 日常页面
     * @return
     */
    @GetMapping("/dailyPage.html")
    public ModelAndView dailyPage(){
        List<TblType> tblTypes = tblTypeMapper.findAll();
        ModelAndView mv = new ModelAndView("admin/dailyedit");
        mv.addObject("tblTypes",tblTypes);
        return mv;
    }

    /**
     * 日常管理页面
     * @return
     */
    @GetMapping("/dailyManage.html")
    public ModelAndView dailyManage(@RequestParam(value="page" ,required = false, defaultValue = "0") Integer page, @RequestParam(value="count" ,required = false, defaultValue = "10") Integer count
            , @RequestParam(value="date" ,required = false) String date, @RequestParam(value="type" ,required = false)  String type, @RequestParam(value="display" ,required = false)  String display
            , @RequestParam(value="keyword" ,required = false)  String keyword){
        HashMap queryMap = new HashMap();
        int start = page * count;
        int end = start + count;
        queryMap.put("start", start);
        queryMap.put("end", end);
        queryMap.put("date", date);
        queryMap.put("type", type);
        queryMap.put("keyword", keyword);
        queryMap.put("display", display);

        List<TblDaily> tblDailys = tblDailyMapper.findAllByCondition(queryMap);
        ModelAndView mv = new ModelAndView("admin/dailymanage");
        int resultCount = tblDailyMapper.getCount(display);
        mv.addObject("countAll",tblDailyMapper.getCount(null));
        mv.addObject("count0",tblDailyMapper.getCount("0"));
        mv.addObject("count1",tblDailyMapper.getCount("1"));
        mv.addObject("count2",tblDailyMapper.getCount("2"));

        mv.addObject("count",resultCount);
        mv.addObject("display",display);
        mv.addObject("tblDailys",tblDailys);
        return mv;
    }

    /**
     * 评论管理页面
     * @return
     */
    @GetMapping("/commentManage.html")
    public String commentManage(){
        return "admin/commentmanage";
    }

    /**
     * 分类管理页面
     * @return
     */
    @GetMapping("/classifyManage.html")
    public String classifyManage(){
        return "admin/classifymanage";
    }

    /**
     * 插入
     * @return
     */
    @PostMapping("/saveDaily.html")
    public String insertDaily(@RequestParam("content") String content, @RequestParam("title") String title, @RequestParam("tag") String tag
            , @RequestParam("cover")  String cover, @RequestParam("type")  String type, @RequestParam(value="id" ,required = false)  String id){
        //插入文章
        TblDaily tblDaily = new TblDaily();
        tblDaily.setContent(content.getBytes());
        tblDaily.setCover(cover);
        tblDaily.setTitle(title);
        tblDaily.setType(type);
        tblDaily.setCreatetime(DateUtil.getCurrentTime24());
        tblDaily.setUpdatetime(DateUtil.getCurrentTime24());
        //添加摘要和字数
        content = StringUtil.delHtmlTag(content);
        tblDaily.setWordcount(content.length());
        tblDaily.setSummary(content.length() < 100 ? content : content.substring(100));
        tblDailyMapper.insert(tblDaily);

        //标签管理
        String[] tags = tag.split(",");
        for (String item : tags) {
            TblTag tblTag =  tblTagMapper.selectByTagName(item);
            if(null == tblTag){
                tblTag = new TblTag();
                tblTag.setTagname(item);
                tblTag.setCreatetime(DateUtil.getCurrentTime24());
                tblTagMapper.insert(tblTag);
            }
            TblTagDaily tblTagDaily = new TblTagDaily();
            tblTagDaily.setTagid(tblTag.getId());
            tblTagDaily.setDailyid(tblDaily.getId());
            Map<String, Integer> queryMap = new HashMap<String, Integer>();
            queryMap.put("tagid",tblTagDaily.getTagid());
            queryMap.put("dailyid",tblTagDaily.getDailyid());
            TblTagDaily tblTagDailyQuery = tblTagDailyMapper.selectByTagidAndDailyId(queryMap);
            if(tblTagDailyQuery == null){
                tblTagDailyMapper.insert(tblTagDaily);
            }
        }

        //分类管理
        tblTypeMapper.increase(Integer.parseInt(type));
        return "redirect:dailyManage.html";
    }

    /**
     * 删除操作
     * @return
     */
    @GetMapping("/del/{id}")
    @ResponseBody
    public String del(@PathVariable(value="id") Integer id){
        JSONObject result = new JSONObject();
        result.put("code", "0");
        result.put("desc", "success");

        int count = tblDailyMapper.updateDisplay(id);
        if(count != 1){
            result.put("code", "1");
            result.put("desc", "fail");
        }
        return result.toJSONString();
    }

}
