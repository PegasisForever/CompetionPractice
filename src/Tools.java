
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public class Tools {
    static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int peek() {
            if (numChars == -1) {
                return -1;
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    return -1;
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private String readLine0() {
            StringBuilder buf = new StringBuilder();
            int c = read();
            while (c != '\n' && c != -1) {
                if (c != '\r') {
                    buf.appendCodePoint(c);
                }
                c = read();
            }
            return buf.toString();
        }

        public String readLine() {
            String s = readLine0();
            while (s.trim().length() == 0) {
                s = readLine0();
            }
            return s;
        }

        public String readLine(boolean ignoreEmptyLines) {
            if (ignoreEmptyLines) {
                return readLine();
            } else {
                return readLine0();
            }
        }

        public BigInteger readBigInteger() {
            try {
                return new BigInteger(nextString());
            } catch (NumberFormatException e) {
                throw new InputMismatchException();
            }
        }

        public char nextCharacter() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return (char) c;
        }

        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, nextInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, nextInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public boolean isExhausted() {
            int value;
            while (isSpaceChar(value = peek()) && value != -1) {
                read();
            }
            return value == -1;
        }

        public String next() {
            return nextString();
        }

        public SpaceCharFilter getFilter() {
            return filter;
        }

        public void setFilter(SpaceCharFilter filter) {
            this.filter = filter;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }

        public int[] nextIntArray(int length) {
            int[] array = new int[length];
            for (int i = 0; i < length; ++i) array[i] = nextInt();
            return array;
        }

        public int[] nextSortedIntArray(int length) {
            int[] array = nextIntArray(length);
            Arrays.sort(array);
            return array;
        }

        public int[] nextSumIntArray(int length) {
            int[] array = new int[length];
            array[0] = nextInt();
            for (int i = 1; i < length; ++i) array[i] = array[i - 1] + nextInt();
            return array;
        }

        public long[] nextLongArray(int length) {
            long[] array = new long[length];
            for (int i = 0; i < length; ++i) array[i] = nextLong();
            return array;
        }

        public long[] nextSumLongArray(int length) {
            long[] array = new long[length];
            array[0] = nextInt();
            for (int i = 1; i < length; ++i) array[i] = array[i - 1] + nextInt();
            return array;
        }

        public long[] nextSortedLongArray(int length) {
            long[] array = nextLongArray(length);
            Arrays.sort(array);
            return array;
        }

        public int[][] nextIntMatrix(int width, int height) {
            int[][] matrix = new int[width][height];
            for (int y = 0; y < height; ++y)
                for (int x = 0; x < width; ++x)
                    matrix[x][y] = nextInt();
            return matrix;
        }

        public int[][] nextIntMatrix(int n) {
            return nextIntMatrix(n, n);
        }

        public long[][] nextLongMatrix(int width, int height) {
            long[][] matrix = new long[width][height];
            for (int y = 0; y < height; ++y)
                for (int x = 0; x < width; ++x)
                    matrix[x][y] = nextLong();
            return matrix;
        }

        public long[][] nextLongMatrix(int n) {
            return nextLongMatrix(n, n);
        }

        public char[][] nextCharMatrix(int width, int height) {
            char[][] matrix = new char[width][height];
            for (int y = 0; y < height; ++y)
                for (int x = 0; x < width; ++x)
                    matrix[x][y] = nextCharacter();
            return matrix;
        }

        public char[][] nextCharMatrix(int n) {
            return nextCharMatrix(n, n);
        }
    }

    static class IntPair {
        public final int x;
        public final int y;

        public IntPair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            IntPair casted = (IntPair) o;
            return Objects.equals(x, casted.x) &&
                    Objects.equals(y, casted.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Pair<A, B> {
        public final A first;
        public final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            Pair<A, B> casted = (Pair<A, B>) o;
            return Objects.equals(first, casted.first) &&
                    Objects.equals(second, casted.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    static class LongPair {
        public final long x;
        public final long y;

        public LongPair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            LongPair casted = (LongPair) o;
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
        LongPair get(int i, int j) {
            if (i == 0) {
                return new LongPair(h[j], h2[j]);
            } else {
                long v1 = (h[j] - h[i - 1] * p[j - (i - 1)] % mod + mod) % mod;
                long v2 = (h2[j] - h2[i - 1] * p2[j - (i - 1)] % mod2 + mod2) % mod2;
                return new LongPair(v1, v2);
            }
        }
    }

    // max: inclusive
    static int[] getPrimes(int max, int primeCount) {
        boolean[] notPrime = new boolean[max + 1];
        for (int num = 2; num <= max; num++) {
            if (notPrime[num]) continue;
            for (int factors = num * 2; factors <= max; factors += num) {
                notPrime[factors] = true;
            }
        }
        int[] primes = new int[primeCount];
        int i = 0;
        for (int number = 2; number <= max; number++) {
            if (!notPrime[number]) {
                primes[i] = number;
                i++;
            }
        }

        return primes;
    }

    static void rotate90Clockwise(int[][] m, int width) {
        for (int i = 0; i < width / 2; i++) {
            for (int j = i; j < width - i - 1; j++) {
                int temp = m[i][j];
                m[i][j] = m[width - 1 - j][i];
                m[width - 1 - j][i] = m[width - 1 - i][width - 1 - j];
                m[width - 1 - i][width - 1 - j] = m[j][width - 1 - i];
                m[j][width - 1 - i] = temp;
            }
        }
    }

    static String matrixToString(int[][] m, int width, int height) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(m[x][y]);
                if (x == height - 1) {
                    sb.append('\n');
                } else {
                    sb.append(' ');
                }
            }
        }
        return sb.toString();
    }

    static class DisjointSets {
        int[] depth, parent;
        int n;

        public DisjointSets(int n) {
            depth = new int[n];
            parent = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // Returns representative of x's set
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Unites the set that includes x and the set
        // that includes x
        void union(int x, int y) {
            int xRoot = find(x), yRoot = find(y);
            if (xRoot == yRoot) return;

            if (depth[xRoot] < depth[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (depth[yRoot] < depth[xRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                depth[xRoot] = depth[xRoot] + 1;
            }
        }
    }

    interface CountLambda {
        void call(int[] nums);
    }

    void count(int digit, int base, CountLambda action) {
        int[] nums = new int[digit];
        int count = (int) Math.pow(base, digit) - 1;
        for (int i = 0; i < count; i++) {
            action.call(nums);
            nums[0]++;

            int cursor = 0;
            while (nums[cursor] >= base) {
                nums[cursor] = 0;
                nums[cursor + 1]++;
                cursor++;
            }
        }
        action.call(nums);
    }
}
