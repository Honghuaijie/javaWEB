
水果库存后台管理系统 的业务逻辑
版本1： 有增删改功能
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


版本2： 增加了分页功能，和查询功能
分页功能是如何实现的呢，
首先在index.html中添加4个按钮，分别是首页、上一页、下一页、末页
修改FruitDaoImpl中的getFruitCount方法，使其每次只查询显示指定页码上的记录(page-1)*5 每页显示5个数据
给这四个按钮添加一个事件，这个事件需要传入一个页码参数，事件调用indexServlet，并传入页码参数
indexServlet的页码默认为1，如果传入页码， 则按照传入的页码显示页面
indexServlet主要增加了  查询指定页的sql，和查询所有记录的sql，并计算所有页(fruitCount+4)/5
最后给按钮增加了：如果当前页是第一个则首页和上一页不可点击
如果当前页是末页则尾页和下一页不可点击的功能（通过作用域中的pageNo和pageCounts来判断）

查询操作：
希望通过关键字查询，查询以后，页面上的数据都是关键字记录，并且关键字长期有效
查询和显示都是使用index，因为他们的功能都差不多
我们使用oper来区分是查询还是显示操作
当 oper不等于空，并且等于seach时，我们就可以让页面默认显示第一页，并且通过关键字来查询所有的记录，存放到session中
当 oper等于空，我们就获取指定页码，并且看看通过session还获取关键字，显示与关键字相关的操作，实现一次查询一直有效（直到修改查询框）
