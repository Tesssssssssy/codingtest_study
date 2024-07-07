package week9.lim.week9_2.n_1009;

// 문제: https://www.acmicpc.net/problem/1009

import java.io.*;

public class Main {
    /**
     *  1. 최신 컴퓨터 10대 보유.
     *  2. 각 컴퓨터에 1번부터 10번까지의 번호를 부여 + 각 컴퓨터가 데이터 처리
     *  3. 1번 데이터는 1번 컴퓨터, 2번 데이터는 2번 컴퓨터, 3번 데이터는 3번 컴퓨터, ... ,
     *     10번 데이터는 10번 컴퓨터, 11번 데이터는 1번 컴퓨터, 12번 데이터는 2번 컴퓨터, ...
     *  4. 총 데이터의 개수는 항상 a^b개의 형태
     *  5. 마지막 데이터가 처리될 컴퓨터의 번호 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            int result = 1;
            for (int i = 0; i < b; i++) {
                result = (result * a) % 10;
            }

            if (result == 0) result = 10;  // result가 0이면, 10번째 컴퓨터
            sb.append(result).append('\n');
        }

        System.out.print(sb);
    }
}