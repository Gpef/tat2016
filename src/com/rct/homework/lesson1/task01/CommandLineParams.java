package com.rct.homework.lesson1.task01;


public class CommandLineParams {

    /**
     * Prints <code>args</code> array in reverted order
     * @param args arguments array to revert
     */
    public static void main(String[] args) {
        for (int i = args.length - 1; i >= 0; i--) {
            System.out.println("Аргумент " + i + " = " + args[i]);
        }
    }
}
