package week6.baek.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MoreSpicy {

    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : scoville) {
            pq.add(x);
        }

        int count = 0;
        while(pq.peek() < K){
            if(pq.size() < 2 ){
                return -1;
            }

            int n = pq.poll();
            int m = pq.poll();
            pq.add(n + m *2);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] scoville = { 1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(solution(scoville, K));
    }
}
