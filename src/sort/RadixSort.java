package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {

        int[] arr = {53,3,542,748,14,541};
        radixSort(arr);
    }

    public static void radixSort(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //max+""转化成字符串，求出最大数的位数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n = 1; i < arr.length; i++,n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0){
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //如果 index 当前是 0，执行这一行代码后，index 的值将变为 1，但是当前的操作仍然使用的是 index 为 0 的位置。
                        arr[index++] = bucket[k][l];

                    }
                }
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i+1)+"轮，对个位的排序处理 arr="+ Arrays.toString(arr));
        }
    }


}
