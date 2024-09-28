import java.io.*;
import java.util.*;

class Graph {
    private int V;
    private List<List<Integer>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int v, int w) {
        if (!adj.get(v).contains(w)) {
            adj.get(v).add(w);
        }
        if (!adj.get(w).contains(v)) {
            adj.get(w).add(v);
        }
    }

    public int isEulerian() {
        int odd = 0;

        for (int i = 0; i < V; i++) {
            if (adj.get(i).size() % 2 != 0) {
                odd++;
            }
        }

        if (odd > 2) {
            return 0;
        } else if (odd == 2) {
            return 1;
        } else {
            return 2; 
        }
    }

    // Imprime o resultado
    public String printResult() {
        int res = isEulerian();
        if (res == 0) {
            return "Não é Euleriano";
        } else if (res == 1) {
            return "Semi-Euleriano";
        } else {
            return "É Euleriano";
        }
    }

    public static Graph readGraphFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        List<int[]> edges = new ArrayList<>();
        int maxVertex = -1;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length < 2) continue;

            int v = Integer.parseInt(parts[0].trim());
            int w = Integer.parseInt(parts[1].trim());
            edges.add(new int[]{v, w});
            maxVertex = Math.max(maxVertex, Math.max(v, w));
        }
        reader.close();

        int numVertices = maxVertex + 1;
        Graph g = new Graph(numVertices);

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        return g;
    }

    public static void main(String[] args) {
        String filename = "Email-Enron.txt";
        try {
            Graph g = readGraphFromFile(filename);
            System.out.println(g.printResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
