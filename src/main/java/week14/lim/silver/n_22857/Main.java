package week14.lim.silver.n_22857;

// 문제: https://www.acmicpc.net/problem/22857

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  길이가 N인 수열 S가 있다.
     *  수열 S는 1 이상인 정수로 이루어져 있다.
     *  수열 S에서 원하는 위치에 있는 수를 골라 최대 K번 삭제를 할 수 있다.
     *
     *  예를 들어,
     *  수열 S가 다음과 같이 구성되어 있다고 가정하자.
     *      수열 S : 1 2 3 4 5 6 7 8
     *  수열 S에서 4번째에 있는 4를 지운다고 하면 아래와 같다.
     *      수열 S : 1 2 3 5 6 7 8
     *  수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 구해보자.
     *
     *  [입력]
     *  수열 S의 길이 N와 삭제할 수 있는 최대 횟수인 K가 공백으로 구분되어 주어진다.
     *  두 번째 줄에는 수열 S를 구성하고 있는 N개의 수가 공백으로 구분되어 주어진다.
     *
     *  [출력]
     *  수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 출력.
     *
     *  [풀이]
     *  투 포인터
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 수열의 길이
        int K = Integer.parseInt(st.nextToken());  // 삭제할 수 있는 최대 홀수 개수

        int[] S = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;  // 윈도우의 왼쪽 포인터
        int oddCount = 0;  // 현재 윈도우 내 홀수의 개수
        int maxLength = 0;  // 짝수로 이루어진 부분 수열의 최대 길이

        for (int right = 0; right < N; right++) {
            if (S[right] % 2 != 0) {  // 홀수인 경우
                oddCount++;
            }

            // 홀수의 개수가 K를 초과하면 윈도우의 왼쪽을 이동시킴
            while (oddCount > K) {
                if (S[left] % 2 != 0) {
                    oddCount--;
                }
                left++;
            }

            // 현재 윈도우의 길이를 계산하고 최대 길이를 갱신
            // 짝수로 이루어진 부분 수열의 최대 길이를 갱신
            maxLength = Math.max(maxLength, right - left + 1 - oddCount);
        }

        System.out.println(maxLength);
    }
}
