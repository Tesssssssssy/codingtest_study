package week3.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;


//6
//        011000
//        011011
//        000011
//        000011
//        110010
//        111000
//
//        출력
//        3
//        4 5 7
//
//        4
//        0 0 0 0
//        0 0 0 0
//        0 0 0 0
//        0 0 0 0
//
//        출력
//        0
//
//        4
//        1 0 0 0
//        1 0 0 0
//        0 0 0 0
//        0 0 1 1
//
//        출력
//        2
//        2 2
//
//        7165

class Soft {
    static boolean[][] visited;

    public static void dfs(int x, int y, int[][] map){
        visited[y][x] = true;

        int[] dx = {0,-1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx >= 0 && ny >= 0 && nx < map.length && ny <map.length) {
               if(map[ny][nx] == 1 && visited[ny][nx] == false)
                   dfs(nx, ny, map);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input);

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                map[j][i] = input.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[j][i] + "");
            }
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[j][i] == 1 && visited[j][i] == false){
                    count++;
                    dfs(i, j, map);
                }
            }
        }

        sb.append(count);
        System.out.println(sb);

        return;
    }
}
