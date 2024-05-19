package week2.lim.fullsearch.fatigue;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/87946
// 참고: https://velog.io/@doxxx93/practice-kit-brute-force-2

public class fatigue1 {
    static boolean[] visited;
    static int count = 0;

    public static int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        dfs(0, k, dungeons);

        return count;
    }

    private static void dfs(int depth, int fatigue, int[][] dungeons){
        for (int i = 0; i < dungeons.length; i++){
            if (visited[i] || dungeons[i][0] > fatigue) {
                continue;
            }
            visited[i] = true;
            dfs(depth + 1, fatigue - dungeons[i][1], dungeons);
            visited[i] = false;
        }
        count = Math.max(count, depth);
    }

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20}, {50,40}, {30,10}};
        System.out.println(solution(k, dungeons));  // 3
    }
}
