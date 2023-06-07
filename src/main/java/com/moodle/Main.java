package main.java.com.moodle;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Boolean> booleanList = List.of(true, true, true); // Example list

        int sum = 0;

        for (Boolean value : booleanList) {
            sum += value ? 1 : 0;
        }

        int length = booleanList.size();

        if (booleanList.isEmpty()) {
            throw new IllegalArgumentException("List is empty");

        } else if (sum == 0) {
            System.out.println("Color: red");
            throw new IllegalStateException("All transactions are failed");
        } else if (length > sum) {
            System.out.println("Color: orange");
            throw new RuntimeException("There are some failed transactions");
        } else if (length == sum) {
            System.out.println("Color: green");
        }
    }
}