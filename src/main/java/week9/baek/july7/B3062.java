package week9.baek.july7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3062 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String N = br.readLine();

            //뒤집은 수
            String s = "";
            for (int j =  N.length() -1; j >= 0; j--) {
                s += N.charAt(j);
            }


            N = String.valueOf(Integer.parseInt(N) + Integer.parseInt(s));

            int j = 0;
            while(N.charAt(j) == N.charAt(N.length()-1-j)){
                if(j == N.length() / 2 ) break;
                j++;
            }

            if(j == N.length() / 2 ) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
