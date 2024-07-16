package week10.lim.week10_2.silver.n_9934;

// 문제: https://www.acmicpc.net/problem/9934

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  1. 깊이가 K인 완전 이진 트리
     *  2. 깊이가 k인 완전 이진 트리는 2^k - 1 개의 노드로 이루어져 있다.
     *  3. 각 노드에는 그곳에 위치한 빌딩의 번호가 붙어져 있다.
     *  4. 가장 마지막 레벨을 제외한 모든 집은 왼쪽 자식과 오른쪽 자식을 갖는다.
     *  5. 모든 빌딩에 들어갔고, 들어간 순서대로 번호를 종이에 적었다.
     *     어떤 순서로 도시를 방문했는지 기억.
     *  6. - 트리의 루트 빌딩 앞에 서 있다.
     *     - 현재 빌딩의 왼쪽 자식에 있는 빌딩에 아직 들어가지 않았다면, 왼쪽 자식으로 이동
     *     - 현재 있는 노드가 왼쪽 자식을 가지고 있지 않거나 왼쪽 자식에 있는 빌딩을 이미 들어갔다면,
     *       현재 노드에 있는 빌딩을 들어가고 종이에 번호를 적는다.
     *     - 현재 빌딩을 이미 들어갔다 온 상태이고, 오른쪽 자식을 가지고 있는 경우에는 오른쪽 자식으로 이동
     *     - 현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문했다면, 부모 노드로 이동
     *  7. 종이에 적은 순서를 입력받고, 각 레벨에 있는 빌딩의 번호 출력
     *
     *  [풀이]
     *  중위 순회 (In-order traversal)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine()); // 트리의 깊이
        String[] input = br.readLine().split(" ");
        int[] inOrder = new int[input.length]; // 중위 순회 결과를 저장할 배열

        // 입력받은 문자열 배열을 정수 배열로 변환
        for (int i = 0; i < input.length; i++) {
            inOrder[i] = Integer.parseInt(input[i]);
        }

        // 각 레벨별로 노드를 저장할 리스트 배열 초기화
        List<List<Integer>> levels = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            levels.add(new ArrayList<>());
        }

        // 중위 순회를 기반으로 트리를 구성하고 각 레벨의 노드를 저장
        // 중위 순회된 결과를 바탕으로 트리를 재구성
        // 각 레벨의 빌딩 번호를 해당 레벨의 리스트에 추가
        buildTree(levels, inOrder, 0, inOrder.length - 1, 0);

        // 각 레벨의 빌딩 번호 출력
        // 각 레벨의 노드들은 왼쪽에서 오른쪽 순서로 저장되어 있음.
        StringBuilder sb = new StringBuilder();
        for (List<Integer> level : levels) {
            for (int num : level) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // 중위 순회 결과를 기반으로 트리를 재구성하고 레벨별로 노드를 저장하는 함수
    private static void buildTree(List<List<Integer>> levels, int[] inOrder, int start, int end, int level) {
        if (start > end)
            return; // 범위가 유효하지 않을 경우 종료

        int mid = (start + end) / 2; // 중위 순회에서 중앙에 위치하는 노드가 현재 루트
        System.out.println("start: "+start+", end: "+end);
        System.out.println("mid: "+mid);

        levels.get(level).add(inOrder[mid]); // 현재 레벨에 루트 노드 추가
        System.out.println("level: "+level);
        System.out.println("inOrder["+mid+"]: "+inOrder[mid]);

        buildTree(levels, inOrder, start, mid - 1, level + 1); // 왼쪽 서브트리 재귀 호출
        buildTree(levels, inOrder, mid + 1, end, level + 1); // 오른쪽 서브트리 재귀 호출
        System.out.println();
    }
}
