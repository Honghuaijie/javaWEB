回顾：
Ajax: 异步的JavaScript and XML
目的：用来发送异步的请求，然后当服务器给我响应的时候再进行回调操作
好处：提高用户体验：局部刷新：降低服务器负担、减轻浏览器压力、减轻网络带宽压力
开发步骤：
    1）创建XMLHttpRequest
    2）调用open进行设置："GET" ,URL,true(true表示异步发送)
    3）绑定状态改变时执行的回调函数- onreadystatechange
    4）发送请求 - send()
    5）编写回调函数，在回调函数中，我们只对XMLHttpRequest的readystate为4的时候感兴趣
                              我们只对XMLHttpRequest的status为200的时候感兴趣

今日内容：
1.Vue
    1） {{}} -相当于innerText
    2） v-bind:attr 绑定属性值 例如， v-bind:value -绑定value值
    3）v-model
    4) v-if v-else v-show
    5) v-for
    6) v-on
    7) 其他:
        - trim()  split() join()
        -  watch
        - 声明周期

2.Axios
    Axios是ajax的一个框架，简化Ajax操作
    axios执行Ajax操作的步骤：
        1.添加并引入axios的js文件
        2-1. 客户端向服务器端 异步发送普通参数值
            - 基本格式:axios().then().catch()
            - 示例：
                axios({
                    method:"post",
                    url:"....",
                    params:{
                        k1:v1,
                        k2:v2,
                    }
                })
                .then(function(value){})  //成功响应执行的回调  value.data可以获取到服务器返回的值
                .catch(function(reason){})  //有异常时执行的回调  reason.response.data可以获取到响应的内容
                                                               reason.message/reason.stack 可以查看错误的信息

        2-2. 客户端向服务器发送JSON格式的数据
        - 什么是JSON
            JSON是一种数据格式
            XML也是一种数据格式

            XML表示两个学员信息的代码如下：
            <students>
                <student sid="s001">
                    <sname>jim<sname/>
                    <age>18<age/>
                <student/>
                <student sid="s002">
                    <sname>tom<sname/>
                    <age>19<age/>
                <student/>
            <students/>
            JSON格式表示两个学员信息的代码如下
            [{sid:"s001",age:18},{sid:"s002",age:19}]
            - JSON表达数据更简洁，更能够节约网络带宽
        1)客户端中params需要修改成data：
        2)服务器获取参数值不在是request.getParameter()...
          而是：BufferedReader bufferedReader = request.getReader();
             String str = null;
             StringBuffer stringBuffer = new StringBuffer("");
             while((str=bufferedReader.readLine())!=null){
                 stringBuffer.append(str);
             }
             str = stringBuffer.toString();
        3）我们会发现str的内容如下：
            {"uname":"lina","pwd":"ok"}

        -  服务器端给客户端响应JSON格式的字符串，然后客户端需要将字符串转换成js object
