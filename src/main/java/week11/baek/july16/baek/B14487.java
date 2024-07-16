package week11.baek.july16.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14487 {
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cost = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            count += cost[i];
        }
        Arrays.sort(cost);
        count -= cost[n - 1];

        System.out.println(count);
    }
}
