package week13.lim.silver.n_1497;

// 문제: https://www.acmicpc.net/problem/1497
// 참고: https://sa11k.tistory.com/45

import java.io.*;
import java.util.*;

public class Main {
    /**
     * - 최대한 많은 곡을 제대로 연주하려고 할 때, 필요한 기타의 최소 개수를 구하는 프로그램
     * - ex.)
     * GIBSON 으로 1, 2, 3번 곡을 제대로 연주할 수 있고,
     * FENDER 로 1, 2, 5번 곡을 제대로 연주할 수 있고,
     * EPIPHONE 으로 4, 5번 곡을 제대로 연주할 수 있고,
     * ESP 로 1번곡을 제대로 연주할 수 있다면,
     * -> 세준이는 EPIPHONE 과 GIBSON 을 사면 최소의 개수로 모든 곡을 연주할 수 있다.
     *
     * [입력]
     * 기타의 개수 N (<= 10), 곡의 개수 M (<= 50)
     * N개의 줄에 기타의 이름과 연주할 수 있는 곡의 정보 (1번 곡부터)
     *
     * [출력]
     * 필요한 기타의 개수
     * (연주할 수 있는 곡이 없으면 -1 출력)
     */

    // 기타 정보 클래스
    static class Guitar {
        String name;
        boolean[] canPlaySong;
        int numberOfSongs;

        public Guitar(String name, boolean[] canPlaySong, int numberOfSongs) {
            this.name = name;
            this.canPlaySong = canPlaySong;
            this.numberOfSongs = numberOfSongs;
        }
    }

    static Guitar[] guitars;  // 입력받은 기타들
    static int[] selectedGuitars;  // 현재 선택된 기타 인덱스를 저장하는 배열. (일종의 순열 조합)
    static int N, M, maxSongsCanPlay, minGuitarCount;
    // 기타 개수, 곡 개수, 현재까지 발견된 최대로 연주할 수 있는 곡의 개수, 현재까지 발견된 최소 기타 수.

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int totalSongsPlayable = 0;

        selectedGuitars = new int[N];
        guitars = new Guitar[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            boolean[] canPlaySong = new boolean[M];
            int songCount = 0;

            String canPlay = st.nextToken();
            for (int j = 0; j < M; j++) {
                canPlaySong[j] = (canPlay.charAt(j) == 'Y');
                if (canPlaySong[j]) {
                    songCount++;
                    totalSongsPlayable++;
                }
            }
            // 기타 정보 입력받기
            guitars[i] = new Guitar(name, canPlaySong, songCount);
        }

        // 기타 조합으로 선택
        for (int i = 1; i <= N; i++) {
            findGuitarCombination(0, 0, i);
        }

        // 한 곡도 연주 못하면 -1 출력
        if (totalSongsPlayable == 0)
            System.out.println(-1);
        else
            System.out.println(minGuitarCount);
    }

    /*
        기타 조합 찾기

        - currentCount: 현재까지 선택된 기타 수.
        - start: 현재 탐색을 시작할 기타 인덱스.
        - maxCount: 현재 조합에서 선택할 기타 수.

        - 재귀적으로 모든 기타 조합을 탐색.
        - currentCount 가 maxCount 에 도달하면,
          현재 조합의 기타들로 모든 곡을 연주할 수 있는지 확인 (checkIfAllSongsCovered 메서드).
    */
    public static void findGuitarCombination(int currentCount, int start, int maxCount) {
        if (currentCount == maxCount) {
            if (checkIfAllSongsCovered(guitars, selectedGuitars, maxCount)) {
                return;
            }
            return;
        }

        for (int i = start; i < N; i++) {
            selectedGuitars[currentCount] = i;
            findGuitarCombination(currentCount + 1, i + 1, maxCount);
        }
    }

    /*
        연주 가능한지 확인.
        guitarCheck: 현재 곡을 연주할 수 있는지 여부를 저장.
        모든 곡을 커버할 수 있는지 확인.
        maxSongsCanPlay 와 minGuitarCount 갱신.

        각 곡을 순회하며, 현재 선택된 기타 조합으로 연주 가능한지 확인.

        모든 곡을 커버할 수 있는지 확인.
        maxSongsCanPlay 와 minGuitarCount 를 갱신.
        연주 가능한 곡이 최대라면 이를 갱신하고, 같은 수의 곡을 연주할 수 있다면 더 적은 기타 수를 갱신.
    */
    public static boolean checkIfAllSongsCovered(Guitar[] guitars, int[] selectedGuitars, int maxCount) {
        boolean result = true;
        int songsPlayable = 0;

        for (int i = 0; i < M; i++) {
            boolean songCovered = guitars[selectedGuitars[0]].canPlaySong[i];
            // 첫 번째 기타로 연주할 수 없는 곡이라면 다른 기타로 연주할 수 있는지 확인
            if (!songCovered) {
                for (int j = 1; j < maxCount; j++) {
                    songCovered = guitars[selectedGuitars[j]].canPlaySong[i] || songCovered;
                }
            }
            // 기타 연주가 가능한지 여부 기록
            if (!songCovered)
                result = false;
            if (songCovered)
                songsPlayable++;
        }

        // 연주 가능한 곡이 최대라면 연주 가능한 곡 수와 기타 수 갱신
        if (maxSongsCanPlay < songsPlayable) {
            maxSongsCanPlay = songsPlayable;
            minGuitarCount = maxCount;
        }
        return result;
    }
}