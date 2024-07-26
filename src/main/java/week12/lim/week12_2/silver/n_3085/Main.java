package week12.lim.week12_2.silver.n_3085;

// 문제: https://www.acmicpc.net/problem/3085

import java.io.*;

public class Main {
    /**
     *  - N × N 크기에 사탕을 채워 놓는다.
     *  - 사탕의 색은 모두 같지 않을 수도 있다.
     *  - 상근이는 사탕의 색이 다른 인접한 두 칸을 고른다.
     *    그 다음 고른 칸에 들어있는 사탕을 서로 교환한다.
     *    이제, 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 또는 열)을 고른 다음 그 사탕을 모두 먹는다.
     *
     *  - 사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수를 구하는 프로그램
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int maxCandies = 0;

        // 모든 셀에 대해 검사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 오른쪽 셀과 교환
                if (j < N - 1) {
                    swap(board, i, j, i, j + 1);
                    maxCandies = Math.max(maxCandies, calculateMaxCandies(board, N));
                    swap(board, i, j, i, j + 1);  // 원상복구
                }
                // 아래쪽 셀과 교환
                if (i < N - 1) {
                    swap(board, i, j, i + 1, j);
                    maxCandies = Math.max(maxCandies, calculateMaxCandies(board, N));
                    swap(board, i, j, i + 1, j);  // 원상복구
                }
            }
        }

        System.out.println(maxCandies);
    }

    // 위치 (x1, y1)과 (x2, y2)의 사탕을 교환하는 메소드
    private static void swap(char[][] board, int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    // 보드에서 가장 긴 연속된 사탕의 길이를 계산
    private static int calculateMaxCandies(char[][] board, int N) {
        /*
            연속된 같은 색의 사탕이 가장 많이 나열된 길이를 계산
            한 번의 교환 후에 먹을 수 있는 최대 사탕의 수를 구하는 데 사용
        */

        int maxLen = 0;

        for (int i = 0; i < N; i++) {
            int countRow = 1;
            // 현재 검사중인 행에서 연속된 같은 색의 사탕의 수를 세는 데 사용
            int countCol = 1;
            // 현재 검사중인 열에서 연속된 같은 색의 사탕의 수를 세는 데 사용

            for (int j = 1; j < N; j++) {
                // 행 검사
                if (board[i][j] == board[i][j - 1]) {
                    // 현재 사탕(board[i][j])이 이전 사탕(board[i][j - 1])과 색이 같은지 검사
                    countRow++;
                } else {
                    maxLen = Math.max(maxLen, countRow);
                    countRow = 1;
                }
                // 열 검사
                if (board[j][i] == board[j - 1][i]) {
                    // 현재 사탕(board[j][i])이 바로 위의 사탕(board[j - 1][i])과 색이 같은지 검사
                    countCol++;
                } else {
                    maxLen = Math.max(maxLen, countCol);
                    countCol = 1;
                }
            }
            maxLen = Math.max(maxLen, countRow);
            maxLen = Math.max(maxLen, countCol);
            /*
                루프가 종료되면, 마지막 countRow 와 countCol 값이 아직 maxLen 과 비교되지 않았을 수 있다.
                따라서 루프 종료 후에 한 번 더 maxLen 을 업데이트 .
            */
        }

        return maxLen;
    }
}
