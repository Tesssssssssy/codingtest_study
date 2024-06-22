package week8.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B5073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.equals("0 0 0")) {
            String[] tmp = input.split(" ");
            int[] length = new int[3];
            for (int i = 0; i < 3; i++) {
                length[i] = Integer.parseInt(tmp[i]);
            }

            Arrays.sort(length);

            //Invaild의 경우
            if(length[2] >= length[0] + length[1]) System.out.println("Invalid");
            //세 변의 길이가 같은 경우
            else if(length[0] == length[1] && length[1] == length[2]) System.out.println("Equilateral");
            else if( length[0] == length[1] || length[1] == length[2]) System.out.println("Isosceles");
            else System.out.println("Scalene");

            input = br.readLine();
        }
    }
}
