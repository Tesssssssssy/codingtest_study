package week6.baek.heap;

import week2.baek.heap.Ex3;

import java.util.Collections;
import java.util.PriorityQueue;

public class TwowayPriorityQueue {
    public static int[] solution (String[] operations) {
        PriorityQueue<Integer> ascHeap = new PriorityQueue<>();
        PriorityQueue<Integer> desHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(String s: operations){
            if(s.charAt(0) ==  'I'){
                System.out.println("I입니다");
                int num = Integer.parseInt(s.split(" ")[1]);
                System.out.println(num);
                ascHeap.offer(num);
                desHeap.offer(num);
            }
            else {
                System.out.println("D입니다");
                //최솟값 삭제
                if(s.split(" ")[1].equals("-1")) {
                    Integer i = ascHeap.poll();
                    System.out.println(i);
                    desHeap.remove(i);
                }

                //최댓값 삭제
                else{
                    Integer i = desHeap.poll();
                    System.out.println(i);
                    ascHeap.remove(i);
                }
            }
        }

        int[] result = new int[2];
        if(ascHeap.size() == 0) {
            return result;
        }

        result[0] = desHeap.poll();
        result[1] = ascHeap.poll();
        System.out.println(result[0] + " " + result[1]);
        return result;
    }

    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(solution(operations));
    }
}
