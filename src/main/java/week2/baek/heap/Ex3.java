package week2.baek.heap;

import org.w3c.dom.ls.LSOutput;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Ex3 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> des = new PriorityQueue(Collections.reverseOrder());
        PriorityQueue<Integer> asc = new PriorityQueue();

        for(String s : operations){
            String j = s.split(" ")[0];
            Integer v = Integer.parseInt(s.split(" ")[1]);

            if(j.equals("I")) {
                des.offer(v);
                asc.offer(v);
            }

            //D 일 때 - 삭제 연산
            else {
                //안해주면 null 포인터 에러 뜸
                if(des.isEmpty()) continue;

                //최솟값 삭제
                else if(v < 0) {
                    int min = asc.poll();
                    des.remove(min);
                }

                //최댓값 삭제
                else{
                    int max = des.poll();
                    asc.remove(max);
                }
            }
        }

        if(des.isEmpty())
            return new int[] {0, 0};

        if(des.size() > 0) {
            answer[0] = des.poll();
            answer[1] = asc.poll();
        }


        return answer;
    }
}

class Ex3Main {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(ex3.solution(operations));
    }

}
