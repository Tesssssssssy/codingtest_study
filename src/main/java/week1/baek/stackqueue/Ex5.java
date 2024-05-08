package week1.baek.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class Ex5 {
    public void solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList();

        for (int i : truck_weights){
            for (int j = 0; j < bridge_length; j++) {
                queue.add(i);
            }
        }


        Queue<Integer> bridge = new LinkedList<>();
        int count = 1;
        int currentWeight = truck_weights[0];

        while(!queue.isEmpty()) {
//            현재 다리에 있는 차들의 무게의 합 + 다음에 들어갈 차 무게가 다리의 무게보다 무거우면
//            현재 다리에 있는 차들을 한 칸씩 앞으로
            if(currentWeight + truck_weights[0] > weight) {
                count++;
                queue.poll();
            }
            //들어갈 자리가 있다면
//            else if(c){
//
//            }
        }
    }
}


class Ex5Main {
    public static void main(String[] args) {
        Ex5 ex5 = new Ex5();
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7,4,5,6};

        ex5.solution(bridge_length,  weight, truck_weights);

    }
}