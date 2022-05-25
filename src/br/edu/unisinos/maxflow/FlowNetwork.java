package br.edu.unisinos.maxflow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlowNetwork {
    protected int numberVertices;
    protected int numberEdges;
    protected List<FlowEdge>[] adj;

    public FlowNetwork(int numberVertices) {
        if (numberVertices < 0)
            throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.numberVertices = numberVertices;
        clear();
    }

    public boolean isEmpty() {
        return numberVertices == 0;
    }

    public void clear() {
        this.numberEdges = 0;
        adj = new ArrayList[this.numberVertices];
        for (int i = 0; i < this.numberVertices; i++) {
            adj[i] = new ArrayList<FlowEdge>();
        }
    }

    public int getNumberVertices() {
        return this.numberVertices;
    }

    public int getNumberEdges() {
        return this.numberEdges;
    }

    public void addEdge(int v, int w, double capacity) {
        validateVertex(v);
        validateVertex(w);

        FlowEdge flowEdge = new FlowEdge(v, w, capacity);
        adj[v].add(flowEdge);

        this.numberEdges++;
    }

    public Iterable<FlowEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    protected void validateVertex(int v) {
        if (v < 0 || v >= numberVertices)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (numberVertices - 1));
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberVertices; i++) {
            sb.append("[" + i + "] => " + adj[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}