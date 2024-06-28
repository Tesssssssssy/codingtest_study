package week8.lim.week8_2.n_1018;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dimensions = br.readLine().split(" ");
        int N = Integer.parseInt(dimensions[0]);
        int M = Integer.parseInt(dimensions[1]);

        // 보드 상태를 저장할 2차원 배열
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int minChange = Integer.MAX_VALUE;

        // 8x8 크기의 체스판을 검사하는 모든 시작 위치에 대해 반복
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                minChange = Math.min(minChange, calculateChange(board, i, j));
            }
        }

        System.out.println(minChange);
    }

    // 주어진 시작 위치에서 8x8 체스판의 변경 수를 계산하는 함수
    private static int calculateChange(char[][] board, int startX, int startY) {
        // 두 가지 패턴의 체스판
        char[][] pattern1 = {
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}
        };
        char[][] pattern2 = {
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}
        };

        int count1 = 0; // pattern1과 비교하여 변경할 개수
        int count2 = 0; // pattern2와 비교하여 변경할 개수

        // 8x8 크기의 체스판을 검사
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[startX + x][startY + y] != pattern1[x][y]) {
                    count1++;
                }
                if (board[startX + x][startY + y] != pattern2[x][y]) {
                    count2++;
                }
            }
        }

        // 두 패턴 중 더 적은 변경이 필요한 경우를 반환
        return Math.min(count1, count2);
    }
}

