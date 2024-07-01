package week9.lim.n_1773;

// 문제: https://www.acmicpc.net/problem/1773

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        int[] checkDuplicate = new int[C + 1];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            int cycle = Integer.parseInt(br.readLine());
            for (int j = cycle; j <= C; j += cycle) {
                if (checkDuplicate[j] == 0) {  // 폭죽 안터졌으면
                    checkDuplicate[j] = 1;  // 폭죽 터졌음 표시
                }
            }
        }

        for (int k : checkDuplicate) {
            if (k == 1) {
                answer++; // 폭죽 터진 시간 count
            }
        }

        System.out.println(answer);
    }
}
