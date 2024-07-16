package week10.baek.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B15633 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if(n%i == 0) {
                count += i;
            }
        }
        count = ( count ) * 5 - 24;

        System.out.println(count);

    }
}
