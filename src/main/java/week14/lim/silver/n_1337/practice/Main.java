package week14.lim.silver.n_1337.practice;

// 문제: https://www.acmicpc.net/problem/1337

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  올바른 배열이란 어떤 배열 속에 있는 원소 중 5개가 연속적인 것을 말한다.
     *  (연속적인 것이란 5개의 수를 정렬했을 때, 인접한 수의 차이가 1인 것을 말한다.)
     *
     *  예를 들어 배열 {6, 1, 9, 5, 7, 15, 8}은 올바른 배열이다.
     *  왜냐하면 이 배열 속의 원소인 5, 6, 7, 8, 9가 연속이기 때문이다.
     *  배열이 주어지면, 이 배열이 올바른 배열이 되게 하기 위해서 추가되어야 할 원소의 개수를 출력하는 프로그램을 작성하시오.
     *
     *  [입력]
     *  첫째 줄에 배열의 크기 N이 주어진다. N은 50보다 작거나 같은 자연수이다.
     *  둘째 줄부터 N개의 줄에 배열의 원소가 한 줄에 하나씩 주어진다.
     *  원소는 1,000,000,000보다 작거나 같은 음이 아닌 정수이다.
     *  배열에 중복되는 수는 없다.
     *
     *  [출력]
     *  첫째 줄에 입력으로 주어진 배열이 올바른 배열이 되게 하기 위해서 추가되어야할 원소의 최소 개수를 출력한다.
     *
     *  [풀이]
     *  투 포인터
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0;
        int minToAdd = 5;
        // 5개의 인접한 수의 차이가 각각 1이면 연속적인 거니까
        // 추가해야하는 최대 숫자의 개수는 5개
        for (int right = 0; right < N; right++) {
            while (arr[right] - arr[left] > 4) {
                left++;
            }
            int currentLength = right - left + 1;
            minToAdd = Math.min(minToAdd, 5 - currentLength);
        }
        System.out.println(minToAdd);
    }
}
