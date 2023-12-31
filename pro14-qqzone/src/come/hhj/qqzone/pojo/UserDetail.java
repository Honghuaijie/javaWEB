package come.hhj.qqzone.pojo;

import java.sql.Date;

/**
 * ClassName: UserDetail
 * Package: come.hhj.qqzone.pojo
 * Description:
 *  用户详情表
 * @Author honghuaijie
 * @Create 2023/11/5 15:43
 * @Version 1.0
 * Yesterday is history,tomorrow is a mystery,
 * but today is a gift.That is why it's called the present
 */
public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private Date  birth;
    private String star;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public UserDetail(){

    }
}


// 父类 java.util.Data 年月日时分秒毫秒
// 子类 java.sql.Date 年月日
// 子类 java.sql.Time 时分秒