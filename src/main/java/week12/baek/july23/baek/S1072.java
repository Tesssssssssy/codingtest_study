package week12.baek.july23.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long Z = (long) ((double) Y/X * 100);

        if(Z >= 99) {
            System.out.println(-1);
            return;
        }

        int left = 0;
        int right = 1000000000;
        int result = -1;

        while(left <= right) {
            int mid = (left+right) / 2;
            long newZ = (long) ((double) (Y + mid) / (X+mid) * 100);

            if(Z >= newZ) {
                result = mid + 1;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(result);
        }
   }
