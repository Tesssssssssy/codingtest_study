package week17.lim.n_12927.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12927

import java.util.*;

public class Solution {
    /**
     *  회사원 Demi는 가끔은 야근을 하는데요, 야근을 하면 야근 피로도가 쌓입니다.
     *  야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값입니다.
     *  Demi는 N시간 동안 야근 피로도를 최소화하도록 일할 겁니다.
     *  Demi가 1시간 동안 작업량 1만큼을 처리할 수 있다고 할 때,
     *  퇴근까지 남은 N 시간과 각 일에 대한 작업량 works에 대해
     *  야근 피로도를 최소화한 값을 리턴하는 함수 solution을 완성해주세요.
     */
    public static long solution(int n, int[] works) {
        // 작업량을 큰 수로 정렬해서 저장해야 하기 때문에 우선순위큐 사용
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            priorityQueue.add(work);
        }

        while (n > 0 && !priorityQueue.isEmpty()) {
            int maxWork = priorityQueue.poll();

            if (maxWork > 0) {
                maxWork--;
                n--;
                priorityQueue.add(maxWork);
            } else {
                break;
            }
        }

        long answer = 0;
        while (!priorityQueue.isEmpty()) {
            int work = priorityQueue.poll();
            answer += (long) work * work;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n1 = 4;
        int[] works1 = {4, 3, 3};
        System.out.println(solution(n1, works1)); // 12

        int n2 = 1;
        int[] works2 = {2, 1, 2};
        System.out.println(solution(n2, works2)); // 6

        int n3 = 3;
        int[] works3 = {1, 1};
        System.out.println(solution(n3, works3)); // 0
    }
}
/*
    예시 1: works = [4, 3, 3], n = 4
        초기 상태: 작업량은 [4, 3, 3].
        목표: 4시간 동안 작업량을 줄여야 한다. 야근 피로도는 남은 작업량의 제곱 합이다.

    작업 진행:
        1시간째:
            가장 큰 작업량은 4이다. 이 작업량을 1 줄여서 3으로 만든다.
            남은 작업량: [3, 3, 3], 남은 시간: 3시간
        2시간째:
            가장 큰 작업량은 이제 3이다. 하나를 줄여서 2로 만든다.
            남은 작업량: [3, 3, 2], 남은 시간: 2시간
        3시간째:
            다시 3을 하나 줄여서 2로 만든다.
            남은 작업량: [3, 2, 2], 남은 시간: 1시간
        4시간째:
            마지막으로 3을 하나 줄여서 2로 만든다.
            남은 작업량: [2, 2, 2], 남은 시간: 0시간
    결과:
        최종 남은 작업량: [2, 2, 2]
        야근 피로도는 각 작업량의 제곱을 더한 값이다: 2^2 + 2^2 + 2^2 = 12
        정답: 12
*/