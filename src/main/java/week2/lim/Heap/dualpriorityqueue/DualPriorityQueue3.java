package week2.lim.Heap.dualpriorityqueue;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42628
// 참조: https://thdbs523.tistory.com/234

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DualPriorityQueue3 {
    public static int[] solution(String[] operations) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st;

        for (int i = 0; i < operations.length; i++) {
            st = new StringTokenizer(operations[i]);
            char op = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            switch(op) {
                case 'I':
                    min.add(num);
                    max.add(num);
                    break;
                case 'D':
                    if (max.isEmpty()) break;
                    if (num == 1) {
                        int del = max.poll();
                        min.remove(del);
                    }
                    if (num == -1) {
                        int del = min.poll();
                        max.remove(del);
                    }
            }
        }

        if(max.isEmpty())
            return new int[] {0, 0};

        return new int[] {max.peek(), min.peek()};
    }

    public static void main(String[] args) {
        String[] operations1 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations2 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(solution(operations1))); // [0, 0]
        System.out.println(Arrays.toString(solution(operations2))); // [333, -45]
    }
}
