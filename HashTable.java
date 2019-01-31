/**
 * Filename:   HashTable.java
 * Project:    p3
 * Authors:    Monica Schmidt 002
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   10/19 10pm
 * Version:    1.0
 * 
 * Credits:    None
 * 
 * Bugs:       Only emough memory for 4 billion items
 */


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;


// I used array buckets to deal with collision. I used only 2 buckets per hash index. 
// 
// Array buckets: a 2D array 
//
// I used the hashCode function of java to get a unique hashCode for the key. I then 
// used the modulo symbol and the capacity to create a valid hash index
//       
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
    /**
     * The Node class allows us to store key value pairs. Each node has a key and value to create
     * unique identifiers. It also allows for easy access to the values for each node, by using
     * accessors and mutators. Each node also stores a hashCode to find its unique place in memory
     * @author Monica Schmidt
     *
     * @param <KK>
     * @param <VV>
     */
    public class Node<KK,VV>{
        private KK key;
        private VV value;
        public Node() {
            this.key = null;
            this.value = null;
        }
        /**
         * Constructor creates a new node with unique a key and value
         * @param keyy
         * @param val
         */
        public Node(KK keyy, VV val) {
            this.key = keyy;
            this.value = val;
        }
        /**
         * Allows us to access the value of the node
         * @return value
         */
        private VV getValue() {
            return this.value;
        }
        /**
         * Allows us to access the key of the node
         * @return key
         */
        private KK getKey() {
            return this.key; 
        }
        /**
         * Sets key to a different key
         * @param k
         */
        private void setKey(KK k) {
            this.key = k; 
        }
        /**
         * Sets value to a different value
         * @param v
         */
        private void setValue(VV v) {
            this.value = v;
        }


    }

    private int capacity; //size of the whole table 
    private double LF; // the ratio of keys to table size
    private Node[][] table; //the actual table of values
    private int size;
    /**
     * No arg constructor of the hash table.
     * Initializes the table to be non-null with buckets
     * Creates an initial capacity of 1 and load factor of .01
     * This allows us to avoid a divide by zero error
     */
    public HashTable() {
        this.capacity = 10;
        this.table = new Node[this.capacity][2];
        this.LF = .75;
        this.size = 0;
    }

    /**
     * Creates a new hash table of initial capacity and 2 buckets. Sets up an initial load 
     * factor to abide by for resizing.
     * @param initialCapacity
     * @param loadFactor
     */
    public HashTable(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.table = new Node[this.capacity][2];
        this.LF = loadFactor;
        this.size = 0;
    }
    /**
     * Creates a valid hash index given a key
     * @param key
     * @return hashIndex
     */
    private int hashIndex(Object key) {
        //Gets the hashcode of the key
        int hashCode = key.hashCode();
        //Creates valid hashIndex
        int hashIndex = hashCode % this.capacity;
        return hashIndex;
    }

    /** 
     * insert a <key,value> pair entry into the hash table 
     * if the key already exists in the table, 
     * replace existing value for that key with the 
     * value specified in this call to put.
     * 
     * permits null values but not null keys and permits the same value 
     * to be paired with different key
     * 
     * throw IllegalArgumentException when key is null
     * @param K key
     * @param V Value
     */
    @Override
    public void put(K key, V value) throws IllegalArgumentException {
        //Creates new node
        Node node = new Node(key, value);
        //gets valid hashIndex
        int hashIndex = hashIndex(key);
        //Accounts for null key
        if (key == null) {
            throw new IllegalArgumentException();
        }


        //Checks if there is a key at the hashIndex with the same key
        if (table[hashIndex][0] != null && (table[hashIndex][0].getKey() == key || table[hashIndex][0].getKey().equals(key))) {
            table[hashIndex][0].setValue(value);
        }
        //Checks if there is a key at the hashIndex with the same key
        else if (table[hashIndex][1] != null && (table[hashIndex][1] == key || table[hashIndex][1].getKey().equals(key))) {
            table[hashIndex][1].setValue(value); 
        }
        //Checks if the hashIndex is null bucket 1
        else if(table[hashIndex][0] == null) {
            table[hashIndex][0] = node;
            this.size++;
        }
        //Checks if the hashIndex is null bucket 2
        else if (table[hashIndex][1] == null){
            table[hashIndex][1] = node;
            this.size++;
        }
        if(this.size/this.capacity >= .3) {
            resize(); 
        }

        //checks to see if the table needs to be resized 

    } 
    /**
     * Creates a new table and rehashes the nodes according to the new capacity
     * Up sizing array
     */
    private void resize() {
        //Table load factor equals guideline load factor resize
        Node [][] temp = table; // temp array to hold the nodes
        this.capacity = (this.capacity*2)+1; //new capacity for the table
        table = new Node[this.capacity][2]; //new hash table 
        //Iterates through the table and the buckets to add them to the new table
        for(int x = 0; x< temp.length; x++) {
            for(int y = 0; y<temp[0].length; y++) {
                //new hashIndex for each node
                if(temp[x][y] !=null) {
                    int hashIndex = hashIndex(temp[x][y].getKey());
                    //accounts for collisions
                    if(table[hashIndex][0] == null) {
                        table[hashIndex][0] = temp[x][y];
                    }else if (table[hashIndex][1] == null){
                        table[hashIndex][1] = temp[x][y];
                    }
                }

            }
        }
    }
    /**
     * Creates a new table and rehashes the nodes according to the new capacity
     * Down sizing array
     */
    private void resizeDown() {
        //Table load factor equals guideline load factor resize
        Node [][] temp = table; // temp array to hold the nodes
        this.capacity = ((this.capacity-1) / 2)+1; //new capacity for the table
        table = new Node[this.capacity][2]; //new hash table 
        //Iterates through the table and the buckets to add them to the new table
        for(int x = 0; x< temp.length; x++) {
            for(int y = 0; y<temp[0].length; y++) {
                //new hashIndex for each node
                if(temp[x][y] !=null) {
                    int hashIndex = hashIndex(temp[x][y].getKey());
                    //accounts for collisions
                    if(table[hashIndex][0] == null) {
                        table[hashIndex][0] = temp[x][y];
                    }else if (table[hashIndex][1] == null){
                        table[hashIndex][1] = temp[x][y];
                    }
                }

            }
        }
    }
    /** 
     * return the value associated with the given key.
     * throw IllegalArgumentException if key is null 
     * throw NoSuchElementException if key does not exist 
     */
    @Override
    public V get(K key) throws IllegalArgumentException, NoSuchElementException {
        V value1 = null;
        //Checks if key is null
        if (key == null) {
            throw new IllegalArgumentException();
        }
        //Checks if the element in the array is null if not checks key
        else if (table[hashIndex(key)][0]!= null && (table[hashIndex(key)][0].key == key|| table[hashIndex(key)][0].getKey().equals(key))) { 
            value1 = (V)table[hashIndex(key)][0].value;
            return value1;
        }
        //Checks if the element in the array is null if not checks key
        else if (table[hashIndex(key)][1]!= null && (table[hashIndex(key)][1].key == key|| table[hashIndex(key)][1].getKey().equals(key))) { 
            value1 = (V)table[hashIndex(key)][1].value;
            return value1;
        }else {
            //Key does not exist
            throw new NoSuchElementException();
        }
    }

    /** 
     * remove the (key,value) entry for the specified key
     * throw IllegalArgumentException if key is null 
     * throw NoSuchElementException if key does not exist in the tree 
     */
    @Override
    public void remove(K key) throws IllegalArgumentException, NoSuchElementException {
        //Checks if key is null
        if (key == null) {
            throw new IllegalArgumentException();
        }
        //Checks if the element in the array is null if not checks key
        else if (table[hashIndex(key)][0]!= null && (table[hashIndex(key)][0].key == key|| table[hashIndex(key)][0].getKey().equals(key))) { 
            table[hashIndex(key)][0] = null;
            this.size--;

        }
        //Checks if the element in the array is null if not checks key
        else if (table[hashIndex(key)][1]!= null && (table[hashIndex(key)][1].key == key|| table[hashIndex(key)][1].getKey().equals(key))) { 
            table[hashIndex(key)][1] = null;
            this.size--;
        }else{
            throw new NoSuchElementException();
        }
        if (this.size/this.capacity <=.1) {
            resizeDown();
        }
    }

    /**
     * Gets the size of the hashtable and returns it
     * @return  the number of keys in the hash table 
     **/
    @Override
    public int size() {
        return this.size;
    }

}
