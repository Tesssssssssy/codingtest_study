package week2.baek.sort;

import java.util.*;

public class Ex2 {
    public String solution(int[] numbers) {
        String[] s = new String[numbers.length];
        for(int i=0; i< numbers.length; i++) {
            s[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });

        if(s[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();

        for (String str : s) {
            sb.append(str);
        }

        return sb.toString();
    }
}

class Ex2Main {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        int[] numbers = {6, 10, 2};
        int[] numbers2 = {3, 30, 34, 5, 9};
        System.out.println(ex2.solution(numbers));
        System.out.println(ex2.solution(numbers2));
    }
}