package week6.lim.bfsdfs.network;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43162

import java.util.LinkedList;
import java.util.Queue;

public class network2 {
    /**
     *  queue를 이용한 풀이
     */
    public static void search(int[][] lists, boolean[] visited, Integer startNum) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNum);
        visited[startNum] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            int[] neighbors = lists[node];
            for (int i = 0; i < neighbors.length; i++) {
                if (!visited[i]) {
                    if (neighbors[i] == 1) {
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[computers.length];

        for (int i = 0; i < computers.length; i++) {
            if (visited[i] == false) {
                answer += 1;
                search(computers, visited, i);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int n1 = 3;
        System.out.println(solution(n1, computers1)); //  2

        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int n2 = 3;
        System.out.println(solution(n2, computers2)); //  1
    }
}
