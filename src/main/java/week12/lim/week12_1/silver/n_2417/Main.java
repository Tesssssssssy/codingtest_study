package week12.lim.week12_1.silver.n_2417;

// 문제: https://www.acmicpc.net/problem/2417

import java.io.*;

public class Main {
    /**
     * 1. 정수가 주어지면, 정수 제곱근을 구하라.
     *
     * [입력]
     * 정수 n
     *
     * [출력]
     * q^2 >= n인 가장 작은 음이 아닌 정수 q를 출력
     *
     * [시간 복잡도]
     * O(1)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        long q = (long) Math.sqrt(n);

        if ((q * q) < n)
            q++;

        System.out.println(q);
    }
}
