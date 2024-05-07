package week1.lim.StackQueue.funcdevelop;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12906

import java.util.*;

public class funcDevelop {
    static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> days = new LinkedList<>();   // 각 작업이 완료되기까지 필요한 일수를 저장하기 위한 큐
        List<Integer> result = new ArrayList<>();   // 각 배포 날짜마다 배포되는 기능의 수를 저장하는 리스트

        // 각 작업 별 완료까지 필요한 일수 계산
        // remain: 각 작업의 남은 진도
        // day: 작업을 완료하는데 필요한 일수
        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            int day = (remain + speeds[i] - 1) / speeds[i]; // 나눗셈 결과가 정수가 아닐 때 올림을 하기 위한 계산
            days.offer(day);
        }

        // 배포 일수 계산
        // 배포가 가능한 날에 배포할 수 있는 모든 기능을 함께 배포
        // 연속된 기능이 이미 완성되었다면 함께 배포하고 count++
        while (!days.isEmpty()) {
            int count = 1;  // 해당 배포일에 배포될 기능의 수를 세는 변수
            int deployDay = days.poll(); // 큐에서 가장 앞에 있는 요소를 제거(poll())하며, 이는 첫 번째 배포될 기능의 완성일

            while (!days.isEmpty() && days.peek() <= deployDay) {
                // 첫 번째 배포일(deployDay) 이후에 완성되는 기능들 중에서 deployDay보다 작거나 같은 완성일을 가진 기능들을 찾는다.
                // 이러한 기능들은 deployDay에 함께 배포될 수 있으므로, count를 증가시키고 큐에서 해당 요소를 제거

                count++;
                days.poll();
            }

            result.add(count);
            // 계산된 count를 result 리스트에 추가합니다. 이는 deployDay에 배포되는 기능의 수 의미
        }

        // 결과 리스트를 배열로 변환
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        System.out.println(Arrays.toString(solution(progresses1, speeds1)));  // [2, 1]

        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(solution(progresses2, speeds2)));  // [1, 3, 2]
    }
}
