/**
 * Filename:   AVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, Monica Schmidt
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002
 * 
 * Due Date:   specified in Canvas
 * Version:    1.0
 * 
 * Credits:    Class readings
 * 
 * Bugs:       no known bugs, but not complete either
 */

import java.lang.IllegalArgumentException;

/** AVL Binary Search Tree. Allows for insert delete and lookup.
 * @param <K>
 */
public class AVLTree<K extends Comparable<K>> implements AVLTreeADT<K> {
    private BSTNode<K> node; //Root node
    private String inorder = ""; //Inorder traversal string
    private int balance = 0; //Stores the balance
    /**
     * Allows access to the private instance variable balance 
     * @return Balance
     */
    private int getBalance() {
        return this.balance;
    }
    /**
     * Changes the value of balance 
     * @param int b
     */
    private void setBalance(int b) {
        this.balance = b;
    }
    /**
     * Allows access to the private instance variable root node
     * @return node
     */
    private BSTNode<K> getNode() { 
        return this.node;
    }
    /** 
     * Represents a tree node.
     *
     * @author (Deb Deppler, Monica Schmidt)
     * @param <K>
     */
    class BSTNode<K> {
        /* fields */
        private K key;  //Value of the node
        private int height; //The nodes height in the binary search tree
        private BSTNode<K> left, right; //The children of the node

        /**
         * Creates a Node with a value assigned to key. Sets the children to null and
         * height to zero until it is calculated later 
         * @param key
         */
        BSTNode(K key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
        /**
         * Allows access to the private instance variable key 
         * @return key
         */
        private K getKey() { 
            return key; 
        }
        /**
         * Allows access to the private instance variable left child 
         * @return left
         */
        private BSTNode<K> getLeft() { 
            return left; 
        }
        /**
         * Allows access to the private instance variable right child 
         * @return right
         */
        private BSTNode<K> getRight() {
            return right; 
        }
        /**
         * Allows access to the private instance variable height
         * @return Balance
         */
        private int getHeight() {
            return height; 
        }


        /**
         * Change the value of private instance variable key 
         * @param k
         */
        private void setKey(K k) { 
            this.key = k; 
        }
        /**
         * Change the value of private instance variable left
         * @param left
         */
        private void setLeft(BSTNode<K> left) { 
            this.left = left; 
        }
        /**
         * Change the value of private instance variable right
         * @param right
         */
        private void setRight(BSTNode<K> right) { 
            this.right = right;
        }
        /**
         * Change the value of private instance variable height
         * @param height
         */
        private void setHeight(int height) { 
            this.height = height;
        }
    }

    /**
     * Checks to see if the AVL tree is empty. If the node is null
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        if(this.node == null) {
            return true;
        }else {
            return false;
        }
    }
    /**
     * Recursively inserts nodes. Checks to see if the key is lower or higher
     * than the first key. Sets the nodes according to BST.
     *
     * @param BSTNode<K> node 
     * @param K key
     * @exception DuplicateKeyException
     * @return node
     */
    private BSTNode<K> insertHelper(BSTNode<K> node, K key, int height) throws DuplicateKeyException {
        //No first node
        if (node == null) {
            BSTNode<K> newNode = new BSTNode<K>(key);
            //sets the height
            newNode.setHeight(height);
            return newNode;
        }
        //increases height by one
        node.setHeight(height+1);
        //checks for duplicate
        if (node.getKey().equals(key)) {
            throw new DuplicateKeyException();
        }
        //checks to see if the key is less than the first node
        if (key.compareTo(node.getKey()) < 0) {
            // add key to the left subtree
            node.setLeft(insertHelper(node.getLeft(), key, height+1) );
        }
      //checks to see if the key is more than the first node
        else if(key.compareTo(node.getKey()) > 0){
            // add key to the right subtree
            node.setRight(insertHelper(node.getRight(), key, height+1) );  
        }
        //Gets the balance factor
        int balance = getBalanceFactor(node); 
        
        // Right Rotate Case 
//        if (balance > 1 && key.compareTo(node.getLeft().getKey()) < 0) 
//            return rotateRight(node); 
//  
//        // Left Rotate Case 
//        if (balance < -1 && key.compareTo(node.getRight().getKey()) > 0) 
//            return rotateLeft(node); 
//  
//        // Left Right Rotate Case 
//        if (balance > 1 && key.compareTo(node.getLeft().getKey()) > 0) { 
//            node.setLeft(rotateLeft(node.left)); 
//            return rotateRight(node); 
//        } 
  
        // Right Left Rotate Case 
        if (balance < -1 && key.compareTo(node.getRight().getKey())<0) { 
            node.setRight(rotateRight(node.right)); 
            return rotateLeft(node); 
        } 
  
        // unchanged node
        return node; 
    }
    /**
     * Inserts nodes into the AVL tree
     *
     * @param K key
     * @exception DuplicateKeyException
     * @exception IllegalArguementException
     */
    @Override
    public void insert(K key) throws DuplicateKeyException, IllegalArgumentException {
        this.node = insertHelper(this.node, key, 1);

        
    }
    /** Rotates node a to the left, making its right child into its parent.
     * @param node the grandparent
     * @return the new parent 
     */
    private BSTNode<K> rotateLeft(BSTNode<K> node) {
        BSTNode<K> g = node; // grandparent node
        BSTNode<K> p = g.getRight(); // parent node
        BSTNode<K> c = p.getRight(); // child node
        g.setRight(p.getLeft());
        p.setLeft(g);
        return p;
    }
    /** Rotates node a to the right, making its left child into its parent.
     * @param  node the grandparent
     * @return the new parent 
     */
    private BSTNode<K> rotateRight(BSTNode<K> node) {
        BSTNode<K> g = node; // grandparent node
        BSTNode<K> p = g.getLeft(); // parent node
        BSTNode<K> c = p.getLeft(); // child node
        g.setLeft(p.getRight());
        p.setRight(g);
        return p;
    }
    /**
     * Deletes nodes from the AVL tree
     *
     * @param K key
     * @exception IllegalArguementException
     * 
     */
    @Override
    public void delete(K key) throws IllegalArgumentException {
        this.node = deleteHelper(this.node, key);
    }
    /**
     * Finds the smallest node
     *
     * @param BSTNode<K> node
     * @return K
     * 
     */
    private K smallestNode(BSTNode<K> node){
        if (node.getLeft() == null) {
            return node.getKey();
        } else {
            return smallestNode(node.getLeft());
        }
    }
    /**
     * Recursively deletes nodes from AVL tree
     *
     * @param K key
     * @return BSTNode<K> node
     */
    private BSTNode<K> deleteHelper(BSTNode<K> node, K key) {
        //Empty tree
        if (node == null) {
            return null;
        }
        //if the keys math
        if (key.equals(node.getKey())) {
            // node is the node to be removed
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            // if we get here, then n has 2 children
            K smallNodeKey = smallestNode(node.getRight());
            node.setKey(smallNodeKey);
            node.setRight( deleteHelper(node.getRight(), smallNodeKey ) );
            return node; 
        }
        //looks left
        else if (key.compareTo(node.getKey()) < 0) {
            node.setLeft( deleteHelper(node.getLeft(), key) );
            return node;
        }

        else {
            //looks right
            node.setRight( deleteHelper(node.getRight(), key) );
            return node;
        }
    }
    /**
     * Recursively searches for a node based on a key in an AVL tree
     *
     * @param K key
     * @param BSTNode<K> node
     * @exception IllegalArgumentException
     * @return boolean
     * 
     */
    private boolean searchHelper(K key, BSTNode<K> node) throws IllegalArgumentException{ 
        //empty tree
        if (node == null) {
            return false;
        }

        if (node.getKey().equals(key)) {
            return true;
        }

        if (key.compareTo(node.getKey()) < 0) {
            // key < this node's key left subtree
            return searchHelper(key, node.getLeft());
        }

        else {
            // key > this node's key right subtree
            return searchHelper(key, node.getRight());
        }
    }
    /**
     * Searches for a node in the AVL tree
     *
     * @param K key
     * @exception IllegalArgumentException
     * @return boolean
     */
    @Override
    public boolean search(K key) throws IllegalArgumentException {
       return searchHelper(key, this.node);
    }
    /**
     * Gets the balance factor of AVL Tree
     *
     * @param BSTNode<K> node
     * @exception int BF
     * 
     */
    private int getBalanceFactor(BSTNode<K> node) { 
        if (node== null) { 
            return 0; 
        }
  
        return computeHeight(node.getLeft()) - computeHeight(node.getRight()); 
    }
    /**
     * Helps print in order traversal of AVL Tree
     *
     * @param BSTNode<K> node
     * 
     */
    public void printHelper(BSTNode<K> node) {
        if (node == null) {
            inorder = "";
        }else {
            if (node.getLeft() != null) {
                printHelper(node.getLeft());
            }
            inorder += node.getKey() + " ";
            if (node.getRight() != null) {
                // key < this node's key; look in left subtree
                printHelper(node.getRight());
            }
        }
    }
    /**
     * Performs in-order traversal of AVL Tree
     * @return a String with all the keys, in order, with exactly one space between keys
     */
    @Override
    public String print() {
        printHelper(node);
        return inorder;
    }
    /**
     * Computes the height of the tree
     *
     * @param BSTNode<K> node
     * @return int height
     * 
     */
    public int computeHeight(BSTNode<K> node) {
        { 
            //empty tree
            if (node == null) 
                return 0; 

            //balance factor = 1 + max of left 
            return 1 + Math.max(computeHeight(node.left), computeHeight(node.right)); 
        } 
    }
    /**
     * Recursively checks to see if the tree is balanced
     *
     * @param BSTNode<K> node
     * @return boolean
     * 
     */
    public boolean checkForBalancedHelper(BSTNode<K> node) {

        //tree empty return true
        if (node == null) {
            return true; 
        }

        //Height of subtrees
        int rightHeight = computeHeight(node.getRight()); 
        int leftHeight = computeHeight(node.getLeft()); 
        //Makes sure all subtrees are height balance
        if (Math.abs(leftHeight - rightHeight) <= 1
                        && checkForBalancedHelper(node.right)
                        && checkForBalancedHelper(node.left))  
            return true; 

        //tree not height balanced
        return false; 
    }
    /**
     * Checks for balanced tree
     *
     * @param BSTNode<K> node
     * @exception int BF
     * 
     */
    @Override
    public boolean checkForBalancedTree() {
        return checkForBalancedHelper(node);
        
    }
    /**
     * Recursively checks for BST
     *
     * @param BSTNode<K> node
     * @return boolean
     * 
     */
    private boolean checkForBSTHelper(BSTNode<K> node)  {  
        if (node == null) {  
            return true; 
        }
        else if (node.getKey().compareTo(node.getLeft().getKey()) > 0) {
           return checkForBSTHelper(node.getLeft());
        }

        else {
            //looks right
            return checkForBSTHelper(node.getLeft());
        }
    }  
    /**
     * Checks if Binary Search Tree
     *
     * @return boolean
     * 
     */
    @Override
    public boolean checkForBinarySearchTree() {
        return checkForBSTHelper(node);

    }
}