package week8.lim.n_1110;

// 문제: https://www.acmicpc.net/problem/1110

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int originalN = Integer.parseInt(br.readLine()); // 입력받은 숫자
        int N = originalN; // 처리할 숫자
        int count = 0;
        boolean flag = true;

        while (flag) {
            count++; // 사이클 수 증가
            int leftDigit = N / 10; // 왼쪽 자리 수
            int rightDigit = N % 10; // 오른쪽 자리 수
            int newDigit = (leftDigit + rightDigit) % 10; // 새로운 오른쪽 자리
            N = rightDigit * 10 + newDigit; // 새로운 숫자 생성

            if (N == originalN) {
                flag = false; // 원래 숫자로 돌아왔을 경우 반복 종료
            }
        }

        System.out.println(count);
    }
}