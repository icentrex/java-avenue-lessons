package lesson7.hw;

public class GradeBookDB {
    private final Student[] students = {
            new Student("БатрудиновПК", 18, "Математика"),
            new Student("ХарламовАН", 19, "Черчение"),
            new Student("ГурченкоЛД", 22, "Литература"),
            new Student("КравецТВ", 20, "Физкультура"),
            new Student("ЛеоновНК", 20, "Схемотехника"),
            new Student("АбдуловаЕД", 17, "Компьютерные сети")
    };

    public void addGrade(String fullname, int grade) {
        for (Student student : students) {
            if (student.getFullname().equalsIgnoreCase(fullname)) {
                student.setGrade(grade);
            }
        }
    }

    public void addSomeGrades(String studentsFullnames, String grades) {
        String[] fullnamesValues = studentsFullnames.split("\\s+");
        String[] gradesValues = grades.split("\\s+");
        for (int index = 0; index < fullnamesValues.length; index++) {
            addGrade(fullnamesValues[index], Integer.parseInt(gradesValues[index]));
        }
    }

    public void showGrades() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public double calcAverageGrade() {
        int sumOfGrades = 0;
        for (Student student : students) {
            sumOfGrades += student.getGrade();
        }
        return (double) sumOfGrades / students.length;
    }
}
