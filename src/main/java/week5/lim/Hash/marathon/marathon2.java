package week5.lim.Hash.marathon;

import java.util.HashMap;

public class marathon2 {
    static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();

        // 참가자의 이름을 해시맵에 넣는다.
        for (String part : participant) {
            map.put(part, map.getOrDefault(part, 0) + 1);
        }

        // 완주자의 이름을 해시맵에서 뺀다.
        for (String comp : completion) {
            map.put(comp, map.get(comp) - 1);
        }

        // 값이 0이 아닌 참가자가 완주하지 못한 사람이다.
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                answer = key;
            }
        }

        return answer;

        // 해시맵 연산은 평균적으로 O(1)이므로 전체 시간 복잡도는 O(N).
    }

    public static void main(String[] args) {
        String[] participant1 = {"leo", "kiki", "eden"};
        String[] completion1 = {"eden", "kiki"};
        System.out.println(solution(participant1, completion1));  // leo

        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};
        System.out.println(solution(participant2, completion2));  // vinko

        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};
        String[] completion3 = {"stanko", "ana", "mislav"};
        System.out.println(solution(participant3, completion3));  // mislav
    }
}
