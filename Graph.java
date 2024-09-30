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
        String[] filenames = {
                "Email-Enron.txt",
                "grafo1.txt",
                "grafo2.txt",
                "grafo3.txt",
                "grafo4.txt"
        };

        for (String filename : filenames) {
            Graph g = readGraphFromFile(filename);
            System.out.println("Resultados:");
            System.out.println("Grafo " + filename + ":");
            System.out.println(g.printResult());
            System.out.println();
        }
    }
}
