package week12.baek.july26.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S13335 {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄 입력을 읽고 n, w, l을 파싱
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        // 두 번째 줄 입력을 읽고 트럭의 무게를 파싱
        st = new StringTokenizer(br.readLine());
        Queue<Integer> truck = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            truck.offer(Integer.parseInt(st.nextToken()));
        }

        int time = 0; // 경과된 총 시간을 추적
        int weight = 0;   // 다리 위의 현재 무게
        Queue<Integer> q = new LinkedList<Integer>(); // 다리를 나타내는 큐
        for (int i = 0; i < w; i++) {
            q.add(0); // 초기에는 다리가 비어있음(0으로 채움)
        }

        // 트럭이 다리를 건너는 과정을 시뮬레이션
        while (!q.isEmpty()) {
            time++; // 각 반복마다 시간 증가 (1초씩)
            weight -= q.poll(); // 다리를 떠난 트럭의 무게를 현재 무게에서 뺌

            if (!truck.isEmpty()) {
                // 다음 트럭이 다리의 무게 제한을 초과하지 않고 올라갈 수 있는지 확인
                if (truck.peek() + weight <= l) {
                    weight += truck.peek(); // 다음 트럭의 무게를 다리의 현재 무게에 추가
                    q.offer(truck.poll()); // 트럭을 다리 위로 이동
                } else {
                    q.offer(0); // 트럭이 올라갈 수 없으면 0을 추가하여 시간만 경과
                }
            }
        }
        // 모든 트럭이 다리를 건너는 데 걸린 총 시간을 출력
        System.out.println(time);
    }
}
