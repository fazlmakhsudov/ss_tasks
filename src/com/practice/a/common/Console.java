package com.practice.a.common;

import java.util.Scanner;

/**
 * Get data from user
 */
public class Console {
    private int startRange;
    private int endRange;
    private int n; //number of threads

    public int getStartRange() {
        return startRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public int getN() {
        return n;
    }

    public void getDataFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert start range > 0");
        this.initializeStartRange(scanner);
        System.out.println("Insert end range > start range");
        this.initializeEndRange(scanner);
        System.out.println("Insert number of threads > 1");
        this.initializeN(scanner);
        scanner.close();
    }

    private void initializeStartRange(Scanner scanner) {
//        while (!scanner.hasNextInt()) {
//            System.out.println("wrong type inserted, try againg to type number");
//        }
        this.startRange = 0;
        int number = scanner.nextInt();
        while (number <= this.startRange) {
            System.out.println("wrong value inserted, try again to type number");
            number = scanner.nextInt();
        }
        this.startRange = number;
    }

    private void initializeEndRange(Scanner scanner) {
        int number = scanner.nextInt();
        while (number <= this.startRange) {
            System.out.println("wrong value inserted, try again to type number");
            number = scanner.nextInt();
        }
        this.endRange = number;
    }

    private void initializeN(Scanner scanner) {
        int number = scanner.nextInt();
        while (number <= 1) {
            System.out.println("wrong value inserted, try again to type number");
            number = scanner.nextInt();
        }
        this.n = number;
    }

    @Override
    public String toString() {
        return "Console{" +
                "startRange=" + startRange +
                ", endRange=" + endRange +
                ", n=" + n +
                '}';
    }
}
