package com.hhj.axios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hhj.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
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
@WebServlet("/axios02.do")
public class Axios02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader bufferedReader = request.getReader();
        String str = null;
        StringBuffer stringBuffer = new StringBuffer("");
        while((str=bufferedReader.readLine())!=null){
            stringBuffer.append(str);
        }
        str = stringBuffer.toString();

        //已知string 需要转换成javaObject
        //Gson有两个API
        //1.fromJson（String,T) 将字符串类型的JSON格式转换成java object
        //2.toJson(java object)将javaObj 转换成json字符串，怎样才能响应给客户端
        Gson gson = new Gson();
        User user = gson.fromJson(str, User.class);

        user.setUname("鸠摩智");
        user.setPwd("123456");

        //假设user是从数据库查询出来的，现在需要将其转换成JSON格式的字符串，然后响应到客户端
        //将java 对象，转换成json格式
        String userJsonStr = gson.toJson(user);
        response.setCharacterEncoding("utf-8");
        //MIME-TYPE
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(userJsonStr);

    }
}
