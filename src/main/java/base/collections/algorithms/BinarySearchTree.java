package base.collections.algorithms;

import java.util.Comparator;

public class BinarySearchTree<T> {

    private class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(T data, Comparator<T> comparator) {
        root = insertRec(root, data, comparator);
    }

    private Node insertRec(Node node, T data, Comparator<T> comparator) {
        if (node == null) {
            return new Node(data);
        }
        if (comparator.compare(data, node.data) < 0) {
            node.left = insertRec(node.left, data, comparator);
        } else {
            node.right = insertRec(node.right, data, comparator);
        }
        return node;
    }

    public T search(T key, Comparator<T> comparator) {
        return searchRec(root, key, comparator);
    }

    private T searchRec(Node node, T key, Comparator<T> comparator) {
        if (node == null) return null;
        int cmp = comparator.compare(key, node.data);
        if (cmp == 0) return node.data;
        return cmp < 0 ? searchRec(node.left, key, comparator) : searchRec(node.right, key, comparator);
    }

    public void inOrder(Node node, java.util.List<T> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.data);
            inOrder(node.right, result);
        }
    }

    public java.util.List<T> inOrder() {
        java.util.List<T> result = new java.util.ArrayList<>();
        inOrder(root, result);
        return result;
    }
}
