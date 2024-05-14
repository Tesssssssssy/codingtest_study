package week2.baek.sort;

import java.util.Arrays;
import java.util.Collections;

public class Ex3 {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        int h = 0;

        for (int i = 0; i < citations.length; i++) {
            h = citations.length - i;

            if(citations[i] >= h){
                answer = h;
                break;
            }
        }

        return h;
    }
}

class Ex3Main{
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(ex3.solution(citations));
    }
}
