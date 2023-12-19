package com.hhj.filters;

import com.hhj.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * ClassName: OpenSessionInViewFilter
 * Package: com.hhj.filters
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/4 16:04
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            TransactionManager.beginTrans();
            System.out.println("开启事务");
            filterChain.doFilter(servletRequest,servletResponse);
            TransactionManager.commit();
            System.out.println("提交事务");
        } catch (Exception e) {
            try {
                TransactionManager.rollback();
                System.out.println("回滚事务");
            } catch (SQLException ex) {

                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
