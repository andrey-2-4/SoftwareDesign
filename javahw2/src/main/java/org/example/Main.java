package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

class Students {
    private static Random randGen = new Random();
    private List<Student> list = new ArrayList<>();
    private List<Student> studentsNotAnswered = new ArrayList<>();
    public void addStudent(String name) {
        Student temp = new Student(name);
        list.add(temp);
        studentsNotAnswered.add(temp);
    }
    public boolean allAnswered() {
        return studentsNotAnswered.size() == 0;
    }
    public void displayAllStudents() {
        System.out.println("Все студенты:");
        for (Student student : list) {
            System.out.println(student.getName());
        }
    }
    public void displayStudentsWhoAnswered() {
        System.out.println("Все студенты, что получили оценку:");
        for (Student student : list) {
            if (student.answered) {
                student.displayInfo();
            }
        }
    }
    public void makeRandomStudentAnswer() {
        int i = randGen.nextInt(studentsNotAnswered.size());
        studentsNotAnswered.get(i).answer();
        studentsNotAnswered.remove(i);
    }

    private class Student {
        private boolean wasHere = false;
        private boolean answered = false;
        private int mark = 0;
        private String name;

        private Student(String name) {
            this.name = name;
        }
        private String getName() {
            return this.name;
        }

        private void answer() {
            answered = true;
            Scanner in = new Scanner(System.in);
            System.out.println("Отвечает " + name);
            System.out.println("Присутствует ли на паре? ('true'/'false')");
            wasHere = in.nextBoolean();
            if (wasHere) {
                System.out.print("Оценка за ответ: ");
                mark = in.nextInt();
            } else {
                mark = 0;
                System.out.println("Оценка за ответ: 0 т.к. студент не смог ответить (потому что его нет)");
            }
        }

        private void displayInfo() {
            System.out.println(name + ": " + mark);
        }
    }
}



public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Students students = new Students();
        System.out.println("Чтобы завершить добавление студентов - введите '0'");
        String string = "0";
        while (true) {
            System.out.print("Введите имя студента: ");
            string = in.nextLine();
            if ("0".equals(string)) {
                break;
            }
            students.addStudent(string);
        }
        System.out.println("Чтобы заставить отвечать случайного студента введите 'r'");
        System.out.println("Чтобы вывести список всех студентов введите 'all'");
        System.out.println("Чтобы вывести список всех ОТВЕТИВШИХ студентов введите 'ans'");
        System.out.println("Чтобы завершить досрочно введите '0'");
        boolean finish = false;
        while (!finish && !students.allAnswered()) {
            string = in.nextLine();
            switch (string) {
                case "r":
                    students.makeRandomStudentAnswer();
                    break;
                case "all":
                    students.displayAllStudents();
                    break;
                case "ans":
                    students.displayStudentsWhoAnswered();
                    break;
                case "0":
                    finish = true;
                    break;
            }
        }
        in.close();
        System.out.println("Вы либо завршили сами, либо больше некого спрашивать");
        students.displayAllStudents();
        students.displayStudentsWhoAnswered();
    }
}