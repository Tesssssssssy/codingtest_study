package week17.lim.n_154538.practice;

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
        // x가 y가 같으면 바로 반환
        if (x == y)
            return 0;

        /*
            BFS 탐색
            - Queue
            - visited 배열 (방문 여부 표시)
        */
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[y + 1];

        queue.add(new int[]{x, 0});
        visited[x] = true;

        /*
            Queue에서 값을 빼서 연산 처리해서 y와 같은지 판단
            만약 y와 같으면 그대로 count + 1 해서 출력
            만약 y와 다르면 다시 queue에 넣고 반복
        */
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int value = current[0];
            int count = current[1];

            int[] nextValues = new int[]{value + n, value * 2, value * 3};

            for (int nextValue : nextValues) {
                if (nextValue == y) {
                    return count + 1;
                }

                if (nextValue < y && !visited[nextValue]) {
                    queue.add(new int[]{nextValue, count + 1});
                    visited[nextValue] = true;
                }
            }
        }

        // x를 y로 만들 수 없는 경우 -1 출력
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