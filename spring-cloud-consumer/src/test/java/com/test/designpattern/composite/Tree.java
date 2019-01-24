package com.test.designpattern.composite;

public class Tree {
    private TreeNode root;
    public Tree(String name){
        root = new TreeNode(name);
    }

    public static void main(String[] args) {
        Tree tree = new Tree("A");
        TreeNode treeNode1 = new TreeNode("B");
        TreeNode treeNode2 = new TreeNode("C");

        treeNode1.add(treeNode2);
        tree.root.add(treeNode1);
        System.out.println(tree.root);
    }
}
