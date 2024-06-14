package week6.baek.dfsbfs;

import java.util.ArrayList;
import java.util.Collections;

public class TravelRoute {
    static boolean[] used;
    static ArrayList<String> result;

    public static void dfs(String[][] tickets, String now, int depth, String path){
        if(depth == tickets.length){
            System.out.println(path);
            result.add(path);
        }

        for(int i=0; i<tickets.length; i++){
            if(!used[i] && now.equals(tickets[i][0])) {
                used[i] = true;
                dfs(tickets, tickets[i][1], depth+1, path + tickets[i][1] + " ");
                used[i] = false;
            }
        }
    }

    public static String[] solution(String[][] tickets) {
        result = new ArrayList<>();

        for (int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals("ICN")) {
                used = new boolean[tickets.length];
                used[i] = true;
                dfs(tickets, tickets[i][1], 1, "ICN " + tickets[i][1] + " ");
            }
        }

        Collections.sort(result);
        String path = result.get(0);
        return path.split(" ");
    }

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        solution(tickets);
    }
}
