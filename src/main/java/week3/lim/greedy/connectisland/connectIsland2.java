package week3.lim.greedy.connectisland;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42861
// 참고: https://chatgpt.com/c/0688c680-ce06-4fbb-8407-127b67646689

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class connectIsland2 {
    /**
     *  Prim Algorithm을 사용한 풀이
     *      - 임의의 정점을 시작점으로 선택한다.
     *      - 시작점에서 갈 수 있는 정점 중 가장 가중치가 작은 정점을 연결한다.
     *      - 2번 과정에서 어떠한 정점과 연결되었는데,
     *        이렇게 연결된 정점들의 집합을 x집합이라고 하면
     *        x집합에서 갈 수 있는 x집합에 포함되어 있지 않은 정점들에 대해 가중치가 가장 작은 정점들을 연결한다.
     *      - x 집합의 크기는 점점 커지며, 위 과정을 모든 정점이 연결될때 까지 반복한다.
     */
    public static class Point implements Comparable<Point> {
        int node, cost;

        public Point(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    public static List<List<Point>> map = new ArrayList<>();

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int val = costs[i][2];

            map.get(from).add(new Point(to, val));
            map.get(to).add(new Point(from, val));
        }

        //Prim Algorithm
        boolean[] visited = new boolean[n];
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (visited[cur.node])
                continue;

            visited[cur.node] = true;
            answer += cur.cost;

            for (int i = 0; i < map.get(cur.node).size(); i++) {
                int next = map.get(cur.node).get(i).node;
                int cost = map.get(cur.node).get(i).cost;

                if (visited[next])
                    continue;

                queue.add(new Point(next, cost));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n1 = 4;
        int[][] costs1 = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println(solution(n1, costs1));   // 4
    }
}
/*
    프림(Prim) 알고리즘을 사용하여 주어진 섬들을 최소 비용으로 연결하는 최소 신장 트리(MST)를 구하는 문제를 해결.
    프림 알고리즘은 시작 정점에서 출발하여 점진적으로 MST를 확장해 나가는 방식으로 작동.

    클래스와 자료구조 설명
    - Point 클래스:
        - 각 정점과 해당 정점으로 이동할 때 발생하는 비용을 저장하기 위한 클래스입니다.
          이 클래스는 비교 가능(Comparable)하며, 비용을 기준으로 정렬되도록 compareTo 메소드가 구현되어 있습니다.
    - map
        - 각 정점에서 다른 정점으로 이동 가능한 경로와 그 비용을 저장하는 인접 리스트입니다.
          이 인접 리스트는 각 정점에서 출발하는 모든 가능한 경로를 Point 객체 리스트로 관리합니다.
    - visited 배열
        - 각 정점의 방문 상태를 관리하는 배열입니다.
          프림 알고리즘에서는 이미 MST에 포함된 정점을 중복해서 처리하지 않도록 방문한 정점을 체크합니다.
    - PriorityQueue (queue)
        - 우선순위 큐를 사용하여 가장 낮은 비용의 경로를 먼저 처리합니다.
          이 큐는 MST에 추가할 다음 정점을 결정하는 데 사용됩니다.

    알고리즘의 작동 과정
    - 초기화
        - map을 구성하여 모든 가능한 경로를 저장합니다.
          시작 정점(예제에서는 0번 정점)을 기준으로 큐에 추가합니다.
    - MST 확장
        - 우선순위 큐에서 비용이 가장 낮은 정점을 하나씩 꺼내어 처리합니다.
          이 정점이 아직 MST에 포함되지 않았다면, 해당 정점을 MST에 포함시키고,
          해당 정점에서 갈 수 있는 다른 정점들을 큐에 추가합니다.
    - 사이클 방지
        - 이미 MST에 포함된 정점으로의 경로는 무시합니다(사이클 방지).
    - 종료 조건
        - 모든 정점이 MST에 포함될 때까지 위 과정을 반복합니다.

    예제 실행 과정 (n1 = 4, costs1 = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}})
    - 시작
        - 0번 정점에서 시작, 비용은 0입니다.
    - 처리
        - 0번에서 1번과 2번 정점으로의 경로가 큐에 추가됩니다.
          먼저 1번 정점(비용 1)이 처리되고, 다음으로 2번 정점(비용 2)이 처리됩니다.
    - 계속 확장
        - 1번 정점에서 3번 정점으로의 경로(비용 1)가 큐에 추가됩니다.
    - 최종 선택
        - 3번 정점이 처리되며, MST 완성됩니다.

    최소 비용 계산
    - 위 과정을 통해 각 선택된 경로의 비용을 더하면 최소 비용(4)이 계산됩니다.
      이는 모든 섬을 최소 비용으로 연결하는데 필요한 금액입니다.

    프림 알고리즘은 연결된 그래프에서 모든 정점을 최소 비용으로 연결하는 효율적인 방법을 제공하며,
    주어진 코드는 이 알고리즘을 정확하게 구현하고 있습니다.
*/
