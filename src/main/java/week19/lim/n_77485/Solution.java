package week19.lim.n_77485;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/77485

import java.util.*;

public class Solution {
    public static int[] solution(int rows, int columns, int[][] queries) {
        // 행렬 초기화
        int[][] matrix = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = num++;
            }
        }

        // 결과를 저장할 리스트
        List<Integer> result = new ArrayList<>();

        // 각 회전 처리
        for (int[] query : queries) {
            // 쿼리 정보: x1, y1, x2, y2
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;

            // 테두리 회전 전 가장 왼쪽 위 숫자를 저장하고 시작
            int temp = matrix[x1][y1];
            int minVal = temp;

            // 왼쪽 테두리 위로 이동
            for (int i = x1; i < x2; i++) {
                matrix[i][y1] = matrix[i + 1][y1];
                minVal = Math.min(minVal, matrix[i][y1]);
            }

            // 아래쪽 테두리 왼쪽으로 이동
            for (int i = y1; i < y2; i++) {
                matrix[x2][i] = matrix[x2][i + 1];
                minVal = Math.min(minVal, matrix[x2][i]);
            }

            // 오른쪽 테두리 아래로 이동
            for (int i = x2; i > x1; i--) {
                matrix[i][y2] = matrix[i - 1][y2];
                minVal = Math.min(minVal, matrix[i][y2]);
            }

            // 위쪽 테두리 오른쪽으로 이동
            for (int i = y2; i > y1; i--) {
                matrix[x1][i] = matrix[x1][i - 1];
                minVal = Math.min(minVal, matrix[x1][i]);
            }

            // 저장해둔 temp(맨 왼쪽, 위) 값을 마지막에 넣기
            // 이유는 값이 시계방향으로 이동하면서 덮어 씌워지니까 처음 값을 마지막에 저장.
            matrix[x1][y1 + 1] = temp;
            result.add(minVal);  // 가장 작은 값을 결과에 추가
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        // 예제 1
        int rows1 = 6;
        int columns1 = 6;
        int[][] queries1 = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        System.out.println(Arrays.toString(solution(rows1, columns1, queries1)));
        // [8, 10, 25]

        // 예제 2
        int rows2 = 3;
        int columns2 = 3;
        int[][] queries2 = {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}};
        System.out.println(Arrays.toString(solution(rows2, columns2, queries2)));
        // [1, 1, 5, 3]

        // 예제 3
        int rows3 = 100;
        int columns3 = 97;
        int[][] queries3 = {{1, 1, 100, 97}};
        System.out.println(Arrays.toString(solution(rows3, columns3, queries3)));
        // [1]
    }
}