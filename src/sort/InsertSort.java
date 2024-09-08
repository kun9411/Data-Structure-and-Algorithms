package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {

        int[] arr = {101,34,119,1,-1,89};

        insertSort(arr);


    }

    public static void insertSort(int[] arr){

        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 0; i < arr.length; i++) {

            insertVal = arr[i];
            insertIndex = i -1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }

            System.out.println("第"+i+"轮插入");
            System.out.println(Arrays.toString(arr));
        }

    }
}
