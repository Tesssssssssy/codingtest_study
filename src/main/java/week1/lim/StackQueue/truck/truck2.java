package week1.lim.StackQueue.truck;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42583
// 참고: https://minhamina.tistory.com/241

import java.util.LinkedList;
import java.util.Queue;

public class truck2 {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;
        int time = 0;

        for (int i = 0; i < truck_weights.length; i++) { // 향상된 for문을 쓰는게 좋을 것
            int truck = truck_weights[i];

            while (true) {
                // 큐에 아무것도 없는 경우 = 어떠한 트럭도 다리위에 없음
                if (queue.isEmpty()) {
                    queue.add(truck);
                    sum += truck;
                    time++; // 다리에 오를 때만 시간 추가
                    break;
                } else if (queue.size() == bridge_length) { // 큐에 다리 길이만큼 트럭이 다 찬 경우
                    sum -= queue.poll();
                } else  { // 다리 길이만큼 큐가 차지않았음
                    // weight 값을 넘지 않는 선에서 새로운 트럭을 다리에 올려줌
                    if (sum + truck <= weight) {
                        queue.add(truck);
                        sum += truck;
                        time++;
                        break;
                    } else {
                        // 넘는다면 0을 넣어 이미 큐에 있는 트럭이 다리를 건너게 만듬
                        queue.add(0);
                        time++;
                    }
                }
            }
        }

        // 마지막 트럭에서 반복문이 끝나는데, 마지막 역시 다리 길이만큼 지나가야하기에 + 다리 길이
        return time + bridge_length;
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
