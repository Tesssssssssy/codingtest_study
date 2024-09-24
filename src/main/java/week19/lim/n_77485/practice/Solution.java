package week19.lim.n_77485.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/77485

import java.util.*;

public class Solution {
    /**
     *  rows x columns 크기인 행렬이 있습니다.
     *  행렬에는 1부터 rows x columns 까지의 숫자가 한 줄씩 순서대로 적혀있습니다.
     *  이 행렬에서 직사각형 모양의 범위를 여러 번 선택해, 테두리 부분에 있는 숫자들을 시계방향으로 회전시키려 합니다.
     *  각 회전은 (x1, y1, x2, y2)인 정수 4개로 표현하며, 그 의미는 다음과 같습니다.
     *
     *  x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전합니다.
     */
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[][] matrix = new int[rows][columns];
        int num = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = num++;
            }
        }

        List<Integer> result = new ArrayList<>();

        // 각 회전 처리

        for (int[] query : queries) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;

            int temp = matrix[x1][y1];
            int minVal = temp;

            // 맨 왼쪽 테두리를 위쪽으로 이동
            for (int i = x1; i < x2; i++) {
                matrix[i][y1] = matrix[i + 1][y1];
                minVal = Math.min(minVal, matrix[i][y1]);
            }

            // 아래쪽 테두리를 왼쪽으로 이동
            for (int i = y1; i < y2; i++) {
                matrix[x2][i] = matrix[x2][i + 1];
                minVal = Math.min(minVal, matrix[x2][i]);
            }

            // 오른쪽 테두리를 아래로 이동
            for (int i = x2; i > x1; i--) {
                matrix[i][y2] = matrix[i - 1][y2];
                minVal = Math.min(minVal, matrix[i][y2]);
            }

            // 위쪽 테두리를 오른쪽으로 이동
            for (int i = y2; i > y1; i--) {
                matrix[x1][i] = matrix[x1][i - 1];
                minVal = Math.min(minVal, matrix[x1][i]);
            }

            // (x1, y1) 값 재지정
            matrix[x1][y1 + 1] = temp;

            result.add(minVal);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int rows1 = 6;
        int columns1 = 6;
        int[][] queries1 = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        System.out.println(Arrays.toString(solution(rows1, columns1, queries1)));
        // [8, 10, 25]

        int rows2 = 3;
        int columns2 = 3;
        int[][] queries2 = {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}};
        System.out.println(Arrays.toString(solution(rows2, columns2, queries2)));
        // [1, 1, 5, 3]

        int rows3 = 100;
        int columns3 = 97;
        int[][] queries3 = {{1, 1, 100, 97}};
        System.out.println(Arrays.toString(solution(rows3, columns3, queries3)));
        // [1]
    }
}