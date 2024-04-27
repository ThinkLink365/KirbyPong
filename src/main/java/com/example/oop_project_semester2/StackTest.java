package com.example.oop_project_semester2;

public class StackTest {
    static int i = 0;
    static int objectsCreated = 0;
    static long startTime;
    static long endTime;

    // Method to perform operation on array elements recursively
    public static long processArray(int index) {
        try {
            while (true) {
                // Creating a new array for each call
                long[] arr = new long[1000000];

                // Incrementing the count of objects created
                objectsCreated++;
                // Recursive call
                return processArray(index + 1);
            }
        } catch (StackOverflowError e) {
            // Catching StackOverflowError
            endTime = System.currentTimeMillis();
            System.out.println("StackOverflowError caught!");
            return -1; // returning -1 to indicate the error
        }
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis(); // Capture start time

        // Recursive call without any terminating condition
        StackTest.processArray(i);

        // Printing data after recursion ends
        System.out.println("Objects created before the error: " + objectsCreated);
        System.out.println("Duration: " + ((endTime - startTime) / 1000) + " seconds");
    }
}
