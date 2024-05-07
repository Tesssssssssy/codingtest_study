package week1.lim.Hash.bestalbum;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/42579
// 참고: https://jisunshine.tistory.com/169

import java.util.*;

public class album {
    static int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        // 베스트 앨범에 들어갈 노래의 고유 번호를 저장할 리스트

        HashMap<String, Integer> num = new HashMap<>();
        // 각 장르별로 노래의 총 재생 횟수를 저장할 해시맵

        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        // 각 장르에 속하는 각 노래의 고유 번호와 재생 횟수를 저장할 해시맵의 해시맵

        for(int i = 0; i < plays.length; i++) {
            // genres 배열을 순회하며 각 노래에 대한 정보를 수집

            if(!num.containsKey(genres[i])) {
                // 만약 num 해시맵에 해당 장르가 존재하지 않는다면,
                // 새로운 HashMap을 생성하여 노래 고유 번호와 재생 횟수를 저장하고, music과 num에 해당 정보를 추가

                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
                num.put(genres[i], plays[i]);
            } else {
                // 이미 해당 장르가 num에 존재한다면,
                // 해당 장르의 HashMap에 새로운 노래 정보를 추가하고, 총 재생 횟수를 갱신

                music.get(genres[i]).put(i, plays[i]);
                num.put(genres[i], num.get(genres[i]) + plays[i]);
            }
        }

        List<String> keySet = new ArrayList(num.keySet());
        // num 해시맵의 키셋(장르명)을 리스트로 변환 (정렬하기 위해)

        Collections.sort(keySet, (s1, s2) -> num.get(s2) - (num.get(s1)));
        /*
            리스트를 재생 횟수에 따라 내림차순으로 정렬
            s1과 s2는 keySet에서 가져온 두 개의 장르명.
            num.get(s1)과 num.get(s2)는 각각 장르 s1과 s2의 총 재생 횟수를 num 해시맵에서 조회.
            num.get(s2) - num.get(s1)는 s2의 재생 횟수에서 s1의 재생 횟수를 빼는 것.

            결과가 양수라면 s2가 s1보다 더 많이 재생된 것이므로, s2를 s1보다 앞에 위치 (내림차순).
            결과가 음수라면 s1이 s2보다 더 많이 재생되어, s1을 s2보다 앞에 위치.
            결과가 0이면 두 장르의 재생 횟수가 같다는 의미 => 두 요소의 순서는 변경되지 않는다.
        */

        for(String key : keySet) {
            // 정렬된 장르 리스트를 순회하며 각 장르별로 노래를 정렬

            HashMap<Integer, Integer> map = music.get(key);
            List<Integer> genre_key = new ArrayList(map.keySet());

            Collections.sort(genre_key, (s1, s2) -> map.get(s2) - (map.get(s1)));
            // 각 장르의 HashMap에서 키셋(노래 고유 번호)을 가져와 재생 횟수에 따라 내림차순으로 정렬

            answer.add(genre_key.get(0));
            if(genre_key.size() > 1)
                answer.add(genre_key.get(1));
            // 각 장르에서 재생 횟수가 가장 높은 노래 두 개를 answer 리스트에 추가
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /*
        장르별 재생 횟수 저장 - HashMap<String, Integer> num = new HashMap<>();
        각 장르에 속한 노래의 <고유번호, 재생횟수> 저장 - HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        속한 노래가 많이 재생된 장르를 먼서 수록해야 하므로, 장르별 재생 횟수 저장
        장르 내에서 많이 재생된 노래를 먼저 수록해야 하므로, 곡 수대로 내림차순 정렬
        정렬된 num의 순서대로 music에서 정렬된 곡들 중 상위 2곡의 고유번호를 answer(ArrayList)에 담는다.
        ArrayList를 배열로 바꾸어 리턴.

    */

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        System.out.println(Arrays.toString(solution(genres, plays)));   // [4, 1, 3, 0]
    }
}
