package week2.lim.Heap.dualpriorityqueue;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42628

import java.util.*;

public class DualPriorityQueue {
    public static int[] solution(String[] operations) {
        // 최솟값을 관리할 최소 힙
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 최댓값을 관리할 최대 힙
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 각 숫자의 힙에 존재하는 개수를 추적하기 위한 맵
        Map<Integer, Integer> countMap = new HashMap<>();

        // operations 배열을 순회하면서 각 연산을 수행
        for (String op : operations) {
            String[] parts = op.split(" ");
            String command = parts[0];
            int number = Integer.parseInt(parts[1]);

            if (command.equals("I")) {
                // 'I' 명령어 처리: 숫자 삽입
                // 두 힙에 숫자를 추가하고, countMap에 해당 숫자의 개수를 증가
                minHeap.offer(number);
                maxHeap.offer(number);
                countMap.put(number, countMap.getOrDefault(number, 0) + 1);
            } else if (command.equals("D")) {
                if (number == 1) {
                    // 'D 1' 명령어 처리: 최댓값 삭제
                    // 최대 힙에서 유효한 최댓값을 찾아 제거
                    remove(maxHeap, countMap);
                } else if (number == -1) {
                    // 'D -1' 명령어 처리: 최솟값 삭제
                    // 최소 힙에서 유효한 최솟값을 찾아 제거
                    remove(minHeap, countMap);
                }
            }
        }

        // 모든 연산 후 최댓값과 최솟값을 추출
        int max = extractValid(maxHeap, countMap);
        int min = extractValid(minHeap, countMap);

        // 큐가 비어있으면 [0, 0] 반환, 그렇지 않으면 [최댓값, 최솟값] 반환
        if (max == -1 || min == -1) {
            return new int[]{0, 0};
        } else {
            return new int[]{max, min};
        }
    }

    private static void remove(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap) {
        // 주어진 힙에서 유효한 값(실제로 존재하는 값)을 찾아 제거
        while (!heap.isEmpty()) {
            int num = heap.poll();
            int count = countMap.getOrDefault(num, 0);
            if (count > 1) {
                // 해당 숫자가 여러 개 있으면 개수를 하나 줄임
                countMap.put(num, count - 1);
                break;
            } else if (count == 1) {
                // 해당 숫자가 하나만 있으면 맵에서 완전히 제거
                countMap.remove(num);
                break;
            }
            // 만약 count가 0이면 이미 제거된 것으로 간주하고 계속 다음을 찾음
        }
    }

    private static int extractValid(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap) {
        // 주어진 힙에서 유효한 최대값 또는 최솟값을 추출
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (countMap.containsKey(num)) {
                return heap.poll(); // 유효한 값이면 반환
            } else {
                heap.poll(); // 유효하지 않은 값(이미 제거된 값)은 그냥 제거
            }
        }
        return -1; // 힙이 비어있으면 -1 반환
    }

    public static void main(String[] args) {
        String[] operations1 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations2 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(solution(operations1))); // [0, 0]
        System.out.println(Arrays.toString(solution(operations2))); // [333, -45]
    }
}