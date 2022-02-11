/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// package com.mycompany.node2;

import java.util.ArrayList;

/**
 *
 * @author emilydstein
 */
public class Node {
    // this is the array of children nodes
    private ArrayList<Node> children = new ArrayList<Node>();
    // this is the parent Node
    private Node parent = null;
    // this is the machine (also the level of offset from the root)
    private int machine = 0;
    // this is the "task number" - set during populate and, importantly, not touched again
    private int task;
    // this is the pair of numbers representing machine and task 
    // IN THAT ORDER
    private ArrayList<Integer> pair = new ArrayList<Integer>();
    
    //default constructor
    public Node(){}

    // function that sets the parent, layer, and task during Node creation
    public Node(Node parent, int machine, int task){
        this.parent = parent;
        this.machine = machine;
        this.task = task;
        Integer mach = machine;
        this.pair.add(mach);
        Integer t = task;
        this.pair.add(t);
    }
    
    // function to get the pair
    public ArrayList<Integer> getPair(){
        return this.pair;
    }
    
    // function that populates the child list with 8 new nodes
    public void populate(){
        // the children should represent possible pairs for the next machine
        int next_machine = this.machine + 1;
        // fill pairs by iterating 8 times
        for(int i = 1; i < 9; i++){
            Node new_node = new Node(this, next_machine, i);
            this.children.add(new_node);
        }
    }
    
    // function that iterates through ancestry until the root and returns an ArrayList of all machine pairs thus far
    public ArrayList<ArrayList<Integer>> getPairList(){
        //the Node that we will collect the first pair from
        Node current = this;
        // the list that we will store the pairs in
        ArrayList<ArrayList<Integer>> pair_list = new ArrayList<ArrayList<Integer>>();
        // iterate through until we get to the rool, has a null parent
        while (current.getMachine() != 0){
            ArrayList<Integer> this_pair = current.getPair();
            pair_list.add(this_pair);
            current = current.getParent();
        }
        //System.out.println(pair_list.size());
        return pair_list;
    }
    
    // gets a child based on the index (assigned task number in this case)
    public Node getChild(int index){
        //returns the requested child Node found at the index
        return this.children.get(index);
    }
    
    //terminates the current node by returning the parent
    public Node terminate(){
        // get the list of the parent's children
        ArrayList<Node> parents_children = this.parent.children;
        parents_children.remove(this);
        return this.parent;
    }
    
    public int getListSize(){
        return this.getPairList().size();
    }
    
    public Node getParent(){
        return this.parent;
    }
    
    public int howManyChildren(){
        return this.children.size();
    }
    
    public void printPair(){
        System.out.println(this.machine + ", " +  this.task);
;
    }
    
    public int getMachine(){
        return this.machine;
    }
}
