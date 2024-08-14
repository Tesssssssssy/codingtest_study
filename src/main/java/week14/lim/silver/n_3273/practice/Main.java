package week14.lim.silver.n_3273.practice;

// 문제: https://www.acmicpc.net/problem/3273

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  n개의 서로 다른 양의 정수 a1, a2, ..., an 으로 이루어진 수열이 있다.
     *  ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다.
     *  자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.
     *
     *  [입력]
     *  첫째 줄에 수열의 크기 n이 주어진다.
     *  다음 줄에는 수열에 포함되는 수가 주어진다.
     *  셋째 줄에는 x가 주어진다. (1 ≤ n ≤ 100000, 1 ≤ x ≤ 2000000)
     *
     *  [출력]
     *  문제의 조건을 만족하는 쌍의 개수를 출력한다.
     *
     *  [풀이]
     *  HashMap
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;  // 조건을 만족하는 쌍의 개수

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            int target = x - current;

            if (map.containsKey(target)) {
                ans += map.get(target);
            }

            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        System.out.println(ans);
    }
}
