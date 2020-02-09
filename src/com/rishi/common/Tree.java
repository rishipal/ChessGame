package com.rishi.common;

import java.util.ArrayList;
import java.util.List;

class Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children;

    Node() {}
    Node(T data) {
        this.data = data;
    }

    public boolean insertChildToLast(Node child) {
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
        return true;
    }

    public List<Node<T>> getChildren() {
        return this.children;
    }
}

public class Tree<T> {
    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

}