package week17.lim.n_42888;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42888

import java.util.*;

public class Solution2 {
    public static String[] solution(String[] record) {
        HashMap<String, String> userMap = new HashMap<>();
        List<String> resultList = new ArrayList<>();

        for (String r : record) {
            String[] parts = r.split(" ");
            String command = parts[0];
            String userId = parts[1];

            if (command.equals("Enter")) {
                String nickname = parts[2];
                userMap.put(userId, nickname); // 최신 닉네임으로 갱신
                resultList.add(userId + "님이 들어왔습니다."); // 메시지 저장
            } else if (command.equals("Leave")) {
                resultList.add(userId + "님이 나갔습니다."); // 메시지 저장
            } else if (command.equals("Change")) {
                String nickname = parts[2];
                userMap.put(userId, nickname); // 닉네임 갱신만
            }
        }

        // 저장된 메시지에서 최종적으로 출력할 때 닉네임 매핑
        for (int i = 0; i < resultList.size(); i++) {
            String message = resultList.get(i);
            String userId = message.split("님")[0];
            resultList.set(i, message.replace(userId, userMap.get(userId)));
        }

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
