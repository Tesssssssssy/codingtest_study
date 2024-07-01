package week9.lim.n_9996;

// 문제: https://www.acmicpc.net/problem/9996

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String pattern = br.readLine();

        // 중요: 별표 '*'의 위치를 찾고, 패턴을 두 부분으로 나눔
        int starIndex = pattern.indexOf('*');
        String startPattern = pattern.substring(0, starIndex);
        String endPattern = pattern.substring(starIndex + 1);

        String[] isCorrect = new String[N];

        for (int i = 0; i < N; i++) {
            String fileName = br.readLine();
            if (fileName.startsWith(startPattern) && fileName.endsWith(endPattern)
                    && fileName.length() >= startPattern.length() + endPattern.length()) {
                    // 길이 비교하는 조건 없으면 틀림!!
                isCorrect[i] = "DA";
            } else {
                isCorrect[i] = "NE";
            }
        }

        for (String str : isCorrect) {
            System.out.println(str);
        }
    }
}
/*
    <패턴 비교 시 길이 비교를 하는 이유>
    패턴에서 별표(*)를 사용할 때, 그 위치의 문자열은 0개 이상의 문자들로 대체될 수 있다.
    이 별표가 나타나는 위치에 따라 패턴이 어떻게 파일 이름과 일치해야 하는지 달라진다.
    별표가 파일 이름 중간의 어느 위치에 있든, 패턴의 시작 부분과 끝 부분이 일치해야 하며,
    중간의 *에 해당하는 부분은 그 길이가 가변적일 수 있다.

    길이 비교를 하는 이유:
    - 패턴 일치 확인:
        패턴의 시작 부분과 끝 부분이 파일 이름의 알맞은 위치에 있는지를 확인하기 위해서다.
        예를 들어 패턴이 a*d 이고, 파일 이름이 abcd라면, 시작 부분 a는 파일 이름의 시작에,
        끝 부분 d는 파일 이름의 끝에 위치해야 한다. 이 경우, *가 대체할 수 있는 부분은 bc가 된다.

    - 길이 제약:
        파일 이름의 길이가 패턴의 시작 부분과 끝 부분의 문자들을 합친 길이보다 짧은 경우,
        파일 이름은 패턴과 일치할 수 없다.
        이는 *가 빈 문자열을 포함하여 그 사이에 어떤 문자도 포함하지 않을 수 있음을 의미하므로,
        최소 길이는 시작 부분과 끝 부분의 길이 합과 같거나 그보다 커야 한다.

    길이 조건이 없다면, 시작 부분과 끝 부분이 올바르게 위치하더라도
    전체 길이가 부족하여 실제로는 패턴을 충족하지 못하는 경우에도 일치하는 것으로 잘못 판단될 수 있다.
    예를 들어, 패턴이 abc*xyz이고 파일 이름이 abcxyz인 경우, 길이 조건 없이는 일치하는 것으로 보일 수 있지만,
    실제로는 *가 대체할 문자열의 공간이 없으므로 일치하지 않아야 한다.
    따라서 길이 검증을 통해 이러한 경우를 올바르게 거를 수 있다.
*/