package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//        System.out.println("resIndex=" + resIndex);
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1000);

    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){
        if (left > right){
            return -1;
        }
        int mid = (left + right)/2;
        int midVal = arr[mid];

        if (findVal > midVal){
            return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal < midVal){
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    public static List<Integer> binarySearch2(int[] arr,int left, int right, int findVal){
        if (left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){
            return binarySearch2(arr,mid+1,right,findVal);
        }else if (findVal < midVal){
            return binarySearch2(arr,left,mid-1,findVal);
        }else {
            ArrayList<Integer> resIndexList = new ArrayList<>();

            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }

            resIndexList.add(mid);

            temp = mid + 1;
            while (true){
                if (temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }

            return resIndexList;
        }
    }

    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        System.out.println("插值查找次数");

        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return -1;
        }
        //插值查找适用于数据分布比较均匀的数组。如果数组中的数据分布不均匀（如存在大量重复值或极端值），
        // 插值查找的效率可能不如二分查找，甚至可能退化到线性查找。
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal < midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
}
