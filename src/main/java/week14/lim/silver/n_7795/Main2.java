package week14.lim.silver.n_7795;

// 문제: https://www.acmicpc.net/problem/7795

import java.io.*;
import java.util.*;

public class Main2 {
    /**
     *  심해에는 두 종류의 생명체 A와 B가 존재한다. A는 B를 먹는다. A는 자기보다 크기가 작은 먹이만 먹을 수 있다.
     *  예를 들어, A의 크기가 {8, 1, 7, 3, 1}이고,
     *           B의 크기가 {3, 6, 1}인 경우에
     *           A가 B를 먹을 수 있는 쌍의 개수는 7가지가 있다.
     *           8-3, 8-6, 8-1, 7-3, 7-6, 7-1, 3-1.
     *  두 생명체 A와 B의 크기가 주어졌을 때, A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하는 프로그램
     *
     *  [입력]
     *  첫째 줄에 테스트 케이스의 개수 T가 주어진다.
     *  각 테스트 케이스의 첫째 줄에는 A의 수 N과 B의 수 M이 주어진다.
     *  둘째 줄에는 A의 크기가 모두 주어지며, 셋째 줄에는 B의 크기가 모두 주어진다.
     *  크기는 양의 정수이다. (1 ≤ N, M ≤ 20,000)
     *
     *  [출력]
     *  각 테스트 케이스마다, A가 B보다 큰 쌍의 개수를 출력.
     *
     *  [풀이]
     *  이진 탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // A의 크기
            int M = Integer.parseInt(st.nextToken());  // B의 크기

            int[] arrA = new int[N];
            int[] arrB = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }

            // A와 B 정렬
            Arrays.sort(arrA);
            Arrays.sort(arrB);

            int count = 0;
            for (int i = 0; i < N; i++) {
                // B에서 A[i]보다 작은 수의 개수를 찾기
                count += binarySearch(arrB, arrA[i]);
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    // B 배열에서 target보다 작은 수의 개수를 반환하는 함수
    private static int binarySearch(int[] arrB, int target) {
        int left = 0;
        int right = arrB.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arrB[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}