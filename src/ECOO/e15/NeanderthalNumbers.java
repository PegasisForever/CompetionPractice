package ECOO.e15;

import java.util.*;
import java.io.*;

public class NeanderthalNumbers {
    static BufferedReader br;
    static char[] S;
    static HashSet<String> set;
    static int total;
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        br = new BufferedReader(new InputStreamReader(System.in));
        set = new HashSet<>();
        set.add("ook");
        set.add("ookook");
        set.add("oog");
        set.add("ooga");
        set.add("ug");
        set.add("mook");
        set.add("mookmook");
        set.add("oogam");
        set.add("oogum");
        set.add("ugug");
        String[] token = null;
        for (int x =0; x < 10; x++) {
            S = br.readLine().toCharArray();
            total = 0;
            recur(new StringBuilder(), 0);
            System.out.println(total);
        }
    }
    public static void recur(StringBuilder s, int ind) {
        boolean check = false;
        for (int x = ind; x < S.length; x++) { check = false;
            s.append(S[x]);
            if (set.contains(s.toString())) {
                //  System.out.println(s.toString() + " " + x);
                recur(new StringBuilder(), x+1);
                check = true;
            }
        }
        if (check) total++;
    }

    static int toInt(String s) {
        return Integer.parseInt(s);
    }

    static String[] split(String s) {
        return s.split(" ");
    }

    static String[] read() throws IOException {
        return split(br.readLine());
    }

    static int rInt() throws IOException {
        return toInt(br.readLine());
    }

    static int[] rIntAr() throws IOException {
        String[] temp = read();
        int[] t = new int[temp.length];
        for (int x = 0; x < temp.length; x++) {
            t[x] = toInt(temp[x]);
        }
        return t;
    }
}