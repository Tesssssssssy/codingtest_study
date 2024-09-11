package week18.lim.n_17683;

// 문제: https://school.programmers.co.kr/learn/courses/30/lessons/17683
// 참고: https://dragondeok.tistory.com/59

import java.util.*;

public class Solution {
    // '#'이 붙은 음을 처리하기 위한 매핑을 저장하는 HashMap
    static HashMap<String, String> hm = new HashMap<>();

    public static String solution(String m, String[] musicinfos) {
        // 초기화: '#'이 붙은 음을 소문자 알파벳으로 치환하여 저장

        String answer = "";
        int time = 0;
        hm.put("C#", "c");
        hm.put("D#", "d");
        hm.put("F#", "f");
        hm.put("G#", "g");
        hm.put("A#", "a");
        hm.put("B#", "b");

        // 각 음악 정보에 대해 처리
        for (String musicinfo : musicinfos) {
            String[] arr = musicinfo.split(",");
            String[] start = arr[0].split(":");
            String[] end = arr[1].split(":");

            // 음악의 재생 시간을 분 단위로 계산
            int duration = (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60
                    + (Integer.parseInt(end[1]) - Integer.parseInt(start[1]));

            // 악보와 네오가 기억한 멜로디에서 '#'이 붙은 음을 치환하여 변환
            String melody = converting(arr[3]); // 음악 한 곡의 멜로디를 치환하여 저장
            String neoMelody = converting(m); // 네오가 기억한 멜로디를 치환하여 저장
            String playedMelody = ""; // 재생된 시간 동안 실제 재생된 멜로디를 저장

            // 재생된 시간 동안 악보를 반복하여 실제로 재생된 멜로디를 생성
            for (int i = 0; i < duration; i++) {
                playedMelody += melody.charAt(i % melody.length());
                // 재생 시간이 악보 길이보다 길 경우 악보를 반복해서 재생하기 위해서
                // i % melody.length()로 index 처리.
            }

            // 재생된 멜로디에서 네오가 기억한 멜로디가 포함되어 있는지 확인
            if (playedMelody.contains(neoMelody)) {
                // 조건에 맞는 음악 중 가장 긴 재생 시간을 가진 음악을 선택
                if (duration > time) {
                    answer = arr[2];
                    time = duration;
                }
            }
        }

        // 일치하는 음악이 없는 경우 "(None)"을 반환
        if (answer.isEmpty())
            answer = "(None)";

        return answer;
    }

    // 악보와 멜로디에서 '#'이 붙은 음을 처리하기 위한 메서드
    private static String converting(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            // '#'이 붙은 음을 발견한 경우, 이를 매핑된 소문자 알파벳으로 변환
            if (i < s.length() - 1 && s.charAt(i + 1) == '#') {
                str += hm.get(s.substring(i, i + 2));
                i++; // '#'을 처리했으므로 인덱스를 1 증가시켜 건너뛰기
            } else
                str += s.charAt(i); // 일반 음은 그대로 추가
        }
        return str;
    }

    public static void main(String[] args) {
        String m1 = "ABCDEFG";
        String[] musicinfos1 = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m1, musicinfos1)); // HELLO

        String m2 = "CC#BCC#BCC#BCC#B";
        String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        System.out.println(solution(m2, musicinfos2)); // FOO

        String m3 = "ABC";
        String[] musicinfos3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m3, musicinfos3)); // WORLD
    }
}
