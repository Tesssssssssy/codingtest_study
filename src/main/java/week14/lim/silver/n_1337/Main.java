package week14.lim.silver.n_1337;

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

        int N = Integer.parseInt(br.readLine());  // 배열의 크기
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);  // 배열 오름차순 정렬

        int minToAdd = 5;  // 추가해야 할 원소의 최소 개수, 최대 4까지 추가해야 할 수 있음
        int left = 0;  // 왼쪽 포인터

        for (int right = 0; right < N; right++) {
            // 현재 구간에서 첫 번째와 마지막 원소의 차이가 4 이내인 경우만 유효
            while (arr[right] - arr[left] > 4) {
                left++;
            }

            // 현재 구간의 길이가 5개에 미치지 못하면 추가해야 할 원소 계산
            int currentLength = right - left + 1;
            minToAdd = Math.min(minToAdd, 5 - currentLength);
        }

        System.out.println(minToAdd);
    }
}

/*
    minToAdd = 5가 초기값인 이유
        초기값을 5로 설정하는 이유는, 최악의 경우 5개의 연속된 숫자를 만들기 위해 최대 4개의 숫자를 추가해야 하기 때문.
        예를 들어, 배열이 [10]만 있다고 가정해보면,
        이를 연속된 5개의 숫자로 만들기 위해 [10, 11, 12, 13, 14]와 같은 4개의 숫자를 추가해야 힘.
        따라서 초기값을 5로 설정하고, 점차적으로 필요한 추가 숫자의 개수를 줄여나가는 것이 최선.

    while (arr[right] - arr[left] > 4) 조건 의미
        차이가 4 이하라는 것은, 연속된 숫자들을 형성할 수 있는 범위라는 것을 의미.
        예를 들어,
        배열의 부분 구간 [3, 4, 5, 6, 7]은 연속된 5개의 숫자.
        여기서 최대값 7과 최소값 3의 차이는 4.
        이 조건을 만족하는 구간만이 "연속된" 숫자 그룹이 될 수 있다.
        반대로,
        예를 들어 [2, 3, 4, 5, 10]이라면,
        최대값 10과 최소값 2의 차이는 8로,
        이 구간은 연속된 숫자 그룹이 아니며, 연속되게 하려면 숫자들을 추가해야 함.
*/