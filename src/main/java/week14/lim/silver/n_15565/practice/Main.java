package week14.lim.silver.n_15565.practice;

// 문제: https://www.acmicpc.net/problem/15565

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  꿀귀 라이언 인형과, 마찬가지로 꿀귀인 어피치 인형이 N개 일렬로 놓여 있다.
     *  라이언 인형은 1, 어피치 인형은 2로 표현하자.
     *  라이언 인형이 K개 이상 있는 가장 작은 연속된 인형들의 집합의 크기를 구하여라.
     *
     *  [입력]
     *  첫 줄에 N과 K가 주어진다. ( 1 <= K <= N <= 10^6 )
     *  둘째 줄에 N개의 인형의 정보가 주어진다. (1 또는 2)
     *
     *  [출력]
     *  K개 이상의 라이언 인형을 포함하는 가장 작은 연속된 인형들의 집합의 크기를 출력한다.
     *  그런 집합이 없다면 -1을 출력한다.
     *
     *  [풀이]
     *  투 포인터
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int cnt = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < N; right++) {
            if (arr[right] == 1) {
                cnt++;
            }

            while (cnt >= K) {
                minLength = Math.min(minLength, right - left + 1);

                if (arr[left] == 1) {
                    cnt--;
                }
                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minLength);
        }
    }
}
