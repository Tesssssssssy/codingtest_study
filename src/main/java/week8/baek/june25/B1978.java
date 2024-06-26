package week8.baek.june25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1978 {
    public static boolean primeNumber(int n){
        if(n==1) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0){ return false;}
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String input = br.readLine();

        int count = 0;
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(input.split(" ")[i]);
            if(primeNumber(a)) count++;
        }

        System.out.println(count);

    }
}
