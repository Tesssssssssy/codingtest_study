package week8.lim.week8_2.n_2559;

// 문제: https://www.acmicpc.net/problem/2559

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] temperatures = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            temperatures[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 K일 동안의 합 계산
        int currentSum = 0;
        for (int i = 0; i < K; i++) {
            currentSum += temperatures[i];
        }

        // 최대 합을 현재 합으로 초기화
        int maxSum = currentSum;

        // [슬라이딩 윈도우 기법]을 사용하여 최대 합을 찾음
        for (int i = K; i < N; i++) {
            currentSum = currentSum + temperatures[i] - temperatures[i - K];
            // 새로운 온도를 더하고, 윈도우에서 벗어나는 온도를 뺀다.
            // 한 칸 오른쪽으로 이동하면 새로운 윈도우에 포함되지 않는 맨 왼쪽 값 빼야 함.

            if (currentSum > maxSum) {
                maxSum = currentSum; // maxSum 갱신
            }
        }

        // 결과 출력
        System.out.println(maxSum);
    }
}
