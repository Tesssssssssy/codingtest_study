package week14.lim.silver.n_10025.practice;

// 문제: https://www.acmicpc.net/problem/10025

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  더운 여름날 동물원의 백곰 앨버트는 너무 더워서 꼼짝도 하기 싫다.
     *  다행히도 사육사들이 앨버트의 더위를 식히기 위해 얼음이 담긴 양동이들을 가져다 주었다.
     *  앨버트가 가장 적은 거리만 움직이고도 최대한 많은 얼음으로 더위를 식힐 수 있도록 도와주자.
     *
     *  우리 안은 1차원 배열로 생각하며,
     *  총 N(1 ≤ N ≤ 100000)개의 얼음 양동이들이 xi(0 ≤ xi ≤ 1,000,000)좌표마다 놓여 있고
     *  각 양동이 안에는 gi(1 ≤ gi ≤ 10,000)씩의 얼음이 들어 있다.
     *
     *  일단 앨버트가 자리를 잡으면 그로부터 좌우로 K(1 ≤ K ≤ 2,000,000) 만큼 떨어진 양동이까지 닿을 수 있다.
     *  앨버트는 양동이가 놓여 있는 자리에도 자리잡을 수 있다.
     *  모든 얼음 양동이의 위치는 다르다.
     *
     *  앨버트가 최적의 자리를 골랐을 때 얼음의 합을 구하시오. 즉, 얼음들의 합의 최댓값을 구해야 한다.
     *
     *  [입력]
     *  첫 줄에 정수 N과 K가 들어온다. 둘째 줄부터 N째 줄까지, 공백을 사이에 두고 각 양동이의 얼음의 양을 나타내는 gi와 양동이의 좌표를 나타내는 xi가 주어진다.
     *
     *  [출력]
     *  앨버트가 택한 최적 위치로부터 K만큼 떨어진 거리 내에 있는 얼음들의 합(최댓값)을 출력한다.
     *
     *  [풀이]
     *  슬라이딩 윈도우
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 좌표의 범위는 최대 1,000,000까지
        int[] ice = new int[1000001];

        // 얼음 양과 좌표 입력 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int gi = Integer.parseInt(st.nextToken());
            int xi = Integer.parseInt(st.nextToken());
            ice[xi] += gi;
        }

        int currentSum = 0;
        int windowSize = 2 * K + 1;
        int maxIce = 0;

        for (int i = 0; i < windowSize && i < 1000001; i++) {
            currentSum += ice[i];
        }
        maxIce = currentSum;

        for (int i = windowSize; i < 1000001; i++) {
            currentSum += ice[i];
            currentSum -= ice[i - windowSize];
            maxIce = Math.max(maxIce, currentSum);
        }

        System.out.println(maxIce);
    }
}
