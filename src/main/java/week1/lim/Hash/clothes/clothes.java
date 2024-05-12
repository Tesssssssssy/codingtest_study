package week1.lim.Hash.clothes;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42578

import java.util.HashMap;
import java.util.Map;

public class clothes {
    static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        int answer = 1;
        // 가짓수를 곱해야 하므로 시작값 1로 설정 (항등원)

        for(String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }
        /*
            clothes 배열을 순회하면서 각 옷의 종류(cloth[1])를 확인하고,
            해당 종류가 이미 map에 존재하는 경우, 그 값에 1을 더하고,
            존재하지 않는 경우 기본값 0에 1을 더하여 map에 저장. 이로써 각 종류별 옷의 개수를 카운트
        */

        for(String key : map.keySet()) {
            answer *= (map.get(key) + 1);   // 조합 -> 안입는 경우도 고려하기 위해 + 1
        }
        /*
            map의 모든 키(옷의 종류)에 대해 반복하면서, 각 종류의 옷을 입는 경우의 수 계산.
            각 종류별로 옷을 입는 경우의 수는 옷의 개수 + 1
            (여기서 +1은 그 종류의 옷을 전혀 입지 않는 경우를 포함).
            이 값을 answer에 계속 곱하여 모든 종류의 옷에 대한 조합을 계산
        */

        answer -= 1;
        /*
            모두 '안입음'일 경우 -1 해주기
            앞서 계산한 모든 조합에 "아무것도 입지 않는 경우"가 포함되었기 때문에, 이를 제외
        */

        return answer;
    }

    public static void main(String[] args) {
        String[][] clothes1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        System.out.println(solution(clothes1)); // 5
        System.out.println(solution(clothes2)); // 3
    }
}