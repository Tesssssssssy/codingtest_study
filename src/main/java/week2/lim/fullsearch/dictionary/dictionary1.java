package week2.lim.fullsearch.dictionary;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/84512
// 참고: https://velog.io/@jh5253/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%AA%A8%EC%9D%8C%EC%82%AC%EC%A0%84-Java%EC%9E%90%EB%B0%94

import java.util.ArrayList;
import java.util.List;

public class dictionary1 {
    static List<String> list;
    static String [] words = {"A", "E", "I", "O", "U"};

    public static int solution(String word) {
        int answer = 0;
        list = new ArrayList<>();

        dfs("", 0);

        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    static void dfs(String str, int len) {
        list.add(str);

        if (len == 5)
            return;

        for (int i = 0; i < 5; i++) {
            dfs(str + words[i], len + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("AAAAE")); // 6
        System.out.println(solution("AAAE"));  // 10
        System.out.println(solution("I"));     // 1563
        System.out.println(solution("EIO"));   // 1189
    }
}
