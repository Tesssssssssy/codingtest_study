package week9.lim.week9_1.n_1966;

// 문제: https://www.acmicpc.net/problem/1966

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        // 결과 저장 StringBuilder
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < testCases; t++) {
            String[] np = br.readLine().split(" ");
            int n = Integer.parseInt(np[0]); // 문서의 수
            int m = Integer.parseInt(np[1]); // 궁금한 문서의 위치

            // 문서의 중요도를 입력받음
            String[] priorities = br.readLine().split(" ");
            // 문서의 중요도와 인덱스를 저장할 큐 선언
            Queue<int[]> queue = new LinkedList<>();
            // 중요도를 저장할 우선순위 큐 선언 (최대 힙)
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            // 각 문서의 중요도를 큐와 우선순위 큐에 저장
            for (int i = 0; i < n; i++) {
                int priority = Integer.parseInt(priorities[i]);
                queue.add(new int[]{priority, i});
                priorityQueue.add(priority);
            }

            // 인쇄 순서를 계산
            int printOrder = 0;
            // 문서를 하나씩 처리하는 반복문
            while (!queue.isEmpty()) {
                int[] current = queue.poll(); // 큐에서 문서를 하나 꺼냄
                // 현재 문서가 인쇄 가능한지 확인
                if (current[0] == priorityQueue.peek()) {
                    printOrder++; // 인쇄하고 인쇄 순서 증가
                    priorityQueue.poll(); // 우선순위 큐에서도 제거
                    // 인쇄한 문서가 궁금한 문서인지 확인
                    if (current[1] == m) {
                        sb.append(printOrder).append('\n'); // 결과 저장
                        break; // 현재 테스트 케이스 종료
                    }
                } else {
                    queue.add(current); // 인쇄 못하면 큐의 끝으로 다시 추가
                }
            }
        }
        // 모든 테스트 케이스의 결과 출력
        System.out.print(sb);
    }
}
