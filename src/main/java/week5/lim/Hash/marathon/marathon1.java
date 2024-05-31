package week5.lim.Hash.marathon;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42576

import java.util.Arrays;

public class marathon1 {
    public static String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        // 완주자 크기만큼 비교
        for (int i = 0; i < completion.length; i++) {
            if (!participant[i].equals(completion[i]))
                return participant[i];
                // 다르면 해당 participant가 완주하지 못한 것
        }

        return participant[participant.length - 1];
        // 모든 participant가 completion과 일치하면 마지막 참가자가 완주하지 못한 사람
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
