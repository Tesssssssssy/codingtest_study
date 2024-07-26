package week12.lim.week12_2.silver.n_1713;

// 문제: https://www.acmicpc.net/problem/1713

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - 학교 홈페이지에 추천받은 학생의 사진을 게시할 수 있는 사진틀을 후보의 수만큼 만들었다.
     *
     *  - 학생들이 추천을 시작하기 전에 모든 사진틀은 비어있다.
     *  - 어떤 학생이 특정 학생을 추천하면, 추천받은 학생의 사진이 반드시 사진틀에 게시되어야 한다.
     *  - 비어있는 사진틀이 없는 경우에는 현재까지 추천 받은 횟수가 가장 적은 학생의 사진을 삭제하고,
     *    그 자리에 새롭게 추천받은 학생의 사진을 게시한다.
     *    이때, 현재까지 추천 받은 횟수가 가장 적은 학생이 두 명 이상일 경우에는
     *         그러한 학생들 중 게시된 지 가장 오래된 사진을 삭제한다.
     *  - 현재 사진이 게시된 학생이 다른 학생의 추천을 받은 경우에는 추천받은 횟수만 증가시킨다.
     *  - 사진틀에 게시된 사진이 삭제되는 경우에는 해당 학생이 추천받은 횟수는 0으로 바뀐다.
     *
     *  - 후보의 수 즉, 사진틀의 개수와 전체 학생의 추천 결과가 추천받은 순서대로 주어졌을 때,
     *    최종 후보가 누구인지 결정하는 프로그램
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 사진틀 개수
        int totalRecommendCnt = Integer.parseInt(br.readLine()); // 총 추천 횟수

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        // 저장된 순서를 기억하는 map -> LinkedHashMap

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < totalRecommendCnt; i++) {
            int student = Integer.parseInt(st.nextToken());
            // 추천을 받은 학생의 사진이 이미 틀에 있으면 추천 수 증가
            if (map.containsKey(student)) {
                map.put(student, map.get(student) + 1);
            } else {
                if (map.size() == N) {
                    int minId = map.entrySet().stream()
                            .min(Map.Entry.comparingByValue())
                            .get()
                            .getKey();
                    map.remove(minId);
                }
                // 새 학생을 사진틀에 추가
                map.put(student, 1);
            }
        }

        // 출력: 사진틀에 있는 학생들을 ID 오름차순으로 출력
        map.keySet().stream().sorted().forEach(id -> System.out.print(id + " "));

        /*
        TreeSet<Integer> sortedKeys = new TreeSet<>(map.keySet());
        for (Integer id : sortedKeys) {
            System.out.print(id + " ");
        }
        */
    }
}
/*
     사진틀이 꽉 차있다면, 가장 오래되고 추천 수가 가장 적은 학생을 제거
     LinkedHashMap에서 가장 작은 값(최소 추천 수를 가진 학생)을 찾아 그 키(학생의 ID)를 반환하고,
     그 키를 맵에서 제거하는 작업을 수행.

     entrySet() 메서드는 맵의 모든 키-값 쌍을 Set 컬렉션으로 반환.
     이 Set은 Map.Entry<Integer, Integer> 타입의 객체들을 포함.

     stream() 메서드는 이 Set을 스트림으로 변환.
     스트림 API를 사용하면 컬렉션을 함수형 스타일로 편리하게 처리할 수 있다.

     min() 메서드는 스트림에서 최소값을 찾기 위해 사용.
     이 메서드는 Comparator 인터페이스를 인자로 받아, 이를 기준으로 최소값을 결정.

     Map.Entry.comparingByValue()는 Comparator를 반환하는 메서드로,
     맵 엔트리들을 값(value)을 기준으로 비교. 여기서 값은 각 학생의 추천 수

     min() 메서드는 Optional<Map.Entry<Integer, Integer>> 타입의 객체를 반환.
     이 Optional 객체는 최소값을 찾은 경우 그 값을, 찾지 못한 경우 빈 값을 포함.

     get() 메서드는 Optional 객체가 포함하는 값을 반환.
     만약 Optional 객체가 비어있다면 (즉, 맵이 비어있는 경우) NoSuchElementException을 던짐.

     getKey() 메서드는 Map.Entry 객체에서 키(여기서는 학생의 ID)를 추출.
     이 키는 최소 추천 수를 가진 학생의 ID.

     remove() 메서드는 맵에서 지정된 키를 가진 항목을 제거.
     이 경우, 추출된 minId에 해당하는 키(최소 추천 수를 가진 학생의 ID)를 맵에서 제거
*/