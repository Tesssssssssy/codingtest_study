package week12.lim.week12_1.gold.n_2467;

// 문제: https://www.acmicpc.net/problem/2467

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.
     *  2. 산성 용액:  1부터 1,000,000,000까지의 양의 정수,
     *     알칼리성 용액:  -1부터 -1,000,000,000까지의 음의 정수.
     *  3. 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
     *  4. 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
     *  5. ex.)
     *      [-99, -2, -1, 4, 98]
     *      - 특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고
     *        이 용액의 특성값이 0에 가장 가까운 용액.
     *      - 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로
     *        특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
     *  6. 산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어졌을 때,
     *     이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾아라.
     *
     *  [입력]
     *  첫째 줄에는 전체 용액의 수 N
     *  둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 오름차순으로 입력
     *  (N개의 용액들의 특성값은 모두 서로 다르고,
     *   산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.)
     *
     *  [출력]
     *  첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력.
     *  출력해야 하는 두 용액은 특성값의 오름차순으로 출력.
     *  특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] solutions = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int minAbsSum = Integer.MAX_VALUE; // 최소 절대값을 매우 큰 값으로 초기화
        int[] answer = new int[2]; // 결과를 저장할 배열

        while (left < right) {
            int sum = solutions[left] + solutions[right];

            // 현재 합의 절대값이 이전의 최소 절대값보다 작은 경우, 값을 업데이트
            if (Math.abs(sum) < minAbsSum) {
                minAbsSum = Math.abs(sum);
                answer[0] = solutions[left];
                answer[1] = solutions[right];
            }

            // 합을 조절하기 위해 포인터를 이동
            if (sum > 0) {
                right--; // 합을 줄이기 위해
            } else if (sum < 0) {
                left++; // 합을 늘리기 위해
            } else {
                // 합이 정확히 0인 경우, 최적의 해를 찾은 것이므로 바로 종료
                break;
            }
        }

        // 결과 출력
        System.out.println(answer[0] + " " + answer[1]);
    }
}
/*
    주어진 정렬된 배열에서 두 포인터를 사용하는 방법이 가장 직관적이고 효율적이다.
    이 방법은 배열의 시작과 끝에 각각 포인터를 두고,
    두 포인터가 가리키는 원소의 합을 계산하여 그 합이 0에 가장 가까워지도록 조정하는 방식으로 작동한다.

    여기서는 left 포인터를 배열의 시작에, right 포인터를 배열의 끝에 배치한다.
    두 포인터가 가리키는 두 용액의 특성값 합을 계산하고, 이 합이 0에 가까운지 확인한다.
    합이 0보다 크면 right를 왼쪽으로 이동시켜 합을 줄이고, 합이 0보다 작으면 left를 오른쪽으로 이동시켜 합을 증가시킨다.
    이 과정을 두 포인터가 교차할 때까지 반복한다.
*/