package week6.baek.sort;

import java.util.Arrays;


public class BiggestNum {
    public static String solution(int[] numbers) {
        String[] s = new String[numbers.length];

        for(int i=0; i< numbers.length; i++) {
            s[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(s, (o1, o2)-> (o2+o1).compareTo(o1+o2));

//        Arrays.sort(s, new Comparator<String>(){
//            @Override
//            public int compare(String o1, String o2) {
//                return (o1+o2).compareTo(o2+o1);
//            }
//        });

        if(s[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();

        for (String str : s) {
            sb.append(str);
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        System.out.println(solution(numbers));
    }
}
