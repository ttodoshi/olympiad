import entities.StudentsClass;
import entities.FileParser;
import entities.Student;

import java.io.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList<Student> students = FileParser.getStudentsList("initial_data\\Исходные данные - Учащиеся.csv");

        StudentsClass studentsClass = new StudentsClass(students);
        studentsClass.showClass();
        System.out.println();

        studentsClass.seatStudents();
        studentsClass.showClass();

        FileParser.writeStudentsList(studentsClass.getStudents());
    }
}