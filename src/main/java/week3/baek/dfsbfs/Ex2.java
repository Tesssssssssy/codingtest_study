package week3.baek.dfsbfs;

public class Ex2 {
    static int[] visited;

    public void dfs(int i, int[][] computers){
        visited[i] = 1;
        for (int j = 0; j < computers.length; j++) {
            if(computers[i][j] == 1 && visited[j] == 0){
                dfs(j, computers);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new int[n];

        for (int i = 0; i < computers.length; i++) {
            if(visited[i] == 0){
                answer++;
                dfs(i, computers);
            }
        }

        return answer;
    }
}

class Ex2Main{
    public static void main(String[] args) {
        int n = 3;
        // 110
        // 110
        // 001

        // 110
        // 111
        // 011
        int[][] computers ={{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] computers2 ={{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.solution(n, computers));
        System.out.println(ex2.solution(n, computers2));
    }
}
