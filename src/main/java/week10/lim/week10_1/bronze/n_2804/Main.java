package week10.lim.week10_1.bronze.n_2804;

// 문제: https://www.acmicpc.net/problem/2804

import java.io.*;

public class Main {
    /**
     *  1. 두 단어 A, B가 주어지는데, A는 가로로, B는 세로로 놓여야 함.
     *  2. 두 단어는 서로 교차해야 함. (정확히 한 글자만)
     *  3. 공유하는 글자는 A, B 동시에 포함되어 있는 글자여야 함.
     *  4. 그런 글자가 여럿이면 제일 먼저 등장하는 글자를 선택한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String A = input[0];
        String B = input[1];

        int n = A.length();
        int m = B.length();

        // 교차하는 글자, 위치 찾기
        int aIndex = -1, bIndex = -1;
        boolean found = false; // 공통 글자 발견 상태
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < m && !found; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    aIndex = i;
                    bIndex = j;
                    found = true; // 공통 글자 발견
                }
            }
        }

        // 크로스워드 출력을 위한 배열 생성
        char[][] crossword = new char[m][n];

        // 배열 초기화
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                crossword[i][j] = '.';
            }
        }

        // 세로 단어 삽입
        for (int i = 0; i < m; i++) {
            crossword[i][aIndex] = B.charAt(i);
        }

        // 가로 단어 삽입
        for (int j = 0; j < n; j++) {
            crossword[bIndex][j] = A.charAt(j);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(crossword[i][j]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
        br.close();
    }
}
