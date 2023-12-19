function delFruit(fid) {
    //首先弹出一个对话框
    if (confirm("你确定要删除嘛")){
        window.location.href ="fruit.do?operate=delete&fid=" + fid;
    }
}

function page(pageNo) {
    window.location.href = "fruit.do?pageNo=" + pageNo ;
}

