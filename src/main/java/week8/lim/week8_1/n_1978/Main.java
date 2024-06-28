package week8.lim.week8_1.n_1978;

// 문제: https://www.acmicpc.net/problem/1978

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arrStr = br.readLine().split(" ");

        int answer = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(arrStr[i]);
            if (isPrime(num)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    // 소수 판별 함수
    public static boolean isPrime(int num) {
        if (num <= 1) { // 1 이하의 수는 소수가 아님
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) { // 2부터 num의 제곱근까지 반복
            if (num % i == 0) { // 나누어 떨어지면 소수가 아님
                return false;
            }
        }
        return true;
    }
}
