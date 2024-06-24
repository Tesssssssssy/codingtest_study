package week8.baek;

import java.util.*;
import java.io.*;
public class B2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dorfs = new int[9];

        //9명의 난쟁이의 합을 구함
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            dorfs[i] = Integer.parseInt(br.readLine());
            sum += dorfs[i];
        }

        //완전 탐색 Brute-Force를 사용해서 총합에서 뺄 2명을 선별
        for (int i = 0; i < 8; i++) {
            for (int j = i+1; j < 9; j++) {
                if (sum - dorfs[i] - dorfs[j] == 100) {
                    dorfs[i] = 0;
                    dorfs[j] = 0;
                    Arrays.sort(dorfs);
                    for (int k = 2; k < 9; k++) {
                        System.out.println(dorfs[k]);
                    }
                    return;
                }
            }
        }
    }
}