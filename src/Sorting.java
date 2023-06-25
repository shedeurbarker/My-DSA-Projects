package src;

public class Sorting {

    int[] myArrays;
    int[] sortedArray;

    public Sorting(int[] myArrays) {
        this.myArrays = myArrays;
    }

    public int[] bubbleSort() {
        int n = myArrays.length;
        sortedArray = new int[n];       // initialize sorted array variable
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (myArrays[j] > myArrays[j + 1]) {
                    int temp = myArrays[j];
                    sortedArray[j] = myArrays[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        return sortedArray;
    }

    public int[] selectionSort() {
        int n = myArrays.length;
        sortedArray = new int[n];       // initialize sorted array variable
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (myArrays[j] < myArrays[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = myArrays[minIndex];
            sortedArray[minIndex] = myArrays[i];
            sortedArray[i] = temp;
        }
        return sortedArray;
    }

    public int[] insertionSort() {
        int n = myArrays.length;
        sortedArray = new int[n];       // initialize sorted array variable
        for (int i = 1; i < n; i++) {
            int key = myArrays[i];
            int j = i - 1;
            while (j >= 0 && myArrays[j] > key) {
                myArrays[j + 1] = myArrays[j];
                j--;
            }
            sortedArray[j + 1] = key;
        }
        return sortedArray;
    }

    public void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }
    public void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = myArrays[left + i];
        }
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

    public void quickSort(int low, int high) {
    if (low < high) {
        int pi = partition(low, high);
        quickSort(low, pi - 1);
        quickSort(pi + 1, high);
    }
}
    public int partition(int low, int high) {
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
}
