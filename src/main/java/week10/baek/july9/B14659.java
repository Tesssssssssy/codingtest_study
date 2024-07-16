package week10.baek.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] mountains = new int[n];
        for (int i = 0; i < n; i++) {
            mountains[i] = Integer.parseInt(st.nextToken());
        }

        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i+1; j < n; j++) {
                if(mountains[i] >= mountains[j]) {
                    count++;
                }
                else {
                    break;
                }
            }
            scores[i] = count;
        }


        for (int i = 0; i < n; i++) {
            System.out.println(scores[i]);
        }

        Arrays.sort(scores);

        System.out.println(scores[scores.length - 1]);
    }
}
