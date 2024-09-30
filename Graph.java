import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Graph {
    private Map<Integer, ArrayList<Integer>> adj;

    public Graph() {
        this.adj = new HashMap<>();
    }

    public void addEdge(int v, int w) {
        adj.putIfAbsent(v, new ArrayList<>());
        adj.putIfAbsent(w, new ArrayList<>());

        if (!adj.get(v).contains(w)) {
            adj.get(v).add(w);
        }
        if (!adj.get(w).contains(v)) {
            adj.get(w).add(v);
        }
    }

    public int isEulerian() {
        int oddCount = 0;

        for (ArrayList<Integer> neighbors : adj.values()) {
            if (neighbors.size() % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount > 2) {
            return 0;
        } else if (oddCount == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    public String printResult() {
        int result = isEulerian();
        switch (result) {
            case 0:
                return "Não é Euleriano";
            case 1:
                return "Semi-Euleriano";
            default:
                return "É Euleriano";
        }
    }

    public static Graph readGraphFromFile(String filename) {
        Graph g = new Graph();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\\s+");
                    int v = Integer.parseInt(parts[0]);
                    int w = Integer.parseInt(parts[1]);
                    g.addEdge(v, w);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return g;
    }

    public static void main(String[] args) {
        
        System.out.println("Resultados:");
        Graph g = readGraphFromFile("Email-Enron.txt");
        System.out.println("Grafo Email-Enron.txt:");
        System.out.println(g.printResult());
        System.out.println();
        Graph g1 = readGraphFromFile("grafo1.txt");
        System.out.println("Grafo grafo1.txt:");
        System.out.println(g1.printResult());
        System.out.println();
        Graph g2 = readGraphFromFile("grafo2.txt");
        System.out.println("Grafo grafo2.txt:");
        System.out.println(g2.printResult());
        System.out.println();
        Graph g3 = readGraphFromFile("grafo3.txt");
        System.out.println("Grafo grafo3.txt:");
        System.out.println(g3.printResult());
        System.out.println();
        Graph g4 = readGraphFromFile("grafo4.txt");
        System.out.println("Grafo grafo4.txt:");
        System.out.println(g4.printResult());
        System.out.println();



    }
}
