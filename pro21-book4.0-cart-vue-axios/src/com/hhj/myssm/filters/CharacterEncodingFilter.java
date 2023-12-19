package com.hhj.myssm.filters;

import com.hhj.myssm.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: CharacterEncodingFilter
 * Package: com.hhj.filters
 * Description:
 *      该过滤器专门用来设置编码
 * @Author honghuaijie
 * @Create 2023/11/4 11:53
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebFilter(urlPatterns = "*.do",initParams = {@WebInitParam(name="encoding",value="utf-8")})
public class CharacterEncodingFilter implements Filter {

    private String encoding = "utf-8"; //编码默认请求下是utf-8

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr= filterConfig.getInitParameter("encoding");
        if (StringUtil.isNotEmpt(encodingStr)){
            encoding = encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        servletRequest1.setCharacterEncoding(encoding);
        HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;
        servletResponse1.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
