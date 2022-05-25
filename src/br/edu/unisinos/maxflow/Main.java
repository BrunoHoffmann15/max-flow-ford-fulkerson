package br.edu.unisinos.maxflow;

public class Main {
    public static void main(String[] args) {
        FlowNetwork flowNetwork = new FlowNetwork(6);

        /**
         * A - 0
         * B - 1
         * C - 2
         * D - 3
         * E - 4
         * F - 5
         * */

        flowNetwork.addEdge(0, 1, 11);
        flowNetwork.addEdge(0, 2, 12);

        flowNetwork.addEdge(1, 3, 12);

        flowNetwork.addEdge(2, 1, 1);
        flowNetwork.addEdge(2, 4, 11);

        flowNetwork.addEdge(3, 5, 19);

        flowNetwork.addEdge(4, 3, 7);
        flowNetwork.addEdge(4, 5, 4);


        FordFulkerson maxflow = new FordFulkerson(flowNetwork, 1, 5);
        System.out.println(("Max flow value = " + maxflow.value()));
    }
}
