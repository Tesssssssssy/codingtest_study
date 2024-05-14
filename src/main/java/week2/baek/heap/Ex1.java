package week2.baek.heap;

import java.util.*;

public class Ex1 {
    public int solution(int[] scoville, int K) {
            int answer = 0;

            PriorityQueue<Integer> pq = new PriorityQueue();

            for(int i : scoville){
                pq.offer(i);
            }

            // System.out.println(pq);

            Integer a, b;
            while(pq.peek() < K && pq.size() >= 2){
                if(pq.peek() < K) {
                    a = pq.remove();
                    b = pq.remove();
                    pq.add(a + 2*b);

                    answer++;
                }
            }

            if(pq.size() == 1 && pq.peek() < K) return -1;

            else return answer;
    }
}

class Ex1Main{
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }
}