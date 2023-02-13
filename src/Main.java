import entities.Student;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList<Student> students = new LinkedList<>();
        String row;
        int studentsCount = 0;
        BufferedReader csvReader = new BufferedReader(new FileReader("initial_data\\Исходные данные - Учащиеся.csv"));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            Student student = new Student();
            student.setName(data[0]);
            student.setWorkingHand(data[1]);
            student.setVisionRestriction(data[2]);
            student.setHeight(data[3]);
            student.setConcentrationProblem(data[4]);
            students.add(student);
            studentsCount++;
        }
        csvReader.close();
        students.removeFirst();
        students.forEach(System.out::println);

        BufferedWriter writer = new BufferedWriter(new FileWriter("res.csv"));
        writer.write("Место,Имя\n");
        for (int i = 0; i < students.size(); i++) {
            writer.write(i+1 + "," + students.get(i).getName() + "\n");
        }
        writer.close();

    }
}