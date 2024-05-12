package week1.lim.StackQueue.truck;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42583

import java.util.LinkedList;
import java.util.Queue;

public class truck1 {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>(); // 다리를 현재 건너고 있는 트럭들의 무게를 저장
        int currentTime = 0;    // 현재까지 경과한 시간
        int currentWeight = 0;  // 다리 위에 있는 트럭들의 총 무게
        int truckIndex = 0;     // 대기 트럭 배열에서 다리에 올릴 다음 트럭의 인덱스

        /*
            다리 초기화
            다리를 표현하는 큐 bridge를 초기화하여 다리 길이만큼 0으로 채운다.
            -> 다리가 비어있음 의미.
        */
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while (!bridge.isEmpty()) {
            currentTime++;
            currentWeight -= bridge.poll();
            // 트럭이 다리를 완전히 건너면 큐에서 제거하고 currentWeight를 감소

            if (truckIndex < truck_weights.length) {
                if (currentWeight + truck_weights[truckIndex] <= weight) {
                    // 새 트럭이 다리에 진입할 수 있으면 (현재 무게 + 새 트럭 무게 <= 최대 무게) 큐에 추가,
                    // 그렇지 않으면 큐에 0을 추가해 다리 길이 유지

                    bridge.offer(truck_weights[truckIndex]);
                    currentWeight += truck_weights[truckIndex];
                    truckIndex++;
                } else {
                    // 다리에 공간은 있지만 무게 초과로 다음 트럭을 올릴 수 없을 때
                    bridge.offer(0);
                }
            }
        }

        return currentTime;
    }

    public static void main(String[] args) {
        int[] trucks1 = {7, 4, 5, 6};
        System.out.println(solution(2, 10, trucks1)); // 8

        int[] trucks2 = {10};
        System.out.println(solution(100, 100, trucks2)); // 101

        int[] trucks3 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        System.out.println(solution(100, 100, trucks3)); // 110
    }
}
