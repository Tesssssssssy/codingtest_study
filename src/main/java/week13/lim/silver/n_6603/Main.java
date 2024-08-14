package week13.lim.silver.n_6603;

// 문제: https://www.acmicpc.net/problem/6603

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - 독일 로또는 {1, 2, ..., 49}에서 수 6개를 고른다.
     *  - 49가지 수 중 k(k>6)개의 수를 골라 집합 S를 만든 다음 그 수만 가지고 번호를 선택.
     *  - ex.)
     *        k=8, S={1,2,3,5,8,13,21,34}인 경우 이 집합 S에서 수를 고를 수 있는 경우의 수는 총 28가지.
     *        ([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])
     *
     *  [입력]
     *  첫 번째 수는 k (6 < k < 13)이고, 다음 k개 수는 집합 S에 포함되는 수
     *  입력의 마지막 줄에는 0이 하나 주어진다.
     *
     *  [출력]
     *  각 테스트 케이스마다 수를 고르는 모든 방법을 출력한다. 이때, 사전 순으로 출력
     */

    static ArrayList<Integer> sequence = new ArrayList<>();
    // 백트래킹을 통해 생성된 현재 조합 저장
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            if (k == 0)
                break;  // 입력 종료 조건

            int[] S = new int[k+1];
            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            backtrack(S, 0, 0);
            sb.append('\n');
        }

        System.out.print(sb.toString().trim());
        // 마지막 빈 줄 제거 후 출력
    }

    /*
        조합을 찾는 백트래킹 메서드
        - 재귀의 각 단계에서 현재 선택한 수를 sequence 리스트에 추가하고,
          그 수를 포함하여 그 이후의 수들로 조합을 계속 만든다.
    */
    private static void backtrack(int[] S, int depth, int start) {
        if (depth == 6) {  // 6개의 수가 선택되었을 때 출력
            for (int num : sequence) {
                sb.append(num).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < S.length; i++) {
            sequence.add(S[i]);
            backtrack(S, depth + 1, i + 1);
            sequence.remove(sequence.size() - 1);
            /*
                백트래킹 (마지막 수 제거)
                재귀적으로 탐색하는 과정에서 다음 단계로 넘어가기 전에
                현재 상태에서 했던 선택을 되돌리는(즉, 마지막에 추가했던 원소를 제거하는) 작업을 수행.
                이것을 통해 다음 가능한 조합을 깨끗한 상태에서 시작할 수 있다.

                이전 문제에서 visited 방문 배열을 다시 false 로 하는 것과 동일한 원리
            */
        }
    }
}

/*
    예를 들어 집합 {1, 2, 3}에서 2개를 선택하는 조합을 모두 찾는 경우:

    첫 번째로 1을 선택하고 리스트에 추가 (sequence = [1]).
    다음으로 2를 선택 (sequence = [1, 2]).
    이제 두 개를 모두 선택했으므로 이 조합을 출력하고, 더 이상 진행할 수 없다.

    이제 가장 최근의 선택인 2를 제거
    (sequence.remove(sequence.size() - 1)를 호출하여 sequence = [1]로 만듦).

    다음 조합을 시도하기 위해 3을 선택 (sequence = [1, 3]).
    이 조합을 출력한 후, 다시 3을 제거 (sequence = [1]).

    모든 조합을 시도했으므로 첫 번째 선택도 되돌려서 새로운 시작을 준비.
*/

/*
    start를 1로 설정하고 반복문에 부등호를 넣고 싶다면, 배열 인덱스와 start 값의 관계를 조정해야 합니다.
    기본적으로 배열 인덱스는 0부터 시작하지만, start 값을 1로 설정하면 인덱스를 사용할 때 이를 고려해야 합니다.

    수정 방법:
    backtrack 메서드 호출 시 start를 1로 설정합니다.
    반복문에서 start를 1부터 시작하되, 배열에 접근할 때 인덱스를 i - 1로 조정합니다.
    이는 start가 1부터 시작할 때, 실제 배열 인덱스는 0부터 시작하는 것을 맞추기 위함입니다.

    for (int i = start; i <= S.length; i++) {  // i <= S.length로 변경
                sequence.add(S[i - 1]);  // 실제 배열 인덱스는 i - 1
                backtrack(S, depth + 1, i + 1);
                sequence.remove(sequence.size() - 1);  // 백트래킹 (마지막 수 제거)
    }
*/