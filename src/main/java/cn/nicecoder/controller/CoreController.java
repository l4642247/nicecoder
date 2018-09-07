package cn.nicecoder.controller;

import cn.nicecoder.domain.*;
import cn.nicecoder.mapper.*;
import cn.nicecoder.service.CommentServiceImpl;
import cn.nicecoder.util.DateUtil;
import cn.nicecoder.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    TblUserMapper tblUserMapper;
    @Autowired
    TblCommentMapper tblCommentMapper;
    @Autowired
    CommentServiceImpl commentServiceImpl;

    @RequestMapping("/")
    public String root(){
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html", "/detail/index.html"})
    public ModelAndView index(@RequestParam(value="page" ,required = false, defaultValue = "0") Integer page, @RequestParam(value="count" ,required = false, defaultValue = "20") Integer count
            , @RequestParam(value="date" ,required = false) String date, @RequestParam(value="type" ,required = false)  String type, @RequestParam(value="tag" ,required = false)  String tag
            , @RequestParam(value="keyword" ,required = false)  String keyword){
        HashMap queryMap = new HashMap();
        int start = page * count;
        int end = start + count;
        queryMap.put("start", start);
        queryMap.put("end", end);
        queryMap.put("date", date);
        queryMap.put("type", type);
        queryMap.put("keyword", keyword);
        queryMap.put("tag", tag);
        queryMap.put("display", "0");
        List<TblDaily> tblDailys = tblDailyMapper.findAllByCondition(queryMap);
        List<TblType> tblTypes = tblTypeMapper.findAll();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tblDailys",tblDailys);
        mv.addObject("tblTypes",tblTypes);
        mv.addObject("dailyList", tblDailyMapper.selectHotEight());
        mv.addObject("typeList", tblTypeMapper.selectFirstFive());
        return mv;
    }

    /**
     * 查询单条日常
     * @param id
     * @return
     */
    @GetMapping("/detail/art_{id}.html")
    public ModelAndView info(@PathVariable(value="id") Integer id){
        TblDaily tblDaily = tblDailyMapper.findByPrimaryKey(id);
        List<TblType> tblTypes = tblTypeMapper.findAll();
        ModelAndView mv = new ModelAndView("info");
        //数据转换
        tblDaily.setCreatetime(DateUtil.getDate(tblDaily.getCreatetime()));
        tblDaily.setContentStr(new String(tblDaily.getContent()));
        //查询该文章标签
        ArrayList<TblTag> tblTags = tblTagMapper.selectByDailyId(id);
        //查询所有标签
        ArrayList<TblTag> allTag = tblTagMapper.findAll();
        //查询附近文章
        List<TblDaily> nearDaily = tblDailyMapper.findNearById(id);
        for(TblDaily item: nearDaily){
            if(item.getDir().equals("0")){
                mv.addObject("preDaily",item);
            }
            if(item.getDir().equals("1")){
                mv.addObject("nextDaily",item);
            }
        }
        mv.addObject("tblDaily",tblDaily);
        mv.addObject("tblTypes",tblTypes);
        mv.addObject("tblTags",tblTags);
        mv.addObject("allTag",allTag);
        mv.addObject("dailyList", tblDailyMapper.selectHotEight());
        mv.addObject("typeList", tblTypeMapper.selectFirstFive());
        return mv;
    }

    @GetMapping(value = {"/about.html", "/detail/about.html"})
    public ModelAndView about(){
        List<TblType> tblTypes = tblTypeMapper.findAll();
        ModelAndView mv = new ModelAndView("about");
        mv.addObject("typeList", tblTypeMapper.selectFirstFive());
        mv.addObject("wordCount", tblDailyMapper.getWordCountSum() == null ? "0" :tblDailyMapper.getWordCountSum());
        mv.addObject("dayBetween", DateUtil.daysBetween("20180808", DateUtil.getCurrentDate()));
        return mv;
    }

    @GetMapping(value = {"/gbook.html"})
    public ModelAndView gbook(){
        ModelAndView mv = new ModelAndView("gbook");
        Map queryMap = new HashMap();
        queryMap.put("type", "0");
        queryMap.put("status", "1");
        List<TblComment> tblCommentList = tblCommentMapper.findAll(queryMap);
        for(TblComment tblComment : tblCommentList){
            commentServiceImpl.commentListDeal(tblComment);
            queryMap.put("discussid",tblComment.getId());
            queryMap.put("type","1");
            List<TblComment> tblCommentListSub = tblCommentMapper.findAllSub(queryMap);
            for(TblComment tblCommentSub : tblCommentListSub){
                commentServiceImpl.commentListDeal(tblCommentSub);
            }
            tblComment.setTblCommentList(tblCommentListSub);
        }
        mv.addObject("tblCommentList",tblCommentList);
        return mv;
    }

    @PostMapping("/commentAdd")
    public String gbookAdd(@RequestParam(value="name") String name, @RequestParam(value="email") String email, @RequestParam(value="website" ,required = false) String website,
                           @RequestParam(value="discuss")  String discuss, @RequestParam(value="type", required = false)  String type, @RequestParam(value="id" ,required = false)  String id){
        //查询用户
        TblUser tblUser = tblUserMapper.selectByEmail(email);
        int userId = 0;
        if(tblUser == null){
            TblUser tu = new TblUser();
            tu.setEmail(email);
            tu.setName(name);
            tu.setWebsite(website);
            tblUserMapper.insert(tu);
            userId = tu.getId();
        }else{
            userId = tblUser.getId();
        }

        //插入评论
        TblComment tc = new TblComment();
        tc.setType(type == null ? "0" : type);
        tc.setStatus("1");//未审核
        tc.setContent(discuss.getBytes());
        tc.setDiscussid(id);
        tc.setUserid(userId);
        tc.setPudate(DateUtil.getCurrentTime24());
        tblCommentMapper.insert(tc);
        return "redirect:gbook.html";
    }

}
