package week14.lim.silver.n_20922;

// 문제: https://www.acmicpc.net/problem/20922

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  홍대병에 걸린 도현이는 겹치는 것을 매우 싫어한다.
     *  특히 수열에서 같은 원소가 여러 개 들어 있는 수열을 싫어한다.
     *  도현이를 위해 같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이를 구하려고 한다.
     *
     *  100,000 이하의 양의 정수로 이루어진 길이가 N인 수열이 주어진다.
     *  이 수열에서 같은 정수를 K개 이하로 포함한 최장 연속 부분 수열의 길이를 구하는 프로그램을 작성해보자.
     *
     *  [입력]
     *  첫째 줄에 정수 N, K
     *  둘째 줄에 a1, ..., an
     *
     *  [출력]
     *  조건을 만족하는 최장 연속 부분 수열의 길이 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < N; right++) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            while (map.get(arr[right]) > K) {
                map.put(arr[left], map.get(arr[left]) - 1);

                if (map.get(arr[left]) == 0) {
                    map.remove(arr[left]);
                }

                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}
