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
    private String username;//姓名
    private String phone;   //手机号
    private String uno; //工号
    private Integer age;//年龄
    private Integer sex;//性别 1男 2女
    private String address;//家庭住址
    private String department;//所在车间
    private String grade;//所在班次
    private String avatar; //头像
    private Integer face_id;//人脸识别ID
    public UserInfo(){

    }

    public UserInfo(Integer uid, String phone) {
        this.uid = uid;
        this.phone = phone;
    }

    public UserInfo(Integer uid, String username, String phone, String uno, Integer age, Integer sex, String address, String department, String grade, String avatar, Integer face_id) {
        this.uid = uid;
        this.username = username;
        this.phone = phone;
        this.uno = uno;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.department = department;
        this.grade = grade;
        this.avatar = avatar;
        this.face_id = face_id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getFace_id() {
        return face_id;
    }

    public void setFace_id(Integer face_id) {
        this.face_id = face_id;
    }
}
