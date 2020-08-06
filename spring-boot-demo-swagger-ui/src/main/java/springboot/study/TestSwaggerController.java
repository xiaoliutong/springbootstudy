package springboot.study;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.study.Vo.CommonResponse;

@Controller
@Api(tags = "TestSwagger",value = "测试swagger")
public class TestSwaggerController {
    @RequestMapping("/swaggerMethod")
    @ResponseBody
    @ApiOperation( value = "测试下方法")
    public CommonResponse testSwaggerMethod(){
        return new CommonResponse(200,"请求成功");
    }
}
