package lesson20;

import java.util.Objects;

public class Student {
    private int course;
    private String country;
    private String name;
    private int age;

    public Student(String name, int course,  int age, String country) {
        this.course = course;
        this.country = country;
        this.name = name;
        this.age = age;
    }

    public int getCourse() {
        return course;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public void setCourse(int course) {
        this.course = course;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return course == student.course && age == student.age && Objects.equals(country, student.country) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, country, name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "course=" + course +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
