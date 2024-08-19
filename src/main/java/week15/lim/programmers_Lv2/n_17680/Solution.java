package week15.lim.programmers_Lv2.n_17680;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/17680

import java.util.*;

public class Solution {
    /**
     *  지도개발팀에서 근무하는 제이지는 지도에서 도시 이름을 검색하면
     *  해당 도시와 관련된 맛집 게시물들을 데이터베이스에서 읽어 보여주는 서비스를 개발하고 있다.
     *  이 프로그램의 테스팅 업무를 담당하고 있는 어피치는 서비스를 오픈하기 전
     *  각 로직에 대한 성능 측정을 수행하였는데,
     *  제이지가 작성한 부분 중 데이터베이스에서 게시물을 가져오는 부분의 실행시간이 너무 오래 걸린다는 것을 알게 되었다.
     *  어피치는 제이지에게 해당 로직을 개선하라고 닦달하기 시작하였고,
     *  제이지는 DB 캐시를 적용하여 성능 개선을 시도하고 있지만 캐시 크기를 얼마로 해야 효율적인지 몰라 난감한 상황이다.
     *
     *  어피치에게 시달리는 제이지를 도와, DB 캐시를 적용할 때 캐시 크기에 따른 실행시간 측정 프로그램을 작성하시오.
     */
    public static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0)
            return cities.length * 5;
        /*
            캐시의 크기(cacheSize)가 0인 경우를 특별히 처리

            캐시 크기가 0이라는 것은 캐시가 아예 없다는 의미.
            즉, 캐시가 없는 경우에는 모든 도시 이름에 대해 캐시 미스(cache miss)가 발생.
            캐시가 존재하지 않으므로, 어떠한 도시 이름도 저장할 수 없으며, 모든 요청이 데이터베이스로부터 읽혀야 함.

            동작 원리
                캐시 미스 시 실행 시간은 5.
                캐시가 없으므로 입력된 모든 도시 이름에 대해 캐시 미스가 발생.
         */

        LinkedList<String> cache = new LinkedList<>();
        /*
            LinkedList<String> cache를 사용하여 캐시를 구현.
            LinkedList는 요소의 추가와 제거가 용이
        */

        int totalTime = 0;
        // 총 실행 시간

        for (String city : cities) {
            city = city.toLowerCase();

            if (cache.remove(city)) {
                // 이미 캐시에 존재하는 경우 cache hit 으로 처리

                totalTime += 1;
            } else {
                // cache miss

                totalTime += 5;
                if (cache.size() == cacheSize) {
                    cache.removeFirst();
                    // cache miss일 때, 캐시 크기가 초과하면 가장 오래된 항목(removeFirst)을 제거
                }
            }
            cache.add(city); // 최근에 사용된 항목으로 추가
        }

        return totalTime;
    }

    public static void main(String[] args) {
        String[] cities1 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(3, cities1)); // 50

        String[] cities2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        System.out.println(solution(3, cities2)); // 21

        String[] cities3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(solution(2, cities3)); // 60

        String[] cities4 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution(2, cities4)); // 16

        String[] cities5 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(0, cities5)); // 25
    }
}
