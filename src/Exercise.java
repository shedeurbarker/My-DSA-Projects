package src;

import java.util.Arrays;
import java.util.Random;

public class Exercise{

//    static int[] array = {68, 91, 59, 61, 26, 0, 17, 80, 56, 68, 98, 68, 61, 72, 89, 72, 60, 4, 35, 19};
    static int[] array = new int[20];

    public static void main(String[] arg) {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            array[i] = rand.nextInt(99);
        }

        System.out.println("Array: " + Arrays.toString(array));

        int key = array[0];
        for(int i = 1; i < array.length; i++) {
            if(key < array[i]) {
                key = array[i]; // update key with bigger value
            }
        }
        System.out.print("Biggest Value is: " + key);
    }
}
