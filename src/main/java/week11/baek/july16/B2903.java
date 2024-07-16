package week11.baek.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //한 변에 있는 점의 개수를 구하기
        int count = 2;
        for (int i = 1; i <= N; i++) {
            count += Math.pow(2, i-1);
        }

        //총 점의 개수
        System.out.println(count * count);
    }
}
