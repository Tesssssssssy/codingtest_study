package week4.kim.week4_1.dfs_bfs;

import java.util.*;

/**
 * [단어 변환]
 *
 * 두 개의 단어 begin, target과 단어의 집합 words가 있습니다.
 * 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
 *
 * 1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
 * 2. words에 있는 단어로만 변환할 수 있습니다.
 * 예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.
 *
 * 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때,
 * 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 각 단어는 알파벳 소문자로만 이루어져 있습니다.
 * 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
 * words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
 * begin과 target은 같지 않습니다.
 * 변환할 수 없는 경우에는 0를 return 합니다.
 *
 * 입출력 예
 * begin		target		words							            return
 * "hit"		"cog"		["hot", "dot", "dog", "lot", "log", "cog"]	4
 * "hit"		"cog"		["hot", "dot", "dog", "lot", "log"]		    0
 *
 * 입출력 예 설명
 * 예제 #1
 * 문제에 나온 예와 같습니다.
 *
 * 예제 #2
 * target인 "cog"는 words 안에 없기 때문에 변환할 수 없습니다.
 */

public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();

        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        // 4
        System.out.println(ex2.solution(begin, target, words));
    }

    public int solution(String begin, String target, String[] words) {
        // words 배열을 리스트로 변환
        // target 단어가 포함되어 있는지 확인
        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        if (!wordList.contains(target)) {
            // target이 words에 없으면 변환할 수 없으므로 0을 반환
            return 0;
        }

        // BFS를 위한 큐를 생성하고
        // 시작 단어인 begin을 추가
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);

        // 변환 과정의 단계를 나타내는 변수
        int level = 0;

        // 큐가 비어있지 않을 때까지 반복
        while (!queue.isEmpty()) {
            int size = queue.size();

            // 현재 큐의 크기만큼 반복하여 같은 레벨의 단어들을 처리
            for (int i = 0; i < size; i++) {
                // 큐에서 단어를 하나 꺼냄
                String currentWord = queue.poll();

                if (currentWord.equals(target)) {
                    // 현재 단어가 target과 같으면 level을 반환
                    return level;
                }

                // wordList에서 변환 가능한 단어를 찾아 큐에 추가하고
                // wordList에서 제거
                for (Iterator<String> it = wordList.iterator(); it.hasNext();) {
                    String word = it.next();

                    if (canTransform(currentWord, word)) {
                        queue.offer(word);
                        it.remove();
                    }
                }
            }

            // 다음 레벨로 넘어감
            level++;
        }

        // target으로 변환할 수 없으면 0을 반환
        return 0;
    }

    private boolean canTransform(String source, String target) {
        // 서로 다른 문자의 개수를 세는 변수
        int diff = 0;

        // source와 target을 비교하여 한 문자씩 다른지 확인
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != target.charAt(i)) {
                diff++;
            }
        }

        // 한 문자만 다르면 true를 반환
        return diff == 1;
    }
}