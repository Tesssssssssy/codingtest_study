package week8.lim.week8_1.n_1598;

// 문제: https://www.acmicpc.net/problem/1598

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = 0;

        // 1부터 시작하니까 1을 빼주어야 제 위치를 찾을 수 있다.
        // 아니면 12의 경우 [2]가 나와야 하는데 [3]이 나온다.
        // 그래서 1을 빼주고 4로 나누어주어야 한다.

        // N과 M의 행 위치를 계산
        int N_row = (N - 1) / 4;
        int M_row = (M - 1) / 4;

        // N과 M의 열 위치를 계산
        int N_col = (N - 1) % 4;
        int M_col = (M - 1) % 4;

        // 직각거리 계산: 행 거리와 열 거리의 합
        answer = Math.abs(N_row - M_row) + Math.abs(N_col - M_col);

        System.out.println(answer);
    }
}

/*
    1. 가로 거리 구하기
        입력값을 4로 나누었을 경우 가로 위치를 구할 수 있다.
        (11 - 1) / 4 = 2
        (33 - 1) / 4 = 8
        그리고 두 값을 뺀 절대값이 가로 거리가 된다.
        | 2 - 8 | = 6

    2. 세로 거리 구하기
        입력값을 4로 나누었을 때의 나머지로 세로 위치를 구할 수 있다.
        (11 - 1) % 4 = 2
        (33 - 1) % 4 = 0
        그리고 두 값을 뺀 절대값이 세로 거리가 된다.
        | 2 - 0 | = 2
*/