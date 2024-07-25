    package week12.baek.july23.baek;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    public class S1920 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            Set<Integer> A = new HashSet<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                A.add(Integer.parseInt(st.nextToken()));
            }


            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int t = Integer.parseInt(st.nextToken());

                if(A.contains(t)) System.out.println(1);
                else System.out.println(0);
            }

        }
    }
