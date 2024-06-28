package week8.lim.week8_2.n_1018;

// 문제: https://www.acmicpc.net/problem/1018

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String[] dimensions = br.readLine().split(" ");
        int N = Integer.parseInt(dimensions[0]);
        int M = Integer.parseInt(dimensions[1]);

        // 보드 상태를 저장할 2차원 배열
        char[][] board = new char[N][M];

        // N개의 줄에서 보드의 각 행을 읽어오기
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 최소 변경 수를 저장할 변수, 초기값은 충분히 큰 값으로 설정
        int minChange = Integer.MAX_VALUE;

        // 보드를 8x8 크기로 자를 수 있는 모든 시작 위치 (i, j)에 대해 반복
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                // 두 가지 패턴에 대해 변경해야 하는 칸 수를 초기화
                int change1 = 0; // 왼쪽 위가 'W'인 패턴
                int change2 = 0; // 왼쪽 위가 'B'인 패턴

                // 8x8 크기의 체스판 검사
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        // (x + y) % 2 == 0 인 경우와 아닌 경우로 나누어 검사
                        if ((x + y) % 2 == 0) {
                            // 패턴1: 왼쪽 위가 'W'인 경우
                            if (board[i + x][j + y] != 'W') change1++;
                            // 패턴2: 왼쪽 위가 'B'인 경우
                            if (board[i + x][j + y] != 'B') change2++;
                        } else {
                            // 패턴1: 왼쪽 위가 'W'인 경우
                            if (board[i + x][j + y] != 'B') change1++;
                            // 패턴2: 왼쪽 위가 'B'인 경우
                            if (board[i + x][j + y] != 'W') change2++;
                        }
                    }
                }
                // 두 패턴 중 더 적은 변경이 필요한 경우를 minChange와 비교하여 갱신
                minChange = Math.min(minChange, Math.min(change1, change2));
            }
        }

        // 결과 출력: 최소 변경 수
        System.out.println(minChange);
    }
}