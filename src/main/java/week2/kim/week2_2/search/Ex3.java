package week2.kim.week2_2.search;

import java.util.ArrayList;

/**
 * [모음사전]
 *
 * 사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다.
 * 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
 *
 * 단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * word의 길이는 1 이상 5 이하입니다.
 * word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
 *
 * 입출력 예
 * word		result
 * "AAAAE"	6
 * "AAAE"	10
 * "I"		1563
 * "EIO"	1189
 *
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다.
 * "AAAAE"는 사전에서 6번째 단어입니다.
 *
 * 입출력 예 #2
 *
 * "AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인 10번째 단어입니다.
 *
 * 입출력 예 #3
 *
 * "I"는 1563번째 단어입니다.
 *
 * 입출력 예 #4
 *
 * "EIO"는 1189번째 단어입니다.
*/

public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();

        String word = "EIO";

        // 1189 반환
        System.out.println(ex3.solution(word));
    }

    // 알파벳 모음을 저장하는 배열
    String[] vowels = {"A", "E", "I", "O", "U"};
    // 사전을 저장하는 리스트
    ArrayList<String> dictionary = new ArrayList<>();

    public int solution(String word) {
        int answer = 0;

        // 모든 가능한 단어를 생성하여 사전 생성
        makeDict("", 0);

        // 사전에서 주어진 단어의 위치를 찾아 반환
        answer = dictionary.indexOf(word);

        return answer;
    }

    public void makeDict(String str, int orders) {
        // 단어의 길이가 5를 초과하면 더 이상 단어를 추가하지 않음
        if (orders > 5)
            return;

        // 만든 단어를 사전에 추가
        dictionary.add(str);

        // 모음을 하나씩 추가하며 새로운 단어를 생성
        for (int i = 0; i < 5; i++) {
            // 현재 단어에 모음을 하나 추가하고 > 재귀 호출
            makeDict(str + vowels[i], orders + 1);
        }
    }
}