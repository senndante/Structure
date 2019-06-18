package Lesson_6;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static int maxDepth = 6;
    private final static int numOfTrees = 20;

    public static void main(String[] args) {

        List<Tree> treeList = new ArrayList<>();

        for (int i = 0; i < numOfTrees; i ++) {
            Tree tree = new TreeImpl();
            setTree(tree);
            System.out.println("Глубина дерева: " +  maxDepth(tree.getNodeRoot()));
            tree.displayTree();
            treeList.add(tree);
        }

        int countBalanced = 0;

        for (Tree tree : treeList) {
            if (isBalanced(tree.getNodeRoot())) {
                countBalanced++;
            }
        }
        System.out.println("Доля сбалансированных деревьев: " + (double) (countBalanced) / (double) (numOfTrees) * 100 + "%");
    }

    private static void addNode(Tree tree, int id) {
        Person person = new Person("DefaultName", id, id);
        tree.insert(person);
    }

    private static void setTree(Tree tree) {
        int numOfElements = (int) (Math.pow(2, maxDepth) - 1);
        for (int i = 0; i < 2 * numOfElements; i ++) {
            if (maxDepth(tree.getNodeRoot()) < maxDepth) { // проверка глубины
                addNode(tree, (int) (Math.random() * 40 - 20));
            }
        }
    }

    private static int maxDepth(Node root) {  // проверка глубины дерева
        if (root != null) {
            return Math.max(maxDepth(root.getLeftChild()), maxDepth(root.getRightChild())) + 1;
        }
        return 0;
    }

    private static boolean isBalanced(Node node) { // проверка на сбалансированность
        return (node == null) ||
                isBalanced(node.getLeftChild()) &&
                        isBalanced(node.getRightChild()) &&
                        Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1;
    }

    private static int height(Node node) { // вычисление высоты
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

}