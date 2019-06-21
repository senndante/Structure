package Lesson_7;

public interface Graph {

    void addVertex(String  label);

    boolean addEdge(String fromLabel, String toLabel);

    boolean remove(String label);

    Vertex find(String  label);

    int indexOf(String label);

    boolean isEmpty();

    int getSize();

    void display();

    //Depth-first search, DFS
    void dfs(String startVertexLabel);

    // breadth-first search, BFS
    void bfs(String startVertexLabel, String findVertex);
}