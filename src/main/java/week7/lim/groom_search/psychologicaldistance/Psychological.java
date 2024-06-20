package week7.lim.groom_search.psychologicaldistance;

// 문제: https://level.goorm.io/exam/195775/%EC%8B%AC%EB%A6%AC%EC%A0%81-%EA%B1%B0%EB%A6%AC%EA%B0%90/quiz/1

import java.util.*;
import java.io.*;

public class Psychological {
    /**
     *  이 문제는 최단 경로를 구하여 심리적 거리감을 계산하는 문제로,
     *  다익스트라 알고리즘을 이용할 수 있다.
     *
     *  주어진 그래프는 방향성이 있는 그래프이기 때문에,
     *  각 섬에서 다른 섬으로의 최단 경로를 구한 후, 심리적 거리감을 계산.
     *
     *  심리적 거리감은 a섬에서 b섬까지 필요한 최소 다리 개수와 섬 번호 차이의 절대값의 합.
     *
     *  그래프 초기화: 각 섬을 노드로, 다리를 간선으로 하는 그래프를 리스트로 초기화.
     *  입력 처리: 주어진 다리 정보를 바탕으로 그래프를 구성.
     *  BFS로 최단 경로 계산: K번 섬에서 시작하여 BFS를 수행하며 다른 섬까지의 최소 다리 수를 계산.
     *  심리적 거리감 계산: 각 섬까지의 심리적 거리감을 계산하고, 가장 큰 거리감을 가지는 섬을 찾는다.
     *                  거리감이 같은 경우 번호가 더 큰 섬을 선택.
     *  결과 출력: 가장 심리적 거리감이 큰 섬의 번호를 출력.
     *           만약 모든 섬에 대해 심리적 거리감이 없다면 -1을 출력.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();  // [섬의 수, 다리의 수, 구름이의 섬 번호] 입력 받음.
        StringTokenizer st = new StringTokenizer(input);

        int N = Integer.parseInt(st.nextToken());  // 섬의 개수
        int M = Integer.parseInt(st.nextToken());  // 다리의 개수
        int K = Integer.parseInt(st.nextToken());  // 구름이가 사는 섬의 번호

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());  // 각 섬별로 다리 정보를 저장할 리스트 초기화
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine();
            st = new StringTokenizer(input);
            int start = Integer.parseInt(st.nextToken());  // 출발 섬
            int end = Integer.parseInt(st.nextToken());  // 도착 섬
            graph.get(start).add(end);  // 출발 섬에서 도착 섬으로 가는 다리 정보를 그래프에 추가
        }

        // 최단 거리를 저장할 배열 초기화
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);  // 모든 섬의 최단 거리를 무한대로 설정
        distance[K] = 0;  // 구름이의 섬에서의 거리는 0

        // BFS를 통해 최단 거리 계산
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);  // 구름이의 섬에서 시작

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (distance[next] == Integer.MAX_VALUE) {  // 방문하지 않은 섬이라면
                    distance[next] = distance[current] + 1;  // 현재 섬의 거리 + 1로 설정
                    queue.offer(next);  // 다음 탐색을 위해 큐에 추가
                }
            }
        }

        // 심리적 거리감을 계산하고 최댓값 찾기
        int maxDistance = -1;  // 최대 심리적 거리감
        int maxIsland = -1;  // 최대 거리감을 가진 섬의 번호
        for (int i = 1; i <= N; i++) {
            if (i != K && distance[i] != Integer.MAX_VALUE) {
                // 구름이의 섬이 아니고, 도달 가능한 섬일 경우

                int psychologicalDistance = distance[i] + Math.abs(K - i);
                // 심리적 거리감 계산
                if (psychologicalDistance > maxDistance) {  // 최대값보다 크면 갱신
                    maxDistance = psychologicalDistance;
                    maxIsland = i;
                } else if (psychologicalDistance == maxDistance && i > maxIsland) {
                    // 거리감이 같고 섬 번호가 크면 갱신

                    maxIsland = i;
                }
            }
        }

        // 가장 심리적 거리감이 먼 섬의 번호 출력
        // 만약 모든 섬에 대해 심리적 거리감이 없다면 -1 출력
        System.out.println(maxIsland == -1 ? -1 : maxIsland);
    }
}
