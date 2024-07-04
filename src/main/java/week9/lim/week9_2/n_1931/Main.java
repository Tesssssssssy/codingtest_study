package week9.lim.week9_2.n_1931;

// 문제: https://www.acmicpc.net/problem/1931

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 입력받을 쌍의 개수
        int[][] meetings = new int[N][2];  // 시작 시간과 끝나는 시간을 저장할 배열

        // 입력받은 시작 시간과 끝나는 시간을 meetings 배열에 저장
        for (int i = 0; i < N; i++) {
            String[] time = br.readLine().split(" ");
            meetings[i][0] = Integer.parseInt(time[0]);  // 시작 시간
            meetings[i][1] = Integer.parseInt(time[1]);  // 끝나는 시간
        }

        // 배열을 끝나는 시간에 따라 오름차순으로 정렬
        // 끝나는 시간이 같다면 시작 시간에 따라 오름차순으로 정렬
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int maxMeetingCnt = 0;
        int lastEndTime = 0;

        // 회의실을 최대한 많이 사용할 수 있는 회의 개수 계산
        for (int[] meeting : meetings) { // meeting[0]: 시작 시간, meeting[1]: 종료 시간
            if (meeting[0] >= lastEndTime) {
                lastEndTime = meeting[1];
                maxMeetingCnt++;
            }
        }

        // 계산된 최대 회의 개수 출력
        System.out.println(maxMeetingCnt);
    }
}
