package week8.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] n = new int[2];
        if(Integer.parseInt(input) < 10) {
            n[0] = 0;
            n[1] = Integer.parseInt(input);
        }
        else {
            n[0] = Integer.parseInt(input) / 10;
            n[1] = Integer.parseInt(input)  % 10;
        }


        int count = 1;

        int[] tmp = {n[0], n[1]};
        int sum = tmp[0] + tmp[1];
        tmp[0] = tmp[1];
        tmp[1] = sum % 10;
//        System.out.println(tmp[0] + " " + tmp[1]);

        while(!(tmp[0] == n[0] && tmp[1] == n[1])){
            sum = tmp[0] + tmp[1];
            tmp[0] = tmp[1];
            tmp[1] = sum % 10;
//            System.out.println(tmp[0] + " " + tmp[1]);
            count++;
        }
        System.out.println(count);
    }
}
