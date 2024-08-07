package week13.baek.august06;

import java.util.Scanner;

public class S6603 {
    static int K;
    static int[] S;
    static int[] nums;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            K = sc.nextInt();
            if(K == 0)
                break;
            S = new int[K];
            nums = new int[6];

            for(int i=0; i<K; i++)
                S[i] = sc.nextInt();

            pickNum(0, 0);
            System.out.println();
        }

    }

    static void pickNum(int idx, int cnt) {
        if(cnt == 6) {
            for(int i=0; i<6; i++)
                System.out.print(nums[i]+" ");
            System.out.println();
            return;
        }

        if(idx == K)
            return;

        for(int i=idx; i<K; i++) {
            nums[cnt] = S[i];
            pickNum(i+1, cnt+1);
        }
    }

}