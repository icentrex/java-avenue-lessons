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

    public void addGrade(int id, int grade) {
        students[id].setGrade(grade);
        System.out.printf("Оценка %s добавлена!%n", students[id].getFullname());
    }

    public void addSeveralGrades(String studentsIdString, String studentsGradesString) {
        String[] idValues = studentsIdString.split("\\s+");
        String[] gradeValues = studentsGradesString.split("\\s+");
        for (int index = 0; index < idValues.length; index++) {
            addGrade(Integer.parseInt(idValues[index]), Integer.parseInt(gradeValues[index]));
        }
    }

    public void showGrades() {
        for (int index = 0; index < students.length; index++) {
            System.out.printf("Id: %d, Имя: %s, Возраст: %d, Предмет: %s, Оценка: %d%n",
                    index,
                    students[index].getFullname(),
                    students[index].getAge(),
                    students[index].getSubject(),
                    students[index].getGrade());
        }
    }

    public void showStudents() {
        for (int index = 0; index < students.length; index++) {
            System.out.printf("Id: %d, Имя: %s, Возраст: %d, Предмет: %s%n",
                    index,
                    students[index].getFullname(),
                    students[index].getAge(),
                    students[index].getSubject());
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
