package week8.lim.week8_2.n_9655;

// 문제: https://www.acmicpc.net/problem/9655

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        /*
            상근이가 먼저 시작.
            -> 돌의 개수가 1 또는 3개일 때, 상근이가 항상 승.
            -> 돌의 개수가 2 또는 4개일 때, 창영이가 항상 승.
                => 돌의 개수가 홀수이면 상근이 승, 짝수이면 창영이 승
         */
        if (N % 2 == 1) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
