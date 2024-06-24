package week8.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2979 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] price = new int[3];
        for (int i = 0; i < 3; i++) {
            price[i] =Integer.parseInt(s.split(" ")[i]);
        }

        //조건 : 입력으로 주어지는 시간은 1과 100 사이
        int[] truck = new int[100];
        int min = 0; int max = 0;

        for (int i = 0; i < 3; i++) {
            s = br.readLine();
            int n = Integer.parseInt(s.split(" ")[0]);
            int m = Integer.parseInt(s.split(" ")[1]);

            //가장 먼저 들어온 시각
            min = Math.min(min,n);

            //가장 나중에 나가는 시각
            max = Math.max(max,m);

            for (int j = n; j < m; j++) {
                truck[j]++;
            }
        }

        int total = 0;
        for (int i = min; i < max; i++) {
            switch (truck[i]) {
                case 1:
                    total += price[0];
                    break;
                    case 2:
                        total += price[1] * 2;
                        break;

                case 3:
                    total += price[2] * 3;
                    break;
            }
        }

        System.out.println(total);

    }
}
