package week1.lim.StackQueue;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueEx {
    public static void main(String[] args) {
        Queue priorityQueue = new PriorityQueue();

        priorityQueue.offer(3);	//원래는 priorityQueue.offer(new Integer(3)); 해야하는데 오토박싱이 된것
        priorityQueue.offer(1);
        priorityQueue.offer(5);
        priorityQueue.offer(2);
        priorityQueue.offer(4);
        System.out.println(priorityQueue);

        Object obj = null;

        //PriorityQueue에 저장된 요소를 하나씩 꺼낸다.
        while((obj = priorityQueue.poll()) != null)
            System.out.println(obj);
    }

    /*
            [1, 2, 5, 3, 4]
            1
            2
            3
            4
            5

            배열에 저장된 순서와 실제 우선순위가 다른 것은  PriorityQueue 가 각 요소를 힙이라는 자료구조의 형태로 저장한 것이라서 그렇다.
    */
}
