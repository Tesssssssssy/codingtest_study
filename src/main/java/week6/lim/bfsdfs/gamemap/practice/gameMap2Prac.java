package week6.lim.bfsdfs.gamemap.practice;

import java.util.LinkedList;
import java.util.Queue;

public class gameMap2Prac {
    /**
     *  <혼자 풀기>
     *  1. 도달하지 못할 수도 있다. (벽 때문에)
     *  2. 거쳐온 칸의 개수 반환
     *  3. BFS로 탐색해서 각 칸마다 거쳐온 개수 업데이트하고
     *     탐색이 끝난 후 도착점의 개수 반환
     */
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static int solution(int[][] maps) {
        int answer = 0;

        int[][] distance = new int[maps.length][maps[0].length];
        dfs(maps, distance);

        answer = distance[maps.length - 1][maps[0].length - 1];
        if (answer == 0)
            return -1;

        return answer;
    }

    private static void dfs(int[][] maps, int[][] distance) {
        int x = 0;
        int y = 0;
        distance[x][y] = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            // queue에서 일단 꺼내야 함. (방문할 노드)
            int[] current = queue.remove();
            int currentX = current[0];
            int currentY = current[1];

            for (int i = 0; i < dx.length; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];

                if (nextX < 0 || nextX > maps.length - 1 || nextY < 0 || nextY > maps[0].length - 1)
                    continue;

                if (distance[nextX][nextY] == 0 && maps[nextX][nextY] == 1) {
                    distance[nextX][nextY] = distance[currentX][currentY] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] maps1 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };
        System.out.println(solution(maps1));  // 11

        int[][] maps2 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}
        };
        System.out.println(solution(maps2));  // -1
    }
}
