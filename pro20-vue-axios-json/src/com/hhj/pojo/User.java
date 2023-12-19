package com.hhj.pojo;

/**
 * ClassName: User
 * Package: com.hhj.pojo
 * Description:
 *
 * @Author honghuaijie
 * @Create 2023/11/13 19:39
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class User {
    String uname;
    String pwd;

    public User() {
    }

    public User(String uname, String pwd) {
        this.uname = uname;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
