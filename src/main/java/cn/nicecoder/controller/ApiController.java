package cn.nicecoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiController {

    @RequestMapping("/wechat")
    public String root(){
        return "";
    }
}
