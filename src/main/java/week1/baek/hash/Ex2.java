package week1.baek.hash;

import java.util.*;

//참고 : https://dos-soles.tistory.com/23

public class Ex2 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : participant) {
            //존재하지 않으면 0을 넣고
            //존재하면 1 증가
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (String s : completion) {
            map.put(s, map.get(s) - 1);
        }

        System.out.println(map);

        for (String s : map.keySet()) {
            if (map.get(s) != 0) {
                answer = s;
                break;
            }
        }
        return answer;
    }
}


class Ex2Main {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion = {"josipa", "filipa", "marina", "nikola"};

        System.out.println(ex2.solution(participant, completion));

    }
}