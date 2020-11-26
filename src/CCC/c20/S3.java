package CCC.c20;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class S3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] needle = scan.nextLine().toCharArray();
        char[] haystack = scan.nextLine().toCharArray();

        if (haystack.length < needle.length) {
            System.out.println("0");
            return;
        }

        HashSet<Pair> foundSet = new HashSet<>();
        int[] needleMap = new int[26];
        int[] haystackMap = new int[26];
        int sameCount = 0;
        PolyHash hasher = new PolyHash(haystack);

        for (int i = 0; i < needle.length; i++) {
            needleMap[needle[i] - 97]++;
            haystackMap[haystack[i] - 97]++;
        }
        for (int i = 0; i < 26; i++) {
            if (needleMap[i] == haystackMap[i]) {
                sameCount++;
            }
        }

        if (sameCount == 26) {
            foundSet.add(hasher.get(0, needle.length - 1));
        }
        for (int i = needle.length; i < haystack.length; i++) {
            int addI = haystack[i] - 97;
            int removeI = haystack[i - needle.length] - 97;

            if (addI != removeI) {
                if (needleMap[addI] == haystackMap[addI]) {
                    haystackMap[addI]++;
                    sameCount--;
                } else {
                    haystackMap[addI]++;
                    if (needleMap[addI] == haystackMap[addI]) {
                        sameCount++;
                    }
                }
                if (needleMap[removeI] == haystackMap[removeI]) {
                    haystackMap[removeI]--;
                    sameCount--;
                } else {
                    haystackMap[removeI]--;
                    if (needleMap[removeI] == haystackMap[removeI]) {
                        sameCount++;
                    }
                }
            }

            if (sameCount == 26) {
                foundSet.add(hasher.get(i - needle.length + 1, i));
            }
        }

        System.out.println(foundSet.size());
    }

    static class Pair {
        public final long x;
        public final long y;

        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Pair casted = (Pair) o;
            return Objects.equals(x, casted.x) &&
                    Objects.equals(y, casted.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class PolyHash {
        final static long mod = 972663749L;
        final static long mod2 = 972663749L;
        final static long base = 31L;
        final static long base2 = 131L;

        long[] p;
        long[] p2;
        long[] h;
        long[] h2;

        PolyHash(char[] str) {
            p = new long[str.length + 1];
            p2 = new long[str.length + 1];
            h = new long[str.length + 1];
            h2 = new long[str.length + 1];
            p[0] = 1;
            p2[0] = 1;

            int n = str.length;
            for (int i = 1; i <= n; i++) {
                p[i] = p[i - 1] * base % mod;
                p2[i] = p2[i - 1] * base2 % mod2;
            }

            h[0] = str[0] - 'a' + 1;
            h2[0] = str[0] - 'a' + 1;

            for (int i = 1; i < n; i++) {
                h[i] = (h[i - 1] * base % mod + (str[i] - 'a' + 1)) % mod;
                h2[i] = (h2[i - 1] * base2 % mod2 + (str[i] - 'a' + 1)) % mod2;
            }
        }

        // inclusive
        Pair get(int i, int j) {
            if (i == 0) {
                return new Pair(h[j], h2[j]);
            } else {
                long v1 = (h[j] - h[i - 1] * p[j - (i - 1)] % mod + mod) % mod;
                long v2 = (h2[j] - h2[i - 1] * p2[j - (i - 1)] % mod2 + mod2) % mod2;
                return new Pair(v1, v2);
            }
        }
    }
}
