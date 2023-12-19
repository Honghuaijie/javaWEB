function delFruit(fid){
    if (confirm("你确定要删除嘛？")){
        //window表示当前网页窗口
        //location指的是地址栏对象 localhost:8080/pro4/
        //href 表示给地址栏的对象赋值
        //所以代码的是意思就是给 del.do发请求
        window.location.href="del.do?fid=" + fid;
    }
}