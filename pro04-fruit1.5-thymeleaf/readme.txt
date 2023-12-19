review:
1.post提交方式下的设置编码，防止中文乱码
  request.setCharacterEncoding("utf-8")
  get提交方式，Tomcat8开始，编码不需要设置
  tomcat8之前，get方式设置比较麻烦：
  String fname = request.getParameter("fname")


2.Servlet继承关系以及生命周期
    1）servlet接口：init()，service(), destory()
       GenericServlet抽象子类：abstract service();
       HttpServlet抽象子类：实现了service方法，在service方法内部通过request.getMethod()来判断请求的方式，
        然后根据请求的方式去调用内部的do方法。每一个do方法进行了简单实现，主要是如果请求方式不符合则报405错误。
        目的是让我们的Servlet子类去重写对应的方法，（如果重写的不对，则使用父类的405错误实现）

    2） 生命周期：实例化、初始化，服务，销毁
        - Tomcat负责维护Servlet实例的生命周期
        - 每个Servlet在Tomcat容器中只有一个实例，他是线程不安全的
        - Service的启动时机 <load-on-startup> 数字越小，越提前启动，最小为0
        - Service3.0开始支持注解：@WebServlet （就不需要再web.xml中配置了)


3.http协议：
    1）有request 和response 两部分组成
    2）请求包含了三个部分：请求行、请求消息头、请求主体：普通的get方式请求-query string ;  post方式：form data；json格式：request payload
    3) 响应包含了三个部分：响应行、响应消息头、响应主体

4. HttpSession
    1) HttpSession: 表示 会话
    2）为什么需要HttpSession，原因是因为 Http协议是无状态的
    3）Session保存作用域：一次会话范围内都有效；Session.setAttribyte(k,v); Object Session.getAttribute(k); Session.removeAttribute(k)
    4) 其他的API：session.getId(), Session.isNew(), session.getCreationTime() session.invalidate()

5.服务端转发和客户端重定向
    1.服务端转发
    2.客户端重定向 : response.sendRedirect("index.html")

6.thymeleaf的部分标签
    1.使用步骤：添加jar，新建ViewBaseServlet（有两个方法）
    2.配置两个context-param: view-prefix  view-suffix
    3.部分标签 th:if  th:unless th:each th:text

内容：
1.保存作用域
    原始情况下，保存作用域我们认为有四个：
        page(页面级别，现在几乎不用），request(一次请求响应范围),session(一次会话访问)
        application(整个应用程序范围)

        1) request:一次请求响应范围
        2) session:一次会话范围有效
        3) application：一次应用程序范围有效 直到Tomcat停止

2.路径问题
    1）相对路径
    2）绝对路径
    thymeleaf中 th:href="@{}"  相当于"http://localhost:8080/pro10"
                th:href="@{/css/shopping.css}" 相当于http://localhost:8080/pro10/css/shopping.css




水果库存后台管理系统 的业务逻辑
1.首先页面显示index->IndexServlet
IndexServlet中会从数据库中获取全部的水果信息，并通过session作用域来保存全部的水果信息fruitList 然后通过processTemplate 渲染到index.html中
2.index.html通过 session.fruitList来获取水果信息
并通过thymeleaf来处理水果信息,thymeleaf常用的方法 th:text; th:heaf;  th:each;
为每个水果的名字添加一条超链接，点击后跳转到edit.do 页面上，并且会将该水果的fid也发送到edit.do页面上
3.edit.do ->EditServlet
EditServlet主要是接收传入的水果id，然后从数据库中取出这个水果的记录，
将fruit对象保存在请求作用域下，并跳转到edit.html页面上
4.edit.html页面主要是获取请求作用域中的水果信息
并以文本框的信息，显示到edit.html页面上（ID使用隐藏域来显示）
当用户修改信息后，点击修改， 就跳转到update.do上
5.update.do - > updateServlet
updateServlet主要是获取用户修改后的水果信息，并将修改后的水果信息更新到数据库，
更新后，重新返回到index页面上(通过重定向)
如果想要在html页面中使用th属性，则该html页面必须是经过processTemplate渲染过的
6.当用户点击删除按钮时，执行delFruit事件，在事件中访问/addpage.do页面
addoage.do页面渲染add.html页面
在add.html页面中输入完数据，点击添加，去访问add.do的servlet
在此servlet中获取表单传入的数据，并添加到数据库，然后重新访问index(重定向)









