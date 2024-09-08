package search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {


        int[] arr = {1,8,10,89,1000,1234};
        System.out.println("index=" + fibSearch(arr,89));
    }
    //斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <maxSize ; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    public static int fibSearch(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        int k = 0;
        int mid = 0;
        int[] f = fib();

        while (high > f[k]-1){
            k++;
        }
        //如果 f[k] 小于或等于 a 的长度，新数组的内容是从 a 的第一个元素开始，复制 f[k] 个元素。
        //如果 f[k] 大于 a 的长度，新数组的前 a.length 个元素是从 a 中复制的，剩下的元素将用 0 填充。
        int[] temp = Arrays.copyOf(a,f[k]);
        for (int i = high+1; i < temp.length ; i++) {
            temp[i] = a[high];
        }

        while (low <= high){
            mid = low + f[k-1] - 1;
            if (key < temp[mid]){
                high = mid - 1;
                k--;
            }else if (key > temp[mid]){
                low = mid + 1;
                k -= 2;
            }else {
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
