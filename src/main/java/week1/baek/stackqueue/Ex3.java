package week1.baek.stackqueue;

import java.util.*;

public class Ex3 {
    public Boolean solution(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
        if(s.charAt(i) == '(') {
            stack.push('(');
        }

        else if (s.charAt(i) == ')') {
            if(stack.isEmpty()) return false;
            stack.pop();
        }
    }

        if(stack.isEmpty()) return true;
        else return false;
    }
}


class Ex3Main {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        String s = ")()(";

        System.out.println(ex3.solution(s));
    }
}