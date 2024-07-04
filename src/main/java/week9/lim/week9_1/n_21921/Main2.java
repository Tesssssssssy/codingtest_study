package week9.lim.week9_1.n_21921;

// 문제: https://www.acmicpc.net/problem/21921

import java.io.*;
import java.util.*;

public class Main2 {
    /*
        답은 맞지만 시간 초과
        -> O(N×X)의 시간 복잡도
           슬라이딩 윈도우 방식보다 계산량이 많아 일반적인 경우에는 비효율적
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] visitors = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            visitors[i] = Integer.parseInt(st.nextToken());
        }

        int maxVisitors = 0;  // 최대 방문자 수
        int maxCount = 0;     // 최대 방문자 수를 가진 기간 개수

        // 모든 가능한 X일 동안의 기간에 대해 방문자 수 계산
        for (int start = 0; start <= N - X; start++) {
            int currentSum = 0;
            // 현재 시작점에서 X일 동안의 방문자 수 합산
            for (int i = start; i < start + X; i++) {
                currentSum += visitors[i];
            }

            // 최대 방문자 수 업데이트
            if (currentSum > maxVisitors) {
                maxVisitors = currentSum;
                maxCount = 1;  // 새로운 최대값이므로 카운트를 1로 리셋
            } else if (currentSum == maxVisitors) {
                maxCount++;  // 기존 최대값과 동일한 경우 카운트 증가
            }
        }

        // 결과 출력
        if (maxVisitors == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxVisitors);
            System.out.println(maxCount);
        }
    }
}
