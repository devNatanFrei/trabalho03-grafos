import java.io.*;
import java.util.*;



class Graph {
    private int V;
    private List<List<Integer>> adj;


    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }


    public void addEdge(int v, int w) {
        if (!adj.get(w).contains(v)){
            adj.get(v).add(w);
        }

        if (!adj.get(v).contains(w)){
            adj.get(w).add(v);
        }


    }


    private boolean[] dfsIterative(int start) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int v = stack.pop();
            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
        return visited;
    }


    public boolean isConnected() {
        int i;

        for (i = 0; i < V; i++) {
            if (adj.get(i).size() > 0) {
                break;
            }
        }

        if (i == V) {
            return true;
        }


        boolean[] visited = dfsIterative(i);


        for (i = 0; i < V; i++) {
            if (!visited[i] && adj.get(i).size() > 0) {
                return false;
            }
        }

        return true;
    }


    public int isEulerian() {

        if (!isConnected()) {
            return 0;
        }


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


    public String printResult() {
        int res = isEulerian();
        if (res == 0) {
            return "Não é Euleriano";
        } else if (res == 1) {
            return "Semi-Euleriano";
        } else {
            return "Euleriano";
        }
    }


    public static Graph readGraphFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        List<int[]> edges = new ArrayList<>();
        int maxVertex = -1;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            int v = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            edges.add(new int[] { v, w });
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
        try {

            String filename = "grafo.txt";


            Graph g = readGraphFromFile(filename);


            System.out.println(g.printResult());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
