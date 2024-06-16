package week6.lim.dp.waytoschool;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42898
// 참고: https://velog.io/@ajufresh/%EB%93%B1%EA%B5%A3%EA%B8%B8
// 참고: https://moonsbeen.tistory.com/75

public class school1 {
    public static int solution(int m, int n, int[][] puddles) {
        int[][] street = new int[n][m];

        // 웅덩이는 -1
        for (int[] puddle : puddles)
            street[puddle[1] - 1][puddle[0] - 1] = -1;

        street[0][0] = 1;

        for (int i = 0; i < n; i++) { // 시작점은 1로 저장
            for (int j = 0; j < m; j++) {

                if (street[i][j] == -1) { // 웅덩이면 0으로 바꾸고 continue
                    street[i][j] = 0;
                    continue;
                }

                if (i != 0)
                    street[i][j] += street[i - 1][j] % 1000000007;
                // 숫자가 이 값을 초과할 수 있기 때문에 계산 과정에서 나머지 구하기

                if (j != 0)
                    street[i][j] += street[i][j - 1] % 1000000007; // 왼쪽
                    // 여기서 모듈러 연산하지 않으면 효율성 fail
            }
        }

        return street[n - 1][m - 1] % 1000000007;
    }

    public static void main(String[] args) {
        int m1 = 4;
        int n1 = 3;
        int[][] puddles = {{2, 2}};
        System.out.println(solution(m1, n1, puddles));  // 4
    }
}

/*
문제에서 1000000007로 나누는 이유는 주로 두 가지입니다: (1000000009도 가능)
    - 컴퓨터에서 큰 정수를 효과적으로 처리하기 위해, 그리고 문제의 요구사항을 만족시키기 위해.
    - 1000000007은 컴퓨터 과학에서 자주 사용되는 큰 소수(prime number)로,
      모듈로 연산을 활용하여 큰 수의 계산을 간소화하고 오버플로를 방지하는 데 도움이 됩니다.
*/