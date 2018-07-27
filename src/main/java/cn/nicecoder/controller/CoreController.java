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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CoreController {

    @Autowired
    TblDailyMapper tblDailyMapper;
    @Autowired
    TblTagMapper tblTagMapper;
    @Autowired
    TblTagDailyMapper tblTagDailyMapper;
    @Autowired
    TblTypeMapper tblTypeMapper;

    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public ModelAndView index(){
        List<TblDaily> tblDailys = tblDailyMapper.findAll();
        List<TblType> tblTypes = tblTypeMapper.findAll();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tblDailys",tblDailys);
        mv.addObject("tblTypes",tblTypes);
        return mv;
    }

    /**
     * 查询单条日常
     * @param id
     * @return
     */
    @GetMapping("/info")
    public ModelAndView info(@RequestParam(value="id") Integer id){
        TblDaily tblDaily = tblDailyMapper.findByPrimaryKey(id);
        List<TblType> tblTypes = tblTypeMapper.findAll();
        ModelAndView mv = new ModelAndView("info");
        //数据转换
        tblDaily.setCreatetime(DateUtil.getDate(tblDaily.getCreatetime()));
        tblDaily.setContentStr(new String(tblDaily.getContent()));
        mv.addObject("tblDaily",tblDaily);
        mv.addObject("tblTypes",tblTypes);
        return mv;
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
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
    public String dailyPage(){
        return "daily";
    }


    /**
     * 日常管理页面
     * @return
     */
    @GetMapping("/dailyManage.html")
    public String dailyManage(@RequestParam(value="page" ,required = false) int page, @RequestParam(value="count" ,required = false) int count
            ,@RequestParam(value="date" ,required = false) String date, @RequestParam(value="type" ,required = false)  String type
            , @RequestParam(value="keyword" ,required = false)  String keyword){
        Map<String, String> queryMap = new HashMap<String, String>();
        int start = page * count;
        int end = start + count;
        queryMap.put("start",start+"");
        queryMap.put("end",end+"");
        queryMap.put("date",date);
        queryMap.put("type",type);
        queryMap.put("keyword",keyword);
        

        return "dailymanage";
    }

    /**
     * 评论管理页面
     * @return
     */
    @GetMapping("/commentManage.html")
    public String commentManage(){
        return "commentmanage";
    }

    /**
     * 分类管理页面
     * @return
     */
    @GetMapping("/classifyManage.html")
    public String classifyManage(){
        return "classifymanage";
    }


    /**
     * 插入
     * @return
     */
    @PostMapping("/saveDaily.html")
    public String insertDaily(@RequestParam("content") String content, @RequestParam("title") String title, @RequestParam("tag") String tag
            , @RequestParam("cover")  String cover, @RequestParam("type")  String type, @RequestParam(value="id" ,required = false)  String id){
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

        //查询
        String[] tags = tag.split(",");
        for (String item : tags) {
            TblTag tblTag =  tblTagMapper.selectByTagname(item);
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
        return "daily";
    }


}
