package week1.lim.Hash.marathon;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42576

import java.util.HashMap;

public class marathon1 {
    static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();

        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        // 참가한 선수의 이름을 해시맵에 추가하면서 이름이 이미 존재하면 카운트 1 증가
        // getOrDefault는 이름이 맵에 존재하지 않을 경우, 기본값 0 반환하고 그렇지 않으면 해당 값(카운트) 반환

        for (String player : completion) hm.put(player, hm.get(player) - 1);
        // 완주한 선수의 이름을 해시맵에서 찾아서 해당 카운트 1 감소
        // 완주하지 못한 선수의 카운트는 0이 되지 않는다.

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0){
                answer = key;
            }
        }
        // 해시맵을 순회하면서 카운트가 0이 아닌 선수를 찾는다.
        // 이 선수가 완주하지 못한 선수.

        return answer;

        // 해시맵 연산은 평균적으로 O(1)이므로 전체 시간 복잡도는 O(N).
    }

    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(solution(participant, completion));  // leo
    }
}
