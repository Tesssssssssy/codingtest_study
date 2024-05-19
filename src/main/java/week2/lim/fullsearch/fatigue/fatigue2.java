package week2.lim.fullsearch.fatigue;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/87946

public class fatigue2 {
    // 최대 던전 탐험 수를 저장할 변수
    private static int maxDungeonsExplored = 0;

    public static int solution(int k, int[][] dungeons) {
        maxDungeonsExplored = 0;
        boolean[] visited = new boolean[dungeons.length];

        exploreDungeons(k, dungeons, visited, 0);

        return maxDungeonsExplored;
    }

    // 현재 피로도와 선택된 던전 순서로 최대 탐험 가능 던전 수 계산
    private static void exploreDungeons(int currentFatigue, int[][] dungeons, boolean[] visited, int count) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && currentFatigue >= dungeons[i][0]) {
                visited[i] = true;
                exploreDungeons(currentFatigue - dungeons[i][1], dungeons, visited, count + 1);
                visited[i] = false;
            }
        }
        // 기존의 최대값보다 현재 탐험한 던전 수가 더 많으면 갱신
        maxDungeonsExplored = Math.max(maxDungeonsExplored, count);
    }

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20}, {50,40}, {30,10}};

        System.out.println(solution(k, dungeons));  // 3
    }
}
