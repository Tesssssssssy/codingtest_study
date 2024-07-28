package week12.baek.july26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class S10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if(op.equals("push")) {
                int a = Integer.parseInt(st.nextToken());
                stack.push(a);
            }
            else if(op.equals("pop")) {
                if(stack.isEmpty()) {
                    System.out.println(-1);
                }
                else {
                    System.out.println(stack.pop());
                }
            }
            else if(op.equals("size")){
                System.out.println(stack.size());
            }
            else if(op.equals("empty")){
                if(stack.empty() ) System.out.println(1);
                else System.out.println(0);
            }
            else if(op.equals("top")){
                if(stack.empty()) System.out.println(-1);
                else System.out.println( stack.peek() );
            }

        }
    }
}
