package week8.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1598 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = Integer.parseInt(input.split(" ")[0]) - 1;
        int m = Integer.parseInt(input.split(" ")[1]) - 1;

        System.out.println(Math.abs(m%4 - n%4) + Math.abs(n/4-m/4));
    }
}
