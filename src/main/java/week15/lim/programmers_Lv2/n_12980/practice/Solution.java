package week15.lim.programmers_Lv2.n_12980.practice;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/12980

import java.util.*;

public class Solution {
    /**
     *  한 번에 K 칸을 앞으로 점프하거나, (현재까지 온 거리) x 2 에 해당하는 위치로 순간이동
     *  순간이동을 하면 건전지 사용량이 줄지 않지만, 앞으로 K 칸을 점프하면 K 만큼의 건전지 사용량이 듦.
     *  아이언 슈트를 착용하고 이동할 때는 순간 이동을 하는 것이 더 효율적
     *  아이언 슈트를 착용하고 거리가 N 만큼 떨어져 있는 장소로 감.
     *  건전지 사용량을 줄이기 위해 점프로 이동하는 것은 최소로.
     *  아이언 슈트 구매자가 이동하려는 거리 N이 주어졌을 때, 사용해야 하는 건전지 사용량의 최솟값을 return.
     *
     */
    public static int solution(int n) {
        int ans = 0;

        while (n > 0) {
            if (n % 2 == 1) {
                ans++;
                n--;
            }
            n /= 2;
        }

        return ans;
    }

    public static void main(String[] args) {
        int N1 = 5;
        int N2 = 6;
        int N3 = 5000;

        System.out.println(solution(N1));
        System.out.println(solution(N2));
        System.out.println(solution(N3));
    }
}