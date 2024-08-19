package week15.lim.programmers_Lv2.n_12924.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12924

public class Solution {
    /**
     *  자연수 n을 연속한 자연수들로 표현 하는 방법이 여러개라는 사실을 알게 되었습니다.
     *  1 + 2 + 3 + 4 + 5 = 15
     *  4 + 5 + 6 = 15
     *  7 + 8 = 15
     *  15 = 15
     *
     *  자연수 n이 매개변수로 주어질 때,
     *  연속된 자연수들로 n을 표현하는 방법의 수를 return 하는 solution 완성
     *
     *  [풀이]
     *  투 포인터
     */
    public static int solution(int n) {
        int answer = 0;
        int start = 1;
        int end = 1;
        int sum = 1;

        while (start <= n) {
            if (sum == n) {
                answer++;
                sum -= start;
                start++;
            } else if (sum < n) {
                end++;
                sum += end;
            } else {
                sum -= start;
                start++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println(solution(n)); // 4
    }
}