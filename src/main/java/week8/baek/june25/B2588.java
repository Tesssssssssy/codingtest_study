package week8.baek.june25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String m = br.readLine();

        int total = 0;
        for (int i = 0; i <3; i++) {
            int tmp = n * Integer.parseInt(String.valueOf(m.charAt(2 - i)));
            System.out.println(tmp);

            for (int j = 0; j < i; j++) {
                tmp *= 10;
            }

            total += tmp;
        }

        System.out.println(total);

    }
}
