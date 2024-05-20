package com.epam.school.utils;

public class Status {
    public static String calculateStatus(float grade) {
        System.out.println("grade: " + grade);
        if (grade >= 6) {
            return "APPROVED";
        } else {
            return "FAILED";
        }
    }
}