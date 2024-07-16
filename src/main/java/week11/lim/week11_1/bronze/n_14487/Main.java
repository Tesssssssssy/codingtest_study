package week11.lim.week11_1.bronze.n_14487;

// 문제: https://www.acmicpc.net/problem/14487

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 해안가를 따라 원형으로 마을들이 위치해 있다.
     *  2. A -> B 가기 위해서는 왼쪽 또는 오른쪽 도로를 통해 해안갈르 따라 섬을 돌아야 한다.
     *  3. 마을 -> 마을 이동비용이 주어질 때, 최소한의 이동비용으로 섬의 모든 마을을 관광하려면
     *     얼마의 이동비용을 준비해야 하는지 출력
     *
     *  [풀이]
     *  - 섬이 원형이므로 어디서 시작하든 동일한 비용이 발생
     *  - 한 방향으로의 순회만 고려해서  비용의 총합을 계산
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 마을의 수
        String[] costsStr = br.readLine().split(" "); // 마을 간 이동 비용

        int[] costs = new int[costsStr.length];
        for (int i = 0; i < n; i++) {
            costs[i] += Integer.parseInt(costsStr[i]);
        }

        Arrays.sort(costs);

        long totalCost = 0; // 총 이동 비용

//        for (int i = 0; i < n; i++) {
        // 원형 구조에서는 마지막 마을에서 다시 첫 번재 마을로 돌아오는 비용은 계산에 포함하지 말아야 함.

        for (int i = 0; i < n - 1; i++) {
            totalCost += costs[i];
        }

        // 시계 방향으로 마을을 모두 방문하는 데 필요한 총 비용 출력
        System.out.println(totalCost);
    }
}