package ECOO.e16.r1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1 {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] percentage=new int[4];
        repeat(10,()->{
            for (int i=0;i<4;i++){
                percentage[i]=nextInt();
            }
            int passNum = 0;

            int repeatTime=nextInt();
            for (int i=0;i<repeatTime;i++){
                float totalMark = 0;
                for (int ii=0;ii<4;ii++){
                    totalMark+=nextFloat()*percentage[ii]/100f;
                }
                if (totalMark>=50){
                    passNum++;
                }
            }
            println(passNum);
        });
    }

    static String next() {
        return input.next();
    }

    static int nextInt() {
        return input.nextInt();
    }

    static float nextFloat() {
        return input.nextFloat();
    }

    static void repeat(int time, Action runnable) {
        for (int i = 0; i < time; i++) {
            runnable.run();
        }
    }

    interface Action {
        void run();
    }

    static void print(Object o) {
        System.out.print(o);
    }

    static void println(Object o) {
        System.out.println(o);
    }

    static <E> String list2String(List<E> list, String start, String separate, String end) {
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i != list.size() - 1) {
                sb.append(separate);
            }
        }
        sb.append(end);
        return sb.toString();
    }
}
