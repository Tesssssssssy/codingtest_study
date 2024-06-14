package week6.baek.exhaustiveSearch;

import java.util.ArrayList;

public class MockTest {
    public static ArrayList solution(int[] answers) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] score = new int[3];

        int[][] sch = {{1,2,3,4,5}, {2,1,2,3,2,4,2,5}, {3,3,1,1,2,2,4,4,5,5}};

        for (int i = 0; i < answers.length; i++) {
//            //1번 수포자
//            if(answers[i] == sch[0][i % sch[0].length]) {
//                score[0]++;
//            }
//
//            //2번 수포자
//            if(answers[i] == sch[1][i % sch[1].length]) {
//                score[1]++;
//            }
//            //3번 수포자
//            if(answers[i] == sch[2][i % sch[2].length]) {
//                score[2]++;
//            }


            for(int j = 0; j < 3; j++) {
                if(answers[i] == sch[j][i % sch[j].length]) {
                    score[j]++;
                }
            }
        }

        System.out.println(score[0] + " " + score[1] + " " + score[2]);

        int max = score[0];
        for (int i = 1; i < 3; i++) {
            if(score[i] > max) {
                max = score[i];
            }
        }

        for (int i = 0; i < 3; i++) {
            if(score[i] == max) {result.add(i + 1);}
        }

        return result;
    }
    public static void main(String[] args) {
        int[] answers = {1,3,2,4,2};
        System.out.println(solution(answers));
    }
}
