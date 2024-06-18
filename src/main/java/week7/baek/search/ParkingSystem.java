package week7.baek.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParkingSystem {
    static ArrayList<String> scope = new ArrayList<>();
    static int answer;
    public static void dfs(int[][] map, boolean[][] visited, int x, int y,  int score){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        //방문 처리
        visited[x][y] = true;
        if(map[x][y] == 0) score += 1;
        else if(map[x][y] == 2) score -= 2;

        answer = Math.max(answer, score);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            //못 가는 경우
            if(nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length){
                continue;
            }
            //갈 수 있는 경우
            if(!visited[nx][ny] && map[nx][ny] != 1){
                dfs(map, visited, nx, ny, score);
            }
        }

    }
    public static void main(String[] args) throws Exception {
        //입력값 저장
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int N = Integer.parseInt(input.split(" ")[0]);
        int M = Integer.parseInt(input.split(" ")[1]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.split(" ")[j]);
            }
        }

        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 1 && !visited[i][j]){
                    dfs(map,visited, i, j, 0);
                }
            }
        }

        System.out.println(answer);
    }
}
