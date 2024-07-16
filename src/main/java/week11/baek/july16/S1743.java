package week11.baek.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class S1743 {
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max;
    static int count;

    public static void dfs(int x, int y, int depth){
        visited[x][y] = true;

        count += 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if(visited[nx][ny] == false && map[nx][ny] == 1 ){
                dfs(nx, ny, depth+1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        visited = new boolean[N][M];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1 && visited[i][j] == false) {
                    count = 0;
                    dfs(i, j, 1);
                    if(count > max) max = count;
                }
            }
        }

        System.out.println(max);

    }
}
