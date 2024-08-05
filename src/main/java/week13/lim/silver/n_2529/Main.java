package week13.lim.silver.n_2529;

// 문제: https://www.acmicpc.net/problem/2529

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - 두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열 A가 있다.
     *  - 우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다
     *  - A ⇒ < < < > < < > < >
     *    3 < 4 < 5 < 6 > 1 < 2 < 8 > 7 < 9 > 0
     *  - 부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다.
     *  - 제시된 k개의 부등호 순서를 만족하는 (k+1)자리의 정수 중에서 최댓값과 최솟값을 찾아야 한다.
     *  - 선택된 숫자는 모두 달라야 한다.
     */

    static int k;
    static char[] signs;
    static boolean[] used = new boolean[10];
    static ArrayList<String> results = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine().trim());
        signs = br.readLine().replace(" ", "").toCharArray();

        // 최소값과 최대값을 찾기 위한 백트래킹 시작
        backtrack("", 0);

        // 결과 리스트 정렬 후 최소값과 최대값 출력
        Collections.sort(results);
        System.out.println(results.get(results.size() - 1)); // 최대값
        System.out.println(results.get(0)); // 최소값
    }

    private static void backtrack(String current, int depth) {
        // depth 변수는 현재까지 선택된 숫자의 개수

        if (depth == k + 1) {
            results.add(current);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!used[i]) {
                if (depth == 0 || checkCondition(current.charAt(depth - 1) - '0', i, signs[depth - 1])) {
                    // - '0'을 통해 문자를 정수로 변환

                    /*
                        depth == 0 이면 현재 아무 숫자도 선택되지 않은 상태입니다.
                        따라서 첫 번째 숫자는 어떤 숫자든 선택할 수 있으므로,
                        이 조건을 만족할 때는 추가적인 부등호 검사 없이 숫자를 선택할 수 있다.
                    */
                    used[i] = true;
                    backtrack(current + i, depth + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static boolean checkCondition(int previous, int current, char sign) {
        if (sign == '<' && previous < current) {
            return true;
        } else if (sign == '>' && previous > current) {
            return true;
        }
        return false;
    }
}
