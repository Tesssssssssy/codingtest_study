package week17.lim.n_12987;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12987

import java.util.*;

public class Solution {
    public static int solution(int[] A, int[] B) {
        // 두 배열을 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;
        int aIndex = 0, bIndex = 0;
        int winCount = 0;

        // 그리디 방식으로 승점 계산
        while (bIndex < n) {
            if (B[bIndex] > A[aIndex]) {
                // B 팀원이 이길 수 있으면 승점 추가
                winCount++;
                aIndex++; // A 팀 다음 선수
            }
            // 다음 B 팀원으로 진행
            bIndex++;
        }

        return winCount;
    }

    public static void main(String[] args) {
        int[] A_1 = {5, 1, 3, 7};
        int[] B_1 = {2, 2, 6, 8};
        System.out.println(solution(A_1, B_1)); // 3

        int[] A_2 = {2, 2, 2, 2};
        int[] B_2 = {1, 1, 1, 1};
        System.out.println(solution(A_2, B_2)); // 0
    }
}
