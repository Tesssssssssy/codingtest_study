package week9.lim.week9_1.n_1032;

// 문제: https://www.acmicpc.net/problem/1032

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 파일 개수 입력
        String[] filenames = new String[N];

        // 파일명 입력
        for (int i = 0; i < N; i++) {
            filenames[i] = br.readLine();
        }

        if (N == 1) {
            // 파일이 하나만 있는 경우 그대로 출력
            System.out.println(filenames[0]);
            return;
        }

        StringBuilder pattern = new StringBuilder(filenames[0]);  // 패턴 초기화

        // 모든 파일명을 비교
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < filenames[0].length(); j++) {
                // 현재 위치의 문자가 이전 파일과 다를 경우 '?'로 설정
                if (pattern.charAt(j) != '?' && filenames[i].charAt(j) != pattern.charAt(j)) {
                    pattern.setCharAt(j, '?');
                }
            }
        }

        // 최종 패턴 출력
        System.out.println(pattern);
    }
}
