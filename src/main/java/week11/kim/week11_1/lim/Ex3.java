package week11.kim.week11_1.lim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * [IF문 좀 대신 써줘]
 *
 * 게임 개발자인 밀리는 전투력 시스템을 만들어, 캐릭터가 가진 전투력을 기준으로 칭호를 붙여주려고 한다.
 *
 * 예를 들어, 전투력 10,000 이하의 캐릭터는 WEAK, 10,000 초과 그리고 100,000 이하의 캐릭터는 NORMAL, 100,000 초과 그리고 1,000,000 이하의 캐릭터는 STRONG 칭호를 붙여준다고 하자. 이를 IF문으로 작성한다면 아래와 같이 구현할 수 있다.
 *
 * if power <= 10000
 *  print WEAK
 * else if power <= 100000
 *  print NORMAL
 * else if power <= 1000000
 *  print STRONG
 *
 * 혼자서 게임을 개발하느라 매우 바쁜 밀리를 대신해서, 캐릭터의 전투력에 맞는 칭호를 출력하는 프로그램을 작성하자.
 *
 *
 * 입력
 * 첫 번째 줄에는 칭호의 개수 N (1 ≤ N ≤ 105)과 칭호를 출력해야 하는 캐릭터들의 개수 M (1 ≤ M ≤ 105)이 빈칸을 사이에 두고 주어진다. (1 ≤ N, M ≤ 105)
 *
 * 두 번째 줄부터 N개의 줄에 각 칭호의 이름을 나타내는 길이 1 이상, 11 이하의 영어 대문자로만 구성된 문자열과 해당 칭호의 전투력 상한값을 나타내는 109 이하의 음이 아닌 정수가 주어진다. 칭호는 전투력 상한값의 비내림차순으로 주어진다.
 *
 * N + 2번째 줄부터 M개의 각 줄에는 캐릭터의 전투력을 나타내는 음이 아닌 정수가 주어진다. 해당하는 칭호가 없는 전투력은 입력으로 주어지지 않는다.
 *
 *
 * 출력
 * M개의 줄에 걸쳐 캐릭터의 전투력에 맞는 칭호를 입력 순서대로 출력한다. 어떤 캐릭터의 전투력으로 출력할 수 있는 칭호가 여러 개인 경우 가장 먼저 입력된 칭호 하나만 출력한다.
 *
 *
 * 예제 입력 1
 * 3 8
 * WEAK 10000
 * NORMAL 100000
 * STRONG 1000000
 * 0
 * 9999
 * 10000
 * 10001
 * 50000
 * 100000
 * 500000
 * 1000000
 *
 * 예제 출력 1
 * WEAK
 * WEAK
 * WEAK
 * NORMAL
 * NORMAL
 * NORMAL
 * STRONG
 * STRONG
 *
 * 예제 입력 2
 * 3 5
 * B 100
 * A 100
 * C 1000
 * 99
 * 100
 * 101
 * 500
 * 1000
 *
 * 예제 출력 2
 * B
 * B
 * C
 * C
 * C
*/

// 10_2 임태우 2
public class Ex3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] title = new String[N+1];
        int[] power = new int[N+1];

        // 각 칭호와 그 칭호의 전투력 상한값을 입력 받음
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            title[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            int characterPower = Integer.parseInt(br.readLine());
            // 이분 탐색을 통해 캐릭터의 전투력에 맞는 칭호를 찾음
            int left = 1, right = N;
            while(left <= right) {
                int mid = (left + right) / 2;
                if(power[mid] < characterPower) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // 해당 칭호를 출력
            sb.append(title[left]).append('\n');
        }

        System.out.println(sb);
    }
}