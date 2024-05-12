package week1.lim.Hash.bestalbum;

import java.util.*;

public class album2 {
    static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotalPlays = new HashMap<>();
        // 각 장르의 총 재생 횟수를 저장하는 HashMap

        Map<String, PriorityQueue<int[]>> genreSongs = new HashMap<>();
        // 각 장르별로 노래의 인덱스와 재생 횟수를 저장하는 PriorityQueue를 가진 HashMap
        // PriorityQueue -> 재생 횟수가 많은 순으로 정렬

        for (int i = 0; i < genres.length; i++) {
            // 장르와 재생 횟수 배열을 순회하면서 genreTotalPlays에 각 장르별 총 재생 횟수를 누적

            String genre = genres[i];
            int play = plays[i];

            genreTotalPlays.put(genre, genreTotalPlays.getOrDefault(genre, 0) + play);

            if (!genreSongs.containsKey(genre)) {
                genreSongs.put(genre, new PriorityQueue<>((a, b) -> b[1] - a[1]));
            }
            genreSongs.get(genre).add(new int[]{i, play});

            // genreSongs에는 각 장르별로 PriorityQueue를 생성하고, 각 노래의 인덱스와 재생 횟수 배열을 저장.
            // PriorityQueue의 정렬 기준은 재생 횟수가 높은 노래가 우선순위를 갖는다.
        }

        List<String> sortedGenres = new ArrayList<>(genreTotalPlays.keySet());
        sortedGenres.sort((g1, g2) -> genreTotalPlays.get(g2) - genreTotalPlays.get(g1));
        // genreTotalPlays의 키(장르)를 리스트로 추출한 후, 이를 각 장르의 총 재생 횟수를 기준으로 내림차순 정렬

        List<Integer> answerList = new ArrayList<>();

        for (String genre : sortedGenres) {
            PriorityQueue<int[]> songs = genreSongs.get(genre);
            answerList.add(songs.poll()[0]);

            if (!songs.isEmpty()) {
                answerList.add(songs.poll()[0]);
            }
        }
        // 정렬된 장르 리스트를 순회하면서 각 장르별 PriorityQueue에서 최대 두 개의 노래 인덱스를 추출.
        // 첫 번째는 가장 많이 재생된 노래, 두 번째는 그 다음으로 많이 재생된 노래의 인덱스.

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        // 추출된 노래 인덱스를 배열 answer에 저장하여 반환

        return answer;
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = solution(genres, plays);
        System.out.println(Arrays.toString(result));    // [4, 1, 3, 0]
    }
}
