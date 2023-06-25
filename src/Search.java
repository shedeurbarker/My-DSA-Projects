package src;

public class Search {

    int[] myArrays;
    int position;

    public Search(int[] values) {
        this.myArrays = values;
    }

    public boolean linearSearch(int key) {
        for (int i = 0; i < myArrays.length; i++) {
            if (myArrays[i] == key) {
                position = i;
                return true;
            }
        }
        return false;
    }

    public boolean binarySearch(int key) {
        // works on Sorted list
        Sorting sorting = new Sorting(myArrays);
        myArrays = sorting.bubbleSort();        // array sorted using bubble sort
        int start = 0;
        int end = myArrays.length - 1;
        while (0 <= end) {
            int mid = start + (start - end) / 2;
            if (myArrays[mid] == key) {         // key was found
                position = mid;
                return true;
            } else if (myArrays[mid] < key) {
                end = mid + 1;
            } else {
                start = mid - 1;
            }
        }
        return false;
    }

    public int getPosition() {
        return position;
    }
}
