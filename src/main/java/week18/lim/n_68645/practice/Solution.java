package week18.lim.n_68645.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/68645

import java.util.*;

public class Solution {
    public static int[] solution(int n) {
        int[][] map = new int[n][n];

        int num = 1;
        int x = -1;
        int y = 0;

        /*
            i % 3 == 0 -> 아래로 이동
            i % 3 == 1 -> 오른쪽으로 이동
            i % 3 == 2 -> 위로 이동
        */
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {
                    x++;
                } else if (i % 3 == 1) {
                    y++;
                } else {
                    x--;
                    y--;
                }
                map[x][y] = num++;
            }
        }

        List<Integer> mapList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                mapList.add(map[i][j]);
            }
        }

        return mapList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(4)));
        // [1,2,9,3,10,8,4,5,6,7]

        System.out.println(Arrays.toString(solution(5)));
        // [1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]

        System.out.println(Arrays.toString(solution(6)));
        // [1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
    }
}
