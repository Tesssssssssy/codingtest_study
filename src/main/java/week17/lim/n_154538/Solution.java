package week17.lim.n_154538;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/154538

import java.util.*;

public class Solution {
    /**
     *  자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.
     *      x에 n을 더합니다
     *      x에 2를 곱합니다.
     *      x에 3을 곱합니다.
     *
     *  자연수 x, y, n이 매개변수로 주어질 때,
     *  x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요.
     *  이때 x를 y로 만들 수 없다면 -1을 return 해주세요.
     */
    public static int solution(int x, int y, int n) {
        // x가 이미 y인 경우, 연산이 필요 없으므로 0을 반환
        if (x == y)
            return 0;

        /*
            BFS 탐색을 위한 큐를 초기화 (값, 현재 연산 횟수)
            - 현재 값과 그 값에 도달하기까지의 연산 횟수를 저장
        */
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[y + 1];
        // 이미 방문한 값을 기록하여 중복 연산을 방지

        queue.add(new int[]{x, 0});
        visited[x] = true;

        /*
            큐에서 값을 하나씩 꺼내고, 세 가지 연산(+n, *2, *3)을 적용.
            새로운 값이 y와 같다면 그때까지의 연산 횟수를 반환.
            새로운 값이 y보다 작고 아직 방문하지 않은 경우, 큐에 추가하고 방문 처리.
        */
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int value = current[0];
            int count = current[1];

            // 가능한 세 가지 연산
            int[] nextValues = {value + n, value * 2, value * 3};

            for (int nextValue : nextValues) {
                // 목표 값에 도달하면 연산 횟수 반환
                if (nextValue == y) {
                    return count + 1;
                }

                // y보다 작고, 아직 방문하지 않은 경우 큐에 추가
                if (nextValue < y && !visited[nextValue]) {
                    queue.add(new int[]{nextValue, count + 1});
                    visited[nextValue] = true;
                }
            }
        }

        // y에 도달할 수 없는 경우 -1 반환
        return -1;
    }

    public static void main(String[] args) {
        int x1 = 10;
        int y1 = 40;
        int n1 = 5;
        System.out.println(solution(x1, y1, n1)); // 2

        int x2 = 10;
        int y2 = 40;
        int n2 = 30;
        System.out.println(solution(x2, y2, n2)); // 1

        int x3 = 2;
        int y3 = 5;
        int n3 = 4;
        System.out.println(solution(x3, y3, n3)); // -1
    }
}