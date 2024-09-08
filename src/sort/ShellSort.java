package sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {

        int[] arr = {101,34,119,1,-1,89};
        shellSort(arr);
    }

    public static void shellSort(int[] arr){

        int temp = 0;
        int count = 0;

        for (int gap = arr.length/2; gap >0; gap/=2) {
            for (int i = gap; i < arr.length ; i++) {
                for (int j = i - gap; j >= 0 ; j-= gap) {
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序"+(++count)+"轮="+ Arrays.toString(arr));
        }
    }

    public static void shellSort2(int[] arr){
        for (int gap = arr.length/2; gap >0; gap/=2){
            for (int i = gap; i < arr.length ; i++){
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j-gap]){
                    while (j-gap >= 0 && temp < arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }


}
