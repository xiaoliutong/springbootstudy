package spring.boot.demo.config.component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Date:2020/9/8 21:01
 * @Authour:lenovo
 * @Description:
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化。。。。。");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁。。。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      filterChain.doFilter(servletRequest,servletResponse);
    }
}
