package week3.baek.exhaustivesearch;

import java.util.ArrayList;
import java.util.Collections;

public class Ex7 {
    ArrayList<String> list = new ArrayList<>();

    public void dfs(String[] s, String result, int length){
        if(result.length() == length) list.add(result);

        else{
            for (int i = 0; i < s.length; i++) {
                dfs(s, result + s[i], length);
            }
        }
    }

    public int solution(String word) {
        int answer = 0;

        String[] s = {"A", "E", "I", "O", "U"};

        for (int i = 1; i <= 5; i++) {
            dfs(s, "", i);
        }

//        System.out.println(list);

        Collections.sort(list);

//        System.out.println(list);

        return list.indexOf(word) + 1;
    }
}

class Ex7Main{
    public static void main(String[] args) {
        Ex7 ex7 = new Ex7();
        String word = "AAAAE";
        System.out.println(ex7.solution(word));


    }
}