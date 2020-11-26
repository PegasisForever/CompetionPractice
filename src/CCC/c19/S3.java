package CCC.c19;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

// TODO
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

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
        }

        public int[] nextSortedIntArray(int n) {
            int array[] = nextIntArray(n);
            Arrays.sort(array);
            return array;
        }

        public int[] nextSumIntArray(int n) {
            int[] array = new int[n];
            array[0] = nextInt();
            for (int i = 1; i < n; ++i) array[i] = array[i - 1] + nextInt();
            return array;
        }

        public long[] nextLongArray(int n) {
            long[] array = new long[n];
            for (int i = 0; i < n; ++i) array[i] = nextLong();
            return array;
        }

        public long[] nextSumLongArray(int n) {
            long[] array = new long[n];
            array[0] = nextInt();
            for (int i = 1; i < n; ++i) array[i] = array[i - 1] + nextInt();
            return array;
        }

        public long[] nextSortedLongArray(int n) {
            long array[] = nextLongArray(n);
            Arrays.sort(array);
            return array;
        }

        public int[][] nextIntMatrix(int n, int m) {
            int[][] matrix = new int[n][m];
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m; ++j)
                    matrix[i][j] = nextInt();
            return matrix;
        }

        public int[][] nextIntMatrix(int n) {
            return nextIntMatrix(n, n);
        }

        public long[][] nextLongMatrix(int n, int m) {
            long[][] matrix = new long[n][m];
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m; ++j)
                    matrix[i][j] = nextLong();
            return matrix;
        }

        public long[][] nextLongMatrix(int n) {
            return nextLongMatrix(n, n);
        }

        public char[][] nextCharMatrix(int n, int m) {
            char[][] matrix = new char[n][m];
            for (int i = 0; i < n; ++i)
                matrix[i] = next().toCharArray();
            return matrix;
        }

        public char[][] nextCharMatrix(int n) {
            return nextCharMatrix(n, n);
        }
    }

    static class Square {
        int[][] matrix;
        int unknowns = 0;
        int totalRotated = 0;

        Square(InputReader r) {
            matrix = new int[3][3];
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    String number = r.next();
                    if (number.equals("X")) {
                        unknowns++;
                        matrix[x][y] = Integer.MIN_VALUE;
                    } else {
                        matrix[x][y] = Integer.parseInt(number);
                    }
                }
            }
        }

        void rotate() {
            totalRotated++;
            int[][] oldMatrix = matrix;
            matrix = new int[3][3];

            //corners
            matrix[0][0] = oldMatrix[2][0];
            matrix[0][2] = oldMatrix[0][0];
            matrix[2][2] = oldMatrix[0][2];
            matrix[2][0] = oldMatrix[2][2];

            //edges
            matrix[1][0] = oldMatrix[2][1];
            matrix[0][1] = oldMatrix[1][0];
            matrix[1][2] = oldMatrix[0][1];
            matrix[2][1] = oldMatrix[1][2];

            //center
            matrix[1][1] = oldMatrix[1][1];
        }

        boolean matches(boolean[][] pattern) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (pattern[x][y] == (matrix[x][y] == Integer.MIN_VALUE)) {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean rotateMatches(boolean[][] pattern) {
            if (matches(pattern)) return true;
            rotate();
            if (matches(pattern)) return true;
            rotate();
            if (matches(pattern)) return true;
            rotate();
            return matches(pattern);
        }

        @Override
        public String toString() {
            int r = (4 - totalRotated % 4) % 4;
            for (int i = 0; i < r; i++) {
                rotate();
            }
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < 3; y++) {
                sb.append(matrix[0][y]);
                sb.append(' ');
                sb.append(matrix[1][y]);
                sb.append(' ');
                sb.append(matrix[2][y]);
                sb.append('\n');
            }
            return sb.toString();
        }
    }

    // false means X
    // _ _ _
    // _ X X
    // _ X X
    static boolean[][] pattern1 = {
            {true, true, true},
            {true, false, false},
            {true, false, false}
    };
    // X _ X
    // _ X X
    // X X _
    static boolean[][] pattern2 = {
            {false, true, false},
            {true, false, false},
            {false, false, true}
    };
    // X X _
    // X _ X
    // _ X X
    static boolean[][] pattern3 = {
            {false, false, true},
            {false, true, false},
            {true, false, false},
    };
    // _ X X
    // _ X X
    // _ X X
    static boolean[][] pattern4 = {
            {true, true, true},
            {false, false, false},
            {false, false, false}
    };
    // _ X X
    // _ _ _
    // _ X X
    static boolean[][] pattern5 = {
            {true, true, true},
            {false, true, false},
            {false, true, false}
    };
    // X _ X
    // X _ X
    // X _ X
    static boolean[][] pattern6 = {
            {false, false, false},
            {true, true, true},
            {false, false, false}
    };
    // X _ X
    // _ _ _
    // X _ X
    static boolean[][] pattern7 = {
            {false, true, false},
            {true, true, true},
            {false, true, false}
    };

    public static void main(String[] args) {
        InputReader s = new InputReader(System.in);
        Square square = new Square(s);

        out:
        while (square.unknowns > 0) {
            boolean solved = false;
            // try to solve 2 known in a row
            for (int x = 0; x < 3; x++) {
                int unknowns = 0;
                int unknownY = 0;
                for (int y = 0; y < 3; y++) {
                    if (square.matrix[x][y] == Integer.MIN_VALUE) {
                        unknowns++;
                        unknownY = y;
                    }
                }
                if (unknowns == 1) {
                    if (unknownY == 0) {
                        square.matrix[x][0] = square.matrix[x][1] - (square.matrix[x][2] - square.matrix[x][1]);
                    } else if (unknownY == 1) {
                        square.matrix[x][1] = (square.matrix[x][0] + square.matrix[x][2]) >> 1;
                    } else {
                        square.matrix[x][2] = square.matrix[x][1] + (square.matrix[x][1] - square.matrix[x][0]);
                    }
                    square.unknowns--;
                    solved = true;
                }
            }

            // solve 2 known in a column
            for (int y = 0; y < 3; y++) {
                int unknowns = 0;
                int unknownX = 0;
                for (int x = 0; x < 3; x++) {
                    if (square.matrix[x][y] == Integer.MIN_VALUE) {
                        unknowns++;
                        unknownX = x;
                    }
                }

                if (unknowns == 1) {
                    if (unknownX == 0) {
                        square.matrix[0][y] = square.matrix[1][y] - (square.matrix[2][y] - square.matrix[1][y]);
                    } else if (unknownX == 1) {
                        square.matrix[1][y] = (square.matrix[0][y] + square.matrix[2][y]) >> 1;
                    } else {
                        square.matrix[2][y] = square.matrix[1][y] + (square.matrix[1][y] - square.matrix[0][y]);
                    }
                    square.unknowns--;
                    solved = true;
                }
            }

            if (solved) continue;

            if (square.unknowns == 9) {
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        square.matrix[x][y] = 0;
                    }
                }
                square.unknowns = 0;
            } else if (square.unknowns == 8) {
                int onlyOne = 0;
                a:
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        if (square.matrix[x][y] != Integer.MIN_VALUE) {
                            onlyOne = square.matrix[x][y];
                            break a;
                        }
                    }
                }
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        square.matrix[x][y] = onlyOne;
                    }
                }
                square.unknowns = 0;
            } else if (square.unknowns == 7) {
                int y1 = -1;
                int y1Value = 0;
                int y2 = -1;
                int y2Value = 0;
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        if (square.matrix[x][y] != Integer.MIN_VALUE) {
                            if (y1 == -1) {
                                y1 = y;
                                y1Value = square.matrix[x][y];
                            } else {
                                y2 = y;
                                y2Value = square.matrix[x][y];
                            }
                            break;
                        }
                    }
                }

                if (y1 == 0 && y2 == 2) {
                    if ((y1Value + y2Value) % 2 != 0) {
                        square.rotate();
                        continue out;
                    }
                    int middleValue = (y1Value + y2Value) >> 1;
                    for (int x = 0; x < 3; x++) {
                        square.matrix[x][0] = y1Value;
                        square.matrix[x][1] = middleValue;
                        square.matrix[x][2] = y2Value;
                    }
                } else if (y1 == 0 && y2 == 1) {
                    int thirdValue = y2Value + (y2Value - y1Value);
                    for (int x = 0; x < 3; x++) {
                        square.matrix[x][0] = y1Value;
                        square.matrix[x][1] = y2Value;
                        square.matrix[x][2] = thirdValue;
                    }
                } else {
                    int firstValue = y1Value - (y2Value - y1Value);
                    for (int x = 0; x < 3; x++) {
                        square.matrix[x][0] = firstValue;
                        square.matrix[x][1] = y1Value;
                        square.matrix[x][2] = y2Value;
                    }
                }
                square.unknowns = 0;
            } else if (square.rotateMatches(pattern4)) {
                for (int y = 0; y < 3; y++) {
                    square.matrix[1][y] = square.matrix[0][y];
                    square.matrix[2][y] = square.matrix[0][y];
                }
                square.unknowns = 0;
            } else if (square.rotateMatches(pattern3)) {
                if (square.matrix[2][0] % 2 == 0) { // even
                    square.matrix[0][0] = 0;
                } else {
                    square.matrix[0][0] = 1;
                }
                square.unknowns--;
            } else if (square.rotateMatches(pattern6)) {
                for (int y = 0; y < 3; y++) {
                    square.matrix[0][y] = square.matrix[1][y];
                    square.matrix[2][y] = square.matrix[1][y];
                }
                square.unknowns = 0;
            } else if (square.rotateMatches(pattern2)) {
                int d = (square.matrix[2][2] - 2 * square.matrix[0][1] + square.matrix[1][0]) / 3;
                square.matrix[0][0] = square.matrix[1][0] - d;
                square.matrix[2][0] = square.matrix[1][0] + d;
                square.unknowns -= 2;
            } else if (square.rotateMatches(pattern1)) {
                int d = square.matrix[1][0] - square.matrix[0][0];
                square.matrix[1][1] = square.matrix[0][1] + d;
                square.unknowns--;
            } else if (square.rotateMatches(pattern5)) {
                int d = square.matrix[1][1] - square.matrix[0][1];
                square.matrix[1][0] = square.matrix[0][0] + d;
                square.unknowns--;
            } else if (square.rotateMatches(pattern7)) {
                int d = square.matrix[1][1] - square.matrix[0][1];
                square.matrix[0][0] = square.matrix[1][0] - d;
                square.unknowns--;
            } else {
                System.out.println("error: nothing to do");
                System.out.println(square.toString());
                return;
            }
        }

        System.out.println(square.toString());
    }
}

