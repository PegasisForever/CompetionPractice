package CCC.c16;

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

    static class Node {
        int i;
        HashSet<Node> neighbours = new HashSet<>(4);
        boolean isTarget;
        int depth = 0;

        Node(boolean isTarget, int i) {
            this.isTarget = isTarget;
            this.i = i;
        }

        void removeFromTree() {
            for (Node neighbour : neighbours) {
                neighbour.neighbours.remove(this);
            }
            neighbours.clear();
        }

        boolean isRemoved() {
            return neighbours.isEmpty();
        }

        boolean isLeaf() {
            return neighbours.size() == 1;
        }
    }

    public static void main(String[] args) {
        InputReader s = new InputReader(System.in);
        int nodeCount = s.nextInt();
        int targetNodeCount = s.nextInt();
        boolean[] targetNodesMap = new boolean[nodeCount];
        ArrayList<Node> targetNodes = new ArrayList<>(nodeCount);
        for (int i = 0; i < targetNodeCount; i++) {
            targetNodesMap[s.nextInt()] = true;
        }
        Node[] nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            boolean isTarget = targetNodesMap[i];
            Node node = new Node(isTarget, i);
            nodes[i] = node;
            if (isTarget) {
                targetNodes.add(node);
            }
        }
        for (int i = 0; i < nodeCount - 1; i++) {
            Node a = nodes[s.nextInt()];
            Node b = nodes[s.nextInt()];
            a.neighbours.add(b);
            b.neighbours.add(a);
        }

        ArrayList<Node> leaves = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isLeaf()) {
                leaves.add(node);
            }
        }
        for (Node leaf : leaves) {
            Node curr = leaf;
            while (!curr.isTarget && curr.isLeaf()) {
                Node next = curr.neighbours.iterator().next();
                curr.removeFromTree();
                curr = next;
            }
        }

        ArrayList<Node> nodes2 = new ArrayList<>(nodeCount);
        for (Node node : nodes) {
            if (!node.isRemoved()) {
                nodes2.add(node);
            }
        }

        Node res1 = dfs(nodes2.get(0), nodes.length);
        Node res2 = dfs(res1, nodes.length);
        System.out.println(2 * (nodes2.size() - 1) - res2.depth);
    }

    static Node dfs(Node start, int nodeCount) {
        Node maxNode = start;

        boolean[] visited = new boolean[nodeCount];
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        start.depth = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!visited[node.i]) {
                visited[node.i] = true;

                if (node.depth > maxNode.depth) {
                    maxNode = node;
                }

                for (Node neighbour : node.neighbours) {
                    neighbour.depth = node.depth + 1;
                    stack.push(neighbour);
                }
            }
        }

        return maxNode;
    }
}

