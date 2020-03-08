package com.rishi.common;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    private final Node<T> root;
    private int depth = 0;


    public Tree(T data) {
        Node n = new Node(data);
        this.root = n;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public void addChildAfter(Node n, T child) {
        n.addChild(child);
        // TODO: add depth calculation here
    }

    public void addChildrenAfter(Node n, ArrayList<T> children) {
        for(T child : children) {
            n.addChild(child);
        }
    }

    public List<Node<T>> getChildrenOf(Node n) {
        return (List<Node<T>>) n.getChildren();
    }

    public T getDataOf(Node n) {
        return (T) n.getData();
    }


}