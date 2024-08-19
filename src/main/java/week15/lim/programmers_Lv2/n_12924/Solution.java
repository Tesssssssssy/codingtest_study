package week15.lim.programmers_Lv2.n_12924;

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
        int start = 1; // 시작 포인터
        int end = 1;   // 끝 포인터
        int sum = 1;   // 현재 합

        while (start <= n) {
            if (sum == n) {
                answer++;  // 합이 n과 같으면 방법을 찾음
                sum -= start;
                start++;
            } else if (sum < n) {
                end++;
                sum += end; // 합이 n보다 작으면 끝 포인터를 증가시켜 더 큰 합으로
            } else {
                sum -= start;
                start++;  // 합이 n보다 크면 시작 포인터를 증가시켜 작은 합으로
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println(solution(n)); // 4
    }
}
/*
    투 포인터 알고리즘의 중요한 특성.
    - end 포인터와 start 포인터가 서로 다른 방식으로 작동하는 이유

    end 포인터의 동작
        end를 먼저 증가시키고 sum에 더하는 이유는,
        연속된 자연수의 합을 계속해서 확장하기 위해서.
        end를 증가시키면 더 큰 숫자를 합에 추가하게 되어 현재 합이 커지게 됨.
        이로 인해 현재 합이 목표 값 n에 도달할 가능성이 생김.

        즉, end는 새로운 숫자를 추가하면서 합을 키우는 역할을 함.

    start 포인터의 동작
        start를 먼저 빼고 증가시키는 이유는,
        현재 합에서 작은 수를 제거하여 합을 줄이기 위해서.
        start 포인터가 가리키는 숫자는 현재 범위에서 가장 작은 값이므로,
        이 값을 빼면 합이 줄어들게 됨.
        이렇게 하면 너무 커져버린 합을 다시 줄여 n에 도달할 수 있는지 확인.

        만약 sum이 n보다 큰 경우, start를 빼고 증가시켜 작은 합으로 만들어
        목표 값 n에 도달할 가능성을 높임.


    ex.) n = 15일 때, start = 1, end = 1로 시작.

    처음 상태: start = 1, end = 1, sum = 1
        sum이 n보다 작으므로, end를 증가시키고 sum에 더함.
    상태 2: start = 1, end = 2, sum = 3
        여전히 sum이 n보다 작으므로 end를 증가시키고 sum에 더함.
    상태 3: start = 1, end = 3, sum = 6
        여전히 sum이 n보다 작으므로 end를 증가시키고 sum에 더함.
    상태 4: start = 1, end = 5, sum = 15
        sum이 n과 같으므로 방법을 하나 찾았으므로 answer를 증가시키고, start를 빼고 증가시켜 합을 줄임.

    이 과정에서 start는 작은 값을 제거하여 합을 줄이고, end는 큰 값을 더해 합을 키우는 역할을 함.
*/