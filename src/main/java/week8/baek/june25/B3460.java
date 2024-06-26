package week8.baek.june25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3460 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String s = "";
            while ( N >= 1) {
                s += String.valueOf(N % 2 );
                N /= 2;
            }
            System.out.println(s);

            for (int j = 0; j < s.length() ; j++) {
                if (s.charAt(j) == '1') {
                    System.out.println(j);
                }
            }

        }
    }
}
