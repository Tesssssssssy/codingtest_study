package week3.baek.dfsbfs;

import java.util.ArrayList;
import java.util.Collections;

public class Ex6 {
    ArrayList<String> answer = new ArrayList<>();
    int[] visited;

    public void dfs(int depth, String now, String path, String[][] tickets){
        if(depth == tickets.length){
            answer.add(path);
            return;
        }

        else {
            for (int i = 0; i < visited.length; i++) {
                if(visited[i] == 0 && now.equals(tickets[i][0])){
                    dfs(depth+1, tickets[i][1],path + " " + tickets[i][1], tickets);
                }
            }
        }
    }

    public String[] solution(String[][] tickets) {
        visited = new int[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(answer);

        return answer.get(0).split(" ");
    }

}

class Ex6Main{
    public static void main(String[] args) {
        Ex6 ex6 = new Ex6();
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(ex6.solution(tickets));
    }
}
