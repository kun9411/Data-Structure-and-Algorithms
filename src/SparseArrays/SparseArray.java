package SparseArrays;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //1.遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值放到sparseArr中
        int cout = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    cout++;
                    sparseArr[cout][0] = i;
                    sparseArr[cout][1] = j;
                    sparseArr[cout][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();

        saveSparseArray(sparseArr, "map.data");

        int[][] restoredSparseArr = readSparseArray("map.data");
//
        int chessArr2[][] = new int[restoredSparseArr[0][0]][restoredSparseArr[0][1]];

        for (int i = 1; i < restoredSparseArr.length; i++) {
            chessArr2[restoredSparseArr[i][0]][restoredSparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("恢复后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }

    public static int[][] readSparseArray(String fileName) {
        int[][] sparseArr = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String[] firstLine = br.readLine().split(" ");
            int rows = Integer.parseInt(firstLine[0]);
            int cols = Integer.parseInt(firstLine[1]);
            int sum = Integer.parseInt(firstLine[2]);
            sparseArr = new int[sum + 1][3];
            sparseArr[0][0] = rows;
            sparseArr[0][1] = cols;
            sparseArr[0][2] = sum;

            for (int i = 1; i <= sum; i++) {
                String[] line = br.readLine().split(" ");
                sparseArr[i][0] = Integer.parseInt(line[0]);
                sparseArr[i][1] = Integer.parseInt(line[1]);
                sparseArr[i][2] = Integer.parseInt(line[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sparseArr;
    }

    public static void saveSparseArray(int[][] sparseArr, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int[] row : sparseArr) {
                for (int data : row) {
                    bw.write(data + " ");
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
