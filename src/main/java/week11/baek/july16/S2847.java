package week11.baek.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] level = new int[N];
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = N-1; i > 0; i--) {
            while(level[i] <= level[i-1]) {
                level[i-1]--;
                count++;
            }

        }

        System.out.println(count);
    }
}
