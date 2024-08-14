package week14.lim.silver.n_2531;

// 문제: https://www.acmicpc.net/problem/2531

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  회전 초밥 음식점에는 회전하는 벨트 위에 여러 가지 종류의 초밥이 접시에 담겨 놓여 있고,
     *  손님은 이 중에서 자기가 좋아하는 초밥을 골라서 먹는다.
     *  초밥의 종류를 번호로 표현할 때, 다음 그림은 회전 초밥 음식점의 벨트 상태의 예를 보여주고 있다.
     *  벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다.
     *
     *  새로 문을 연 회전 초밥 음식점이 불경기로 영업이 어려워서, 다음과 같이 두 가지 행사를 통해서 매상을 올리고자 한다.
     *  원래 회전 초밥은 손님이 마음대로 초밥을 고르고, 먹은 초밥만큼 식대를 계산하지만,
     *  벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다.
     *  각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고,
     *  1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다.
     *
     *  만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공한다.
     *  위 할인 행사에 참여하여 가능한 한 다양한 종류의 초밥을 먹으려고 한다.
     *
     *  위 그림의 예를 가지고 생각해보자. k=4이고, 30번 초밥을 쿠폰으로 받았다고 가정하자.
     *  쿠폰을 고려하지 않으면 4가지 다른 초밥을 먹을 수 있는 경우는
     *  (9, 7, 30, 2), (30, 2, 7, 9), (2, 7, 9, 25) 세 가지 경우가 있는데,
     *  30번 초밥을 추가로 쿠폰으로 먹을 수 있으므로 (2, 7, 9, 25)를 고르면 5가지 종류의 초밥을 먹을 수 있다.
     *
     *  회전 초밥 음식점의 벨트 상태, 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 접시의 개수, 쿠폰 번호가 주어졌을 때,
     *  손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램을 작성하시오.
     *
     *  [입력]
     *  첫 번째 줄에는 회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c가 각각 하나의 빈 칸을 사이에 두고 주어진다.
     *  단, 2 ≤ N ≤ 30,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d이다.
     *  두 번째 줄부터 N개의 줄에는 벨트의 한 위치부터 시작하여 회전 방향을 따라갈 때
     *  초밥의 종류를 나타내는 1 이상 d 이하의 정수가 각 줄마다 하나씩 주어진다.
     *
     *  [출력]
     *  주어진 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댓값을 하나의 정수로 출력한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 접시 수
        int d = Integer.parseInt(st.nextToken());  // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken());  // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken());  // 쿠폰 번호

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d + 1];  // 각 초밥 종류의 개수를 저장하는 배열
        int currentVariety = 0;  // 현재 윈도우 내의 초밥 종류 수
        int maxVariety = 0;  // 최종 결과로 출력할 최대 초밥 종류 수

        // 초기 윈도우 설정 (첫 k개의 초밥)
        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) {
                currentVariety++;
            }
            count[sushi[i]]++;
        }

        maxVariety = currentVariety;

        // 슬라이딩 윈도우 처리
        for (int i = 0; i < N; i++) {
            // 윈도우에서 벗어나는 초밥 제거
            int outgoing = sushi[i];
            count[outgoing]--;
            if (count[outgoing] == 0) {
                currentVariety--;
            }

            // 윈도우에 새로 들어오는 초밥 추가
            int incoming = sushi[(i + k) % N];
            if (count[incoming] == 0) {
                currentVariety++;
            }
            count[incoming]++;

            // 쿠폰 초밥을 포함한 초밥 종류 수 계산
            int totalVariety = currentVariety;
            if (count[c] == 0) {
                totalVariety++;
            }

            // 최대 초밥 종류 수 갱신
            maxVariety = Math.max(maxVariety, totalVariety);
        }

        System.out.println(maxVariety);
    }
}
/*
접근 방법
    슬라이딩 윈도우
        벨트 위에서 연속된 k개의 초밥을 선택하며, 윈도우를 한 칸씩 옮기면서 다양한 초밥을 선택할 수 있는 최대 가짓수를 계산.

    초밥 종류 카운팅
        초밥 종류의 출현 횟수를 기록하는 배열을 사용.

    쿠폰 처리
        쿠폰으로 제공되는 초밥 종류는 반드시 포함해야 하므로, 쿠폰 초밥이 현재 윈도우에 포함되어 있지 않다면 추가할 수 있다.
*/
/*
    코드 설명
        입력 처리
            N: 접시 수, d: 초밥의 가짓수, k: 연속해서 먹는 접시 수, c: 쿠폰 번호.
            sushi[]: 벨트 위의 초밥 종류를 저장하는 배열.

        초기 윈도우 설정
            첫 k개의 초밥을 윈도우에 넣고, 그동안 초밥 종류의 개수를 count[] 배열에 기록.
            currentVariety: 현재 윈도우 내에서의 초밥 종류의 수.

        슬라이딩 윈도우
            윈도우를 한 칸씩 오른쪽으로 이동시키면서 새로운 초밥을 추가하고, 윈도우에서 벗어난 초밥을 제거.
            초밥이 윈도우에 추가될 때와 벗어날 때마다 count[] 배열을 갱신하여 현재 초밥 종류의 개수를 업데이트.

        쿠폰 처리
            쿠폰 번호로 주어진 초밥이 현재 윈도우에 없다면, 이 초밥 종류를 추가로 포함하여 totalVariety를 계산.

        최대값 갱신
            maxVariety를 이용해 초밥 종류의 최대값을 저장하고, 슬라이딩 윈도우가 벨트 전체를 한 바퀴 돌 때까지 계산.

    결과
        슬라이딩 윈도우를 통해 최대 초밥 가짓수를 계산한 후, 이를 출력.
*/