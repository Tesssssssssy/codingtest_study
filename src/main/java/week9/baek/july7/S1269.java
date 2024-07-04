package week9.baek.july7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S1269 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Set<String> A = new HashSet<String>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            A.add(st.nextToken());
        }

        Set<String> B = new HashSet<String>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            B.add(st.nextToken());
        }


        //A-B
        Set<String> A2 = new HashSet<>(A);
        for (String s: B) {
            if(A2.contains(s))  A2.remove(s);
        }

        //B-A
        Set<String> B2 = new HashSet<>(B);
        for (String s: A) {
            if(B2.contains(s)) B2.remove(s);
        }

        //대칭 차집합
        System.out.println(A2.size() + B2.size());
    }
}
