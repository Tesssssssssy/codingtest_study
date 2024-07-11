package week10.lim.week10_1.bronze.n_15633;

// 문제: https://www.acmicpc.net/problem/15633

import java.io.*;

public class Main {
    /**
     *  1. 백준님에 대한 치사량 이상으로 흡입해 사망한다는 미신...
     *  2. fan death 에 다다를 수 있을 정도로 백준을 사랑하는지 확인...
     *  3. n이 주어졌을 때, n의 모든 약수의 합 구한다.
     *  4. 구한 값에 5를 곱하고 24를 빼서 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        System.out.println(sum * 5 - 24);
    }
}
