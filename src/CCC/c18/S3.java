package CCC.c18;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

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

    static class IntPair implements Comparable<IntPair> {
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
        public int compareTo(IntPair other) {
            if (y != other.y) {
                return y - other.y;
            } else {
                return x - other.x;
            }
        }
    }

    static class Conveyor {
        boolean processed = false;
        boolean visited = false;
        IntPair end;

        Conveyor(int x, int y) {
            this.end = new IntPair(x, y);
        }
    }

    public static void main(String[] args) {
        InputReader s = new InputReader(System.in);
        int height = s.nextInt();
        int width = s.nextInt();
        char[][] map = s.nextCharMatrix(width, height);

        Conveyor[][] conveyors = new Conveyor[width][height];
        TreeMap<IntPair, Integer> target = new TreeMap<>();
        int targetNotFounds = 0;
        boolean[][] camArea = new boolean[width][height];
        IntPair start = null;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                char c = map[x][y];
                if (c == 'U') {
                    conveyors[x][y] = new Conveyor(x, y - 1);
                } else if (c == 'D') {
                    conveyors[x][y] = new Conveyor(x, y + 1);
                } else if (c == 'L') {
                    conveyors[x][y] = new Conveyor(x - 1, y);
                } else if (c == 'R') {
                    conveyors[x][y] = new Conveyor(x + 1, y);
                } else if (c == 'S') {
                    start = new IntPair(x, y);
                } else if (c == '.') {
                    target.put(new IntPair(x, y), -1);
                    targetNotFounds++;
                } else if (c == 'C') {
                    camArea[x][y] = true;
                    for (int xx = x - 1; xx >= 0; xx--) {
                        char cc = map[xx][y];
                        if (cc == 'W' || cc == 'C') break;
                        if (cc == '.' || cc == 'S') {
                            camArea[xx][y] = true;
                        }
                    }
                    for (int xx = x + 1; xx < width; xx++) {
                        char cc = map[xx][y];
                        if (cc == 'W' || cc == 'C') break;
                        if (cc == '.' || cc == 'S') {
                            camArea[xx][y] = true;
                        }
                    }
                    for (int yy = y - 1; yy >= 0; yy--) {
                        char cc = map[x][yy];
                        if (cc == 'W' || cc == 'C') break;
                        if (cc == '.' || cc == 'S') {
                            camArea[x][yy] = true;
                        }
                    }
                    for (int yy = y + 1; yy < height; yy++) {
                        char cc = map[x][yy];
                        if (cc == 'W' || cc == 'C') break;
                        if (cc == '.' || cc == 'S') {
                            camArea[x][yy] = true;
                        }
                    }
                }
            }
        }
        for (Conveyor[] column : conveyors) {
            for (Conveyor conveyor : column) {
                if (conveyor == null || conveyor.processed) continue;
                ArrayList<Conveyor> path = new ArrayList<>();
                conveyor.visited = true;
                path.add(conveyor);
                Conveyor next = conveyor;
                boolean loop = false;
                while ((next = conveyors[next.end.x][next.end.y]) != null) {
                    if (next.visited) {
                        loop = true;
                        break;
                    }
                    next.visited = true;
                    path.add(next);
                    if (next.processed){
                        break;
                    }
                }

                if (loop) {
                    for (Conveyor cInPath : path) {
                        cInPath.visited = false;
                        cInPath.processed = true;
                        cInPath.end = null;
                    }
                } else {
                    Conveyor last = path.get(path.size() - 1);
                    for (Conveyor cInPath : path) {
                        cInPath.visited = false;
                        cInPath.processed = true;
                        cInPath.end = last.end;
                    }
                }
            }
        }

        ArrayDeque<IntPair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[width][height];
        int currStep = 0;
        queue.add(start);
        while (!queue.isEmpty() && targetNotFounds > 0) {
            int size = queue.size();
            for (int i = 0; i < size && targetNotFounds > 0; i++) {
                IntPair pos = queue.poll();
                if (pos == null) continue;
                int x = pos.x;
                int y = pos.y;
                if (x < 0 || x >= width || y < 0 || y >= height) continue;
                if (visited[x][y]) continue;
                visited[x][y] = true;
                if (camArea[x][y]) continue;
                if (map[x][y] == 'W') continue;
                // is . or S here
                if (map[x][y] == '.') {
                    target.put(pos, currStep);
                    targetNotFounds--;
                }

                tryConveyor(x - 1, y, conveyors, queue);
                tryConveyor(x + 1, y, conveyors, queue);
                tryConveyor(x, y + 1, conveyors, queue);
                tryConveyor(x, y - 1, conveyors, queue);
            }
            currStep++;
        }

        for (int step : target.values()) {
            System.out.println(step);
        }
    }

    static void tryConveyor(int x, int y, Conveyor[][] conveyors, ArrayDeque<IntPair> queue) {
        Conveyor conveyor = conveyors[x][y];
        if (conveyor != null) {
            if (conveyor.end != null) {
                queue.add(conveyor.end);
            }
        } else {
            queue.add(new IntPair(x, y));
        }
    }
}

