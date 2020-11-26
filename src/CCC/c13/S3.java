package CCC.c13;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public class S3 {
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

        @Override
        public String toString() {
            return "IntPair{" + x + ", " + y + "}";
        }
    }

    static int myTeam;
    static IntPair[] games = {
            new IntPair(1, 2),
            new IntPair(1, 3),
            new IntPair(1, 4),
            new IntPair(2, 3),
            new IntPair(2, 4),
            new IntPair(3, 4)
    };

    public static void main(String[] args) {
        InputReader s = new InputReader(System.in);
        myTeam = s.nextInt() - 1;
        int gamesPlayedCount = s.nextInt();

        ArrayList<IntPair> gamesPlayed = new ArrayList<>();
        int[] points = new int[4];
        for (int i = 0; i < gamesPlayedCount; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            gamesPlayed.add(new IntPair(a, b));
            int aScore = s.nextInt();
            int bScore = s.nextInt();
            if (aScore > bScore) {
                points[a - 1] += 3;
            } else if (aScore < bScore) {
                points[b - 1] += 3;
            } else {
                points[a - 1]++;
                points[b - 1]++;
            }
        }

        IntPair pair = step(gamesPlayed, points);
        System.out.println(pair.x);
    }

    // win to all
    static IntPair step(ArrayList<IntPair> gamesPlayed, int[] points) {
        if (gamesPlayed.size() == 6) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < 4; i++) {
                max = Math.max(max, points[i]);
            }
            if (max != points[myTeam]) return new IntPair(0, 1);

            int maxCount = 0;
            for (int i = 0; i < 4; i++) {
                if (max == points[i]) maxCount++;
            }

            if (maxCount == 1) {
                return new IntPair(1, 1);
            } else {
                return new IntPair(0, 1);
            }
        }

        IntPair currGame = null;
        for (IntPair game : games) {
            if (!gamesPlayed.contains(game)) {
                currGame = game;
                gamesPlayed.add(game);
                break;
            }
        }

        int win = 0;
        int all = 0;
        // win
        {
            points[currGame.x - 1] += 3;
            IntPair r = step(gamesPlayed, points);
            points[currGame.x - 1] -= 3;
            win += r.x;
            all += r.y;
        }
        // loss
        {
            points[currGame.y - 1] += 3;
            IntPair r = step(gamesPlayed, points);
            points[currGame.y - 1] -= 3;
            win += r.x;
            all += r.y;
        }
        // tie
        {
            points[currGame.x - 1]++;
            points[currGame.y - 1]++;
            IntPair r = step(gamesPlayed, points);
            points[currGame.x - 1]--;
            points[currGame.y - 1]--;
            win += r.x;
            all += r.y;
        }

        gamesPlayed.remove(gamesPlayed.size() - 1);

        return new IntPair(win, all);
    }
}

