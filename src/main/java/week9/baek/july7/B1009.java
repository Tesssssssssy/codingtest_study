package week9.baek.july7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String input = br.readLine();
            int a = Integer.parseInt(input.split(" ")[0]);
            int b = Integer.parseInt(input.split(" ")[1]);

            int n = 1;
            for (int j = 0; j < b; j++) {
                n *= a;
                n %= 10;
            }
            if(n == 0) System.out.println(10);
            else System.out.println(n%10);
        }
    }
}
