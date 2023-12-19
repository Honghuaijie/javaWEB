1.熟悉QQzone业务需求
    1）用户登陆
    2）登陆成功，显示主界面。左侧显示好友列表：上端显示欢迎词。如果不是自己的空间，显示超链接“返回自己的空间”，下端显示日志列表
    3）查看日志详情：
        - 日志本身的信息（作者头像、昵称、日志标题、日志内容、日志的日期）
        - 回复列表（回复者的头像、昵称、回复内容、回复后期）
        - 主人回复信息
    4）删除日志
    5）删除特定回复
    6）删除特定主人回复


    7）添加日志、添加回复、添加主人回复
    8）点击左侧好友链接，进入好友的空间
2.数据库设计
    1）抽取实体：用户登陆信息、用户详情信息、日志、回帖、主人回复
    2）分析其中的属性：
        - 用户登录信息：账号、密码、头像、昵称
        - 用户详情信息：真实姓名、星座、血型、邮箱、手机号
        - 日志：标题、内容、日期、作者
        - 回复：内容、日期、作者、日志
        - 主人回复：内容、日期、作者、回复
    3）分析实体之间的关系
        - 用户登陆信息  ：用户详情信息  1:1
        - 用户： 日志：               1:n
        - 日志：回复：                1：n
        - 回复：主人回复：             1：1
        - 用户：好友：                n:m
3.数据库的范式：
    1）第一范式：列不可再分
    2）第二范式：一张表只表达一层含义（只描述一件事情）
    3）第三范式：表中的每一列和主键都是直接依赖关系，而不是间接依赖
4.数据库设计的范式和数据库的查询性能很多时候是相悖的，我们需要根据实际的业务请求做一个选择：
    - 查询频次不高的请求下，我们更倾向于数据库的设计范式，从而提高存储效率
    - 查询频次较高的情况下，我们更倾向于降低数据库设计的范式，允许特定的冗余，从而提高查询的性能

5.QQzone登陆功能实现出现的四个错误
    1) URL没有修改，用的还是fruitdb
    2）left.html没有样式，同时数据也不展示，原因：我们是直接去请求的静态页面资源，那么并没有执行super.processTemplate()，也就是templeaf没有起作用
        (之前的表单也是这个原因）
           解决方法： 新增pageController，添加page方法  目的是执行super.processTemplate()方法，thymeleaf生效
    3）rsmd.getColumnName

-- 在设计DAO层时，需要注意的就是，对哪个表进行操作，就将该操作写在这个表的DAO层上

进入内容：
1.top.html页面显示登录者昵称、判断是否是自己的空间
    1)显示登陆者昵称：${session.friend.nickName}
    2)判断是否是自己的空间：${session.userBasic.id !=session.friend.id}
        如果不是期望的效果，首先考虑将两者的id都显示出来
2.点击左侧的好友链接，进入好友空间
点击好友链接，将好友的id传入到一个friend方法中，在这个方法里面
        1)根据id获取该好友的信息，获取该好友的日志信息，将日志信息保存到好友信息中，
        修改session作用域中的friend，渲染index页面
        2）main页面应该展示的是friend中的topiclist，而不是userbasic中的topicList
        3)跳转后 在左侧（left）中显示整个index页面
            - 问题：在left页面显示整个index布局
            - 出现的原因 就是a标签的target默认为显示在父窗口，所以显示在left中
            - 解决：给超链接添加target属性：target="_top" 保证在顶层窗口显示整个index页面

        4)top.html页面需要修改
            1)欢迎进入${session.friend.nickName}
            2) “返回自己的空间”需要修改： <a th:href="@{|/user.do?operate=friend&id=${session.userBasic.id}|}"
                                                target="_top"
                                             >返回自己的空间!</a>
3.日志详情页面实现
    根据日志的id去获取所有的回复，遍历所有的回复，去获取所有的主人回复
    1)已知topic的id，需要根据topic的id获取指定topic select * from t_topic where id = id
    2）获取这个topic关联的所有的回复 select * from t_reply where topic = id
    3)如果某个回复有主人回复，需要查询出来 select * from t_host_reply where reply = reply.id
        -在TopicController中获取指定的topic
        -具体这个toopic中关联多少个reply，有replyService内部实现
    4)获取到的topic中的author只有id，我们可以利用session中的friend，因为topic的author就是session中的friend
    5)在reply中也有author，而且这个author只有id，我们需要根据ID来获取author所有的信息，并设置到reply中

4.添加回复
    1)

5.删除回复
    1)如果回复有关联的主人回复，需要先删除主人回复
    2）删除回复
    我们在删除回复表记录时，失败，原因是：在主人回复表中仍然有记录引用待删除的回复这条记录

6.删除日志
    1）删除日志，首先需要判断是否有关联的回复
    2）删除回复，首先需要判断是否有关联的主人回复
    3）另外，如果不是自己的空间，则不等你删除日志

格式化问题
1.日志和字符串之间的格式化
    String -> java.util.Date
    SimpleDateFormat sdf = new SimpleDateFormat
    sdf.parse(dateStr)
    Java-util-Date -> String
    sdf.format(date)

2.thymeleaf中使用的是#dates这个公共的内置对象
${#dates.format(topic.topicDate,'yyyy-MM-dd HH:mm:ss')}

3. 系统启动时，我们访问的页面是pro15/page.do?operate=page&page=login
     为什么不是pro15/login.html
     答：如果是后者，那么属于直接访问静态页面。那么会导致页面上的thymeleaf表达式或标签，浏览器不能识别
     我们访问前者的目的就是为了要执行viewBaseServlet中的processTemplete()
4. http://localhost:8080/pro15/page.do?operate=page&page=login 访问这个URL，执行的过程是什么样的？
    答：
    http://  localhost   :8080   /pro15          /page.do                ?operate=page&page=login
    协议      serverIp     port  context root     request.getServletPath    query string
    1）DispatcherServlet -->urlPattern:  *.do  拦截page.do
    2) request.getServletPath() -> /page.do
    3) 解析处理字符串，将/page.do -> page
    4) 拿到page这个字符串去IOC容器(beanFactory)中寻找id=page 的那个bean对象 -> pageController.java
    5) 获取operate的值 ->page  因此得知，应该执行 PageController 中的page()方法
    6) pageController中的page方法定义如下：
            public String page(String page){
                return page;
            }
    7)在 query string： operate=page&page=login 中获取请求参数，参数名为page， 参数值是login
        因此page方法的参数page值会被赋上"login"
        然后return "login", return给谁?
    8)因为PageController中的page 方法是DispatcherServlet通过反射调用的
        method.invoke()
        因此，字符串"login"返回给dispatcherServlet
    9) dispatcherServlet接收到返回值，处理视图
        目前处理视图的方式有两种1.带前缀：redirect: 2.不带前缀
        当前，返回"login”是不带前缀的
        那么执行super.processTemplete("login",request,response);
    10) 此时 viewBaseServlet中的processTemplete,效果是：
        在"login"这个字符串前面拼接"/"(其实就是配置文件中view-prefix配置的值）
        在”login“这个字符串后面拼接".html"(其实就是配置文件中view-suffix配置的值）\
        最后服务器进行转发

5.目前我们进行javaweb项目开发的"套路"
    1)拷贝 myssm包
    2）新建配置文件applicationContext.xml或者可以不叫这个名字，可以在web.xml中指定文件名
    3）在web.xml文件中配置：
        1）配置前缀和后缀，这样thymeleaf引擎就可以根据我们返回的字符串进行拼接，再跳转
        2）配置监听器要读取的参数，目的是加载IOC容器的配置文件(也就是applicationContext.xml)
        <context-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>applicationContext.xml</param-value>
        </context-param>
    4) 开发具体的业务模块
        1）一个具体的业务模块纵向上由几个部分组成：
            - html页面
            - pojo类
            - DAO接口和实现类
            - Service接口和实现类
            - Controller 控制器组件
        2）如果html页面有thymeleaf表达式，一定不能直接访问。必须要经过pageController
        3)在application.xml中配置DAO、Service、Controller ，以及三者之间的依赖关系
        4）DAO实现类中，使用BaseDAO来完成操作
        5）Service是业务实现类，这一层我们只需要记住一点：
            - 业务逻辑我们都封装在service层，不要分散在controller层，也不要出现在DAO层，需要保证DAO方法的单精度特性
            - 当每一个业务功能需要使用其他模块的业务功能时，尽量的调用别人的service，而不要深入到其他模块的DAO细节
        6）Controller类的编写规则
            - 在applicationContext.xml中配置 controller
                <bean id = "user" class="....UserController/>
                那么用户在前端对应的servletpath对应的就是/user.do,其中的user就是对应此处的bean的id 的值
            - 在controller中设计的方法名需要和operate的值对应
            - 在表单中，组件的name属性和controller中方法的参数名一致
            <input type="text" name="loginId" />
            - 另外需要注意的是controller方法中的参数不一定是通过请求参数的方式去获取的
            if("request".equals...) else if ("response".equals)..{
             直接赋值
             }else{
                此处才是从request的请求参数中获取
                request.getParameter("loginId")....
             }
        7) DispatcherServlet中步骤大致分为：
            0.从application作用域中获取IOC容器(beanFactory)
            1.解析servletpath 需要对应的controller组件
            2.准备operate指定方法所要求参数
            3.调用operate指定的方法
            4.接受到operate指定的方法的执行返回值,对返回值进行处理（视图解析）
        8) 为什么dispatcherServlet能够从application作用域中获取到容器？
            ContextLoaderListener 在容器启动时会执行初始化容器，而它的操作就是：
            - 解析IOC配置文件，创建一个一个的组件，并完成组件之间依赖关系的注入
            - 将IOC容器保存到application作用域

