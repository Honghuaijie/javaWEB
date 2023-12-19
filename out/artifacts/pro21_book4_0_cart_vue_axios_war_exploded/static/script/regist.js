function $(id){
    return document.getElementById(id);
}


function preRegist(){
    var unameTxt= $("unameTxt");
    //验证用户名：用户名应为6~16位数组和字母组成
    var unameReg = /[a-zA-Z0-9]{6,16}/;
    var uname = unameTxt.value;
    var unameSpan= $("unameSpan");
    if(!unameReg.test(uname)){
        unameSpan.style.visibility = "visible";
        return false;
    }else{
        unameSpan.style.visibility = "hidden";
    }


    //密码的长度至少为8位
    var pwdTxt= $("pwdTxt");
    var pwdReg = /.{8,}/;
    var pwd = pwdTxt.value;
    var pwdSpan= $("pwdSpan");
    if(!pwdReg.test(pwd)){
        pwdSpan.style.visibility = "visible";
        return false;
    }else{
        pwdSpan.style.visibility = "hidden";
    }

    //判断确认密码
    var pwdTxt2= $("pwdTxt2");
    var pwd2 = pwdTxt2.value;
    var pwdSpan2= $("pwdSpan2");
    if(pwd != pwd2){
        pwdSpan2.style.visibility = "visible";
        return false;
    }else{
        pwdSpan2.style.visibility = "hidden";
    }

    //验证邮箱格式
    var email = $("emailTxt").value;
    var emailSpan = $("emailSpan");
    var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!emailReg.test(email)){
        emailSpan.style.visibility = "visible";
        return false ;
    }else{
        emailSpan.style.visibility = 'hidden';
    }


    return true;
}

//如果想要发送异步请求，我们需要一个关键的对象：XMLHttpRequest
var xmlHttpRequest;
function createXMLHttpRequest(){
    if (window.XMLHttpRequest){
        //符合DOM2标准的浏览器，xmlHttpRequest的创建方式
        xmlHttpRequest = new XMLHttpRequest();
    }else if (window.ActiveXObject){ //IE浏览器
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e){
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP")
        }
    }

}

function ckUname(uname){
    createXMLHttpRequest();
    var uri = "user.do?operate=ckUname&uname=" + uname;
    xmlHttpRequest.open("GET",uri,true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    //发送请求
    xmlHttpRequest.send();
}

function ckUnameCB(){
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
        //接受服务器发送来的数据
        //xmlHttpRequest.responseText 表示服务器端相应给我的文本内容
        //alert(xmlHttpRequest.responseText);
        var responseText = xmlHttpRequest.responseText;
        // {'uname':'1'}
        // alert(responseText);
        var unameSpan2 = $("unameSpan2");
        if (responseText == "{'uname': '1'}"){
            //说明用户名存在，需要提示用户
            unameSpan2.style.visibility = "visible"
        }else{
            unameSpan2.style.visibility = "hidden";
        }
    }
}