package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileIterator fileIterator = new FileIterator("src/main/java/org/example/FileIterator.java");
            while(fileIterator.hasNext()) {
                System.out.println(fileIterator.next());
            }
        } catch (IOException e) {
            System.out.println("Исключение при создании fileIterator");
        } catch (Exception e) {
            System.out.println("Исключение: " + e.getMessage());
        }
    }
}