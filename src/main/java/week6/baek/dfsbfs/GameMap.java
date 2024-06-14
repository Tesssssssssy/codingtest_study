package week6.baek.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class GameMap {
    static boolean[][] visited;
    static int answer = -1;

    public static void bfs(int[][] maps, int x, int y){
        //현재 노드 방문 처리
        visited[y][x] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0,0,-1, 1};

        Queue<Integer[]> q = new LinkedList<>();
        //노드의 x, y, 총 움직인 거리
        q.offer(new Integer[]{x, y, 1});

        //현재 노드와 인접한 노드 중에서 map 범위 내에 있고, 길이 있는 노드를 찾아서 queue에 넣음
        //queue가 빌 때까지 노드를 빼내고, bfs를 진행
        while(!q.isEmpty()){
            Integer[] a = q.poll();

            //도착점에 도달했으면
            if(a[0] ==  maps[0].length-1 && a[1] == maps.length-1){
                answer = a[2];
                break;
            }

            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nx = a[0] + dx[i];
                ny = a[1] +dy[i];

                if(nx < 0 || nx >= maps[0].length || ny < 0 || ny >= maps.length) continue;
                if(maps[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new Integer[]{nx, ny, a[2] +1});
                }
            }
        }
    }

    public static int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];

        bfs(maps, 0, 0);

        return answer;
    }



    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }
}
