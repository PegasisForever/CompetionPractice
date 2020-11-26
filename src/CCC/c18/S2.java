package CCC.c18;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class S2 {
    static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

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
            while (isWhitespace(c)) {
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
            } while (!isWhitespace(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isWhitespace(c)) {
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
            } while (!isWhitespace(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isWhitespace(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isWhitespace(c));
            return res.toString();
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
            while (isWhitespace(c)) {
                c = read();
            }
            return (char) c;
        }

        public double nextDouble() {
            int c = read();
            while (isWhitespace(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isWhitespace(c) && c != '.') {
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
                while (!isWhitespace(c)) {
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
            while (isWhitespace(value = peek()) && value != -1) {
                read();
            }
            return value == -1;
        }

        public String next() {
            return nextString();
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

    public static void main(String[] args) {
        InputReader s = new InputReader(System.in);
        int width = s.nextInt();
        int[][] table = s.nextIntMatrix(width);

        while (!(table[0][0] < table[0][1] && table[0][0] < table[1][0])) {
            rotate90Clockwise(table, width);
        }

        System.out.println(matrixToString(table, width, width));
    }
}

