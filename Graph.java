import java.io.*;
import java.util.*;

class Graph {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;


    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
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
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        List<int[]> edges = new ArrayList<>();


        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
        
            int v = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            edges.add(new int[] { v, w });
        }

        br.close();


        int maxVertex = -1;
        for (int[] edge : edges) {
            maxVertex = Math.max(maxVertex, Math.max(edge[0], edge[1]));
        }

        int numVertices = maxVertex + 1;
        Graph g = new Graph(numVertices);


        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        return g;
    }

    public static void main(String[] args) {
        try {
            String filename = "Email-Enron.txt";
            String filename1 = "grafo1.txt";
            String filename2 = "grafo2.txt";
            String filename3 = "grafo3.txt";
            String filename4 = "grafo4.txt";


            Graph g = readGraphFromFile(filename);
            Graph g1 = readGraphFromFile(filename1);
            Graph g2 = readGraphFromFile(filename2);
            Graph g3 = readGraphFromFile(filename3);
            Graph g4 = readGraphFromFile(filename4);


            System.out.println("Resultados:\n");
            System.out.println("Email-Enron:");
            System.out.println(g.printResult());
            System.out.println("\nGrafo 1:");
            System.out.println(g1.printResult());
            System.out.println("\nGrafo 2:");
            System.out.println(g2.printResult());
            System.out.println("\nGrafo 3:");
            System.out.println(g3.printResult());
            System.out.println("\nGrafo 4:");
            System.out.println(g4.printResult());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
