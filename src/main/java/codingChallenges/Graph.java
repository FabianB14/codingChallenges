package codingChallenges;

import java.util.*;

public class Graph {
    private Map<Vertex, List<Vertex>> adjVertices;
    public int size = 0;


    public Map<Vertex, List<Vertex>> getAdjVertices(String vertex) {
        return adjVertices;
    }

    public void setAdjVertices(Map<Vertex, List<Vertex>> adjVertices) {
        this.adjVertices = adjVertices;
    }

   public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
        this.size ++;
    }

   public void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
        this.size --;
    }

   public void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }
    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

   public Set<String> breadthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        List<Vertex> listOfVertex = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            listOfVertex.add((Vertex) graph.getAdjVertices(vertex));
            for(Vertex v : listOfVertex ){
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    queue.add(v.label);
                }
            }
        }
        return visited;
    }
}
