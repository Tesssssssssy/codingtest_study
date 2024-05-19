package week3.lim.connectisland;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42861
// 참고: https://chatgpt.com/c/0688c680-ce06-4fbb-8407-127b67646689

import java.util.Arrays;

public class connectIsland1 {
    /**
     *  Kruskal Algorithm을 사용한 풀이
     *      - 그래프의 간선들을 가중치 기준 오름차순으로 정렬한다.
     *      - 정렬된 간선 리스트를 순서대로 선택하여, 간선의 정점들을 연결한다.
     *        이때 정점을 연결하는 것은 Union-Find의 Union으로 구현한다.
     *      - 만약 간선의 두 정점 a,b가 연결되어 있다면 스킵한다.
     *      - 위의 과정을 반복하여 최소 비용의 간선들만 이용하여 모든 정점이 연결된다.
     */
    private static int[] parent;
    public static int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        /*
            Kruskal Algorithm
                union find를 통해 임의의 두 노드가 같은 그래프에 속하는지 아닌지를 알 수 있게 됨.
                그리고 사이클은 같은 그래프에 속한 두 노드를 연결했을 때 발생.
                => 크루스칼 알고리즘에서는 간선을 선택하기 전에 해당 간선이 연결하는 두 노드의 부모를 확인해
                   부모가 서로 같다면(같은 그래프에 속한다면) 그 간선은 선택 x
        */
        for (int i = 0; i < costs.length; i++) {
            if (find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
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
