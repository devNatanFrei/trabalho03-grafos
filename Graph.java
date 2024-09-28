import java.io.*;
import java.util.*;

class Graph {
    private int V; // Número de vértices
    private List<List<Integer>> adj; // Lista de adjacências para representar o grafo

    // Construtor
    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Função para adicionar uma aresta ao grafo
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v); // O grafo é não-direcionado
    }

    // Função auxiliar de DFS (busca em profundidade) usando abordagem iterativa
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

    // Função para verificar se o grafo é conectado, considerando apenas vértices com arestas
    public boolean isConnected() {
        int i;
        // Encontra o primeiro vértice com grau maior que 0
        for (i = 0; i < V; i++) {
            if (adj.get(i).size() > 0) {
                break;
            }
        }

        if (i == V) {
            return true; // Se não houver arestas, o grafo é considerado Euleriano
        }

        // Faz uma DFS a partir do primeiro vértice encontrado com grau > 0
        boolean[] visited = dfsIterative(i);

        // Verifica se todos os vértices com arestas foram visitados
        for (i = 0; i < V; i++) {
            if (!visited[i] && adj.get(i).size() > 0) {
                return false;
            }
        }

        return true;
    }

    // Função para verificar se o grafo é Euleriano
    public int isEulerian() {
        // Verifica se o grafo é conectado
        if (!isConnected()) {
            return 0;
        }

        // Conta o número de vértices com grau ímpar
        int odd = 0;
        for (int i = 0; i < V; i++) {
            if (adj.get(i).size() % 2 != 0) {
                odd++;
            }
        }

        // Se o número de vértices com grau ímpar for maior que 2, o grafo não é Euleriano
        if (odd > 2) {
            return 0;
        } else if (odd == 2) {
            return 1; // Caminho Euleriano (Semi-Euleriano)
        } else {
            return 2; // Ciclo Euleriano
        }
    }

    // Função para imprimir o resultado baseado no tipo de grafo
    public String printResult() {
        int res = isEulerian();
        if (res == 0) {
            return "O grafo não é Euleriano";
        } else if (res == 1) {
            return "O grafo tem um caminho Euleriano (Semi-Euleriano)";
        } else {
            return "O grafo tem um ciclo Euleriano";
        }
    }

    // Função para ler o grafo a partir de um arquivo txt
    public static Graph readGraphFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        List<int[]> edges = new ArrayList<>();
        int maxVertex = -1; // Para determinar o número de vértices

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            int v = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            edges.add(new int[] { v, w });
            maxVertex = Math.max(maxVertex, Math.max(v, w));
        }

        reader.close();

        int numVertices = maxVertex + 1; // O número total de vértices é o máximo + 1
        Graph g = new Graph(numVertices);

        // Adiciona as arestas ao grafo
        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        return g;
    }

    // Função principal
    public static void main(String[] args) {
        try {
            // Caminho para o arquivo de entrada
            String filename = "grafo.txt"; // Substitua pelo caminho correto do seu arquivo

            // Lê o grafo do arquivo
            Graph g = readGraphFromFile(filename);

            // Imprime o resultado
            System.out.println(g.printResult());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
