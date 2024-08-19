package week15.lim.programmers_Lv2.n_12981;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12981

import java.util.*;

public class Solution {
    /**
     *  1부터 n까지 번호가 붙어있는 n명의 사람이 영어 끝말잇기
     *      1번부터 번호 순서대로 한 사람씩 차례대로 단어를 말합니다.
     *      마지막 사람이 단어를 말한 다음에는 다시 1번부터 시작합니다.
     *      앞사람이 말한 단어의 마지막 문자로 시작하는 단어를 말해야 합니다.
     *      이전에 등장했던 단어는 사용할 수 없습니다.
     *      한 글자인 단어는 인정되지 않습니다.
     *
     *  다음은 3명이 끝말잇기를 하는 상황을 나타냅니다.
     *  tank → kick → know → wheel → land → dream → mother → robot → tank
     *
     *  1번 사람이 자신의 첫 번째 차례에 tank를 말합니다.
     *  2번 사람이 자신의 첫 번째 차례에 kick을 말합니다.
     *  3번 사람이 자신의 첫 번째 차례에 know를 말합니다.
     *  1번 사람이 자신의 두 번째 차례에 wheel을 말합니다.
     *  (계속 진행)
     *
     *  끝말잇기를 계속 진행해 나가다 보면,
     *  3번 사람이 자신의 세 번째 차례에 말한 tank 라는 단어는 이전에 등장했던 단어이므로 탈락하게 됩니다.
     *
     *  사람의 수 n과 사람들이 순서대로 말한 단어 words 가 매개변수로 주어질 때,
     *  가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지를 구해서 return.
     *  ( 만약 주어진 단어들로 탈락자가 생기지 않는다면, [0, 0]을 return 해주세요. )
     */
    public static int[] solution(int n, String[] words) {
        Set<String> wordSet = new HashSet<>();
        wordSet.add(words[0]); // 첫 단어는 미리 추가
        char lastChar = words[0].charAt(words[0].length() - 1); // 첫 단어의 마지막 글자

        for (int i = 1; i < words.length; i++) {
            String currentWord = words[i];

            // 1. 이미 사용된 단어인지 체크
            if (wordSet.contains(currentWord)) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }

            // 2. 끝말잇기 규칙을 따르는지 체크
            if (currentWord.charAt(0) != lastChar) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }

            // 현재 단어를 set에 추가하고, 마지막 글자를 업데이트
            wordSet.add(currentWord);
            lastChar = currentWord.charAt(currentWord.length() - 1);
        }

        // 탈락자가 없을 경우
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        int n1 = 3;
        String[] words1 = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        System.out.println(Arrays.toString(solution(n1, words1))); // [3,3]

        int n2 = 5;
        String[] words2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage",
                "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        System.out.println(Arrays.toString(solution(n2, words2))); // [0,0]


        int n3 = 2;
        String[] words3 = {"hello", "one", "even", "never", "now", "world", "draw"};
        System.out.println(Arrays.toString(solution(n3, words3))); // [1,3]
    }
}
