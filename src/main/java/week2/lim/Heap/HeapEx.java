package week2.lim.Heap;

import java.util.PriorityQueue;

public class HeapEx {
    /*
        Java에서는 PriorityQueue를 이용해 Heap을 구현할 수 있다.
         PriorityQueue는 요소를 삽입할 때 Heap 속성을 유지하고,
         peek() 메소드를 통해 최소값에 접근하며, poll() 메소드를 통해 최소값을 삭제한다.
     */
    public static void main(String[] args) {
        // Integer를 저장하는 최소 힙 생성
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 요소 추가
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(8);
        minHeap.offer(1);

        // 최소값 확인
        int minValue = minHeap.peek();
        System.out.println("Min value: " + minValue); // Min value: 1

        // 최소값 삭제
        int deletedValue = minHeap.poll();
        System.out.println("Deleted value: " + deletedValue); // Deleted value: 1

        // 최소값 확인
        minValue = minHeap.peek();
        System.out.println("Min value: " + minValue); // Min value: 2
    }
}
