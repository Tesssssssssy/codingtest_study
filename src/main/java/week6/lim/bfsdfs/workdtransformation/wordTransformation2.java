package week6.lim.bfsdfs.workdtransformation;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43163
// 참고: https://isshosng.tistory.com/168

import java.util.*;

public class wordTransformation2 {
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        Queue<String> queue = new LinkedList<>(); // 변환 가능한 단어들을 저장할 queue
        Set<String> set = new HashSet<>(Arrays.asList(words)); // 단어들의 집합 set

        if (!set.contains(target)) {
            return 0; // 변환할 수 없는 경우
        }

        queue.offer(begin); // queue에 begin 단어 추가
        set.remove(begin); // set에서 begin 단어 제거

        while (!queue.isEmpty()) {

            for (int i = 0; i < queue.size(); i++) {
                // queue에서 단어를 하나씩 꺼내어 current (현재단어)에 저장
                String current = queue.poll();

                if (current.equals(target)) {
                    return answer; // 타겟 단어에 도달
                }

                // HashSet 타입은 for 루프를 사용할 수 없으므로 to.Array()를 사용해 배열로 변환
                for (String word : set.toArray(new String[set.size()])) {
                    // 변환 가능하면 queue에 해당 단어를 추가하고 set에서 제거
                    if (canConvert(current, word)) {
                        queue.offer(word);
                        set.remove(word);
                    }
                }
            }

            answer++;
        }

        return 0; // 변환할 수 없는 경우
    }

    private static boolean canConvert(String word1, String word2) {
        int diffCnt = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }

    public static void main(String[] args) {
        String begin1 = "hit";
        String target1 = "cog";
        String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution(begin1, target1, words1));  // 4

        String begin2 = "hit";
        String target2 = "cog";
        String[] words2 = {"hot", "dot", "dog", "lot", "log"};
        System.out.println(solution(begin2, target2, words2));  // 0
    }
}
