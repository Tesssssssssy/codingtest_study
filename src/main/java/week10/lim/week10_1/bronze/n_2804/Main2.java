package week10.lim.week10_1.bronze.n_2804;

import java.io.*;
import java.util.*;

public class Main2 {
    public static void printCrossWord(String A, String B, int x, int y) {
        for (int i = 0; i < B.length(); i++) {
            if (i == y) {
                System.out.println(A);
                continue;
            }
            for (int j = 0; j < A.length(); j++) {
                if (j == x)
                    System.out.print(B.charAt(i));
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();

        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    printCrossWord(A, B, i, j);
                    return;
                }
            }
        }
    }
}
