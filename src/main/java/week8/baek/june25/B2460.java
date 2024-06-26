package week8.baek.june25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2460 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] on = new int[10];

        int count = 0;
        for (int i = 0; i < 10; i++) {
            String input = br.readLine();
            count -= Integer.parseInt(input.split(" ")[0]);
            count  += Integer.parseInt(input.split(" ")[1]);
            on[i] = count;
        }

        Arrays.sort(on);

        System.out.println(on[on.length-1]);
    }
}
