package week10.lim.week10_1.bronze.n_15633;

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 1; i * i <= n; i++) {
//        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                count += i;
                if (i != n / i) {
                    count += n / i;
                }
            }
        }
        count = count * 5 - 24;  // (count + n) 에서 n을 더하지 않습니다

        System.out.println(count);
    }
}
