package week13.lim.silver.n_2961;

// 문제: https://www.acmicpc.net/problem/2961

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - 재료가 N개 있다.
     *  - 도영이는 각 재료의 신맛 S와 쓴맛 B를 알고 있다.
     *  - 여러 재료를 이용해서 요리할 때, 그 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다.
     *  - 영이는 재료를 적절히 섞어서 요리의 신맛과 쓴맛의 차이를 작게 만들려고 한다.
     *  - 또, 물을 요리라고 할 수는 없기 때문에, 재료는 적어도 하나 사용해야 한다.
     *  - 재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램.
     *
     *  [입력]
     *  재료의 개수 N
     *  N개 줄 - 신맛과 쓴맛이 공백으로 주어짐
     *
     *  [출력]
     *  신맛과 쓴맛의 차이가 가장 작은 요리의 차이 출력
     */

    static int N;
    static int[] sour;  // 각 재료의 신맛
    static int[] bitter;  // 각 재료의 쓴맛
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sour = new int[N];
        bitter = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        findMinDiff(0, 1, 0);
        // 신맛은 곱이니까 항등원으로 1로 설정.

        System.out.println(minDiff);
    }

    // 백트래킹을 이용한 최소 차이 찾기
    private static void findMinDiff(int index, int totalSour, int totalBitter) {
        // index 는 현재 탐색하고 있는 재료의 인덱스,
        // totalSour 과 totalBitter 는 현재까지 선택된 재료들의 신맛과 쓴맛의 총곱과 총합.

        if (index > 0) {  // 적어도 하나의 재료를 사용했을 때
            int currentDiff = Math.abs(totalSour - totalBitter);
            minDiff = Math.min(minDiff, currentDiff);
        }

        for (int i = index; i < N; i++) {
            findMinDiff(i + 1, totalSour * sour[i], totalBitter + bitter[i]);
        }
    }
}
