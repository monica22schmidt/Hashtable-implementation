/**
 * Filename:   Profile.java
 * Project:    p3
 * Authors:    Monica Schmidt 002
 *
 * Semester:   Fall 2018
 * Course:     CS400
 *
 * Due Date:   10/29 10:00 pm
 * Version:    1.0
 *
 * Credits:    None
 *
 * Bugs:       None
 */

import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Profile<K extends Comparable<K>, V> {

    HashTableADT<K, V> hashtable; //hashtable variable
    TreeMap<K, V> treemap; // treemap variable
    /**
     * No argument constructor that creates a new profile to compare hashtable and tree map
     */
    public Profile() {
        this.hashtable = new HashTable(); //new instance of HashTable
        this.treemap = new TreeMap(); //new instance of TreeMap
    }
    /**
     * Inserts key and value into a hashtable and treemap
     * @param Key
     * @param Value
     */
    public void insert(K key, V value) {
        try {
            hashtable.put(key, value);
            treemap.put(key, value);
        }catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Gets the value from the given key in treemap and hashtable
     * @param initialCapacity
     * @param loadFactor
     */
    public void retrieve(K key) {
        try {
            hashtable.get(key);
            treemap.get(key);
        }catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Expected 1 argument: <num_elements>");
            System.exit(1);
        }
        int numElements = Integer.parseInt(args[0]);
        Profile<Integer, Integer> profile = new Profile<Integer, Integer>();
        for (int x = 0; x < numElements; x++) {
            profile.insert(x, x*4);
        }
        for (int x = 0; x < numElements; x++) {
            profile.retrieve(x);
        }

        String msg = String.format("Successfully inserted and retreived %d elements into the hash table and treemap", numElements);
        System.out.println(msg);
    }
}
