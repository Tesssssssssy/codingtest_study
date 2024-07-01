package week9.lim.n_4796;

// 문제: https://www.acmicpc.net/problem/4796

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNumber = 1;

        while (true) {
            String[] input = br.readLine().split(" ");
            int L = Integer.parseInt(input[0]);
            int P = Integer.parseInt(input[1]);
            int V = Integer.parseInt(input[2]);

            if (L == 0 && P == 0 && V == 0)
                break;

            // 캠핑 가능 일수 계산
            int fullCycles = V / P;
            int remainingDays = V % P;
            int maxUsableDays = fullCycles * L + Math.min(remainingDays, L);

            System.out.println("Case " + caseNumber + ": " + maxUsableDays);
            caseNumber++;
        }
    }
}

/*
    예제 1
    [입력]
    5 8 20
    5 8 17
    0 0 0

    [출력]
    Case 1: 14
    Case 2: 11

    20 / 8 = 2
    20 % 8 = 4
    2 * 5 + 4 = 14

    17 / 8 = 2
    17 % 8 = 1
    2 * 5 + 1 = 11
*/
