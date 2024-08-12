package week14.lim.silver.n_2003.practice;

// 문제: https://www.acmicpc.net/problem/2003

import java.io.*;
import java.util.*;

public class Main {
    /**
     *    N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다.
     *    이 수열의 i번째 수부터 j번째 수까지의 합 A[i] + A[i+1] + … + A[j-1] + A[j]가 M이 되는 경우의 수를 구하는 프로그램.
     *
     *    [입력]
     *    첫째 줄에 N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)이 주어진다.
     *    다음 줄에는 A[1], A[2], …, A[N]이 공백으로 분리되어 주어진다.
     *    ( 각각의 A[x]는 30,000을 넘지 않는 자연수이다. )
     *
     *    [출력]
     *    첫째 줄에 경우의 수를 출력.
     *
     *    [풀이]
     *    투 포인터(Two Pointer) 기법을 사용해 배열의 연속된 부분 수열의 합을 계산
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int ans = 0;
        int start = 0;

        for (int end = 0; end < N; end++) {
            sum += arr[end];

            while (sum > M) {
                sum -= arr[start++];
                // 투 포인터 이용해 start 지점을 오른쪽으로 이동
            }

            if (sum == M) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
