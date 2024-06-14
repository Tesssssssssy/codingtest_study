package week6.lim.bfsdfs.gamemap;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/1844
// 참고: https://tmdrl5779.tistory.com/216

import java.util.LinkedList;
import java.util.Queue;

public class gameMap2 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static int solution(int[][] maps) {
        int answer = 0;

        int[][] distance = new int[maps.length][maps[0].length];

        bfs(maps, distance);
        answer = distance[maps.length-1][maps[0].length-1];

        if(answer == 0){
            answer = -1;
        }

        return answer;
    }

    public static void bfs(int[][] maps, int[][] distance){
        int x = 0;
        int y = 0;
        distance[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];

            for(int i = 0; i < 4; i++){
                int nX = cX + dx[i];
                int nY = cY + dy[i];

                if(nX < 0 || nX > maps.length-1 || nY < 0 || nY > maps[0].length-1)
                    continue;

                if(distance[nX][nY] == 0 && maps[nX][nY] == 1){
                    distance[nX][nY] = distance[cX][cY] + 1;
                    queue.add(new int[]{nX, nY});
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
