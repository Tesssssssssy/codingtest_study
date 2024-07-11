package week10.lim.week10_1.bronze.n_19532;

// 문제: https://www.acmicpc.net/problem/19532

import java.io.*;

public class Main {
    /**
     *  1. -999 이상 999 이하의 정수만 입력 가능
     *  2. ax + by = c
     *     dx + ey = f
     *     -> a, b, c, d, e, f로 입력받음
     *  3. 방정식의 해인 x, y값 공백으로 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        int[] input = new int[inputStr.length];

        for (int i = 0; i < inputStr.length; i++) {
            input[i] = Integer.parseInt(inputStr[i]);
        }

        for (int i = -999; i <= 999; i++) {
            for (int j = -999; j <= 999; j++) {
                if (input[0] * i + input[1] * j == input[2]
                && input[3] * i + input[4] * j == input[5]) {
                    System.out.println(i + " " + j);
                    break;
                }
            }
        }
    }
}