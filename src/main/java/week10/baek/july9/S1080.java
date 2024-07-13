package week10.baek.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1080 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        //A 행렬 입력
        int[][] A = new int[n][m];
        for (int i = 0; i < n; i++) {
            String l = br.readLine();
            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(String.valueOf(l.charAt(j)));
            }
        }

        //B 행렬 입력
        int[][] B = new int [n][m];
        for (int i = 0; i < n; i++) {
            String l = br.readLine();
            for (int j = 0; j < m; j++) {
                B[i][j] = Integer.parseInt(String.valueOf(l.charAt(j)));
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //가장 왼쪽 위부터 시작해서 A와 B가 같지 않고, 범위 안이면 바꿔주기
                if(A[i][j] != B[i][j] && i+2 < n && j+2 < m) {
                    //3*3 행렬 바꿔주기
                    for (int k = i; k <i+3 ; k++) {
                        for (int l = j; l <j+3 ; l++) {
                            A[k][l] = A[k][l] == 0 ? 1 : 0;
                        }
                    }
                    count++;
                }
            }
        }

        //A와 B가 같은 지 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(A[i][j] != B[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(count);
    }
}
