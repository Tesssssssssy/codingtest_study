package week12.week12_1.baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [기타 레슨]
 *
 * 강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다. 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다. 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.
 *
 * 강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에, 블루레이의 개수를 가급적 줄이려고 한다. 오랜 고민 끝에 강토는 M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다. 이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다.
 *
 * 강토의 각 강의의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.
 *
 *
 * 입력
 * 첫째 줄에 강의의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다. 다음 줄에는 강토의 기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로 주어진다. 각 강의의 길이는 10,000분을 넘지 않는다.
 *
 *
 * 출력
 * 첫째 줄에 가능한 블루레이 크기중 최소를 출력한다.
 *
 *
 * 예제 입력 1
 * 9 3
 * 1 2 3 4 5 6 7 8 9
 *
 * 예제 출력 1
 * 17
 *
 *
 * 힌트
 * 강의는 총 9개이고, 블루레이는 총 3개 가지고 있다.
 *
 * 1번 블루레이에 1, 2, 3, 4, 5, 2번 블루레이에 6, 7, 3번 블루레이에 8, 9 를 넣으면 각 블루레이의 크기는 15, 13, 17이 된다. 블루레이의 크기는 모두 같아야 하기 때문에, 블루레이의 크기는 17이 된다. 17보다 더 작은 크기를 가지는 블루레이를 만들 수 없다.
*/

public class Ex1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 강의의 수를 입력받음
        int M = Integer.parseInt(st.nextToken()); // 블루레이의 개수를 입력받음

        int[] lectures = new int[N]; // 각 강의의 길이를 저장할 배열을 선언
        int maxLecture = 0; // 강의의 최대 길이를 저장할 변수를 선언
        int totalLength = 0; // 강의의 총 길이를 저장할 변수를 선언
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken()); // 각 강의의 길이를 입력받아 배열에 저장
            if (lectures[i] > maxLecture) {
                maxLecture = lectures[i]; // 강의의 최대 길이를 갱신
            }
            totalLength += lectures[i]; // 강의의 총 길이를 갱신
        }

        int start = maxLecture; // 이분 탐색의 시작점을 강의의 최대 길이로 설정
        int end = totalLength; // 이분 탐색의 끝점을 강의의 총 길이로 설정
        int result = totalLength; // 블루레이의 최소 크기를 저장할 변수를 선언

        while (start <= end) {
            int mid = (start + end) / 2; // 중간값을 계산
            int sum = 0; // 현재 블루레이에 담긴 강의의 총 길이를 저장할 변수를 선언
            int count = 1; // 필요한 블루레이의 개수를 저장할 변수를 선언

            for (int lecture : lectures) {
                if (sum + lecture > mid) { // 현재 블루레이에 강의를 담을 수 없으면
                    count++; // 블루레이의 개수를 증가
                    sum = lecture; // 새로운 블루레이에 강의를 담음
                } else {
                    sum += lecture; // 현재 블루레이에 강의를 담음
                }
            }

            if (count <= M) { // 필요한 블루레이의 개수가 M 이하이면
                result = mid; // 블루레이의 최소 크기를 갱신
                end = mid - 1; // 블루레이의 크기를 줄이기 위해 끝점을 줄임
            } else { // 필요한 블루레이의 개수가 M 초과이면
                start = mid + 1; // 블루레이의 크기를 늘리기 위해 시작점을 늘림
            }
        }

        // 블루레이의 최소 크기를 출력
        System.out.println(result);
    }
}