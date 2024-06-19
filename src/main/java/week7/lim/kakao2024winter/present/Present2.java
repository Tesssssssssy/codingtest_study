package week7.lim.kakao2024winter.present;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/258712
// 참고: https://dev-ddol-e.tistory.com/3

import java.util.*;

public class Present2 {
    private static int solution(String[] friends, String[] gifts) {
        int answer = 0;
        // 선물을 주고 받은 기록을 이차원 배열로 기록
        int[][] giftGraph = new int[friends.length][friends.length];
        //결과 배열 :  0 받은 선물 1 준 선물 2 선물 지수 3 다음 달에 받을 선물
        int[][] result = new int[friends.length][4];
        // 친구들 이름과 index 저장
        HashMap<String, Integer> map = new HashMap<>();

        // 친구들 이름 및 인덱스 저장
        for (int i = 0; i < friends.length; i++) {
            map.put(friends[i], i);
        }

        // giftGraph 구현
        for (String temp : gifts) {
            String[] ab = temp.split(" ");
            String A = ab[0];
            String B = ab[1];
            giftGraph[map.get(A)][map.get(B)]++;
        }

        // 각 친구들 별 result 구하기
        for (Map.Entry<String, Integer> a : map.entrySet()) {
            int idx = a.getValue();
            int receivedCnt = 0;
            int givenCnt = 0;
            for (int i = 0; i < friends.length; i++) {
                givenCnt += giftGraph[idx][i];
            }
            result[idx][0] = givenCnt;

            for (int i = 0; i < friends.length; i++) {
                receivedCnt += giftGraph[i][idx];
            }
            result[idx][1] = receivedCnt;

            result[idx][2] = givenCnt - receivedCnt;

        }


        // 선물 그래프에서 가운데를 기준으로 위쪽만 비교
        for (int i = 0; i < friends.length; i++) {
            // 선물 그래프에서 가운데를 기준으로 위쪽만 비교
            for (int j = i + 1; j < friends.length; j++) {
                // i가 준 선물 수 와 j 가 준 선물 수가 같은 경우
                if (giftGraph[i][j] == giftGraph[j][i]) {
                    // 선물 지수가 i가 큰 경우
                    if (result[i][2] > result[j][2]) result[i][3]++;
                    // 선물 지수가 j가 큰 경우
                    if (result[i][2] < result[j][2]) result[j][3]++;
                    // 선물 지수가 같은 경우
                    if (result[i][2] == result[j][2]) continue;
                }
                // i가 준 선물이 더 많을 경우
                else if (giftGraph[i][j] > giftGraph[j][i]) result[i][3]++;
                    // j가 준 선물이 더 많은 경우
                else if (giftGraph[i][j] < giftGraph[j][i]) result[j][3]++;
            }
        }

        // 결과 배열에서 최대값 구하기
        for (int i = 0; i < friends.length; i++) {
            if (result[i][3] > answer) answer = result[i][3];
        }

        return answer;
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
