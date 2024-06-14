package week4.baek.dfsbfs;

public class Word {
    static int answer;
    static boolean[] visit;

    public static void dfs(String begin, String target, String[] words, int count) {
        if (begin.equals(target)) {
            answer = count;
            return;
        }

        //단어를 하나하나 체크한다.
        //아직 visit처리가 안됐고, 알파벳 한개만 다를 때 (변경 가능) 변경해준다.
        for (int i = 0; i< words.length; i++) {

            if (visit[i] == true)
                continue;

            String word = words[i];
            int notEqual = 0;

            for (int c = 0; c<begin.length(); c++) {
                if (begin.charAt(c) != word.charAt(c)) {
                    notEqual += 1;
                }
            }

            //만약 1개만 다르면 변경 가능한 것
            if (notEqual == 1) {
                visit[i] = true;
                dfs(word, target, words, count+1);
                visit[i] = false;
            }
        }
    }

    public static int cal(String x, String y){
        int count = 0;
        for (int i = 0; i < x.length(); i++) {
            if(x.charAt(i) == y.charAt(i))
                count++;
        }
        return count;
    }

    public static int solution(String begin, String target, String[] words) {
        visit = new boolean[words.length];

        dfs(begin, target, words, 0);

        return answer;
    }

    public static void main(String[] args) {
        String begin ="hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution(begin, target, words));
    }
}
