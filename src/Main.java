package src;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int[] userArray;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] arg) {

        System.out.println("---------------------------------------------------");
        System.out.println("Hello there and Welcome! My Name is Anthony Barker");
        System.out.println("------ THE SEARCHING AND SORTING ALGORITHM --------");
        System.out.println("---------------------------------------------------");
        System.out.println();
        System.out.println("What would you like to do today?");

        launchProgram();
    }

    public static void launchProgram() {
        boolean inMotion = true;
        while (inMotion) {
            System.out.println("1. Press 1 for Search");
            System.out.println("2. Press 2 for Sorting");
            System.out.println("3. Press 0 to Quit");

            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();
            System.out.println();
            if(choice == 1) {
                searchProgram();        // call searching method to handle - keep code clean
            }
            else if (choice == 2) {
                sortingProgram();       // call sorting method to handle - keep code clean
            }
            else {
                inMotion = false;       // end loop
                System.out.println();
                System.out.println("Thank you for trying my App. Goodbye!");
            }
        }
    }

    public static void searchProgram() {
        userArray = getUserValues();    // decided to get the values first
        boolean inMotion = true;
        while (inMotion) {
            System.out.println();
            System.out.println("What Type of Search Algorithm Would You Prefer?");
            System.out.println("1. Press 1 for Linear Search");
            System.out.println("2. Press 2 for Binary Search");
            System.out.println("3. Press 0 to go back");

            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();
            System.out.println();
            if(choice == 1) {       // using Linear Search
                System.out.print("What number are you searching for(type 'q' to go back): ");
                boolean searching = true;
                while (searching) {
                    String key = scanner.next();
                    Search search = new Search(userArray);
                    if(key.equalsIgnoreCase("q")) {
                        searching = false;                // go back up the loop
                    }
                    else if(search.linearSearch(Integer.parseInt(key))) {      // key is in the array
                        System.out.println("Great " + key + " was found in your Values at INDEX " + search.getPosition());
                        System.out.println();
                        System.out.print("Try another number( or 'q' to go back): ");
                    }
                    else {      // key is not in the array
                        System.out.println("Sorry, the number" + key + " was NOT in the Values you provided");
                        System.out.println();
                        System.out.print("Try another number( or 'q' to go back): ");
                    }
                }
            }
            else if (choice == 2) {
                System.out.print("What number are you searching for(type 'q' to go back): ");
                boolean searching = true;
                while (searching) {
                    String key = scanner.next();
                    Search search = new Search(userArray);
                    if(key.equalsIgnoreCase("q")) {
                        searching = false;                // go back up the loop
                    }
                    else if(search.linearSearch(Integer.parseInt(key))) {      // key is in the array
                        System.out.println("Great " + key + " was found in your Values at INDEX " + search.getPosition());
                        System.out.println();
                        System.out.print("Try another number( or 'q' to go back): ");
                    }
                    else {      // key is not in the array
                        System.out.println("Sorry, the number" + key + " was NOT in the Values you provided");
                        System.out.println();
                        System.out.print("Try another number( or 'q' to go back): ");
                    }
                }
            }
            else {
                inMotion = false;       // end loop
                System.out.println();
                System.out.println("What would you like to do today?");
            }
        }
    }

    public static void sortingProgram() {

    }

    private static int[] getUserValues() {
        System.out.print("Nice, how many values do you have: ");
        int total = scanner.nextInt();
        userArray = new int[total];
        System.out.println("Great, enter each numbers. After each number, press the ENTER key");
        for(int i = 0; i < total; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            userArray[i] = scanner.nextInt();
        }
        // done taking values
        System.out.println("Numbers entered: " + Arrays.toString(userArray));
        return userArray;
    }
}
