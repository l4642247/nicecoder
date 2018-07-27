package cn.nicecoder.controller;

import cn.nicecoder.mapper.TblDailyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lt
 * @Description: 日常控制器
 *
 * @Date: 下午6:15 2018/6/2
 */
@Controller
public class DailyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public TblDailyMapper tblDailyMapper;

    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public String findByPage(){
        System.out.println(tblDailyMapper.findAll().size());
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        return "admin";
    }

}
