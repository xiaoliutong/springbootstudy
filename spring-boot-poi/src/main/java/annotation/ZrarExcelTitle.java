package annotation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: java类作用描述
 * @Author: LiuZhuangZhuang
 * @CreateDate: 2020/9/7 11:09
 * @UpdateUser: LiuZhuangZhuang
 * @UpdateDate: 2020/9/7 11:09
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ZrarExcelTitle {
    public String title();
}
