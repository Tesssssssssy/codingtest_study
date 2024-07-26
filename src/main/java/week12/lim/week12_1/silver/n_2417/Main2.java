package week12.lim.week12_1.silver.n_2417;

// 문제: https://www.acmicpc.net/problem/2417

import java.io.*;

public class Main2 {
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
     * O(log n)
     */
    static long n, answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Long.parseLong(br.readLine());
        binarySearch(n);
        System.out.println(answer);
    }

    static void binarySearch(long key) {
        long left = 0;
        long right = key;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (key <= Math.pow(mid, 2)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
}
