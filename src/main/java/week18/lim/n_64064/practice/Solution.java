package week18.lim.n_64064.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/64064

import java.util.*;

public class Solution {
    public static int solution(String[] user_id, String[] banned_id) {
        // 모든 조합을 저장할 Set
        Set<Set<String>> result = new HashSet<>();

        backtrack(user_id, banned_id, 0, new HashSet<>(), result);

        return result.size();
    }

    private static void backtrack(String[] user_id, String[] banned_id, int idx,
                                  Set<String> currentSet, Set<Set<String>> result) {
        // 가능한 모든 조합 탐색

        // idx가 banned_id.length와 같으면 결과에 현재까지 set 추가
        if (idx == banned_id.length) {
            result.add(new HashSet<>(currentSet));
            return;
        }

        String pattern = banned_id[idx];

        for (String user : user_id) {
            // 패턴과 일치하고, currentSet에 없는 유저만 추가
            if (matches(pattern, user) && !currentSet.contains(user)) {
                currentSet.add(user);
                backtrack(user_id, banned_id, idx + 1, currentSet, result);
                currentSet.remove(user);
            }
        }
    }

    private static boolean matches(String pattern, String user) {
        if (pattern.length() != user.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '*' && pattern.charAt(i) != user.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] user_id1 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id1 = {"fr*d*", "abc1**"};
        System.out.println(solution(user_id1, banned_id1)); // 2

        String[] user_id2 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id2 = {"*rodo", "*rodo", "******"};
        System.out.println(solution(user_id2, banned_id2)); // 2

        String[] user_id3 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id3 = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id3, banned_id3)); // 3
    }
}