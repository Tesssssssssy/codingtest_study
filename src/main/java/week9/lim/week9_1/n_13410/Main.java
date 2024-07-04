package week9.lim.week9_1.n_13410;

// 문제: https://www.acmicpc.net/problem/13410

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int max = 0; // 거꾸로 변환하고 저장한 값들 중 가장 큰 값

        for (int i = 0; i < K; i++) {
            int multiple = N * (i + 1);

            // 숫자를 문자열로 변환 후 뒤집기
            StringBuilder sb = new StringBuilder(String.valueOf(multiple));
            sb.reverse(); // 숫자 뒤집기

            // 정수로 변환하여 가장 큰 값 비교
            int reversedNumber = Integer.parseInt(sb.toString());
            if (reversedNumber > max) {
                max = reversedNumber;
            }
        }

        System.out.println(max); // 가장 큰 값 출력
    }
}
