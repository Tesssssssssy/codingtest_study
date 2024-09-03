package week17.lim.n_12938;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12938

import java.util.*;

public class Solution {
    public static int[] solution(int n, int s) {
        // 예외 처리: 자연수 n개의 합이 s가 될 수 없는 경우
        if (n > s) {
            return new int[]{-1};
        }

        // 몫과 나머지 구하기
        int q = s / n;
        int r = s % n;

        // 결과 배열 초기화
        int[] result = new int[n];
        Arrays.fill(result, q);

        // 나머지를 분배하여 최대 곱 만들기
        for (int i = 0; i < r; i++) {
            result[i]++;
        }

        // 결과 배열 반환
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(2, 9)));  // [4, 5]
        System.out.println(Arrays.toString(solution(2, 1)));  // [-1]
        System.out.println(Arrays.toString(solution(2, 8)));  // [4, 4]
    }
}
/*
    목표:
        주어진 합 s를 n개의 자연수로 나누어 곱이 최대가 되도록 하는 것이 목표.
        이때, 최대 곱을 얻기 위해서는 각 숫자가 가능한 한 비슷해야 한다.

    접근 방법:
        몫 q와 나머지 r:
            s를 n으로 나누었을 때의 몫을 q라고 하고, 나머지를 r라고 한다.
            예를 들어, s = 9이고 n = 2일 때, q = 4이고 r = 1이다.
            이는 9를 2개로 나눌 때, 각 숫자가 4가 되고, 나머지 1을 추가적으로 분배할 필요가 있음을 의미한다.

        결과 배열 채우기:
            우선 모든 원소에 q를 넣는다. 예를 들어, q = 4라면 [4, 4]가 된다.
            그 후, 나머지 r을 1씩 분배해준다.
            즉, 배열의 앞에서부터 r번의 인덱스에 1을 더해준다.

    예시 설명:
        예를 들어, s = 9, n = 2일 때:

        q = 4, r = 1이 된다.
        결과 배열을 [4, 4]로 초기화한다.
        나머지 1을 분배해야 하므로, 배열의 첫 번째 원소에 1을 더해준다. 그러면 [5, 4]가 된다.
        배열을 오름차순으로 정렬하여 [4, 5]를 얻는다.
*/