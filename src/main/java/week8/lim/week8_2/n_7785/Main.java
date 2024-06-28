package week8.lim.week8_2.n_7785;

// 문제: https://www.acmicpc.net/problem/25206

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> employees = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String[] log = br.readLine().split(" ");
            String name = log[0];
            String action = log[1];

            if (action.equals("enter")) {
                employees.add(name);
            } else if (action.equals("leave")) {
                employees.remove(name);
            }
        }

        // HashSet을 List로 변환
        List<String> employeeList = new ArrayList<>(employees);
        // 역순으로 정렬
        Collections.sort(employeeList, Collections.reverseOrder());

        for (String name : employeeList) {
            System.out.println(name);
        }
    }
}