package week17.lim.n_42888;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42888

import java.util.*;

public class Solution {
    public static String[] solution(String[] record) {
        HashMap<String, String> userMap = new HashMap<>();
        // userId와 nickname 매핑해 저장

        List<String> resultList = new ArrayList<>();

        // 첫 번째 반복: Enter와 Change 명령어를 처리하여 userMap 갱신
        for (String r : record) {
            String[] parts = r.split(" ");
            String command = parts[0];
            String userId = parts[1];

            if (command.equals("Enter") || command.equals("Change")) {
                String nickname = parts[2];
                userMap.put(userId, nickname);
            }
        }

        // 두 번째 반복: 출력할 최종 메시지 구성
        for (String r : record) {
            String[] parts = r.split(" ");
            String command = parts[0];
            String userId = parts[1];

            if (command.equals("Enter")) {
                resultList.add(userMap.get(userId) + "님이 들어왔습니다.");
            } else if (command.equals("Leave")) {
                resultList.add(userMap.get(userId) + "님이 나갔습니다.");
            }
        }

        // 결과를 배열로 변환
        return resultList.toArray(new String[resultList.size()]);
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
                "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] result = solution(record);
        for (String message : result) {
            System.out.println(message);
        }
        // ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]

    }
}
