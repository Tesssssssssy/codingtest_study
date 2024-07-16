package week11.baek.july16.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1012 {
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void dfs(int x, int y, int m, int n){
        visited[x][y] = true;

        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            //범위를 벗어나면
            if(nx < 0 || ny < 0 || nx >= m || ny  >= n) continue;

            if(visited[nx][ny] == false && map[nx][ny] == 1){
                dfs(nx, ny, m, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            map = new int[m][n];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                //배추의 위치를 1로 표기
                map[x][y] = 1;
            }

            visited = new boolean[m][n];

            int count = 0;
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < n; l++) {
                    if(map[j][l] == 1 && visited[j][l] == false){
                        dfs(j,l, m, n);
//                        System.out.println("j = " + j + " l = " + l);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }



    }
}
