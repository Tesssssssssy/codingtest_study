package week12.baek.july26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n==1) {
            System.out.println("1/1");
            return;
        }

        //(k * (k+1)) / 2 = n
        // k*(k+1) = 2n
        // k^2 + k = 2n

        //1+2 = 3
        //1+2+3 = 6
        //1+2+3+4 = 10

        int count = 0;
        int p = 0;
        while(count < n) {
            p++;
            count = p * (p+1) / 2;
        }

        int q = n - (p-1)*p / 2;

        //짝수행 : 1/p 로 시작
        if(p % 2 == 0) {
            System.out.println(q + "/" + (p - q +1));
        }
        else {
            System.out.println((p - q +1) + "/" + q);
        }

    }
}

/*
1그룹:  1/1       1

2그룹:  1/2 2/1    2

3그룹:  3/1 2/2 1/3    3

4그룹:  1/4 2/3 3/2 4/1   4
 */