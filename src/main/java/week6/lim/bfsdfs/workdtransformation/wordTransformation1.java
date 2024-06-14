package week6.lim.bfsdfs.workdtransformation;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43163

import java.util.*;

public class wordTransformation1 {
    /**
     *  큐 초기화
     *      - BFS를 수행할 큐를 초기화하고, 시작 단어 begin과 단계 수 0을 큐에 추가.
     *
     *  방문 리스트
     *      - words 배열을 리스트로 변환하여 각 단어의 방문 여부를 쉽게 관리합니다.
     *
     *  BFS 실행
     *      - 큐가 빌 때까지 BFS를 실행.
     *      - 큐에서 단어를 꺼내고, 해당 단어에서 한 글자만 변경하여 다른 단어로 변환 가능한지 검사.
     *        가능한 경우 해당 단어를 큐에 추가하고, 변환된 단어가 target과 같은지 검사하여 조건을 만족하면 단계 수를 반환.
     *
     *  단어 변환 검사
     *      - canConvert 메소드를 통해 두 단어가 한 글자만 다른지 확인.
     */
    public static int solution(String begin, String target, String[] words) {
        // target이 words 배열에 없으면 변환 불가능
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>(); // BFS를 위한 큐
        queue.add(new Node(begin, 0)); // 시작 단어와 단계 수(0)를 큐에 추가

        // words 배열을 방문 여부 체크를 위해 리스트로 변환
        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // 큐에서 노드(단어와 단계 수)를 꺼냄

            // 모든 단어를 순회하며 가능한 변환 탐색
            Iterator<String> it = wordList.iterator();
            while (it.hasNext()) {
                String nextWord = it.next();
                // 현재 단어에서 한 글자만 변경하여 nextWord가 될 수 있는지 확인
                if (canConvert(current.word, nextWord)) {
                    if (nextWord.equals(target)) { // 변환된 단어가 target과 같다면
                        return current.level + 1; // 현재 단계에서 한 단계 더 진행한 값을 반환
                    }
                    queue.add(new Node(nextWord, current.level + 1)); // 큐에 추가
                    it.remove(); // 방문한 단어는 리스트에서 제거
                }
            }
        }
        return 0; // 변환할 수 없는 경우
    }

    // 두 단어 간에 변환이 가능한지 (한 글자만 다른지) 검사하는 메서드
    private static boolean canConvert(String current, String next) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != next.charAt(i)) {
                count++;
            }
            if (count > 1) return false;
        }
        return count == 1;
    }

    // BFS 탐색을 위해 사용할 노드 클래스
    static class Node {
        String word;
        int level;

        Node(String word, int level) {
            this.word = word;
            this.level = level;
        }
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
