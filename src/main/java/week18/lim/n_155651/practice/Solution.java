package week18.lim.n_155651.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/155651

import java.util.*;

public class Solution {
    public static int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> {
            String[] startA = a[0].split(":");
            String[] startB = b[0].split(":");
            int startMinutesA = Integer.parseInt(startA[0]) * 60 + Integer.parseInt(startA[1]);
            int startMinutesB = Integer.parseInt(startB[0]) * 60 + Integer.parseInt(startB[1]);
            return startMinutesA - startMinutesB;
        });

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (String[] time : book_time) {
            String[] start = time[0].split(":");
            String[] end = time[1].split(":");

            int startMinutes = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endMinutes = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10;

            if (!priorityQueue.isEmpty() && priorityQueue.peek() <= startMinutes) {
                priorityQueue.poll();
            }

            priorityQueue.add(endMinutes);
        }

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
