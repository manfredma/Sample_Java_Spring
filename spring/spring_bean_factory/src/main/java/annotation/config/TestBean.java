package annotation.config;

import org.springframework.beans.factory.annotation.Autowired;

public class TestBean {

    public int age;

    @Autowired(required = false)
    private String name;

    private TestBean spouse;

    private String country;

    public TestBean(String name) {
        this.name = name;
    }


    public TestBean() {
    }

    public TestBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setSpouse(TestBean spouse) {
        this.spouse = spouse;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", spouse=" + spouse +
                ", country='" + country + '\'' +
                '}';
    }
}
