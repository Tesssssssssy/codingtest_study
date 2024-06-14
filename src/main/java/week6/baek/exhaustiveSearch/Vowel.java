package week6.baek.exhaustiveSearch;

import java.util.ArrayList;
import java.util.Collections;

public class Vowel {
    static String[] vowel = {"A", "E", "I", "O", "U"};
    static ArrayList<String> dic = new ArrayList<>();

    static void dfs(String s, int length){
        if(length > 4) return;

        for(String v : vowel){
            dic.add(s+v);
            dfs(s+v, length+1);
        }
    }

    public static int solution(String word) {
        dfs("", 0);
        Collections.sort(dic);
        System.out.println(dic);
        return dic.indexOf(word) + 1;
    }

    public static void main(String[] args) {
        System.out.println(solution("AAAAE"));
    }
}
