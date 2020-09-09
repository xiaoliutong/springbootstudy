package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: java类作用描述
 * @Author: LiuZhuangZhuang
 * @CreateDate: 2020/9/7 11:14
 * @UpdateUser: LiuZhuangZhuang
 * @UpdateDate: 2020/9/7 11:14
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ZrarExcelProperty {
    public int columnIndex();

    public String columnName() default "";

    


}
