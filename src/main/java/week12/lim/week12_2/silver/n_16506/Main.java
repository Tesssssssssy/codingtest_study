package week12.lim.week12_2.silver.n_16506;

import java.io.*;
import java.util.*;

public class Main {
    /**
     *  - ex.)
     *    두 수의 합을 구하는 연산의 어셈블리어 코드가 ADD,
     *    기계어 코드가 00000이면 어셈블러는 ADD 를 읽어서 그대로 00000로 바꾸어주는 것
     *  - 어셈블리어 코드는 "opcode rD rA rB" 또는 "opcode rD rA #C"의 형태
     *  - 레지스터 rA와 rB에 있는 두 수 또는 레지스터 rA에 있는 수와 상수 #C를 opcode 에 해당하는 연산을 수행하고,
     *    그 결괏값을 레지스터 rD에 저장하는 명령어
     *  - rA는 opcode 에 따라 사용하지 않을 수도 있다.
     *    어셈블러는 opcode, rD, rA, rB, #C를
     *    각 bit 의 자리에 맞게 2진수 0과 1로 이루어진 16-bit 기계어 코드로 변역
     *
     *  - 0~4 : CPU 가 수행해야 할 연산을 나타내는 opcode.
     *          만약 4번 bit 가 0일 경우 레지스터 rB를, 1일 경우 상수 #C를 사용.
     *    5 : 사용하지 않는 bit 이며, 항상 0.
     *    6~8 : 결괏값을 저장하는 레지스터 rD의 번호.
     *    9~11 : 연산에 사용되는 레지스터 rA의 번호.
     *           사용하지 않을 경우에는 000.
     *    12~15 : 만약 4번 bit 가 0일 경우 12~14번 bit 는 연산에 사용되는 레지스터 rB의 번호,
     *            15번 bit 는 항상 0.
     *            만약 4번 bit 가 1일 경우 12~15번 bit 는 상수 #C.
     *
     *  [입력]
     *  명령어의 개수 N
     *  opcode rD rA rB" 또는 "opcode rD rA #C"의 형태로 주어짐.
     *  (문자열 opcode 는 항상 대문자)
     *  (정수 rD, rA, rB (0 ≤ rD, rA, rB ≤ 7)는 레지스터 번호를 의미)
     *  (사용하는 레지스터 번호는 1부터 7까지이며, 사용하지 않을 경우에만 0이 주어짐)
     *
     *  [출력]
     *  N개의 각 줄에 어셈블리어 코드를 기계어 코드로 번역하여 출력
     */
    public static void main(String[] args) throws IOException {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("ADD", "0000");
        hm.put("SUB", "0001");
        hm.put("MOV", "0010");
        hm.put("AND", "0011");
        hm.put("OR", "0100");
        hm.put("NOT", "0101");
        hm.put("MULT", "0110");
        hm.put("LSFTL", "0111");
        hm.put("LSFTR", "1000");
        hm.put("ASFTR", "1001");
        hm.put("RL", "1010");
        hm.put("RR", "1011");
        // 어셈블리어 명령어(opcode)에 대응하는 기계어 코드를 저장하기 위해 HashMap 사용

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String opcode = st.nextToken(); // opcode
            int rD = Integer.parseInt(st.nextToken()); // 레지스터 rD
            int rA = Integer.parseInt(st.nextToken()); // 레지스터 rA
            int rB = Integer.parseInt(st.nextToken()); // 레지스터 rB

            if (opcode.charAt(opcode.length() - 1) == 'C') {
                /*
                    입력받은 opcode 의 마지막 문자가 'C' 인지 확인.
                    'C'는 주로 상수를 사용하는 명령어에서 끝에 붙는 표시.
                    ex.) "MOVC"는 MOVE Constant 의 약자로, 이 명령어는 레지스터 대신 상수 값을 사용한다는 의미
                 */

                sb.append(hm.get(opcode.substring(0, opcode.length() - 1))).append("1");
                /*
                    만약 opcode 가 'C'로 끝난다면, 이는 상수를 사용하는 명령어임을 의미.
                    opcode.substring(0, opcode.length() - 1)는 'C'를 제외한 실제 명령어를 추출.
                    예를 들어 "MOVC"에서 "MOV"를 추출.
                    추출된 명령어를 HashMap 에서 조회하여 해당하는 기계어 코드를 StringBuilder 에 추가.
                    이후 기계어 코드에서 상수 사용을 의미하는 플래그로 '1'을 추가
                    이 '1'은 기계어 코드의 특정 비트 위치(4번 비트)에 설정
                */
            } else {
                sb.append(hm.get(opcode)).append("0");
                /*
                    만약 opcode 의 마지막 문자가 'C'가 아니라면, 이는 레지스터를 사용하는 명령어.
                    opcode 에 해당하는 기계어 코드를 HashMap 에서 직접 조회하여 StringBuilder 에 추가.
                    기계어 코드에서 레지스터 사용을 나타내는 플래그로 '0'을 추가.
                    이 '0'도 기계어 코드의 4번 비트 위치에 설정.
                */
            }

            sb.append("0"); // 여분의 비트는 항상 0으로 설정

            String binary = Integer.toBinaryString(rD);
            sb.append(append(0, binary, 2));
            /*
                정수 값을 이진 문자열로 변환하고,
                필요한 길이만큼 앞에 0을 붙여 주는 append 메서드를 호출하여 정확한 비트 수를 맞춘다.

                depth
                    재귀 함수의 깊이를 추적.
                    초기 호출에서는 0으로 설정되며, 각 재귀 호출에서 1씩 증가.
                limit
                    결과 문자열이 도달해야 하는 최종 길이를 지정.
                    이 경우 2로 설정되어 있으므로,
                    함수는 입력된 문자열 binary 가 두 자리 이진수가 되도록 앞에 '0'을 붙여야 함.

                    limit = 2로 설정된 이유는 레지스터 번호 rD, rA, rB가 상황에 따라 2비트 또는 3비트를 사용하는 경우에 맞추어진 것.
                    rD와 rA에 대해 2로 설정된 경우, 이는 해당 레지스터 번호를 나타내는 비트가 일반적으로 3비트가 필요한 상황에서
                    코드의 특정 사용 방식 또는 테스트 환경에 따라 최소화되어 사용될 수 있음을 의미.
            */

            if (opcode.equals("NOT") || opcode.equals("MOV") || opcode.equals("MOVC")) {
                /*
                    특정 명령어("NOT", "MOV", "MOVC")는 rA를 사용하지 않는다.
                    이 경우, 해당하는 비트 위치(9~11)에 '000'을 삽입하여 이를 기계어 코드에 명시
                */
                sb.append("000");
            } else {
                /*
                    "NOT", "MOV", "MOVC" 이외의 명령어는 rA를 사용하므로, rA의 값을 이진수로 변환하고,
                    append 함수를 통해 이진수 길이를 2비트로 맞춘다.
                    이렇게 맞춘 값을 sb(StringBuilder 객체)에 추가.
                */
                String binary1 = Integer.toBinaryString(rA);
                sb.append(append(0, binary1, 2));
            }

            String binary2 = Integer.toBinaryString(rB);
            // String binary2: rB의 값을 이진수 문자열로 변환. rB는 명령어에 따라 레지스터 번호 또는 상수값을 포함할 수 있다.

            if (opcode.charAt(opcode.length() - 1) == 'C') {
                /*
                    명령어의 마지막 글자가 'C'일 경우(예: "MOVC") 상수를 사용함 의미,
                    -> 4비트 필요 (limit을 3으로 설정하여 재귀적으로 3비트 길이의 이진 문자열을 생성).
                */
                sb.append(append(0, binary2, 3));
            } else {
                /*
                    rB는 레지스터 번호로 처리되며, 이를 3비트 길이로 맞춘다.
                    여기서는 append(0, binary2, 2)를 호출해 2비트 길이의 이진수를 만들고,
                    추가적으로 '0'을 끝에 붙여 3비트를 완성.
                */
                sb.append(append(0, binary2, 2)).append("0");
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static String append(int depth, String str, int limit) {
        /*
            (자리수를 맞춰주기 위해서 dfs 를 사용)
            이 메소드에서 주어진 문자열 str 의 길이가 limit 에 도달할 때까지 재귀적으로 문자열 앞에 0을 추가.
            depth 는 현재 재귀의 깊이를 나타내며,
            최대 길이 limit 에 도달하거나 이미 limit 이상인 경우 재귀를 종료하고 문자열을 반환
        */

        if (depth >= limit || limit < str.length()) {
            // limit을 str 길이가 넘어가면 (이미 bit 자릿수 채운 것)
            // 0을 앞에 채우지 않고 바로 str 반환
            return str;
        }
        return append(depth + 1, "0" + str, limit);
    }
}
