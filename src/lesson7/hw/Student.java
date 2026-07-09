package lesson7.hw;

import java.util.Objects;

public class Student {
    private String fullname;
    private int age;
    private String subject;
    private int grade;

    public Student(String fullname, int age, String subject) {
        this.age = age;
        this.subject = subject;
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public String getSubject() {
        return subject;
    }

    public String getFullname() {
        return fullname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullname='" + fullname + '\'' +
                ", age=" + age +
                ", subject='" + subject + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && grade == student.grade && Objects.equals(subject, student.subject) && Objects.equals(fullname, student.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, subject, fullname, grade);
    }
}
