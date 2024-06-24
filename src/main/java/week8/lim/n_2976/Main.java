package week8.lim.n_2976;

// 문제: https://www.acmicpc.net/problem/2979

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 요금 정보 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 각 분마다 주차된 트럭 수를 기록할 배열 (최대 100분까지)
        int[] parkingTime = new int[101];

        // 트럭별로 도착 시간과 떠난 시간을 입력 받아 처리
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int arrive = Integer.parseInt(st.nextToken());
            int depart = Integer.parseInt(st.nextToken());
            for (int j = arrive; j < depart; j++) {
                parkingTime[j]++;
            }
        }

        // 요금 계산
        int totalCost = 0;
        for (int i = 1; i <= 100; i++) {
            switch (parkingTime[i]) {
                case 1:
                    totalCost += A;
                    break;
                case 2:
                    totalCost += 2 * B;
                    break;
                case 3:
                    totalCost += 3 * C;
                    break;
            }
        }

        System.out.println(totalCost);
    }
}
