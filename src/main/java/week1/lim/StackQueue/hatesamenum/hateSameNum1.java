package week1.lim.StackQueue.hatesamenum;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12906

import java.util.ArrayList;
import java.util.Arrays;

public class hateSameNum1 {
    static int[] solution(int []arr) {
        ArrayList<Integer> answerList = new ArrayList<Integer>();

        int value = -1;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] != value) {
                answerList.add(arr[i]);
                value = arr[i];
            }
        }
        return answerList.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        int []arr = {1,1,3,3,0,1,1};

        System.out.println(Arrays.toString(solution(arr))); // [1, 3, 0, 1]
    }
}
