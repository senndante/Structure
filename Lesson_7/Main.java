package Lesson_7;

public class Main {

    public static void main(String[] args) {
        Graph graph = new GraphImpl(10);
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addVertex("8");
        graph.addVertex("9");
        graph.addVertex("10");
        graph.addEdge("1", "2");
        graph.addEdge("1",  "8");
        graph.addEdge("1",  "5");
        graph.addEdge("2",  "3");
        graph.addEdge("3",  "4");
        graph.addEdge("5",  "6");
        graph.addEdge("6",  "7");
        graph.addEdge("7",  "4");
        graph.addEdge("8",  "9");
        graph.addEdge("9",  "10");
        graph.addEdge("10", "4");
        graph.bfs("1", "10");
        graph.bfs("1", "4");
        graph.bfs("1", "7");
    }
}