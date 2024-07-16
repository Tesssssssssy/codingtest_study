package week11.lim.week11_1.bronze.n_2163;

// 문제: https://www.acmicpc.net/problem/2163

import java.io.*;

public class Main {
    /**
     *  1. N x M 크기의 초콜릿
     *  2. 초콜릿은 금이 가 있는 모양이고, 그 금에 의해 N x M 조각으로 나눠질 수 있음.
     *  3. 초콜릿을 계속 쪼개서 총 N x M 개의 조각으로 쪼갠다.
     *  4. 쪼갤 때, 초콜릿 조각을 하나 들고, 적당한 위치에서 쪼갠다.
     *  5. 금이 가 있는 위치에서만 쪼갤 수 있다.
     *  6. 이 과정 반복
     *  7. 초콜릿을 쪼개는 횟수 최소로
     *  8. 1 x 1 크기의 초콜릿으로 쪼개기 위한 최소 쪼개기 횟수 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        int N = Integer.parseInt(inputStr[0]);
        int M = Integer.parseInt(inputStr[1]);

        // 전체 초콜릿 조각 수에서 1을 빼면 최소 쪼개기 횟수가 됩니다.
        int minSplits = N * M - 1;

        System.out.println(minSplits);  // 결과 출력
    }
}
