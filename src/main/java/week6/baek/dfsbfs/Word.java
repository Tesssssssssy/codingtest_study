package week6.baek.dfsbfs;

import java.util.*;

public class Word {
    static boolean[] visited;
    static ArrayList<Integer> result;

    //문자 차이가 1인지 유사도를 구하는 함수
    public static boolean similarity(String target, String word){
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if(target.charAt(i) != word.charAt(i)) count++;
        }
        if(count == 1) return true;
        else return false;
    }

    public static void dfs(String target, String[] words, String word, ArrayList<String> path){
        if(word == target) {
//            System.out.println(path);
            result.add(path.size());
            return;
        }


        for (int i = 0; i < words.length; i++) {
            if(!visited[i] && similarity(word, words[i])){
                visited[i] = true;
                path.add(words[i]);
                dfs(target, words, words[i], path);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }

    }

    public static int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        result = new ArrayList<>();

        for (int i = 0; i < words.length; i++){
            if(similarity(words[i], begin)) {
                ArrayList<String> path = new ArrayList<>();
                path.add(words[i]);
                visited = new boolean[words.length];
                visited[i] = true;

                dfs(target, words, words[i], path);
            }
        }

        Collections.sort(result);
//        System.out.println(result);

        if(result.isEmpty()) return 0;

        return result.get(0);
    }

    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution("hit", "cog", words));
    }
}
