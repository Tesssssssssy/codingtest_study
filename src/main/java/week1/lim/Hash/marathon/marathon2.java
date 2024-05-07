package week1.lim.Hash.marathon;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42576

import java.util.Arrays;

public class marathon2 {
    static String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i;
        for (i=0; i<completion.length; i++){
            if (!participant[i].equals(completion[i])){
                return participant[i];
            }
        }
        return participant[i];
    }

    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(solution(participant, completion));  // leo
    }
}
