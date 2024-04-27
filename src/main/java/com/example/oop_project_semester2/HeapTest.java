package com.example.oop_project_semester2;

import java.util.*;

public class HeapTest {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(); // Create an array list of Integers
        long startTime = System.currentTimeMillis(); // Log the time we started the test
        int objectsCreated = 0; // Counter for tracking the number of objects created
        try {
            while (true) { // Infinite loop of creating new ints and adding them to the list
                Integer nextInt = 2;
                integerList.add(nextInt);
                objectsCreated++; // Increment the counter for each object created
            }
        } catch (OutOfMemoryError e) {
            long endTime = System.currentTimeMillis(); // Log the time the test finished
            long timeToException = endTime - startTime; // Calculate how long it took for the exception to occur
            System.out.println("Out of memory error occurred after " + timeToException / 1000 + " seconds"); // Display the info
            System.out.println("Objects created before the error: " + objectsCreated); // Display the number of objects created
        }
    }
}
