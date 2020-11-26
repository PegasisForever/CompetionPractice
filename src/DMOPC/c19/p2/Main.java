package DMOPC.c19.p2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int action = nextInt();
        int healthp = nextInt();
        int[] h = new int[2];
        h[0] = healthp;
        h[1] = healthp;
        String[] a = new String[action * 2];
        int[] d = new int[action * 2];
        for (int i = 0; i < action; i++) {
            a[i * 2] = next();
            d[i * 2] = nextInt();
        }
        for (int i = 0; i < action; i++) {
            a[i * 2 + 1] = next();
            d[i * 2 + 1] = nextInt();
        }
        for (int i = 0; i < action * 2; i++) {
            if (i == 0) {
                if (a[i].equals("A")) {
                    if (i % 2 == 0) {
                        h[i % 2 + 1] = h[i % 2 + 1] - d[i];
                    } else {
                        h[i % 2 - 1] = h[i % 2 - 1] - d[i];
                    }
                } else if (a[i].equals("D") && !a[i + 1].equals("A")) {
                    h[i % 2] = h[i % 2] - d[i];
                }
            } else if (i == action * 2 - 1) {
                if (a[i].equals("A") && !a[i - 1].equals("D")) {
                    if (i % 2 == 0) {
                        h[i % 2 + 1] = h[i % 2 + 1] - d[i];
                    } else {
                        h[i % 2 - 1] = h[i % 2 - 1] - d[i];
                    }
                } else if (a[i].equals("D")) {
                    h[i % 2] = h[i % 2] - d[i];
                }
            } else {
                if (a[i].equals("A") && !a[i - 1].equals("D")) {
                    if (i % 2 == 0) {
                        h[i % 2 + 1] = h[i % 2 + 1] - d[i];
                    } else {
                        h[i % 2 - 1] = h[i % 2 - 1] - d[i];
                    }
                } else if (a[i].equals("D") && !a[i + 1].equals("A")) {
                    h[i % 2] = h[i % 2] - d[i];
                }
            }
            if (h[0] <= 0) {
                System.out.println("DEFEAT");
                break;
            } else if (h[1] <= 0) {
                System.out.println("VICTORY");
                break;
            } else if (i == action * 2 - 1) {
                System.out.println("TIE");
                break;
            }
        }

    }

    static Scanner scanner = new Scanner(System.in);

    static int nextInt() {
        return scanner.nextInt();
    }

    static String next() {
        return scanner.next();
    }
}
