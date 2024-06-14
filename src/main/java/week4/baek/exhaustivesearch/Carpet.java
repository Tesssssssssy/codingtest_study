package week4.baek.exhaustivesearch;

import java.util.ArrayList;
import java.util.Collections;

public class Carpet {
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int all = brown + yellow;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= (int)Math.sqrt(all); i++) {
            if(all % i == 0) {
                list.add(i);
                if(all / i != i)
                    list.add(all/i);
            }
        }

        Collections.sort(list);
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i);
            //y가 가로(더 크니까)
            int y = all / x;

            //가로는 무조건 3 이상이어야 함
            if(y < 3) continue;

            // (가로-2)*(세로-2) = 노랑색 개수
            if((y-2) * (x-2) == yellow) {
                answer[0] = y;
                answer[1] = x;
                System.out.print("answer[0] = " + answer[0]);
                System.out.println("answer[1] = " + answer[1]);
                break;
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution(10, 2));
    }
}
