package Lesson_7;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class GraphImpl implements Graph {

    private Vertex[] vertexes;
    private int[][] adjMat;

    private int size;

    public GraphImpl(int maxVertexCount) {
        this.vertexes = new Vertex[maxVertexCount];
        this.size = 0;
        createAdjMat(maxVertexCount);
    }

    private void createAdjMat(int maxVertexCount) {
        this.adjMat = new int[maxVertexCount][maxVertexCount];
        for (int i = 0; i < maxVertexCount; i++) {
            for (int j = 0; j < maxVertexCount; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    @Override
    public void addVertex(String label) {
        Vertex newVertex = new Vertex(label);
        vertexes[size++] = newVertex;
    }

    @Override
    public boolean addEdge(String fromLabel, String toLabel) {
        int from = indexOf(fromLabel);
        int to = indexOf(toLabel);

        if (from == -1 || to == -1) {
            return false;
        }

        adjMat[from][to] = 1;
        adjMat[to][from] = 1;
        return true;
    }

    @Override
    public boolean remove(String label) {
        int vertexIndex = indexOf(label);
        if (vertexIndex == -1) {
            return false;
        }
        clearEdges(vertexIndex);
        clearVertex(vertexIndex);
        size--;
        return true;
    }

    private void clearVertex(int vertexIndex) {
        vertexes[vertexIndex] = null;
    }

    private void clearEdges(int vertexIndex) {
        for (int i = 0; i < vertexes.length; i++) {
            adjMat[vertexIndex][i] = 0;
            adjMat[i][vertexIndex] = 0;
        }
    }

    private boolean hasEdge(Vertex from, Vertex to) {
        return hasEdge(from.getLabel(), to.getLabel());
    }

    private boolean hasEdge(String fromLabel, String toLabel) {
        int from = indexOf(fromLabel);
        int to = indexOf(toLabel);
        if (from == -1 || to == -1)
            return false;

        return adjMat[from][to] == 1;
    }

    @Override
    public Vertex find(String label) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == null) {
                continue;
            }
            String vertexLabel = vertexes[i].getLabel();
            if (vertexLabel.equals(label)) {
                return vertexes[i];
            }
        }
        return null;
    }

    @Override
    public int indexOf(String label) {
        for (int index = 0; index < vertexes.length; index++) {
            if (vertexes[index] == null) {
                continue;
            }
            String vertexLabel = vertexes[index].getLabel();
            if (vertexLabel.equals(label)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void display() {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == null) {
                continue;
            }
            String out = vertexes[i].toString();
            for (int j = 0; j < vertexes.length; j++) {
                if (adjMat[i][j] == 1) {
                    out += " -> " + vertexes[j];
                }
            }
            System.out.println(out);
        }
    }

    @Override
    public void dfs(String startVertexLabel) {
        Vertex vertex = find(startVertexLabel);
        if (vertex == null) {
            return;
        }
        Stack<Vertex> stack = new Stack<>();
        visit(vertex, stack);

        while (!stack.isEmpty()) {
            vertex = getAdjUnvisitedVertex(stack.peek());
            if (vertex == null) {
                vertex = stack.pop();
            } else {
                visit(vertex, stack);
            }
        }

        resetVertexStates();
    }

    @Override
    public void bfs(String startVertexLabel, String findVertexLabel) {
        Stack<Vertex> way = new Stack<>();
        Vertex vertex = find(startVertexLabel);
        Vertex vertexToFind = null;
        if (vertex == null) {
            return;
        }

        Queue<Vertex> queue = new ArrayDeque();
        visit(vertex, queue);

        while (!queue.isEmpty()) {
            vertex = queue.remove();
            Vertex currentVertex = null;
            while ((currentVertex = getAdjUnvisitedVertex(vertex)) != null) {
                visit(currentVertex, queue);
                currentVertex.setPreviousvertex(vertex);
                if (currentVertex == find(findVertexLabel)) {
                    vertexToFind = currentVertex;
                    break;
                }
            }
        }

        Vertex counter = vertexToFind;
        while (counter.getPreviousvertex() != null) {
            way.push(counter);
            counter = counter.getPreviousvertex();
        }
        way.push(find(startVertexLabel));
        while (!way.empty()) {
            if (way.size() == 1) {
                System.out.print(way.pop() + "\n");
            } else {
                System.out.print(way.pop() + " -> ");
            }
        }
        resetVertexStates();
    }

    private void visit(Vertex vertex, Queue<Vertex> queue) {
        //display(vertex);
        vertex.setWasVisited(true);
        queue.add(vertex);
    }

    private Vertex getAdjUnvisitedVertex(Vertex vertex) {
        for (int i = 0; i < vertexes.length; i++) {
            Vertex currentVertex = vertexes[i];
            if (hasEdge(vertex, currentVertex) && !currentVertex.isWasVisited()) {
                return currentVertex;
            }
        }

        return null;
    }

    private void resetVertexStates() {
        for (int i = 0; i < vertexes.length; i++) {
            vertexes[i].setWasVisited(false);
        }
    }

    private void visit(Vertex vertex, Stack<Vertex> stack) {
        stack.push(vertex);
        display(vertex);
        vertex.setWasVisited(true);
    }

    private void display(Vertex vertex) {
        System.out.println(vertex);
    }
}