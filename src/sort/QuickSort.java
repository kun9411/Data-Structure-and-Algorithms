package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -2, 900, 4561};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr=" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int l = left;
            int r = right;
            int pivot = arr[(left + right) / 2];

            while (l <= r) {
                while (arr[l] < pivot) {
                    l += 1;
                }
                while (arr[r] > pivot) {
                    r -= 1;
                }
                if (l <= r) {
                    // Swap arr[l] and arr[r]
                    int temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;
                    l += 1;
                    r -= 1;
                }
            }

            // Recursively sort the left and right partitions
            if (left < r) {
                quickSort(arr, left, r);
            }
            if (l < right) {
                quickSort(arr, l, right);
            }
        }
    }
}
