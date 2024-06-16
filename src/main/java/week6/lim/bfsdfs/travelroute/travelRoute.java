package week6.lim.bfsdfs.travelroute;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/43164

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class travelRoute {
    static ArrayList<String> list = new ArrayList<>();
    static boolean useTickets[];

    public static String[] solution(String[][] tickets) {
        useTickets = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(list);

        return list.get(0).split(" ");
    }

    static void dfs(int depth, String now, String path, String[][] tickets) {
        if (depth == tickets.length) {
            list.add(path);
            return;
        }

        for (int i = 0; i < useTickets.length; i++) {
            if (!useTickets[i] && now.equals(tickets[i][0])) {
                useTickets[i] = true;
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
                useTickets[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(Arrays.toString(solution(tickets))); // [ICN, JFK, HND, IAD]
    }
}
