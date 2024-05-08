package week1.baek.stackqueue;

import java.util.*;


class Ex1 {
    public Stack<Integer> solution(int[] args) {
        Stack<Integer> stack = new Stack();
        stack.push(args[0]);

        for (int i = 1; i < args.length; i++) {
            if (stack.peek() == args[i]) continue;
            stack.push(args[i]);
        }
        return stack;
    }
}

class Ex1Main {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        int[] arr = {1,1,3,3,0,1,1};

        Stack s = ex1.solution(arr);
        System.out.println(s);
    }
}

