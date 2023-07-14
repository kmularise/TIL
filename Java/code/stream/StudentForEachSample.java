package stream;

import java.util.ArrayList;

public class StudentForEachSample {
    public static void main(String[] args) {
        StudentForEachSample sample = new StudentForEachSample();
        ArrayList<StudentDto> studentList = new ArrayList<>();
        studentList.add(new StudentDto("yoda", 43, 99, 10));
        studentList.add(new StudentDto("dddd", 30, 71, 85));
        studentList.add(new StudentDto("gunbbang", 32, 81, 75));
        sample.printStudentNames(studentList);
    }

    private void printStudentNames(ArrayList<StudentDto> students) {
        students.stream().map(StudentDto::getName).forEach(System.out::println);
    }
}
