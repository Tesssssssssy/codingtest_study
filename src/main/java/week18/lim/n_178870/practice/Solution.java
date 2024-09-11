package week18.lim.n_178870.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/178870

import java.util.Arrays;

public class Solution {
    public static int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        int[] result = new int[2];
        int sum = sequence[0];
        int minLength = Integer.MAX_VALUE;

        while (right < sequence.length) {
            if (sum == k) {
                int currentLength = right - left;
                if (currentLength < minLength) {
                    minLength = currentLength;
                    result[0] = left;
                    result[1] = right;
                }

                sum -= sequence[left];
                left++;

            } else if (sum < k) {
                right++;
                if (right < sequence.length) {
                    sum += sequence[right];
                }
            } else {
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
