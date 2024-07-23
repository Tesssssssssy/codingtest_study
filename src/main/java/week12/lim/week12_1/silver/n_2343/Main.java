package week12.lim.week12_1.silver.n_2343;

// 문제: https://www.acmicpc.net/problem/2343

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다
     *  - i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.
     *  - 블루레이의 개수를 가급적 줄이려고 한다.
     *  - M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로.
     *  - 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다.
     *    단, M개의 블루레이는 모두 같은 크기이어야 한다.
     *  - 각 강의의 길이가 분 단위(자연수)로 주어진다.
     *    이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램
     *
     *  [입력]
     *  강의의 수 N (1 ≤ N ≤ 100,000) / M (1 ≤ M ≤ N)
     *  기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로
     *
     *  [출력]
     *  가능한 블루레이 크기중 최소를 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] lectures = new int[N];
        st = new StringTokenizer(br.readLine());
        int maxLecture = 0;
        int sumLectures = 0;

        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            maxLecture = Math.max(maxLecture, lectures[i]);
            sumLectures += lectures[i];
        }

        int left = maxLecture; // 최소 블루레이 크기: 가장 긴 강의 길이
        int right = sumLectures; // 최대 블루레이 크기: 모든 강의 길이의 합
        int result = sumLectures;

        while (left <= right) {
            int mid = (left + right) / 2;
            int currentSum = 0;
            int countBluRays = 1;  // 필요한 블루레이의 수

            for (int lecture : lectures) {
                if (currentSum + lecture > mid) {  // 현재 블루레이에 이 강의를 추가할 수 없으면
                    countBluRays++;  // 새 블루레이 시작
                    currentSum = lecture;  // 현재 강의 길이로 시작
                    if (countBluRays > M) {  // 블루레이 수 초과
                        break;
                    }
                } else {
                    currentSum += lecture;  // 현재 블루레이에 강의 추가
                }
            }

            if (countBluRays <= M) {  // 모든 강의를 M개 이하의 블루레이에 배치할 수 있으면
                result = mid;  // 가능한 블루레이 크기 업데이트
                right = mid - 1;  // 더 작은 크기를 시도
            } else {
                left = mid + 1;  // 크기를 늘려야 함
            }
        }

        System.out.println(result);  // 최소 가능 블루레이 크기 출력
    }
}
