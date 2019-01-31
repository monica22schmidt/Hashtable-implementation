/**
 * Filename:   TestAVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, Monica Schmidt
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002
 * 
 * Due Date:   as specified in Canvas
 * Version:    1.0
 * 
 * Credits:    
 * 
 * Bugs:       Tests not independent of each other.
 */






import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.lang.IllegalArgumentException;
import org.junit.Before;
import org.junit.Test;

/** TODO: add class header comments here*/
public class TestAVLTree {
    private AVLTree<Integer> tree;
    @Before 
    public void beforeTest() {
        this.tree = new AVLTree<Integer>();
    }

    /**
     * Tests that an AVLTree is empty upon initialization.
     */
    @Test
    public void test01isEmpty() {
        
        assertTrue(tree.isEmpty());
    }

    /**
     * Tests that an AVLTree is not empty after adding a node.
     */
    @Test
    public void test02isNotEmpty() {
        try {
            tree.insert(1);
            assertFalse(tree.isEmpty());
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests functionality of a single delete following several inserts.
     */
    @Test
    public void test03insertManyDeleteOne() {
        try {
            tree.insert(3);
            tree.insert(1);
            tree.insert(2);
            tree.insert(5);
            tree.insert(4);
            tree.delete(1);
            assertFalse(tree.search(1));
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests functionality of many deletes following several inserts.
     */
    @Test
    public void test04insertManyDeleteMany() {
        // TODO: implement this test
        try {
            tree.insert(3);
            tree.insert(1);
            tree.insert(2);
            tree.insert(5);
            tree.insert(4);
            tree.delete(1); 
            tree.delete(2);
            tree.delete(5);
            assertFalse(tree.search(1));
            assertFalse(tree.search(2));
            assertFalse(tree.search(5));
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality duplicate exception thrown
     */
    @Test
    public void test05insertDuplicate() {
        try {
            tree.insert(3);
            tree.insert(3);
            fail("Did not throw duplicate key");
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
  
//    /**
//     * Tests functionality in-order print
//     */
//    @Test
//    public void test06Print() {
//        try {
//            tree.insert(20);
//            tree.insert(40);
//            tree.insert(10);
//            tree.insert(7);
//            tree.insert(5);
//            tree.insert(8);
//            tree.insert(30);
//            tree.insert(50);
//            tree.insert(60);
//            tree.insert(35);
//            tree.insert(25);
//            tree.insert(15);
//            tree.insert(11);
//            assertEquals("5 7 8 10 11 15 20 25 30 35 40 50 60 " , tree.print());
//        } catch (DuplicateKeyException e) {
//            System.out.println(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    /**
//     * Tests functionality check Balance
//     */
//    @Test
//    public void test08checkBalanceFalse() {
//        try {
//            tree.insert(3);
//            tree.insert(2);
//            tree.insert(1);
//            assertTrue(tree.checkForBalancedTree());
//        } catch (DuplicateKeyException e) {
//            System.out.println(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    /**
//     * Tests functionality of check Balance
//     */
//    @Test
//    public void test09checkBalanceTrue() {
//        try {
//            tree.insert(2);
//            tree.insert(3);
//            tree.insert(1);
//            assertTrue(tree.checkForBalancedTree());
//        } catch (DuplicateKeyException e) {
//            System.out.println(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    /**
     * Tests functionality many inserts
     */
    @Test
    public void test06InsertMany() {
        try {
         for(int i = 0; i < 1000; i++) {
            tree.insert(i);
         }
         assertTrue(tree.checkForBalancedTree());
         
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
//    /**
//     * Tests functionality check for BST
//     */
//    @Test
//    public void test07BST() {
//        try {
//            tree.insert(3);
//            tree.insert(2);
//            tree.insert(1);
//         assertTrue(tree.checkForBalancedTree());
//        } catch (DuplicateKeyException e) {
//            System.out.println(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    /**
     * Tests functionality of inserting deleting and inserting again
     */
    @Test
    public void test07InsertDeleteInsertAgain() {
        try {
            tree.insert(3);
            tree.delete(3);
            tree.insert(1);
            assertFalse(tree.search(3));
            assertTrue(tree.search(1));
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests functionality of inserting a few and deleting a few
     */
    @Test
    public void test08InsertFewDeleteFew() {
        try {
            for(int i = 0; i < 4; i++) {
                tree.insert(i+1); 
            }
            for(int i = 0; i < 4; i++) {
                tree.delete(i+1); 
            }
            assertTrue(tree.isEmpty());
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality of isEmpty after inserting and deleting 
     */
    @Test
    public void test09CheckIfEmptyAfterInsertDelete() {
        try {
            for(int i = 0; i < 4; i++) {
                tree.insert(i+1); 
            }
            for(int i = 0; i < 4; i++) {
                tree.delete(i+1); 
            }
            assertTrue(tree.isEmpty());
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests functionality of many inserts and deletes 
     */
    @Test
    public void test10InsertManyDeleteMany() {
        try {
            for(int i = 0; i < 100; i++) {
                tree.insert(i+1); 
            }
            for(int i = 0; i < 100; i++) {
                tree.delete(i+1); 
            }
            assertTrue(tree.isEmpty());
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality of isEmpty after inserting deleting and inserting again
     */
    @Test
    public void test11CheckIfNotEmptyAfterInsertDelete() {
        try {
            for(int i = 0; i < 4; i++) {
                tree.insert(i+1); 
            }
            for(int i = 0; i < 4; i++) {
                tree.delete(i+1); 
            }
            tree.insert(1);
            assertFalse(tree.isEmpty());
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality of deleting on empty tree
     */
    @Test
    public void test12DeleteOnEmptyTree() {
        try { 
            tree.delete(null);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Tests functionality of searching for one item not in tree
     */
    @Test
    public void test13SearchNotInTree() {
        try { 
            tree.insert(1);
            tree.delete(1);
            assertFalse(tree.search(1));
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality of search one item in tree
     */
    @Test
    public void test14SearchInTree() {
        try { 
            tree.insert(1);
            assertTrue(tree.search(1));
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality of search with many items
     */
    @Test
    public void test15SearchTreeWithManyItems() {
        try { 
            for(int i = 1; i < 25; i+=2) {
                tree.insert(i);
            }
            assertTrue(tree.search(23));
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality of search with many items
     */
    @Test
    public void test16SearchTreeWithManyItemsFalse() {
        try { 
            for(int i = 1; i < 25; i+=2) {
                tree.insert(i);
            }
            assertFalse(tree.search(20));
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality insert balancing
     */
    @Test
    public void test17InsertManyBalance() {
        try { 
            for(int i = 1; i < 100; i++) {
                tree.insert(i);
            }
            
            assertTrue(tree.checkForBalancedTree());
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
    /**
     * Tests functionality insert balancing
     */
    @Test
    public void test18InsertFewBalance() {
        try { 
            for(int i = 1; i < 25; i+=2) {
                tree.insert(i);
            }
          
            assertTrue(tree.checkForBalancedTree());
        } catch (DuplicateKeyException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }
}