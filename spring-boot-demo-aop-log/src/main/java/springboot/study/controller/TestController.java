package springboot.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String testAOP(){
        return "aop-》log日志的handler";
    }
}
