package week8.baek.june25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2609 {
    //최대 공약수
    public static int max(int a, int b){
        System.out.println(a + " " + b);

        for(int i=b; i>0; i--){
            if(a % i == 0 && b % i == 0) return i;
        }
        return -1;
    }

    //최소 공배수
    public static int min(int a, int b){
        for(int i=b; i <= a*b; i++){
            if(i % a == 0 && i % b == 0) return i;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input.split(" ")[0]);
        int m = Integer.parseInt(input.split(" ")[1]);

        int[] a = new int[] {n, m};
        Arrays.sort(a);

        int max = max(a[0], a[1]);
        System.out.println(max(a[0],a[1]) + "\n" + n * m / max);
    }

    //유클리드 호제법
    static int gdc(int a, int b) { //최대 공약수
        if(a<b) // 유클리드 호제법 조건
        {
            int temp = a;
            a = b;
            b = temp;
        }
        while(b!=0) { // 유클리드 호제법
            int r=a%b;
            a=b;
            b=r;
        }
        return a;
    }

    static int lcm(int a, int b) { //최소 공배수
        return a*b / gdc(a,b);
    }
}
