package entities;

import java.io.*;
import java.util.LinkedList;

public class FileParser {
    private FileParser() {
    }

    public static LinkedList<Student> getStudentsList(String path) {
        LinkedList<Student> linkedList = new LinkedList<>();
        String row;
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if ((row = csvReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] data = row.split(",");
            Student student = new Student();
            student.setName(data[0]);
            student.setWorkingHand(data[1]);
            student.setVisionRestriction(data[2]);
            student.setHeight(data[3]);
            student.setConcentrationProblem(data[4]);
            linkedList.add(student);
        }
        try {
            csvReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        linkedList.removeFirst();
        return linkedList;
    }

    public static void writeStudentsList(LinkedList<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result.csv"));
        writer.write("Место,Имя\n");
        for (int i = 0; i < students.size(); i++) {
            writer.write(i+1 + "," + students.get(i).getName() + "\n");
        }
        writer.close();
    }
}
