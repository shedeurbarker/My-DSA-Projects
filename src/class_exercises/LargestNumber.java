package src.class_exercises;

import java.util.Arrays;
import java.util.Random;

public class LargestNumber {

    static int[] array = new int[20];   // I wish to generate the arrays randomly

    public static void main(String[] arg) {
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            array[i] = rand.nextInt(99);
        }

        System.out.println("Array: " + Arrays.toString(array));

        int key = array[0];
        for(int i = 1; i < array.length; i++) {
            if(key < array[i]) {
                key = array[i];         // update key with bigger value
            }
        }
        System.out.print("Biggest Value is: " + key);
    }
}
