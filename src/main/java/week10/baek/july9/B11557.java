package week10.baek.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class B11557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] school = new String[n];
            int[][] score = new int[n][2];
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                school[j] = st.nextToken();
                score[j][1] = Integer.parseInt(st.nextToken());
                score[j][0] = j;
            }

            Arrays.sort(score, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });

            int index = score[0][0];
            System.out.println(school[index]);
        }
    }
}
