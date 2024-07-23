package week12.baek.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2417 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

//        1. Math.sqrt() 사용해서 푸는 방법
//        long q = (long) Math.sqrt(n);
//
//        if((q*q) < n) q++;
//
//        System.out.println(q);

//        2. 이분 탐색으로 푸는 방법
        long start = 0;
        long end = n;
        long result = 0;

        while(start <= end){
            long mid = (start + end) / 2;

            //줄여야하는 경우 : end가 mid-1이  되면 됨
            if(n <= Math.pow(mid, 2)){
                result = mid;
                end = mid -1;
            }
            //늘려야하는 경우
            else {
                start = mid + 1;
            }
        }

        System.out.println(result);



    }
}
