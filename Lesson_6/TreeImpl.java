package Lesson_6;

import java.util.Stack;

public class TreeImpl implements Tree {

    private Node root;
    private int currentSize;

    private class RemovedElementAndParent {
        Node removedElement;
        Node parent;
    }

    @Override
    public Node getNodeRoot() {
        return root;
    }

    @Override
    public void insert(Person person) {
        Node newNode = new Node(person);
        int key = newNode.getKey();

        Node parent = findParentForInsert(key);

        if (parent == null) {
            root = newNode;
        } else {
            if (parent.isLeft(key)) {
                parent.setLeftChild(newNode);
            } else {
                parent.setRightChild(newNode);
            }
        }
        currentSize++;
    }

    private Node findParentForInsert(int key) {
        Node currentNode = root;
        Node parent = null;

        RemovedElementAndParent elementAndParent = new RemovedElementAndParent();
        elementAndParent.removedElement = currentNode;
        elementAndParent.parent = parent;


        while (currentNode != null) {
            parent = currentNode;
            if (currentNode.isRight(key)) {
                currentNode = currentNode.getRightChild();
            } else {
                currentNode = currentNode.getLeftChild();
            }
        }
        return parent;
    }


    @Override
    public boolean remove(int key) {
        RemovedElementAndParent elementAndParent = findRemovedElementAndParent(key);

        if (elementAndParent.removedElement == null) {
            return false;//Элемент не найден, удалять нечего
        }

        Node removedNode = elementAndParent.removedElement;
        Node parent = elementAndParent.parent;

        if (removedNode.isLeaf()) {
            removeLeafNode(removedNode, parent);
        } else if (removedNode.hasOnlyOneChild()) {
            removeNodeWithOnlyOneChild(removedNode, parent);
        } else {
            removeNodeWithBothChild(removedNode, parent);
        }

        currentSize--;
        return true;
    }

    private void removeNodeWithBothChild(Node removedNode, Node parent) {
        Node successor = getSuccessor(removedNode);
        if (removedNode == root) {
            root = successor;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(successor);
        } else {
            parent.setRightChild(successor);
        }
        successor.setLeftChild(removedNode.getLeftChild());
        if (successor != removedNode.getRightChild()) {
            successor.setRightChild(removedNode.getRightChild());
        }
    }

    private void removeNodeWithOnlyOneChild(Node removedNode, Node parent) {
        Node removeChildNode = getSingleChildNode(removedNode);

        if (removedNode == root) {
            root = removeChildNode;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(removeChildNode);
        } else {
            parent.setRightChild(removeChildNode);
        }
    }

    private Node getSingleChildNode(Node removedNode) {
        return removedNode.getLeftChild() != null ? removedNode.getLeftChild() : removedNode.getRightChild();
    }

    private void removeLeafNode(Node removedNode, Node parent) {
        if (removedNode == root) {
            root = null;
        } else {
            parent.deleteChild(removedNode);
        }
    }

    private RemovedElementAndParent findRemovedElementAndParent(int key) {
        RemovedElementAndParent elementAndParent = new RemovedElementAndParent();
        elementAndParent.removedElement = root;
        elementAndParent.parent = null;

        while (elementAndParent.removedElement != null) {
            if (elementAndParent.removedElement.getKey() == key) {
                break;
            }

            elementAndParent.parent = elementAndParent.removedElement;
            if (elementAndParent.removedElement.isRight(key)) {
                elementAndParent.removedElement = elementAndParent.removedElement.getRightChild();
            } else {
                elementAndParent.removedElement = elementAndParent.removedElement.getLeftChild();
            }
        }
        return elementAndParent;
    }

    private Node getSuccessor(Node node) {
        Node successorParent = null;//Родитель элемента под замену
        Node successor = node;//Под замену
        Node current = node.getRightChild();//Для навигации

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != node.getRightChild()) {
            //Дочерние элементы successor не теряем, а отдаем его родителю
            successorParent.setLeftChild(successor.getRightChild());
        }

        return successor;
    }

    @Override
    public Person find(int key) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.getKey() == key) {
                return currentNode.getPerson();
            }
            if (currentNode.isRight(key)) {
                currentNode = currentNode.getRightChild();
            } else {
                currentNode = currentNode.getLeftChild();
            }
        }

        return null;
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Person getRoot() {
        return root.getPerson();
    }

    @Override
    public void traverse(TraverseMode traverseMode) {
        switch (traverseMode) {
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            case IN_ORDER:
                inOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown traverse mode: " + traverseMode);
        }
    }

    private void preOrder(Node node) {
        if (node != null) {
            node.display();
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    private void postOrder(Node node) {
        if (node != null) {
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
            node.display();
        }
    }

    private void inOrder(Node node) {
        if (node != null) {
            preOrder(node.getLeftChild());
            node.display();
            preOrder(node.getRightChild());
        }
    }

    @Override
    public void displayTree() {
        Stack<Node> globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("........................................");

        while ( !isRowEmpty ) {
            Stack<Node> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while ( !globalStack.isEmpty() ) {
                Node tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getKey());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks*2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while ( !localStack.isEmpty() ) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("........................................");
    }


}