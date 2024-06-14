package week6.baek.dfsbfs;

public class Network {
    static boolean[] visited;
    public static void dfs(int[][] computers, int x){
        visited[x] = true;

        for (int i = 0; i < computers.length; i++) {
            if(computers[x][i] == 1 && !visited[i]) {
                dfs(computers, i);
            }
        }

    }
    public static int solution(int n, int[][] computers) {
        visited = new boolean[computers.length];

        int count = 0;
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(computers, i);
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] computers = {{1,1,0}, {1,1 ,0}, {0,0,1}};
        System.out.println(solution(3, computers));
    }
}
