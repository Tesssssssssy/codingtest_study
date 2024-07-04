package week9.lim.week9_2.n_10826;

// 문제: https://www.acmicpc.net/problem/10826

import java.io.*;
import java.math.BigInteger;

public class Main {
    /**
     *  피보나치 수열 문제
     *
     *  [주의]
     *  n번째 피보나치 수를 구하라는 문제인데, n이 최대 10,000 이라서 long 자료형으로도 커버가 불가능.
     *  우리는 보통 long 자료형보다 더 큰 자료형을 모르는 경우가 많은데, 놀랍게도 '무한대'를 커버할 수 있는 자료형이 있다.
     *  => 바로 BigInteger 라는 자료형을 이용하는 것.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());  // n 입력 받기

        if (n == 0) {
            System.out.println(0);  // n이 0일 경우 0을 출력
        } else if (n == 1) {
            System.out.println(1);  // n이 1일 경우 1을 출력
        } else {
            BigInteger[] fib = new BigInteger[n + 1];  // 피보나치 수열을 저장할 배열
            fib[0] = BigInteger.ZERO;;  // 첫 번째 피보나치 수
            fib[1] = BigInteger.ONE;;  // 두 번째 피보나치 수

            for (int i = 2; i <= n; i++) {
                fib[i] = fib[i - 2].add(fib[i - 1]);  // i번째 피보나치 수 계산
            }

            System.out.println(fib[n]);  // n번째 피보나치 수 출력
        }
    }
}
