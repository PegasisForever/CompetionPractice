package CCC.c18;/*
 * Copyright 2019 Pegasis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class S5 {
    private static ArrayList<ArrayList<Vertex>> vertexList;
    private static int vertexAmount = 0;
    private static List<Edge> edgeList;
    private static long initialCost;
    private static long minCost;

    public static void main(String... args) throws IOException {
        String input = readFile("C:\\Users\\Pegasis\\Downloads\\windows_data\\S5\\s5.3-03.in");
        String[] inputLines = input.split("\n");
        int[] inputLine1 = split(4, inputLines[0]);
        int N, M, P, Q;
        N = inputLine1[0];  //Total of planets
        M = inputLine1[1];  //Cities every planet have
        P = inputLine1[2];  //Flights every planet have
        Q = inputLine1[3];  //Portals every planet have

        System.out.println("Total Edges: " + ((long)P * (long)N + (long)Q * (long)M));

        if (((long)P * (long)N + (long)Q * (long)M) > 100_000_000L) {
            edgeList = new LinkedList<>();
        } else {
            edgeList = new ArrayList<>();
        }
        vertexList = new ArrayList<>();

        //Add all the cities to the list and checkList
        for (int planetNum = 1; planetNum <= N; planetNum++) {
            ArrayList<Vertex> newVertexList = new ArrayList<>();
            vertexList.add(newVertexList);
            for (int cityNum = 1; cityNum <= M; cityNum++) {
                Vertex newVertex = new Vertex(planetNum, cityNum);
                newVertexList.add(newVertex);
            }
            vertexAmount += newVertexList.size();
            //System.out.println(vertexAmount);
        }
        //Add all the roads come from flights and portals to the list
        for (int line = 1; line <= P; line++) {
            for (int planetNum = 0; planetNum < N; planetNum++) {
                int[] intInLine = split(3, inputLines[line]);
                edgeList.add(new Edge(vertexList.get(planetNum).get(intInLine[0] - 1),
                        vertexList.get(planetNum).get(intInLine[1] - 1),
                        intInLine[2]));
            }
        }
        for (int line = 1 + P; line <= P + Q; line++) {
            for (int cityNum = 0; cityNum < M; cityNum++) {
                int[] intInLine = split(3, inputLines[line]);
                edgeList.add(new Edge(vertexList.get(intInLine[0] - 1).get(cityNum),
                        vertexList.get(intInLine[1] - 1).get(cityNum),
                        intInLine[2]));
            }
        }
        //Sort edge list from min weight to max weight, makes it easier to find edge have smaller weight
        edgeList.sort(Comparator.comparingInt(value -> value.w));

        //Calculate the cost before optimize
        for (Edge edge : edgeList) {
            initialCost += edge.w;
        }
        //Run the algorithm
        primTree();

        //Output cost saved
        System.out.println(initialCost - minCost);
    }

    private static void primTree() {
        Vertex start = vertexList.get(0).get(0);
        start.isNew = true;
        for (int newVertexesAmount = 1; newVertexesAmount != vertexAmount; newVertexesAmount++) {
            System.out.println(newVertexesAmount + "/" + vertexAmount);
            Vertex newVertex = null;
            Edge minWeightEdge = null;
            for (Edge edge : edgeList) {
                if (edge.start.isNew && !edge.end.isNew) {
                    minWeightEdge = edge;
                    newVertex = edge.end;
                    break;
                } else if (!edge.start.isNew && edge.end.isNew) {
                    minWeightEdge = edge;
                    newVertex = edge.start;
                    break;
                }
            }

            newVertex.isNew = true;
            minCost += minWeightEdge.w;
        }

    }

    private static int[] split(int amount, String str) {
        int[] result = new int[amount];
        int lastIndex = 0;
        for (int i = 0; i < amount - 1; i++) {
            int index = str.indexOf(" ", lastIndex);
            result[i] = Integer.parseInt(str.substring(lastIndex, index));
            lastIndex = index + 1;
        }
        result[amount - 1] = Integer.parseInt(str.substring(lastIndex));
        return result;
    }

    static class Vertex {
        int planet;
        int city;
        boolean isNew = false;

        Vertex(int planet, int city) {
            this.planet = planet;
            this.city = city;
        }

        public String toString() {
            return "City " + planet + "," + city;
        }
    }


    static class Edge {
        Vertex start;
        Vertex end;
        int w;

        Edge(Vertex start, Vertex end, int w) {
            this.start = start;
            this.end = end;
            this.w = w;
        }

        public String toString() {
            return "Edge " + start.toString() + "--" + end.toString();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream is = new FileInputStream(filePath);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = reader.readLine();
        }
        reader.close();
        is.close();
        return sb.toString();
    }
}
