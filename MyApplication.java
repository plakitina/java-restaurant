package com.company;

import java.util.Scanner;

public class MyApplication {
    private final Restaraunt restaraunt;
    private final Scanner scanner;

    public MyApplication(Restaraunt restaraunt) {
        this.restaraunt = restaraunt;
        scanner = new Scanner(System.in);
    }

    public void start() {
        Scanner scn = new Scanner(System.in);
        while(true) {
            System.out.println();
            System.out.println("Welcome to our restaraunt!");
            System.out.println("0. Exit");
            System.out.println("Choose free table (\"yes\" means free):");

            try {
                restaraunt.showTables();

                System.out.println("Choose free table by number");
                System.out.println("Number of table: ");
                int option = scn.nextInt();

                if(option == 0) {
                    System.exit(1);
                }

                while(!(restaraunt.chooseTable(option))) {
                    System.out.println("Selected table is not free, please choose another one: ");
                    option = scn.nextInt();
                }
                System.out.println("You have chosen table number " + option);

                System.out.println("Order dishes");

                restaraunt.showMenu();
                System.out.println();
                System.out.println("*****************************");
                System.out.println("Your current order:");
                restaraunt.showOrder();
                System.out.println("*****************************");
                System.out.println();
                System.out.println("Add new dish by ID or input 0 to make order");
                option = scn.nextInt();

                while(option != 0) {
                    restaraunt.addDish(option);
                    System.out.println();
                    System.out.println("*****************************");
                    System.out.println("Your current order:");
                    restaraunt.showOrder();
                    System.out.println("*****************************");
                    System.out.println();
                    System.out.println("Your total sum: " + restaraunt.getSum() + "$");
                    System.out.println("Add new dish by ID or input 0 to make order");
                    option = scn.nextInt();
                }
                System.out.println("You ordered");
                System.out.println("Your total sum of ordered dishes " + restaraunt.getSum() + "$");

                System.exit(1);
            } catch (Exception e) {

            }
        }
    }
}
