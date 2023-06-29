package src;

import java.util.Arrays;

public class Sorting {
    long runtime = 0;
    static long startTime = 0;
    static long endTime = 0;

    public int[] bubbleSort(int[] myArrays) {
        startTime = System.nanoTime();
        int n = myArrays.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (myArrays[j] > myArrays[j + 1]) {
                    int temp = myArrays[j];
                    myArrays[j] = myArrays[j + 1];
                    myArrays[j + 1] = temp;
                }
            }
        }
        endTime = System.nanoTime();
        runtime = (endTime - startTime);
        return myArrays;
    }

    public int[] selectionSort(int[] myArrays) {
        startTime = System.nanoTime();
        int n = myArrays.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (myArrays[j] < myArrays[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = myArrays[minIndex];          // pull value from this index
            myArrays[minIndex] = myArrays[i];       // store the value old value at the empty index
            myArrays[i] = temp;         // replace value
        }
        endTime = System.nanoTime();
        runtime = (endTime - startTime);
        return myArrays;
    }

    public int[] insertionSort(int[] myArrays) {
        startTime = System.nanoTime();
        int n = myArrays.length;
        for (int i = 1; i < n; i++) {   // loops through the array
            int key = myArrays[i];      // take the current value
            int j = i - 1;
            while (j >= 0 && myArrays[j] > key) {   // compare with array until right position is found
                myArrays[j + 1] = myArrays[j];      // right position is found
                j--;
            }
            myArrays[j + 1] = key;                  // update the array
        }
        endTime = System.nanoTime();
        runtime = (endTime - startTime);
        return myArrays;
    }

    public int[] mergeSort(int[] arr, int left, int right) {
        startTime = System.nanoTime();
    if (left < right) {
        int mid = (left + right) / 2;
        arr = mergeSort(arr, left, mid);
        arr = mergeSort(arr, mid + 1, right);
        arr = merge(arr, left, mid, right);
    }
        endTime = System.nanoTime();
        runtime = (endTime - startTime);
    return arr;
}

    public int[] merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    int[] L = new int[n1];
    int[] R = new int[n2];
     //noinspection ManualArrayCopy
     for (int i = 0; i < n1; i++) {
        L[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = arr[mid + 1 + j];
    }
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
    return arr;
}

    public int[] quickSort(int[] myArrays, int low, int high) {
        startTime = System.nanoTime();
        if (low < high) {
            int pi = partition(myArrays, low, high);
            quickSort(myArrays, low, pi - 1);
            quickSort(myArrays, pi + 1, high);
        }
        endTime = System.nanoTime();
        runtime = (endTime - startTime);
        return myArrays;
    }

    public int partition(int[] myArrays, int low, int high) {
        int pivot = myArrays[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (myArrays[j] < pivot) {
                i++;
                int temp = myArrays[i];
                myArrays[i] = myArrays[j];
                myArrays[j] = temp;
            }
        }
        int temp = myArrays[i + 1];
        myArrays[i + 1] = myArrays[high];
        myArrays[high] = temp;
        return i + 1;
    }

    public int[] radixSort(int[] arr) {
        startTime = System.nanoTime();
// Find the maximum number to determine the number of digits
        int max = Arrays.stream(arr).max().getAsInt();

        // Perform counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
        endTime = System.nanoTime();
        runtime = (endTime - startTime);
        return arr;
    }

    public void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Initialize count array
        Arrays.fill(count, 0);

        // Count the occurrences of each digit
        for (int j : arr) {
            count[(j / exp) % 10]++;
        }

        // Calculate cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to the original array
        System.arraycopy(output, 0, arr, 0, n);
    }

    public int[] shellSort(int[] arr) {
        startTime = System.nanoTime();
        int n = arr.length;
        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform insertion sort for elements at the current gap
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }

                arr[j] = temp;
            }
        }
        endTime = System.nanoTime();
        runtime = (endTime - startTime);
        return arr;
    }

    public long getRuntime() {
        return runtime;
    }
}
