package week5.lim.Hash.bestalbum.practice;

import java.util.*;

public class bestAlbum1 {
    /**
     *  1. 장르별 총 재생횟수 저장
     *  2. 장르 내 인덱스별 저장횟수 저장
     *  3. 총재생횟수로 장르 정렬
     *  4. 장르 내 재생 횟수로 인덱스 정렬
     *  5. 선정된 노래의 index 저장
     */
    public static int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        // 베스트 앨범에 들어갈 index 저장

        HashMap<String, Integer> totalSongsByGenre = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> songsInGenre = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            String genre = genres[i];
            Integer play = plays[i];
            if (!totalSongsByGenre.containsKey(genre)) {
                totalSongsByGenre.put(genre, play);

                HashMap<Integer, Integer> temp = new HashMap<>();
                temp.put(i, play);
                songsInGenre.put(genre, temp);
            } else {
                totalSongsByGenre.put(genre, totalSongsByGenre.get(genre) + play);
                songsInGenre.get(genre).put(i, play); // hashmap은 key 하나에 value 여러 개 가능
            }
        }
        // 저장 끝

        // 이제 정렬해야.
        List<String> sortedGenre = new ArrayList(totalSongsByGenre.keySet());
        Collections.sort(sortedGenre, (s1, s2) -> totalSongsByGenre.get(s2) - (totalSongsByGenre.get(s1)));
        // 총 재생횟수로 장르 정렬

        // 이제 장르 내에서 재생 횟수로 정렬
        for (String key : sortedGenre) {
            HashMap<Integer, Integer> songInfoInGenre = songsInGenre.get(key);
            List<Integer> genreKey = new ArrayList(songInfoInGenre.keySet());

            Collections.sort(genreKey, (s1, s2) -> songInfoInGenre.get(s2) - (songInfoInGenre.get(s1)));

            answer.add(genreKey.get(0));
            if (genreKey.size() > 1) {
                answer.add(genreKey.get(1));    // index를 지정하니까 더 커져도 상관 x
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        System.out.println(Arrays.toString(solution(genres, plays)));   // [4, 1, 3, 0]
    }
}
