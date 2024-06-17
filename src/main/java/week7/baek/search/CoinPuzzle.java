package week7.baek.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CoinPuzzle {
    public static void main(String[] args) throws Exception {
        //입력값 저장
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int h1 = Integer.parseInt(input.split(" ")[0]);
        int w1 = Integer.parseInt(input.split(" ")[1]);

        char[][] map1 = new char[h1][w1];
        for (int i = 0; i < h1; i++) {
            input = br.readLine();
            for (int j = 0; j < w1; j++) {
                map1[i][j] = input.charAt(j);
            }
        }

        input = br.readLine();

        int h2 = Integer.parseInt(input.split(" ")[0]);
        int w2= Integer.parseInt(input.split(" ")[1]);

        char[][] map2 = new char[h1][w1];
        for (int i = 0; i < h1; i++) {
            input = br.readLine();
            for (int j = 0; j < w1; j++) {
                map2[i][j] = input.charAt(j);
            }
        }


    }
}
