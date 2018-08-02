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
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
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

    @RequestMapping("/")
    public String root(){
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html", "/detail/index.html"})
    public ModelAndView index(@RequestParam(value="page" ,required = false, defaultValue = "0") Integer page, @RequestParam(value="count" ,required = false, defaultValue = "10") Integer count
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
        List<TblDaily> tblDailys = tblDailyMapper.findAllByCondition(queryMap);
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
        return mv;
    }

    /**
     * 查询前八条热门信息
     * @return
     */
    @RequestMapping(value = {"/getDailyHotShow", "/detail/getDailyHotShow"}, method = RequestMethod.GET)
    @ResponseBody
    public String getDailyHotShow(){
        JSONObject result = new JSONObject();
        result.put("dailyList", tblDailyMapper.selectHotEight());
        return result.toString();
    }

    /**
     * 查询前五条类别信息
     * @return
     */
    @RequestMapping(value = {"/getTypeShow", "/detail/getTypeShow"}, method = RequestMethod.GET)
    @ResponseBody
    public String getTypeShow(){
        JSONObject result = new JSONObject();
        result.put("typeList", tblTypeMapper.selectFirstFive());
        return result.toString();
    }

}
