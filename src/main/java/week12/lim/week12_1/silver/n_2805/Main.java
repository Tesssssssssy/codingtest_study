package week12.lim.week12_1.silver.n_2805;

// 문제: https://www.acmicpc.net/problem/2805

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 나무 M 미터가 필요
     *  2. 절단기에 높이 H를 지정해야 한다.
     *  3. 높이를 지정하면 톱날이 땅으로부터 H 미터 위로 올라간다.
     *  4. 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다.
     *  5. 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것.
     *  6. ex.)
     *     한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17.
     *      높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것
     *      길이가 5인 나무와 2인 나무를 들고 집에 갈 것.
     *      ((총 7미터를 집에 들고 간다) 절단기에 설정할 수 있는 높이는 양의 정수 또는 0이다.)
     *  7. 나무를 필요한 만큼만 집으로 가져가려고 한다.
     *     적어도 M 미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구해라.
     *
     *  [입력]
     *  나무의 수 N / 가져가려고 하는 나무의 길이 M
     *  나무의 높이 (나무 높이의 합은 항상 M 보다 크거나 같기에
     *            필요한 나무를 항상 가져갈 수 있다.)
     *
     *  [출력]
     *  적어도 M 미터의 나무릴 집에 가져가기 위해 설정할 수 있는 높이의 최댓값 출력.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        int M = Integer.parseInt(st.nextToken()); // 가져가려고 하는 나무의 길이

        st = new StringTokenizer(br.readLine());
        long[] trees = new long[N];
        long maxTree = 0;
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            maxTree = Math.max(maxTree, trees[i]);
        }

        long left = 0;
        long right = maxTree;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0; // 나무 길이를 더할 때 사용할 변수, long 타입 사용

            for (long tree : trees) {
                if (tree > mid) {
                    sum += tree - mid; // mid 높이 이상인 나무만 잘린다.
                }
            }

            if (sum >= M) {
                answer = mid; // 최소 M미터 이상 나무를 가져갈 수 있는 최대 mid를 찾음
                left = mid + 1; // 더 큰 높이에서도 가능한지 확인
            } else {
                right = mid - 1; // 불가능한 경우, 높이를 낮춤
            }
        }
        System.out.println(answer);
    }
}
