package src;

import java.util.Arrays;

public class Search {

    int[] myArrays;
    int position;
    long runtime = 0;
    static long startTime = 0;
    static long endTime = 0;

    public Search(int[] values) {
        this.myArrays = values;
    }

    public boolean linearSearch(int key) {
        startTime = System.nanoTime();
        for (int i = 0; i < myArrays.length; i++) {
            if (myArrays[i] == key) {
                position = i;
                endTime = System.nanoTime();
                runtime = (endTime - startTime);
                return true;
            }
        }
        return false;
    }

    public boolean binarySearch(int key) {
        // works on Sorted list
        startTime = System.nanoTime();
        Sorting sorting = new Sorting();
        myArrays = sorting.bubbleSort(myArrays);        // array sorted using bubble sort
        System.out.println("Numbers Sorted: " + Arrays.toString(myArrays));
        int left = 0;
        int right = myArrays.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (myArrays[mid] == key) {
                position = mid;
                endTime = System.nanoTime();
                runtime = (endTime - startTime);
                return true;
            } else if (myArrays[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public int getPosition() {
        return position;
    }

    public long getRuntime() {
        return runtime;
    }
}
