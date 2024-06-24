package week8.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int A = Integer.parseInt(input.split(" ")[0]);
        int B = Integer.parseInt(input.split(" ")[1]);
        int V = Integer.parseInt(input.split(" ")[2]);

       int days = V / (A-B) + 1;
        System.out.println(days);
    }
}
