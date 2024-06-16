package week7.lim.search.동전퍼즐;

// 문제: https://level.goorm.io/exam/195037/%EB%8F%99%EC%A0%84-%ED%8D%BC%EC%A6%90/quiz/1

import java.io.*;
import java.util.*;

public class Puzzle {
    /**
     * 이 문제에서 주어진 조건은 "동전을 최소한으로 이동하여 두 번째 배치와 동일하게 만드는 것".
     * 각 동전의 위치를 각각 리스트로 추출하고, 목표 위치로의 이동 중 겹치는 위치를 찾아내는 것이 중요.
     *
     * 두 격자에서 동전의 위치를 추출하여 각각의 리스트에 저장.
     * 격자의 모든 동전 위치를 기준으로 상대적 위치를 계산하여, 두 격자의 동전이 서로 일치하는 최대 수를 구함.
     * 이동해야 하는 최소 동전 수는 전체 동전 수에서 일치하는 최대 동전 수를 뺀 값.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 격자의 크기 및 구조 입력 받기
        String[] firstLine = br.readLine().split(" ");
        int H1 = Integer.parseInt(firstLine[0]); // 첫 번째 격자의 행 수
        int W1 = Integer.parseInt(firstLine[1]); // 첫 번째 격자의 열 수

        List<Pair> firstCoins = new ArrayList<>(); // 첫 번째 격자의 동전 위치 저장 리스트

        for (int i = 0; i < H1; i++) {
            String line = br.readLine();
            for (int j = 0; j < W1; j++) {
                if (line.charAt(j) == 'O') { // 동전이 있는 경우
                    firstCoins.add(new Pair(i, j)); // 해당 위치를 리스트에 추가
                }
            }
        }

        // 두 번째 격자의 크기 및 구조 입력 받기
        String[] secondLine = br.readLine().split(" ");
        int H2 = Integer.parseInt(secondLine[0]); // 두 번째 격자의 행 수
        int W2 = Integer.parseInt(secondLine[1]); // 두 번째 격자의 열 수

        List<Pair> secondCoins = new ArrayList<>();
        // 두 번째 격자의 동전 위치 저장 리스트

        for (int i = 0; i < H2; i++) {
            String line = br.readLine();
            for (int j = 0; j < W2; j++) {
                if (line.charAt(j) == 'O') { // 동전이 있는 경우
                    secondCoins.add(new Pair(i, j)); // 해당 위치를 리스트에 추가
                }
            }
        }

        // 겹치는 동전의 최대 수를 찾는 함수 호출
        int maxOverlap = findMaxOverlap(firstCoins, secondCoins);

        // 총 동전 수에서 최대 겹침 수를 뺀 값이 최소 이동해야 할 동전 수
        System.out.println(firstCoins.size() - maxOverlap);
    }

    // 두 리스트의 동전 위치를 비교하여 최대 겹침 수를 찾는 함수
    private static int findMaxOverlap(List<Pair> firstCoins, List<Pair> secondCoins) {
        Map<Pair, Integer> displacementCounts = new HashMap<>(); // 격자 이동에 따른 동전 수 카운트
        int maxCount = 0; // 최대 겹침 수

        // 모든 첫 번째 격자 동전 위치에 대해
        for (Pair p1 : firstCoins) {
            // 모든 두 번째 격자 동전 위치에 대해
            for (Pair p2 : secondCoins) {
                Pair delta = new Pair(p1.x - p2.x, p1.y - p2.y);
                // 두 동전 위치의 상대적 차이
                displacementCounts.put(delta, displacementCounts.getOrDefault(delta, 0) + 1);
                // 차이에 따른 카운트 증가
                maxCount = Math.max(maxCount, displacementCounts.get(delta));
                // 최대 겹침 수 갱신
            }
        }

        return maxCount; // 최대 겹침 수 반환
    }

    // 위치를 저장하는 간단한 클래스
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

/*
접근 방식
    1. 두 격자의 동전 위치 추출: 먼저, 각 격자에서 동전이 있는 위치를 찾아 리스트로 저장.
    2. 동전 위치 겹침 최대화: 두 리스트의 각 동전 위치를 비교하여, 격자를 어떻게 이동시킬 때 겹침(overlap)이 최대가 되는지 계산.
                         이를 위해 상대적 위치 차이(이동 벡터)를 사용.

겹침 최대화 방식 설명
    - 상대적 위치 차이 (이동 벡터): 첫 번째 격자의 동전 위치를 기준으로 두 번째 격자의 각 동전 위치와의 차이를 계산.
                              예를 들어, 첫 번째 격자의 동전이 (2,3)에 있고, 두 번째 격자의 동전이 (4,6)에 있다면,
                              이동 벡터는 (2-4, 3-6) = (-2, -3)이 됨.
                              이 벡터는 첫 번째 격자를 (2,3)만큼 오른쪽과 아래로 이동시키면 두 동전이 겹친다는 의미.

    - 이동 벡터 빈도 계산: 각 이동 벡터가 얼마나 자주 나타나는지 계산.
                       가장 빈번하게 나타나는 이동 벡터는, 그만큼 많은 동전이 서로 겹치게 될 수 있음을 의미.

    - 최대 겹침 계산: 가장 빈번한 이동 벡터에 해당하는 겹침 수가 최대 겹침 수.
                   예를 들어, 가장 빈번한 이동 벡터가 5번 나타났다면, 이동하지 않고도 5개의 동전을 일치시킬 수 있다.

결과 계산
    - 최소 이동 동전 수: 전체 동전 수에서 최대 겹침 수를 빼면, 나머지 동전은 이동해야 함.
                     이 값이 옮겨야 할 최소 동전 수입니다.
                     이 접근 방식을 사용하면, 특정 격자의 변형(회전, 대칭 등) 없이 두 격자 사이의 최소 동전 이동 수를 계산할 수 있다.
                     이러한 계산은 효율적이며, 크기가 다른 격자나 다양한 동전 배치에도 적용할 수 있다.
*/