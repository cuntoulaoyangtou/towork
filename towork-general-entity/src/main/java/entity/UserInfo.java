package entity;

/**
 * @ClassNameUserInfo
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/18 0018 10:46
 * @Version V1.0
 **/

public class UserInfo {
    private Integer uid;
    private String username;
    private String password;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
