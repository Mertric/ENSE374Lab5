package main.java.gameoflife;

import main.java.gameoflife.core.Habitat;

import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args){
        int days = 0;
        Habitat habitat = new Habitat();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            habitat.printHabitat();
            System.out.print("Days Elapsed : " + days);
            days++;
            System.out.println("Enter x to advance simulation one step (quit to exit):");
            String input = keyboard.nextLine();
            if(input != null) {
                System.out.println("Your input is : " + input);
                if ("quit".equals(input)) {
                    System.out.println("Exit program");
                    exit = true;
                } else if ("x".equals(input)) {
                    habitat.move();
                }
            }
        }
        keyboard.close();
    }
}
