这个module是用来复习fruit项目的
用到的技术栈
1. servlet
2.反射
3.前端使用thymeleaf
4.使用IOC DI 来控制所有组件
5.使用druid来连接数据库

步骤：
1.使用独立的servlet类来实现增删改查   √
2.将独立的servlet整合成完全的的controller
3.使用dispatchServlet来执行冗余的代码（可以将请求中参数的获取，和最后资源的跳转统一写，通过反射）

这个项目主要包含以下功能
页面的显示  /index
1.先从数据库中获取到数据，保存到session作用域中，显示在页面上
2.设置分页效果，一开始默认显示第一页，

内容的新增
内容的修改
遇到的问题：
1.如何从html页面中获取session中的元素 th:object="${fruit}":  拿到fruit对象，并且在table中，无需写成fruit.name 直接写成name就可以了
内容的删除


2.将独立的servlet整合成完全的的controller
实现的思路：
将增删改查设置为四个方法
这4个操作会传入不同的operate，servlet根据operate来调用不同的方法

