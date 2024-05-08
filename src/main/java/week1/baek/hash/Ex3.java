package week1.baek.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Ex3 {
    public Boolean solution(String[] phone_book) {
        //오름차순 정렬
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length-1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        return true;
    }
}




class Ex3Main {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        String[] phone_book = {"119", "97674223", "1195524421"};

        System.out.println(ex3.solution(phone_book));

    }
}