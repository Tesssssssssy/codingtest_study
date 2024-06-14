package week6.baek.exhaustiveSearch;

import java.util.HashSet;
import java.util.Set;

public class PrimeNum {
    static Set<Integer> set = new HashSet();
    static boolean[] visited;

    public static void dfs(String numbers, String s, int depth){
        if(depth > numbers.length()) {
            return;
        }

        for(int i = 0; i < numbers.length(); i++){
            if(!visited[i]) {
                visited[i] = true;
                set.add(Integer.parseInt(s + numbers.charAt(i)));
                dfs(numbers, s + numbers.charAt(i), depth + 1);
                visited[i] = false;
            }
        }
    }

    public static boolean isPrime(int n){
        if(n < 2) return false;
        else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if(n % i == 0) return false;
            }

            return true;
        }
    }
    public static int solution(String numbers) {
        //만들 수 있는 모든 숫자 조합을 구하기
        visited = new boolean[numbers.length()];

        dfs(numbers, "", 0);

        //소수 찾기
        int count = 0;
        for(int i : set){
            if(isPrime(i)) count++;
        }
        return count;
    }

    public  static void main(String[] args) {
        System.out.println(solution("17"));
    }
}
