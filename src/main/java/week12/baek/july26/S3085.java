package week12.baek.july26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3085 {
    static char[][] map;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        //맵 초기화
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(j + 1 < n) {
                    swap(i, j, i, j + 1); // 위쪽이랑 swap
                    max = Math.max(search(), max);
                    swap(i, j, i, j + 1); // 원상 복귀
                }
                if(i + 1 < n) {
                    swap(i, j, i + 1, j); // 아래쪽이랑 swap

                    swap(i, j, i + 1, j); // 원상 복귀
                }
            }
        }
        System.out.println(max);


    }

    public static void swap(int x1, int y1, int x2, int y2) {
        char tmp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = tmp;
    }

    public static int search(){
        int max = Integer.MIN_VALUE;

        //행 최대 길이 탐색
        for (int i = 0; i < n ; i++) {
            int count = 1;

            for (int j = 0; j < n-1; j++) {
                if(map[i][j] == map[i][j+1]) count++;
                else break;
            }

            max = Math.max(max, count);
        }

        //열 최대 길이 탐색
        for (int i = 0; i < n ; i++) {
            int count = 1;

            for (int j = 0; j < n-1; j++) {
                if(map[j][i] == map[j+1][i]) count++;
                else break;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}
