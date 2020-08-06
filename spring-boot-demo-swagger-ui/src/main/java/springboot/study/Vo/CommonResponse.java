package springboot.study.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {
    @ApiModelProperty(value = "状态码 200->成功")
    private int code;
    @ApiModelProperty(value = "是否成功的信息")
    private String message;
}
