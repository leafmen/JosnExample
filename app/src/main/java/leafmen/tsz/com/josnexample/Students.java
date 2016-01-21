package leafmen.tsz.com.josnexample;

/**
 * Created by LiQiang on 2016/1/20.
 */
public class Students {
    private Long id;
    private String name;
    private String sex;
    private short age;
    private String grade;

    public Students() {

    }

    public Students(Long id, String name, String sex, short age, String grade) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
