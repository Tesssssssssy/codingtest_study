package week3.kim.week3_2.dp;

import java.util.Arrays;

/**
 * [체육복]
 *
 * 위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다.
 * 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다.
 * 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
 *
 * 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
 *
 * 제한사항
 * 삼각형의 높이는 1 이상 500 이하입니다.
 * 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
 *
 * 입출력 예
 * triangle							                        result
 * [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]	30
*/

public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        // 30 반환
        System.out.println(ex2.solution(triangle));
    }


    public int solution(int[][] triangle) {
        // 삼각형의 높이
        int height = triangle.length;

        // 2차원 배열 생성
        int[][] dp = new int[height][height];

        // 삼각형의 꼭대기 값을 초기값으로 설정
        dp[0][0] = triangle[0][0];

        // 삼각형의 각 층을 순회하면서
        for (int i = 1; i < height; i++) {
            // 각 층의 각 요소를 순회하면서
            for (int j = 0; j <= i; j++) {
                // 왼쪽 위에서 내려오는 경우와 오른쪽 위에서 내려오는 경우 중 큰 값을 선택
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }

        // 마지막 층에서 가장 큰 값을 찾아 반환
        return Arrays.stream(dp[height - 1]).max().getAsInt();
    }
}