package week7.lim.kakao2024winter.present;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/258712
// 참고: https://given-dev.tistory.com/105

import java.util.*;

public class Present3 {
    /**
     * 친구마다 인덱스를 부여한다.
     * 2차원 배열에 선물을 주고 받은 기록을 저장한다.
     * 각각의 선물 지수는 선물을 줄 때 마다 +1, 받을 때마다 -1한다.
     */
    public static int solution(String[] friends, String[] gifts) {
        int n = friends.length;  // 친구들의 수
        Map<String, Integer> friendIdx = new HashMap<>();  // 친구 이름과 인덱스를 매핑하는 해시맵
        for (int i = 0; i < n; i++) {
            friendIdx.put(friends[i], i); // 친구의 이름을 키로, 배열 인덱스를 값으로 저장
        }

        int[][] giftLog = new int[n][n];  // 친구들 간 선물 교환 횟수를 저장하는 2차원 배열
        int[] giftPoint = new int[n];  // 각 친구의 선물 지수를 저장하는 배열
        for (String gift : gifts) {
            String[] g = gift.split(" ");
            int giver = friendIdx.get(g[0]);  // 선물 준 사람의 인덱스
            int taker = friendIdx.get(g[1]);  // 선물 받은 사람의 인덱스

            giftLog[giver][taker]++;  // giver가 taker에게 준 선물 수 증가
            giftPoint[giver]++;  // giver의 선물 지수 증가
            giftPoint[taker]--;  // taker의 선물 지수 감소
        }

        int[] nextMonth = new int[n];  // 다음 달에 각 친구가 받게 될 선물 수
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (giftLog[i][j] > giftLog[j][i]) {  // i가 j에게 준 선물이 더 많다면
                    nextMonth[i]++;  // i는 j로부터 선물을 받음
                } else if (giftLog[i][j] < giftLog[j][i]) {  // j가 i에게 준 선물이 더 많다면
                    nextMonth[j]++;  // j는 i로부터 선물을 받음
                } else {  // 선물을 주고받은 수가 같다면
                    if (giftPoint[i] > giftPoint[j]) {  // i의 선물 지수가 더 높다면
                        nextMonth[i]++;  // i는 j로부터 선물을 받음
                    } else if (giftPoint[i] < giftPoint[j]) {  // j의 선물 지수가 더 높다면
                        nextMonth[j]++;  // j는 i로부터 선물을 받음
                    }
                }
            }
        }
        Arrays.sort(nextMonth);  // 선물을 받는 수에 따라 배열을 정렬
        return nextMonth[n - 1];  // 가장 많이 선물을 받는 수를 반환
    }

    public static void main(String[] args) {
        String[] friends1 = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts1 = {"muzi frodo", "muzi frodo", "ryan muzi",
                "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        System.out.println(solution(friends1, gifts1)); // 2

        String[] friends2 = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts2 = {"alessandro brad", "alessandro joy",
                "alessandro conan", "david alessandro", "alessandro david"};
        System.out.println(solution(friends2, gifts2)); // 4

        String[] friends3 = {"a", "b", "c"};
        String[] gifts3 = {"a b", "b a", "c a", "a c", "a c", "c a"};
        System.out.println(solution(friends3, gifts3)); // 0
    }
}
