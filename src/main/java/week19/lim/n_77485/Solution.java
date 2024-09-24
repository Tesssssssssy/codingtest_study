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

            // 직사각형 테두리의 숫자들을 시계 방향으로 회전

            /*
                1. 왼쪽 테두리 위로 이동

                초기 상태:                변화 후:
                1   2   3   4   5          1   2   3   4   5
                6   7   8   9   10   ->    6   12  8   9   10
                11  12  13  14  15         11  17  13  14  15
                16  17  18  19  20         16  22  18  19  20
                21  22  23  24  25         21  22  23  24  25

                (2, 2)에서 (4, 2)까지 위로 한 칸씩 이동:
                7 -> 12, 12 -> 17, 17 -> 22
            */
            for (int i = x1; i < x2; i++) {
                matrix[i][y1] = matrix[i + 1][y1];
                minVal = Math.min(minVal, matrix[i][y1]);
            }

            /*
                2. 아래쪽 테두리 왼쪽으로 이동

                초기 상태:                변화 후:
                1   2   3   4   5          1   2   3   4   5
                6   12  8   9   10   ->    6   12  8   9   10
                11  17  13  14  15         11  17  13  14  15
                16  22  18  19  20         16  18  19  20  20
                21  22  23  24  25         21  22  23  24  25

                (4, 2)에서 (4, 4)까지 왼쪽으로 한 칸씩 이동:
                22 -> 18, 18 -> 19, 19 -> 20
            */
            for (int i = y1; i < y2; i++) {
                matrix[x2][i] = matrix[x2][i + 1];
                minVal = Math.min(minVal, matrix[x2][i]);
            }

            /*
                3. 오른쪽 테두리 아래로 이동

                초기 상태:                변화 후:
                1   2   3   4   5          1   2   3   4   5
                6   12  8   9   10   ->    6   12  8   9   10
                11  17  13  14  15         11  17  13  9   15
                16  18  19  20  20         16  18  19  14  20
                21  22  23  24  25         21  22  23  24  25

                (4, 4)에서 (2, 4)까지 아래로 한 칸씩 이동:
                19 -> 20, 14 -> 15, 9 -> 10
            */
            for (int i = x2; i > x1; i--) {
                matrix[i][y2] = matrix[i - 1][y2];
                minVal = Math.min(minVal, matrix[i][y2]);
            }

            /*
                4. 위쪽 테두리 오른쪽으로 이동

                초기 상태:                변화 후:
                1   2   3   4   5          1   2   3   4   5
                6   12  8   9   10   ->    6   12  12  8   10
                11  17  13  9   15         11  17  13  14  15
                16  18  19  14  20         16  22  18  19  20
                21  22  23  24  25         21  22  23  24  25

                (2, 4)에서 (2, 2)까지 오른쪽으로 한 칸씩 이동:
                3 -> 2, 2 -> 1
            */
            for (int i = y2; i > y1; i--) {
                matrix[x1][i] = matrix[x1][i - 1];
                minVal = Math.min(minVal, matrix[x1][i]);
            }

            // 저장해둔 temp(맨 왼쪽, 위) 값을 마지막에 넣기
            // 이유는 값이 시계방향으로 이동하면서 덮어 씌워지니까 처음 값을 마지막에 저장.
            matrix[x1][y1 + 1] = temp;
            /*
                최종 변화 후: (최초 (2, 2) 값 7 추가)
                1   2   3   4   5
                6   7  12   8   10
                11  17  13  14  15
                16  22  18  19  20
                21  22  23  24  25
            */

            result.add(minVal);  // 가장 작은 값을 결과에 추가
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