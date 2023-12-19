1.Cookie
    1）.创建cookie对象
    2）.在客户端保存Cookie
    3). 设置cookie的有效时长
        cookie.setMaxAge(60) 设置cookie的有效时长是60秒
        cookie.setDomain(pattern);
        cookie.setPath(uri);
    4） cookie的应用
        - 记住用户名和密码十天cookie.setMaxAge(60*60*24*10)
        - 十天免登录
2.Kaptcha
    1.为什么需要验证码，为了确保发送请求的是人，而不是机器
    2.Kaptcha如何使用
        - 添加jar
        - 在web.xml文件中注册 KaptchaServlet，并设置验证码图片的相关属性
        - 在html页面上编写一个img标签，然后设置src等于kaptchaServlet对应的URL-pattern
    3. kaptcha验证码图片的各个属性在常量接口：constants中
    4.KaptchaServlet在生成验证码图片时，会同时将验证码信息保存到session中
      因此，我们在注册请求时，首先将用户文本框中输入的验证码值和session中保存的值进行比较
      ,相等则进行注册
3.JS --Exp
    1.正则表达式的使用三步骤：
        1、定义正则表达式
            正则表达式定义有两种方式
            1）对象形式
                new RegExp("abc")
            2) 直接量的形式
                var reg = //;
            3)匹配模式：
                - g  表示全局匹配
                - i 忽略大小写匹配resoutset
                - m 多行匹配
                - gim这三个可以组合使用，不区分先后顺序
                  例如： var reg = /abc/gim, var reg = new RexExp("abc","gim");
        2、定义待校验的字符串
        3、校验
    2) 元字符
      .  \w \W   \s  \S  \d \D \b ^ $

    3) []表示集合
       [abc] 表示a或b 或c
       [^abc] 表示取反 ，只要不是a不是b不是c就匹配
       [a-c] 表示a到c这个范围匹配

    4) 出现的次数
      *表示多次(0~n)
      +至少一次 （>=1)
      ?最多一次（0 ~ 1）
      {n} 表示出现n次
      {n,} 表示出现n次，或多次