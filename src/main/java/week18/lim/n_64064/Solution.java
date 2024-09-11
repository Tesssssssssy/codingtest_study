package week18.lim.n_64064;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/64064

import java.util.*;

public class Solution {
    public static int solution(String[] user_id, String[] banned_id) {
        /*
            제재 아이디 조합의 모든 가능한 경우의 수를 찾기 위해 백트래킹을 호출
            Set을 사용하여 중복된 조합을 자동으로 제거하고, 결과의 크기를 반환
        */

        Set<Set<String>> result = new HashSet<>();
        /*
            Set을 사용하여 조합의 중복 제거.
            ex.) [frodo, abc123]와 [abc123, frodo]는 동일한 조합으로 처리
        */

        backtrack(user_id, banned_id, 0, new HashSet<>(), result);
        // 백트래킹을 통해 가능한 조합 찾기

        return result.size(); // 중복을 제거한 가능한 조합의 수 반환
    }

    private static void backtrack(String[] user_id, String[] banned_id, int idx,
                                  Set<String> currentSet, Set<Set<String>> result) {
        /*
            백트래킹 알고리즘을 구현하여 가능한 모든 조합을 탐색

            각 단계에서 현재 아이디가 불량 사용자 패턴에 일치하는지 확인하고, 일치하면 다음 패턴으로 이동.
            현재 단계에서 선택된 조합을 Set에 추가. 이 과정에서 모든 조합이 탐색됨.
        */

        // 모든 불량 사용자 아이디를 처리한 경우, 현재 조합을 결과에 추가
        if (idx == banned_id.length) {
            result.add(new HashSet<>(currentSet));
            // Set을 사용하여 중복된 조합을 자동으로 제거

            return;
        }

        String pattern = banned_id[idx];
        // 현재 처리할 불량 사용자 아이디의 패턴

        for (String user : user_id) {
            // 모든 응모자 아이디에 대해 반복

            // 패턴과 일치하고, 아직 currentSet에 포함되지 않은 아이디만 추가
            if (matches(pattern, user) && !currentSet.contains(user)) {
                currentSet.add(user); // 현재 아이디를 선택
                backtrack(user_id, banned_id, idx + 1, currentSet, result);
                // 다음 패턴으로 재귀 호출

                currentSet.remove(user);
                // 선택한 아이디를 제거 (백트래킹의 핵심)
            }
        }
    }

    private static boolean matches(String pattern, String user) {
        /*
            불량 사용자 패턴과 실제 사용자가 일치하는지 확인.

            '*' 문자는 어떤 문자와도 일치할 수 있으며, 다른 문자는 정확히 일치해야 함.
            패턴과 사용자가 일치하면 true, 그렇지 않으면 false를 반환.
        */

        // 패턴과 아이디의 길이가 다르면 일치할 수 없음
        if (pattern.length() != user.length()) {
            return false;
        }

        // 패턴의 각 문자와 사용자 아이디의 문자를 비교
        for (int i = 0; i < pattern.length(); i++) {
            /*
                '*' 문자는 어떤 문자와도 일치할 수 있으므로 건너뜀
                다른 문자는 정확히 일치해야 함
            */
            if (pattern.charAt(i) != '*' && pattern.charAt(i) != user.charAt(i)) {
                return false; // 하나라도 일치하지 않으면 false 반환
            }
        }

        return true; // 모든 문자가 일치하면 true 반환
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

/*
    백트래킹의 기본 개념
        백트래킹은 가능한 모든 경우의 수를 탐색하는 알고리즘이다.
        DFS(깊이 우선 탐색)를 사용하여 한 경로를 끝까지 탐색한 후,
        다른 경로를 탐색하기 위해 이전 선택을 되돌리는 과정을 포함한다.
        이를 통해 한 번의 탐색으로 다양한 경우를 모두 탐색할 수 있다.

    currentSet.remove(user)가 중요한 이유
        다른 조합 탐색
            백트래킹에서 currentSet.remove(user)는 이전 단계에서 추가한 선택을 제거함으로써,
            다른 가능한 선택지를 탐색할 수 있게 해준다.
            즉, 이미 한 선택을 제거하고 다른 선택을 탐색하게 되는 것이다.

        중복 방지
            currentSet은 현재 탐색 중인 경로에서 선택한 아이디들을 저장하는데,
            특정 경로를 탐색한 후에는 이 선택을 되돌려야만 다른 경로를 탐색할 때 이전 선택이 영향을 미치지 않도록 할 수 있다.
            만약 제거하지 않는다면, 잘못된 조합이 만들어질 수 있다.

        모든 가능한 조합 탐색
            백트래킹의 핵심은 한 경로를 탐색한 후 원래 상태로 돌아가서 다른 경로를 탐색하는 것이다.
            currentSet.remove(user)가 없으면 한 번 선택한 user가 계속 currentSet에 남아 있게 되어,
            올바른 모든 조합을 탐색할 수 없게 된다.

        ex.)
        Set<String> currentSet = new HashSet<>();

        // Step 1: 선택
        currentSet.add("frodo");  // currentSet = {"frodo"}
        currentSet.add("abc123"); // currentSet = {"frodo", "abc123"}

        // 탐색이 끝나면, "abc123"을 제거하고 다른 조합을 탐색
        currentSet.remove("abc123"); // currentSet = {"frodo"}

        // 다음 선택
        currentSet.add("fradi"); // currentSet = {"frodo", "fradi"}

        // 탐색이 끝나면, "frodo"도 제거하고 다른 조합을 탐색
        currentSet.remove("frodo"); // currentSet = {}

        // 다음 선택을 탐색...
*/