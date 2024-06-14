package week4.baek.dfsbfs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class GameMap {
    static int n;
    static int m;
    static ArrayList<Integer> result;
    static boolean[][] visited;
    static int answer = -1;

    public static void bfs(int x, int y, int[][] maps){
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        visited[y][x] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y,1});

        while(!queue.isEmpty()){
            int[] loc = queue.poll();
            x = loc[0];
            y = loc[1];
            int count = loc[2];

            if(x == n-1 && y == m-1) {
                answer = count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(maps[ny][nx] == 0) continue;
                if(!visited[ny][nx] && maps[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    queue.add(new int[]{nx, ny, count+1});
                }
            }
        }



    }

    public static int solution(int[][] maps) {
        //세로 길이
        m = maps.length;
        //가로 길이
        n = maps[0].length;

        visited = new boolean[m][n];

         bfs(0, 0,  maps);

        return answer;


    }

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }
}
