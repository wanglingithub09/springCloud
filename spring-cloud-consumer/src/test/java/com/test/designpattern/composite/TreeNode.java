package com.test.designpattern.composite;

import lombok.Data;

import java.util.Vector;
@Data
public class TreeNode {
    private String name;
    private TreeNode parent;
    private Vector<TreeNode> children = new Vector<>();
    public TreeNode(String name){
        this.name = name;
    }
    public void add(TreeNode treeNode){
        children.add(treeNode);
    }
    public void remove(TreeNode treeNode){
        children.remove(treeNode);
    }
}
