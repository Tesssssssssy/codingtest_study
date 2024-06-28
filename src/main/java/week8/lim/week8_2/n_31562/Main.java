package week8.lim.week8_2.n_31562;

// 문제: https://www.acmicpc.net/problem/31562

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄 입력: N (노래 개수), M (시도할 노래 개수)
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        // 노래 데이터를 저장할 맵 생성
        Map<String, String> songMap = new HashMap<>();

        // N개의 노래 데이터 입력
        for (int i = 0; i < N; i++) {
            String[] songInfo = br.readLine().split(" ");
            int T = Integer.parseInt(songInfo[0]);  // 노래 제목의 길이
            String title = songInfo[1];  // 노래 제목
            String key = songInfo[2] + songInfo[3] + songInfo[4];  // 첫 세 음으로 키 생성

            // 맵에 저장 (이미 존재하는 키인 경우 `?`로 설정)
            if (songMap.containsKey(key)) {
                songMap.put(key, "?");
            } else {
                songMap.put(key, title);
            }
        }

        // M개의 시도할 노래 첫 세 음 입력 및 처리
        for (int i = 0; i < M; i++) {
            String[] attempt = br.readLine().split(" ");
            String key = attempt[0] + attempt[1] + attempt[2];

            // 결과 출력
            if (songMap.containsKey(key)) {
                System.out.println(songMap.get(key));
            } else {
                System.out.println("!");
            }
        }
    }
}
