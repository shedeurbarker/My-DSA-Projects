package src;

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
        int[] sortedArray = new int[n];
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

    public void mergeSort(int[] myArrays, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(myArrays, left, mid);
            mergeSort(myArrays, mid + 1, right);
            merge(myArrays, left, mid, right);
        }
    }

    public void merge(int[] myArrays, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(myArrays, left, L, 0, n1);
        for (int j = 0; j < n2; j++) {
            R[j] = myArrays[mid + 1 + j];
        }
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                myArrays[k] = L[i];
                i++;
            } else {
                myArrays[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            myArrays[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            myArrays[k] = R[j];
            j++;
            k++;
        }
    }

    public void quickSort(int[] myArrays, int low, int high) {
    if (low < high) {
        int pi = partition(myArrays, low, high);
        quickSort(myArrays, low, pi - 1);
        quickSort(myArrays, pi + 1, high);
    }
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

    public long getRuntime() {
        return runtime;
    }
}
