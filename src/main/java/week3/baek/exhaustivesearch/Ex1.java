package week3.baek.exhaustivesearch;

import java.util.Arrays;

public class Ex1 {
    public int solution(int[][] sizes) {
        //가로든 세로든 길이가 작은 거, 긴거 순서로 다시 정렬
        for(int[] i : sizes){
            Arrays.sort(i);
        }

        //그 중 가장 길고 작은 길이를 구하기
        int max1 = 0;
        int max2 = 0;
        for(int[] i : sizes){
            if(i[0] > max1) max1 = i[0];
            if(i[1] > max2) max2 = i[1];
        }

        return max1*max2;
    }
}

class Ex1Main{
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        int [][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        System.out.println(ex1.solution(sizes));
    }
}