review:
1 -mvc.最初的做法是：一个请求对应一个servlet，这样存在的问题是servlet太多了
2.把一些列的请求都对应一个servlet，IndexServlet/addservlet/editServlet/delServlet/UpdateServlet ->合并成fruitServlet
    通过一个operate的值来决定调用FruitServlet中的哪一个方法
    使用的是Switch-case
3-mvc-reflect.在上一个版本中，servlet中充斥着大量的Switch-case，试想一下，虽则我们的项目业务规模扩大，那么就会有很多的servlet，也就意味着很多的Switch-case，
    这是一种代码冗余，因此我们在servlet中使用了反射技术，我们规定operate的值和方法名一致，那么接收到的operate的值是什么，就表明我们需要调用对应的方法进行相应
    如果找不到对应的方法，则抛异常
4.在上一个版本中，我们使用了反射，但是其实还是存在一定的问题：每一个servlet中都有类似的反射的代码。因此继续抽取，设计了中央控制器类：dispatcherServlet
    DispatcherServlet这个类的工作分为两大部分:
    1.根据URL定位到能够处理这个请求的controller组件：
        1）从url中提取servletpath：/fruit.do - >fruit
        2）根据fruit找到对应的组件>fruitController，这个对应的依据，我们存储在application.xml中
                    <bean id="fruit" class="come.hhj.fruit.FruitControllers></bean>
            通过DOM技术我们去解析XML文件，在中央控制器中形成一个beanMap容器，用来存放所有的controller组件
        3）.根据获取到的operate的值定位到我们fruitcontroller中需要调用的方法
    2.调用controller组件中的方法
        1）获取参数
            获取即将要调用的方法的参数签名信息：Parameter[] parameters = method.getParameters()
            获取参数的名称通过parameter.getName() ,通过parameter.getType()获取参数的类型
            准备了一个object[] parametervalues 这个数组用来存放对应参数的参数值，另外我们需要考虑参数的类型问题，需要进行类型转换
        2）执行方法
           Object returnObj = method.invoke(controllerBean,parameterValues);
        3）视图处理
           String returnstr = (String)returnObj;
           if(returnstr.startWith("redirect")){
            ...
           }else{
           ...
           }











