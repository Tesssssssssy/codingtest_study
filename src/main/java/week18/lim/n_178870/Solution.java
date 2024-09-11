package week18.lim.n_178870;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/178870

import java.util.*;

public class Solution {
    /**
     *  비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
     *
     *  - 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
     *  - 부분 수열의 합은 k입니다.
     *  - 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
     *  - 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
     *
     *  수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때,
     *  위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return 하는 solution 함수를 완성해주세요.
     *  이때 수열의 인덱스는 0부터 시작합니다.
     */
    public static int[] solution(int[] sequence, int k) {
        /*
            투 포인터 설정
                left와 right라는 두 개의 포인터를 설정.
                left는 부분 수열의 시작을, right는 부분 수열의 끝을 가리킴.
                초기에는 둘 다 수열의 시작을 가리킴.

            부분 수열의 합 계산
                right 포인터를 이동시키며 부분 수열의 합을 계산.
                부분 수열의 합이 k와 같아지면, 현재 부분 수열의 길이를 기록하고,
                최소 길이와 비교해 더 짧은 경우를 선택.

                합이 k보다 크면 left 포인터를 이동시켜 합을 줄임.

            결과 반환
                가장 짧은 길이의 부분 수열의 시작과 끝 인덱스를 반환.
        */
        int left = 0, right = 0;
        int sum = sequence[0];
        int minLength = Integer.MAX_VALUE;
        int[] result = new int[2];

        while (right < sequence.length) {
            if (sum == k) {
                // 현재 부분 수열의 길이를 계산하고, 최소 길이와 비교하여 더 짧은 경우를 갱신.

                int currentLength = right - left;
                if (currentLength < minLength) {
                    minLength = currentLength;
                    result[0] = left;
                    result[1] = right;
                }

                sum -= sequence[left];
                left++;

            } else if (sum < k) {
                // sum이 k보다 작으면 right 포인터를 오른쪽으로 이동.

                right++;
                if (right < sequence.length) {
                    sum += sequence[right];
                }

            } else {
                // sum이 k보다 크면 left 포인터를 오른쪽으로 이동하여 합 조정.

                sum -= sequence[left];
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] sequence1 = {1, 2, 3, 4, 5};
        int k1 = 7;
        System.out.println(Arrays.toString(solution(sequence1, k1))); // [2, 3]

        int[] sequence2 = {1, 1, 1, 2, 3, 4, 5};
        int k2 = 5;
        System.out.println(Arrays.toString(solution(sequence2, k2))); // [6, 6]

        int[] sequence3 = {2, 2, 2, 2, 2};
        int k3 = 6;
        System.out.println(Arrays.toString(solution(sequence3, k3))); // [0, 2]
    }
}
