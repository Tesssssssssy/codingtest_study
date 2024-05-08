package week1.baek.stackqueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class Ex4 {
    public Integer solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(Integer i : priorities) {
            pq.add(i);
        }

        System.out.println(pq);

        int count = 0;
        while(!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                //배열을 돌면서 우선순위 큐의 가장 큰 값을 찾음
                if (pq.peek() == priorities[i]) {
                    count++;

                    //가장 큰 수여서 큐에서 나가야 할 뿐만 아니라, 찾고 있던 원소였다면 반복문 탈출
                    if(i == location){
                        return count;
                    }

                    //가장 큰 수이지만 찾고 있던 원소가 아니라면 다시 배열 탐색
                    pq.poll();
                }
            }
        }
        return count;
    }
}

class Ex4Main {
    public static void main(String[] args) {
        Ex4 ex4 = new Ex4();
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        System.out.println(ex4.solution(priorities, location));

    }
}