package week10.lim.week10_2.bronze.n_1919;

// 문제: https://www.acmicpc.net/problem/1919

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 두 영어 단어가 철자의 순서를 뒤바꾸어 같아질 수 있을 때,
     *     그러한 두 단어를 서로 애너그램 관계에 있다고 한다.
     *  2. occurs 와 succor 는 서로 애너그램 관계
     *  3. 각 단어의 문자를 제거해서 애너그램 관계를 만들 수 있다.
     *  4. 두 단어가 서로 애너그램 관계가 되도록 제거해야 하는 최소 개수의 문자 수 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        Map<Character, Integer> a_map = new HashMap<>();
        Map<Character, Integer> b_map = new HashMap<>();

        // 첫 번째 단어의 각 문자 빈도 계산
        for (char c : a.toCharArray()) {
            a_map.put(c, a_map.getOrDefault(c, 0) + 1);
        }

        // 두 번째 단어의 각 문자 빈도 계산
        for (char c : b.toCharArray()) {
            b_map.put(c, b_map.getOrDefault(c, 0) + 1);
        }

        // 두 해시맵의 차이를 통해 제거할 문자 수 계산
        int diff = calculateRemovals(a_map, b_map);

        // 결과 출력
        System.out.println(diff);
    }

    private static int calculateRemovals(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        int removals = 0;

        // map1의 모든 키에 대해 처리
        for (char key : map1.keySet()) {
            if (map2.containsKey(key)) {
                removals += Math.abs(map1.get(key) - map2.get(key));
                // 만약 같은 개수를 갖고 있다면 뺄 필요 없을 것.
            } else {
                removals += map1.get(key);
                // 만약 한 해시맵에는 있지만 다른 해시맵에는 없는 키가 있다면,
                // 그 문자는 완전히 제거해야 하므로 그 문자의 출현 횟수를 제거 횟수에 더한다.
            }
        }

        // map2에만 있는 키들에 대해 처리
        for (char key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                removals += map2.get(key);
            }
        }

        return removals;
    }
}
