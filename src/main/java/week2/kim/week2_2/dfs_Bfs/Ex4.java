package week2.kim.week2_2.dfs_Bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * [여행 경로]
 *
 * 주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
 *
 * 항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
 * 주어진 공항 수는 3개 이상 10,000개 이하입니다.
 * tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
 * 주어진 항공권은 모두 사용해야 합니다.
 * 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
 * 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
 *
 * 입출력 예
 * tickets												                            return
 * [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]						            ["ICN", "JFK", "HND", "IAD"]
 * [["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]]	["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
 *
 * 입출력 예 설명
 * 예제 #1
 *
 * ["ICN", "JFK", "HND", "IAD"] 순으로 방문할 수 있습니다.
 *
 * 예제 #2
 *
 * ["ICN", "SFO", "ATL", "ICN", "ATL", "SFO"] 순으로 방문할 수도 있지만 ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] 가 알파벳 순으로 앞섭니다.
*/

public class Ex4 {
    public static void main(String[] args) {
        Ex4 ex4 = new Ex4();

        String[][] tickets1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};

        // ["ICN", "JFK", "HND", "IAD"] 반환
        System.out.println(Arrays.toString(ex4.solution(tickets1)));
        // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] 반환
        System.out.println(Arrays.toString(ex4.solution(tickets2)));
    }

    List<String> answer = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", tickets, 0);

        // 알파벳 순서가 앞서는 경로를 반환하기 위해 정렬
        Collections.sort(answer);

        return answer.get(0).split(" ");
    }

    // current > 현재 위치
    // path > 지금까지의 여행 경로
    // tickets > 항공권 정보
    // count > 지금까지 사용한 항공권의 수(모든 항공권 사용 후 여행 경로 완성)
    public void dfs(String current, String path, String[][] tickets, int count) {
        if(count == tickets.length) {
            answer.add(path);
            return;
        }

        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;

                dfs(tickets[i][1], path + " " + tickets[i][1], tickets, count + 1);

                // 모든 경로를 찾기 위해 방문한 곳을 다시 방문하지 않은 상태로 변경
                visited[i] = false;
            }
        }
    }
}