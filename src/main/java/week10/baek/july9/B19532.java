package week10.baek.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B19532 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[6];
        for (int i = 0; i < 6; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = -999; i <=999; i++) {
            for (int j = -999; j <= 999; j++) {
                int a = i * nums[0] + j * nums[1];
                int b = i * nums[3] + j * nums[4];

                if(a == nums[2] && b == nums[5]) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }

    }
}
