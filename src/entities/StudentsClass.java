package entities;

import java.util.*;

public class StudentsClass {
    private final int numberOfStudents;
    private LinkedList<Student> students;
    private final LinkedList<Integer> occupiedPlaces = new LinkedList<>();
    private final HashMap<Integer, Student> studentsPlaces = new HashMap<>();

    public StudentsClass(LinkedList<Student> students) {
        this.numberOfStudents = students.size();
        this.students = students;
        fillTheClass();
    }

    public LinkedList<Student> getStudents() {
        return new LinkedList<>(studentsPlaces.values());
    }

    private void fillTheClass() {
        for (int i = 0; i < students.size(); i++) {
            studentsPlaces.put(i + 1, students.get(i));
        }
    }

    public void seatStudents() {
//        seatByConcentrationProblem();
        seatByVision();
        seatByHand();
    }

    private static class StudentVisionComparator implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            return student2.getVisionRestriction() - student1.getVisionRestriction();
        }
    }


    private void seatByConcentrationProblem() {
        for (int i = 1; i < students.size(); i++) {
            if ((students.get(i).getConcentrationProblem()) && !(students.get(i - 1).getConcentrationProblem())) {
                Collections.swap(students, i, i - 1);
            }
        }
    }

    private void seatByVision() {
        LinkedList<Integer> possibleLocations;
        students.sort(new StudentVisionComparator());
        LinkedList<Student> copy = (LinkedList<Student>) students.clone();
        for (int i = 0; i < numberOfStudents; i++) {
            switch (students.get(i).getVisionRestriction()) {
                case 3:
                    possibleLocations = new LinkedList<>();
                    possibleLocations.addLast(2);
                    possibleLocations.addLast(3);
                    break;
                case 2:
                    possibleLocations = createList(12);
                    break;
                case 1:
                    possibleLocations = createList(18);
                    break;
                default:
                    possibleLocations = createList(31);
                    break;
            }
            int place = chosePlace(possibleLocations);
            Collections.swap(copy, copy.indexOf(students.get(i)), place);
//            swap(students.get(i), i-1, students.get(place), place-1);
        }
        students = copy;
        fillTheClass();
    }

    private LinkedList<Integer> createList(int length) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int j = 0; j < length; j++) {
            if (occupiedPlaces.contains(j))
                continue;
            list.addLast(j);
        }
        Collections.reverse(list);
        return list;
    }

    private int chosePlace(LinkedList<Integer> possibleLocations) {
        for (int currentPlace : possibleLocations) {
            if (occupiedPlaces.contains(currentPlace))
                continue;
            else {
                occupiedPlaces.addLast(currentPlace);
                return currentPlace;
            }
        }
        throw new RuntimeException("Не нашлось места");
    }


    private void seatByHand() {
        for (int i = 2; i < numberOfStudents; i += 2) {
            Student leftStudent = studentsPlaces.get(i - 1);
            Student rightStudent = studentsPlaces.get(i);
            if (leftStudent.getWorkingHand() && !rightStudent.getWorkingHand()) {
                swap(leftStudent, i - 1, rightStudent, i);
            }
        }
    }

    private void swap(Student student1, int student1Index, Student student2, int student2Index) {
        studentsPlaces.put(student1Index, student2);
        studentsPlaces.put(student2Index, student1);
    }

    public void showClass() {
        for (int i = 1; i <= numberOfStudents; i++) {
            if (i == numberOfStudents) {
                System.out.printf("%56s", studentsPlaces.get(i).getName() + " ");
                System.out.println();
            } else
                System.out.printf("%-10s", studentsPlaces.get(i).getName() + " ");
            if (i % 2 == 0) {
                System.out.printf("%-4s", "||");
            }
            if (i % 6 == 0) {
                System.out.println();
            }
        }
    }
}
