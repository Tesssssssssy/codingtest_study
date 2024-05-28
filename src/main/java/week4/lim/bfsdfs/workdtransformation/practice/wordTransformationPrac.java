package week4.lim.bfsdfs.workdtransformation.practice;

import java.util.*;

public class wordTransformationPrac {
    /**
     * <혼자 풀이>
     * 1. Node 클래스 사용
     * 2. BFS를 사용하기 위해 Queue 사용
     */
    public static int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target))
            return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));

        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            Iterator<String> iterator = wordList.iterator();
            while (iterator.hasNext()) {
                String nextWord = iterator.next();

                if (canConvert(currentNode.word, nextWord)) {
                    if (nextWord.equals(target))
                        return currentNode.level + 1;

                    queue.add(new Node(nextWord, currentNode.level + 1));
                    iterator.remove();
                }
            }
        }

        return 0;
    }

    private static boolean canConvert(String currentWord, String nextWord) {
        int cnt = 0;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) != nextWord.charAt(i))
                cnt++;
            if (cnt > 1)
                return false;
        }
        return cnt == 1;
    }

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
