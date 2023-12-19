package com.hhj.axios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: Axios01Servlet
 * Package: com.hhj.axios
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/13 18:39
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
@WebServlet("/axios01.do")
public class Axios01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接受客户端传入的值
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        System.out.println("uname = " + uname);
        System.out.println("pwd = " + pwd);

        //向客户端发送数据
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(uname + "_" +pwd);
        throw new  NullPointerException("空指针");

    }
}
