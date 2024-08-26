package week16.lim.programmers_Lv2.n_138476;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/138476

import java.util.*;

public class Solution {
    /**
     *  경화는 과수원에서 귤을 수확했습니다.
     *  경화는 수확한 귤 중 'k'개를 골라 상자 하나에 담아 판매하려고 합니다.
     *  그런데 수확한 귤의 크기가 일정하지 않아 보기에 좋지 않다고 생각한 경화는
     *  귤을 크기별로 분류했을 때 서로 다른 종류의 수를 최소화하고 싶습니다.
     *
     *  예를 들어, 경화가 수확한 귤 8개의 크기가 [1, 3, 2, 5, 4, 5, 2, 3].
     *  경화가 귤 6개를 판매하고 싶다면,
     *  크기가 1, 4인 귤을 제외한 여섯 개의 귤을 상자에 담으면,
     *  귤의 크기의 종류가 2, 3, 5로 총 3가지가 되며 이때가 서로 다른 종류가 최소일 때입니다.
     *
     *  경화가 한 상자에 담으려는 귤의 개수 k와 귤의 크기를 담은 배열 tangerine이 매개변수로 주어집니다.
     *  경화가 귤 k개를 고를 때 크기가 서로 다른 종류의 수의 최솟값을 return 하도록 solution 함수를 작성해주세요.
     */
    public static int solution(int k, int[] tangerine) {
        // 귤 크기별 개수를 세기 위한 HashMap
        Map<Integer, Integer> sizeCountMap = new HashMap<>();

        // 크기별 개수를 HashMap에 저장
        for (int size : tangerine) {
            sizeCountMap.put(size, sizeCountMap.getOrDefault(size, 0) + 1);
        }

        // 개수를 내림차순으로 정렬하기 위해 리스트에 담음
        List<Integer> counts = new ArrayList<>(sizeCountMap.values());
        Collections.sort(counts, Collections.reverseOrder());

        int total = 0;
        int types = 0;

        // 가장 많은 개수를 가진 크기부터 선택하여 k개를 모음
        for (int count : counts) {
            total += count;
            types++;
            if (total >= k) {
                break;
            }
        }

        return types;
    }

    public static void main(String[] args) {
        int k1 = 6;
        int[] tangerine1 = {1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k1, tangerine1)); // 3

        int k2 = 4;
        int[] tangerine2 = {1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k2, tangerine2)); // 2

        int k3 = 2;
        int[] tangerine3 = {1, 1, 1, 1, 2, 2, 2, 3};
        System.out.println(solution(k3, tangerine3)); // 1
    }
}

/*
    TreeMap을 사용하면 기본적으로 키를 정렬된 상태로 유지할 수 있기 때문에, 코드의 일부를 더 간단하게 만들 수 있다.
    하지만 이 문제에서는 사실 TreeMap보다는 HashMap과 리스트를 이용한 정렬 방식이 더 적합하다.

    TreeMap과 HashMap의 차이점
    TreeMap
        키를 자동으로 정렬하며, 기본적으로 O(log n)의 시간 복잡도로 삽입 및 조회를 처리한다.
        그러나 이 문제에서는 값(귤의 개수) 기준으로 정렬해야 하므로, TreeMap을 사용하려면 값에 대한 추가 정렬이 필요하다.

    HashMap
        키를 정렬하지 않으며, O(1) 시간 복잡도로 삽입 및 조회가 가능하다.
        이후 값(귤의 개수) 기준으로 별도의 리스트에서 정렬하면 된다.

    문제 해결에 있어서의 고려사항
        이 문제의 핵심은 값(귤의 크기별 개수)에 대한 정렬이다.
        TreeMap을 사용하면 키 기준으로 자동 정렬되지만, 키가 아닌 값 기준으로 정렬해야 하므로,
        TreeMap보다는 HashMap을 사용하고, 값들을 모아 별도로 정렬하는 것이 더 효율적이다.
*/