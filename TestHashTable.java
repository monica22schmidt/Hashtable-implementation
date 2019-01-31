/**
 * Filename:   TestHashTable.java
 * Project:    p3
 * Authors:    TODO: add your name(s) and lecture numbers here
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   TODO: add assignment due date and time
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */

import java.util.NoSuchElementException; // expect to need
import static org.junit.Assert.*; 
import org.junit.Before;  // setUp method
import org.junit.After;   // tearDown method
import org.junit.Test;   

/** TODO: add class header comments here*/
public class TestHashTable{

    // TODO: add other fields that will be used by multiple tests

    // Allows us to create a new hash table before each test
    static HashTableADT<Integer, Object> ht;

    // TODO: add code that runs before each test method
    @Before
    public void setUp() throws Exception {
        ht = new HashTable<Integer, Object>();  
    }

    // TODO: add code that runs after each test method
    @After
    public void tearDown() throws Exception {
        ht = null;
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is empty upon initialization
     */
    @Test
    public void test1_IsEmpty() {
        assertEquals("size with 0 entries:", 0, ht.size());
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is not empty after adding one (K, V) pair
     */
    @Test
    public void test2_IsNotEmpty() {
        ht.put(1,"0001");
        int expected = 1;
        int actual = ht.size();
        assertEquals("size with one entry:",expected,actual);
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Other tests assume <int,Object> pairs,
     * this test checks that <Long,Object> pair also works.
     */
    @Test
    public void test3_Long_Object() {
        Long key = 9876543210L;
        Object expected = "" + key;		
        HashTableADT<Long,Object> table = 
                        new HashTable<Long,Object>();
        table.put(key, expected);
        Object actual = table.get(key);
        assertTrue("put-get of (Long,Object) pair",
                        expected.equals(actual));
    }

    /*
     * Tests that the value for a key is updated 
     * when tried to insert again.
     */
    @Test
    public void test4_Update() {
        try {
            ht.put(5,5);
            ht.put(5, 6);
            int expected = 6;
            int actual = (int)ht.get(5);               
            assertEquals("Updated value:",expected, actual);
        }catch(IllegalArgumentException e) {
            fail(e.getMessage());
        }catch(NoSuchElementException e) {
            fail(e.getMessage());
        }
    }

    /*
     * Tests that inserting many and removing one entry
     * from the hash table works
     */
    @Test(timeout=1000 * 10)
    public void test5_InsertManyRemoveOne() {
        try {
            for(int x = 1; x < 10000; x++) {
                ht.put(x, x);
            }
            ht.remove(10);

            ht.get(10);
        }catch(IllegalArgumentException e) {
            fail(e.getMessage());
        }catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Tests ability to insert many entries and 
     * and remove many entries from the hash table
     */
    @Test
    public void test6_InsertRemoveMany() {
        try {
            for(int x = 1; x < 100000; x++) {
                ht.put(x, x);
            }

            for(int x = 1; x < 100000; x++) {
                ht.remove(x);
            }
        }catch(IllegalArgumentException e) {
            fail(e.getMessage());
        }catch(NoSuchElementException e) {
            fail(e.getMessage());
        }
    }
    /*
     * Tests ability to insert many entries and 
     * and remove many entries from the hash table
     */
    @Test
    public void test7_InsertRetrieveMany() {
        try {
            for(int x = 1; x < 1000; x++) {
                ht.put(x, x);
              
            }

            for(int x = 1; x < 1000; x++) {

                ht.get(x);
            }
        }catch(IllegalArgumentException e) {
            fail(e.getMessage());
        }catch(NoSuchElementException e) {
            fail(e.getMessage());
        }
    } /*
     * Tests ability to insert many entries and 
     * and remove many entries from the hash table
     */
    @Test
    public void test8_InsertDeleteInsertGet() {
        try {
            for(int x = 1; x < 15; x++) {
                ht.put(x, x);
            }

            for(int x = 1; x < 15; x++) {
                ht.remove(x);
            }
            ht.put(1, 4);
            int value = (int)ht.get(1);
            if(value != 4) {
                fail("expected 4 received "+ value);
            }
        }catch(IllegalArgumentException e) {
            fail(e.getMessage());
        }catch(NoSuchElementException e) {
            fail(e.getMessage());
        }
    }
    public void test9_Insert3Values() {
        try {
            ht.put(5,10);
            ht.put(10,10);
            ht.put(15,10);
            ht.get(5);
            ht.get(15);
            ht.get(10); 
            
        }catch(IllegalArgumentException e) {
            fail(e.getMessage());
        }catch(NoSuchElementException e) {
            fail(e.getMessage());
        }
    }
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Other tests assume <int,Object> pairs,
     * this test checks that <Long,Object> pair also works.
     */
    @Test
    public void test3_String_Object() {
        String key = "Dog";
        Object expected = "" + key;     
        HashTableADT<String,Object> table = 
                        new HashTable<String,Object>();
        table.put(key, expected);
        Object actual = table.get(key);
        assertTrue("put-get of (Long,Object) pair",
                        expected.equals(actual));
    }
}
