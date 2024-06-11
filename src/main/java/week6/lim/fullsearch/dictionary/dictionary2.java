package week6.lim.fullsearch.dictionary;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/84512
// 참고: https://velog.io/@rvbear/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%AA%A8%EC%9D%8C%EC%82%AC%EC%A0%84

import java.util.ArrayList;

public class dictionary2 {
    static char[] alphabet = {'A', 'E', 'I', 'O', 'U'}; // 모음 저장
    static ArrayList<String> list;  // 사전에 들어갈 모든 경우의 수를 저장할 배열

    // 깊이 우선 탐색
    public static void dfs(String result) {
        // 값을 저장
        list.add(result);

        // 문자열의 길이가 5라면 반환해줌
        if (result.length() == 5) {
            return;
        }

        // 모음을 저장한 배열을 돌면서 값을 넣어줌
        for (int i = 0; i < alphabet.length; i++) {
            dfs(result + alphabet[i]);
        }
    }

    public static int solution(String word) {
        int answer = 0;
        list = new ArrayList<>();
        dfs("");

        // list에 저장된 값을 하나씩 탐색
        for (int i = 1; i < list.size(); i++) {
            // 찾으려는 단어와 같다면
            if (list.get(i).equals(word)) {
                // 해당 위치의 인덱스를 저장
                answer = i;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("AAAAE")); // 6
        System.out.println(solution("AAAE"));  // 10
        System.out.println(solution("I"));     // 1563
        System.out.println(solution("EIO"));   // 1189
    }
}
