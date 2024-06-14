package week4.baek.graph;
class Rating {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] floyd = new int[n+1][n+1];

        for(int i = 0; i < results.length; i++){
            int A = results[i][0];
            int B = results[i][1];
            // A > B
            floyd[A][B] = 1;
            floyd[B][A] = -1;
        }

        // 4 > 3 , 3 > 2 => 4 > 2
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                for(int k = 1; k <= n; k++){
                    if(floyd[i][k] == 1 && floyd[k][j] == 1){
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }
                    if(floyd[i][k] == -1 && floyd[k][j] == -1){
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++){
            int cnt = 0;
            for(int j = 1; j <= n; j++){
                if(floyd[i][j] != 0) cnt++;
            }
            if(cnt == n-1) answer++;
        }

        return answer;
    }
}
//public class Rating {
//    public static int solution(int n, int[][] results) {
//        int answer = 0;
//        return answer;
//    }
//
//    public static void main(String[] args) {
//        int n = 5;
//        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
//        System.out.println(solution(n, results));
//    }
//}
