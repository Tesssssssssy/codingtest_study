package week12.baek.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[참고: 등차수열] https://jiji-gilog.tistory.com/12
public class S11561 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for(int i=0;i<T;i++) {
            long N = Long.parseLong(bf.readLine());
            long start = 0;
            long last = (long) Math.sqrt((2 * N - 1));
            long result = 0;

            while (start <= last) {
                long mid = (start + last) / 2;

                long sum = (mid) * (mid + 1) / 2;

                if (sum <= N) {
                    result = Math.max(mid, result);
                    start = mid + 1;
                } else {
                    last = mid - 1;
                }
            }

            System.out.println(result);
        }

        }
}
