package week1.baek.stackqueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Ex2 {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        int days;
        ArrayList<Integer> list = new ArrayList();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            double d = (100 - progresses[i])/speeds[i];
            days = (int) Math.ceil(d);
            queue.add(days);
        }

        System.out.println(queue);

        //큐에서 하나씩 원소를 빼내면서,
        int m = queue.poll();
        int count = 1;
        System.out.println("m = " + m);
        while(!queue.isEmpty()) {
            //큐에서 하나를 빼냄
            int n = queue.poll();
            System.out.println( "n = "+ n);

            //크면 현재까지 count를 list에 저장
            if(n > m){
                list.add(count);
                System.out.println("count= " + count);

                count = 1;
                if(queue.size() > 0) {
                    m = n;
                    System.out.println("m = " + m);
                }

                //마지막 원소였다면
                else{
                    list.add(1);
                }
            }

            else {
                count++;

                //마지막 원소였다면
                if(queue.isEmpty()) list.add(count);
            }
        }
        System.out.println(list);
        return list;
    }
}

class Ex2Main {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};

        ex2.solution(progresses, speeds);

    }
}