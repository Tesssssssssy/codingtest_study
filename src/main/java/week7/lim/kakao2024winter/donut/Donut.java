package week7.lim.kakao2024winter.donut;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/258711
// 참고: https://jih3508.tistory.com/201

import java.util.*;

public class Donut {
    public static int[] solution(int[][] edges) {
        Map<Integer, int[]> nodeCnt = new HashMap<>();
        // 각 노드의 '들어오는', '나가는' 연결 수를 저장할 해시맵
        int[] answer = {0, 0, 0, 0};
        // 결과값을 저장할 배열

        // 각 엣지를 순회하면서 들어오는, 나가는 연결 수를 계산
        Arrays.stream(edges).forEach(edge -> {
            int start = edge[0]; // 시작 노드
            int end = edge[1]; // 끝 노드
            if (!nodeCnt.containsKey(start)) {
                nodeCnt.put(start, new int[]{0, 0}); // 시작 노드 초기화
            }
            if (!nodeCnt.containsKey(end)) {
                nodeCnt.put(end, new int[]{0, 0}); // 끝 노드 초기화
            }
            nodeCnt.get(start)[0] += 1; // 시작 노드의 '나가는' 연결 증가
            nodeCnt.get(end)[1] += 1; // 끝 노드의 '들어오는' 연결 증가
        });

        int[] connections;
        for (int key : nodeCnt.keySet()) {
            connections = nodeCnt.get(key);

            // '들어오는' 연결이 없고 '나가는' 연결이 2개 이상인 경우, 정점이 됨
            if (connections[0] >= 2 && connections[1] == 0) {
                answer[0] = key; // 정점으로 설정
            }
            // '나가는' 연결이 없고 '들어오는' 연결이 있는 경우, 막대 그래프 개수 증가
            else if (connections[0] == 0 && connections[1] > 0) {
                answer[2]++;
            }
            // '들어오는'과 '나가는' 연결이 각각 2개 이상인 경우, 8자 그래프 개수 증가
            else if (connections[0] >= 2 && connections[1] >= 2) {
                answer[3]++;
            }
        }

        // 도넛 그래프의 개수 계산: 정점에서 나가는 노드 수에서 막대와 8자 그래프의 개수를 빼줌
        answer[1] = nodeCnt.get(answer[0])[0] - answer[2] - answer[3];

        return answer; // 계산된 결과 반환
    }


    public static void main(String[] args) {
        int[][] edges1 = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        System.out.println(Arrays.toString(solution(edges1)));  // [2, 1, 1, 0]

        int[][] edges2 = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11},
                {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        System.out.println(Arrays.toString(solution(edges2)));  // [4, 0, 1, 2]
    }
}
