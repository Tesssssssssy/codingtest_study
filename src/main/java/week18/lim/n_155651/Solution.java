package week18.lim.n_155651;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/155651

import java.util.*;

public class Solution {
    /**
     * 호텔을 운영 중인 코니는 최소한의 객실만을 사용하여 예약 손님들을 받으려고 합니다.
     * 한 번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님들이 사용할 수 있습니다.
     * 예약 시각이 문자열 형태로 담긴 2차원 배열 book_time이 매개변수로 주어질 때,
     * 코니에게 필요한 최소 객실의 수를 return 하는 solution 함수를 완성해주세요.
     */
    public static int solution(String[][] book_time) {
        // 시간을 "HH:MM" 형식에서 분으로 변환하는 함수
        // 예약 시간을 시작 시간 기준으로 정렬
        Arrays.sort(book_time, (a, b) -> {
            String[] startA = a[0].split(":");
            String[] startB = b[0].split(":");
            int startMinutesA = Integer.parseInt(startA[0]) * 60 + Integer.parseInt(startA[1]);
            int startMinutesB = Integer.parseInt(startB[0]) * 60 + Integer.parseInt(startB[1]);
            return startMinutesA - startMinutesB;
        });

        // 우선순위 큐(최소 힙)를 사용하여 현재 사용 중인 객실의 종료 시간을 관리
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (String[] time : book_time) {
            String[] start = time[0].split(":");
            String[] end = time[1].split(":");

            int startMinutes = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endMinutes = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10; // 청소 시간 10분 추가

            // 현재 사용 중인 객실 중 가장 빠른 종료 시간이 새로운 시작 시간보다 작거나 같으면, 해당 객실을 재사용
            if (!priorityQueue.isEmpty() && priorityQueue.peek() <= startMinutes) {
                priorityQueue.poll(); // 해당 객실을 재사용하므로 종료 시간을 제거
            }

            // 새로운 종료 시간을 큐에 추가
            priorityQueue.add(endMinutes);
        }

        // 큐에 남아있는 요소의 개수가 필요한 최소 객실 수
        return priorityQueue.size();
    }

    public static void main(String[] args) {
        String[][] book_time1 = {
                {"15:00", "17:00"},
                {"16:40", "18:20"},
                {"14:20", "15:20"},
                {"14:10", "19:20"},
                {"18:20", "21:20"}
        };
        System.out.println(solution(book_time1)); // 3

        String[][] book_time2 = {
                {"09:10", "10:10"},
                {"10:20", "12:20"}
        };
        System.out.println(solution(book_time2)); // 1

        String[][] book_time3 = {
                {"10:20", "12:30"},
                {"10:20", "12:30"},
                {"10:20", "12:30"}
        };
        System.out.println(solution(book_time3)); // 3
    }
}
