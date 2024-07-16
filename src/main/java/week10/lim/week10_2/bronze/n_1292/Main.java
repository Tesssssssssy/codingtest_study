package week10.lim.week10_2.bronze.n_1292;

// 문제: https://www.acmicpc.net/problem/1292

import java.io.*;

public class Main {
    /**
     *  1. 1 2 2 3 3 3 4 4 4 4 5 ...
     *     이렇게 수열을 만들고 어느 일정한 구간을 주면 그 구간의 합을 구한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);

        int[] sequence = new int[1000];
        int index = 0;

        // 수열 생성: 각 숫자 n을 n번 반복해서 배열에 저장 (1 2 2 3 3 3 4 4 4 4 5 ...)
        for (int i = 1; index < sequence.length; i++) {
            for (int j = 1; j <= i && index < sequence.length; j++) {
                // 각 숫자 i를 i번 반복

                sequence[index++] = i;
            }
        }

        // A에서 B 구간까지의 합 계산 (인덱스는 0부터 시작하므로 A-1, B-1 조정 필요)
        int sum = 0;
        for (int i = A - 1; i <= B - 1; i++) {
            sum += sequence[i];
        }

        // 계산된 합 출력
        System.out.println(sum);
    }
}
