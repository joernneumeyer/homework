/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurant;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hom
 */
public class QueueTest {
    Queue<String> instance = null;

    @Before
    public void setUp() {
        instance = new Queue<String>();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of empty method, of class Queue.
     */
    @Test
    public void testEmpty() {
        assertTrue("new q is empty", instance.empty());
    }
    
    /**
     * Test of get on empty Queue.
     */
    @Test(expected=NullPointerException.class)
    public void testEmptyGet() {
        String unAvialable= instance.get();
    }

    /**
     * Test of get on empty Queue.
     */
    @Test
    public void testEmptyGetAlt() {
        try{ 
            String unAvialable= instance.get();
            fail("Should have thrown an exception");
        } catch (NullPointerException npe) {
            assertTrue("All is well",true );
        } catch (Throwable towel) {
            fail("wrong thing thrown.");
        }
    }

    /**
     * Test of put method, of class Queue.
     */
    @Test
    public void testPut() {
        instance.put("A");
        assertFalse(" q with A is not empty", instance.empty());
    }

    /**
     * Test of get method, of class Queue.
     */
    @Test
    public void testGet() {
        instance.put("A");
        instance.put("B");
        instance.put("C");
        assertFalse("Three elements is not empty", instance.empty());
        assertEquals("A", instance.get());
        assertEquals("B", instance.get());
        assertEquals("C", instance.get());
        assertTrue("All elements gone", instance.empty());
    }

}