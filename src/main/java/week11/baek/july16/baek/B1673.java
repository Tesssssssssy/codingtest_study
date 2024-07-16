package week11.baek.july16.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1673 {
    public static void chicken(int n , int k){
        int chicken = 0;
        //쿠폰이 k장 이상일 동안 반복
        while(n >= k) {
            chicken += k;
            n -= k - 1;
        }
        chicken += n;
        System.out.println(chicken);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input;
        while((input = br.readLine()) != null && !input.isEmpty()) {
            st = new StringTokenizer(input);

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            chicken(n, k);
        }
    }
}
