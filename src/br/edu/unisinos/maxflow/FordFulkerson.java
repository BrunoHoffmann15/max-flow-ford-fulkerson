package br.edu.unisinos.maxflow;

import java.util.PriorityQueue;
import java.util.Queue;

public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;


    public FordFulkerson(FlowNetwork graph, int source, int destination) {
        while (hasAugmentingPath(graph, source, destination)) {
            double bottle = Double.POSITIVE_INFINITY;

            for (int v = destination; v != source; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));

            for (int v = destination; v != source; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);

            value += bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork graph, int source, int destination) {
        marked = new boolean[graph.getNumberVertices()]; // Is path to this vertex known?
        edgeTo = new FlowEdge[graph.getNumberVertices()]; // last edge on path

        Queue<Integer> q = new PriorityQueue<Integer>();
        marked[source] = true; // Mark the source
        q.add(source); // and put it on the queue.

        while (!q.isEmpty())
        {
            int v = q.poll();
            for (FlowEdge e : graph.adj(v))
            {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }

        return marked[destination];
    }

    public double value() { return value; }
    public boolean inCut(int v) { return marked[v]; }

}
