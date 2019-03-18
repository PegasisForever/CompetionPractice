package CCC.c18;//package Maximum_Strategic_Savings;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

//public class Main {
public class Maximum_Strategic_Savings {
    static int N = 0, M = 0, P = 0, Q = 0;
    static Vertex[] flight_graph, portal_graph;
    static long minimum_cost = 0;

    public static void main(String[] args) {
        long flight_sum = 0;
        long portal_sum = 0;
        Scanner in;
        try {
            //in = new Scanner(new File("Maximum_Strategic_Savings.in"));
            in = new Scanner(new File("C:\\Users\\Pegasis\\Desktop\\windows_data\\S5\\s5.4-05.in"));
            //in = new Scanner(System.in);
            N = in.nextInt();
            M = in.nextInt();
            P = in.nextInt();
            Q = in.nextInt();
            flight_graph = new Vertex[M];
            for (int i = 0; i < M; i++)
                flight_graph[i] = new Vertex();

            portal_graph = new Vertex[N];
            for (int i = 0; i < N; i++)
                portal_graph[i] = new Vertex();

            for (int i = 0; i < P; i++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1, c = in.nextInt();
                flight_graph[a].edges.add(new Edge(b, c));
                flight_graph[b].edges.add(new Edge(a, c));
                flight_sum += c;
            }
            for (int i = 0; i < Q; i++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1, c = in.nextInt();
                portal_graph[a].edges.add(new Edge(b, c));
                portal_graph[b].edges.add(new Edge(a, c));
                portal_sum += c;
            }
            in.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        long initial_cost = flight_sum * N + portal_sum * M;
        PriorityQueue<Edge> flight_MST = Prim(flight_graph);
        PriorityQueue<Edge> portal_MST = Prim(portal_graph);
        while (!flight_MST.isEmpty() && !portal_MST.isEmpty()) {
            if (flight_MST.peek().w <= portal_MST.peek().w) {
                minimum_cost += (long) flight_MST.poll().w * portal_MST.size();
            } else {
                minimum_cost += (long) portal_MST.poll().w * flight_MST.size();
            }
        }
        System.out.println(initial_cost - minimum_cost);

        // Explanation:
        // The objective is to find an MST for the product graph portal_graph x flight_graph
        // An MST for portal_MST x flight_MST should be an MST for portal_graph x flight_graph
        // We consider portal_graph[0] and flight_graph[0] to be the roots of their respective graphs
        // For each non-root vertex of these graphs there is exactly one edge leading towards the root in the MST
        // In portal_MST x flight_MST, each vertex has at most two edges leading towards
        // the root of the product graph: portal_graph[0] x flight_graph[0]
        // The vertices which have exactly one edge leading towards the root are in
        // portal_graph[0] x flight_MST and portal_MST x flight_graph[0]
        // We include these edges in the MST by adding a line to Prim(): minimum_cost += shortest_edge.w;
        // For the remaining vertices we make a choice for which of the two edges leading towards the root we want to include
        // Obviously we pick whichever one is shorter
        // To make all these choices efficiently we iteratively add the shortest available edges to our MST
        // If for example the shortest available edge is in flight_MST
        // this edge corresponds to the set of edges portal_MST x shortest_edge in the product graph
        // We add the edges from this set whose destination vertex does not already have an edge to it
        // There are portal_MST.size() such vertices
        // Now the vertices portal_MST x shortest_edge.d are all accounted for and this is represented by
        // shortest_edge being removed from flight_MST
    }

    static PriorityQueue<Edge> Prim(Vertex[] graph) {
        PriorityQueue<Edge> MST = new PriorityQueue<Edge>();
        PriorityQueue<Edge> edge_heap = new PriorityQueue<Edge>();
        int remaining_vertices = graph.length;
        edge_heap.addAll(graph[0].edges);
        graph[0].visited = true;
        remaining_vertices--;
        while (remaining_vertices > 0) {
            Edge shortest_edge = edge_heap.poll();
            Vertex new_vertex = graph[shortest_edge.d];
            if (new_vertex.visited == false) {
                MST.add(shortest_edge);
                edge_heap.addAll(new_vertex.edges);
                new_vertex.visited = true;
                remaining_vertices--;
                minimum_cost += shortest_edge.w;
            }
        }
        return MST;
    }

    static class Vertex {
        boolean visited = false;
        ArrayList<Edge> edges = new ArrayList<Edge>();
    }

    static class Edge implements Comparable<Edge> {
        int d, w; //destination, weight

        Edge(int d, int w) { //d is the index of the destination vertex in the graph that this edge belongs to
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Edge other_edge) {
            return this.w - other_edge.w;
        }
    }
}
