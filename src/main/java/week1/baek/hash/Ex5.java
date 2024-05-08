package week1.baek.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Ex5 {
    public void solution(String[] genres, int[] plays) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0; i < genres.length; i++){
            if(map.containsKey(genres[i])){
                map.get(genres[i]).add(plays[i]);
            }
            else {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(plays[i]);
                map.put(genres[i], a);
            }
        }

        for(String s : map.keySet()){
            ArrayList<Integer> a = map.get(s);
            Collections.sort(a);

            int count = 0;
            for(int i : a){

            }
        }
    }
}


class Ex5Main {
    public static void main(String[] args) {
        Ex5 ex5 = new Ex5();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        ex5.solution(genres, plays);

    }
}