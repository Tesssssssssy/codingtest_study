package week8.lim.week8_2.n_2231;

// 문제: https://www.acmicpc.net/problem/2231

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0; // 생성자가 없는 경우 0을 출력하기 위함

        // 브루트포스 방식
        for (int i = 0; i <= N; i++) {
            int sum = i; // 현재 숫자 i를 sum에 더함
            int number = i; // 숫자를 각 자리수로 분해하기 위해 number에 저장
            /*
                각 숫자를 sum에 저장하고 그 숫자를 다시 numbers에 저장
            */

            // 숫자의 각 자리수를 더함
            while (number != 0) {
                sum += number % 10; // 마지막 자리수를 sum에 더함
                number /= 10; // 현재 반복을 돌고 있는 number의 값을 10씩 분해하여 계속 반복.
            }

            // 분해합이 N과 같으면 결과를 i로 설정하고 루프 종료
            if (sum == N) {
                answer = i; // 생성자이므로 i를 할당하고
                break; // 종료
            }
        }

        System.out.println(answer);
    }
}
