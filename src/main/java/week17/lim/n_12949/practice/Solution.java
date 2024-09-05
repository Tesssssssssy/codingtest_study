package week17.lim.n_12949.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12949?language=java

public class Solution {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        /*
             1   4       3   3
             3   2   x   3   3
             4   1

             3 x 2       2 x 2  =  3 x 2
             i   k       k   j     i   j
        */
        int[][] answer = new int[arr1.length][arr2[1].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                for (int k = 0; k < arr1[0].length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] arr1_1 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2_1 = {{3, 3}, {3, 3}};
        System.out.println(solution(arr1_1, arr2_1));
        // [[15, 15], [15, 15], [15, 15]]

        int[][] arr1_2 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] arr2_2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
        System.out.println(solution(arr1_2, arr2_2));
        // [[22, 22, 11], [36, 28, 18], [29, 20, 14]]
    }
}