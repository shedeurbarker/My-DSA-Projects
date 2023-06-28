package src;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int[] userArray;
    static Scanner scanner = new Scanner(System.in);
    static int totalDefaultValues = 20;
    private static final int[] defaultValues = new int[totalDefaultValues];


    public static void main(String[] arg) {

        System.out.println("##########################################");
        System.out.println("#       Hello there and Welcome!         #");
        System.out.println("#               GROUP 47                 #");
        System.out.println("#   THE SEARCHING AND SORTING ALGORITHM  #");
        System.out.println("##########################################");
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
            String choice = scanner.next();
            System.out.println();
            if(choice.equalsIgnoreCase("1")) {
                searchProgram();        // call searching method to handle - keep code clean
            }
            else if(choice.equalsIgnoreCase("2")) {
                sortingProgram();       // call sorting method to handle - keep code clean
            }
            else if(choice.equalsIgnoreCase("0")) {
                inMotion = false;       // end loop
                System.out.println();
                System.out.println("Thank you for trying my App. Goodbye!");
            }
            else {
                System.out.println("Invalid Choice. Retry: ");
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
            String choice = scanner.next();
            System.out.println();

            if(isInteger(choice)) {
                if(choice.equals("1") || choice.equals("2")) {
//                    System.out.println("Your Array: " + Arrays.toString(userArray));
                    System.out.print("What number are you searching for(type 'q' to go back): ");
                    boolean searching = true;
                    while (searching) {
                        String key = scanner.next();
                        System.out.println();

                        Search search = new Search(userArray);  // create search Object
                        if(isInteger(key)) {
                            boolean result;
                            if(choice.equals("1")) {
                                System.out.println("USING LINEAR SEARCH ALGORITHM");
                                result = search.linearSearch(Integer.parseInt(key));
                            }
                            else {
                                System.out.println("USING BINARY SEARCH ALGORITHM");
                                result = search.binarySearch(Integer.parseInt(key));
                            }
                            if(result) {
                                System.out.println("Great " + key + " was found in your Values at INDEX " + search.getPosition());
                                System.out.println("Took Approximately: " + search.getRuntime() + " nanoseconds");
                            }
                            else {
                                System.out.println("Sorry, the number " + key + " was NOT in the Values you provided");
                            }
                            System.out.println();
                            System.out.print("Try another number( or 'q' to go back): ");
                        }
                        else if(key.equalsIgnoreCase("q")) {
                            searching = false;
                        }
                        else {
                            System.out.print("Invalid Number. Retry: ");
                        }
                    }
                }
                else if(choice.equals("0")) {
                    inMotion = false;
                }
                else {
                    System.out.print("Invalid Choice. Retry: ");
                }
            }
            else {
                System.out.print("Invalid Choice. Retry: ");
            }
        }
    }

    public static void sortingProgram() {
        userArray = getUserValues();    // decided to get the values first
        Sorting sorting = new Sorting();
        boolean inMotion = true;
        while (inMotion) {
            System.out.println();
            System.out.println("What Type of Sorting Algorithm Would You Prefer?");
            System.out.println("1. Press 1 for Bubble Sort");
            System.out.println("2. Press 2 for Selection Sort");
            System.out.println("3. Press 3 for Insertion Sort");
//            System.out.println("4. Press 4 for Merge Sort"); System.out.println("5. Press 5 for Quick Sort");
            System.out.println("0. Press 0 to go back");

            System.out.print("Your Choice: ");
            String choice = scanner.next();
            System.out.println();

            if(isInteger(choice)) {
                if(Integer.parseInt(choice) > 0 && Integer.parseInt(choice) < 6) {  // options available
                    System.out.println("Current Array: " + Arrays.toString(userArray));
                    System.out.println();
                    int[] sorted = new int[userArray.length];
                    switch (choice) {
                        case "1" -> {
                            System.out.println("SORTED USING BUBBLE SORT");
                            sorted = sorting.bubbleSort(copyArray(userArray));

                        }
                        case "2" -> {
                            System.out.println("SORTED USING SELECTION SORT");
                            sorted = sorting.selectionSort(copyArray(userArray));
                        }
                        case "3" -> {
                            System.out.println("SORTED USING INSERTION SORT");
                            sorted = sorting.insertionSort(copyArray(userArray));
                        }
                        case "4" -> {
                            int left;
                            int right;
                            System.out.println("Enter Left Index");
                            left = scanner.nextInt();
                            System.out.println("Enter Right Index");
                            right = scanner.nextInt();
                            System.out.println("SORTED USING MERGE SORT");
                            sorted = sorting.mergeSort(copyArray(userArray), left, right);
                        }

                        //sorted = sorting.mergeSort();
                        case "5" -> {
                            int low;
                            int high;
                            System.out.println("Enter Low Value");
                            low = scanner.nextInt();
                            System.out.println("Enter High Value");
                            high = scanner.nextInt();
                            System.out.println("SORTED USING QUICK SORT");
                            sorted = sorting.quickSort(copyArray(userArray), low, high);
                        }

                        // sorted = sorting.quickSort();
                    }
                    System.out.println("New Array: " + Arrays.toString(sorted));
                    System.out.println("Took Approximately: " + sorting.getRuntime() + " nanoseconds");
                }
                else if(choice.equals("0")) {
                    inMotion = false;
                }
                else {
                    System.out.print("Invalid Choice. Retry: ");
                }
            }
            else {
                System.out.print("Invalid Choice. Retry: ");
            }
        }
    }

    private static int[] getUserValues() {
        System.out.println("Nice, how many values do you have?");
        System.out.print("or Press 'd' for default values): ");
        boolean adding = true;
        while (adding) {
            String total = scanner.next();
            if(isInteger(total)) {
                userArray = new int[Integer.parseInt(total)];
                System.out.println();
                System.out.println("Great, enter each numbers. After each number, press the ENTER key");
                for(int i = 0; i < Integer.parseInt(total); i++) {
                    System.out.print("Number " + (i + 1) + ": ");
                    boolean fetching = true;
                    while(fetching) {
                        String each = scanner.next();
                        if(isInteger(each)) {
                            userArray[i] = Integer.parseInt(each);
                            fetching = false;
                        }
                        else {
                            System.out.print("Sorry, retry Number " + (i + 1) + ": ");
                        }
                    }
                }
                adding = false;
                // done taking values
                System.out.println();
                System.out.println("Numbers entered:\n" + Arrays.toString(userArray));
            } else if (total.equalsIgnoreCase("d")) {
                adding = false;
                // generating random numbers
                Random rand = new Random();
                for (int i = 0; i < totalDefaultValues; i++) {
                    defaultValues[i] = rand.nextInt(99);
                }
                System.out.println();
                System.out.print("Default Values\n" + Arrays.toString(defaultValues));
                System.out.println();

                userArray = defaultValues;

            } else {
                System.out.print("Invalid Number. Enter Number of Values: ");
            }
        }
        return userArray;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int[] copyArray(int[] userArray){
        int[] temp = new int[userArray.length];
        System.arraycopy(userArray, 0, temp, 0, userArray.length);
        return temp;
    }
}
