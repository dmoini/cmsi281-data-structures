package linked_yarn;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.rules.Timeout;

public class LinkedYarnGradingTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Grade record-keeping
    static int possible = 0, passed = 0;
    
    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    // Used as the basic empty LinkedYarn to test; the @Before
    // method is run before every @Test
    LinkedYarn ball;
    @Before
    public void init () {
        possible++;
        ball = new LinkedYarn();
    }
    
    // Used for grading, reports the total number of tests
    // passed over the total possible
    @AfterClass
    public static void gradeReport () {
        System.out.println("============================");
        System.out.println("Tests Complete");
        System.out.println(passed + " / " + possible + " passed!");
        if ((1.0 * passed / possible) >= 0.9) {
            System.out.println("[!] Nice job!"); // Automated acclaim!
        }
        System.out.println("============================");
    }
    
    
    // =================================================
    // Unit Tests
    // =================================================
    // For grading purposes, every method has ~3 tests, 
    // weighted equally and totaled for the score.
    // The tests increase in difficulty such that the
    // basics are unlabeled and harder tiers are tagged
    // t1, t2, t3, ...
    
    
    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testInit() {
        assertTrue(ball.isEmpty());
        assertEquals(0, ball.getSize());
        passed++;
    }

    // Basic Tests
    // -------------------------------------------------
    @Test
    public void testIsEmpty() {
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());
        passed++;
    }
    @Test
    public void testIsEmpty_t1() {
        ball.insert("a");
        ball.insert("a");
        ball.removeAll("a");
        assertTrue(ball.isEmpty());
        passed++;
    }
    @Test
    public void testIsEmpty_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("a");
        ball.removeAll("a");
        assertFalse(ball.isEmpty());
        ball.removeAll("b");
        assertTrue(ball.isEmpty());
        passed++;
    }

    @Test
    public void testGetSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        ball.insert("unique");
        assertEquals(3, ball.getSize());
        passed++;
    }
    @Test
    public void testGetSize_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(3, ball.getSize());
        passed++;
    }
    @Test
    public void testGetSize_t2() {
        ball.insert("u1");
        ball.insert("u2");
        ball.insert("u3");
        assertEquals(3, ball.getSize());
        passed++;
    }
    @Test
    public void testGetSize_t3() {
        ball.insert("u1");
        ball.insert("u2");
        ball.remove("u1");
        assertEquals(1, ball.getSize());
        ball.remove("u2");
        assertEquals(0, ball.getSize());
        passed++;
    }

    @Test
    public void testGetUniqueSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(1, ball.getUniqueSize());
        ball.insert("unique");
        assertEquals(2, ball.getUniqueSize());
        passed++;
    }
    public void testGetUniqueSize_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(1, ball.getUniqueSize());
        passed++;
    }
    @Test
    public void testGetUniqueSize_t2() {
        ball.insert("u1");
        ball.insert("u2");
        ball.insert("u3");
        assertEquals(3, ball.getUniqueSize());
        passed++;
    }
    @Test
    public void testGetUniqueSize_t3() {
        ball.insert("u1");
        ball.insert("u2");
        ball.remove("u1");
        assertEquals(1, ball.getUniqueSize());
        ball.remove("u2");
        assertEquals(0, ball.getUniqueSize());
        passed++;
    }

    // LinkedYarn Manipulation Tests
    // -------------------------------------------------
    @Test
    public void testInsert() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        passed++;
    }
    @Test
    public void testInsert_t1() {
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("dup");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertEquals(3, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        passed++;
    }
    @Test
    public void testInsert_t2() {
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("dup");
        ball.remove("unique");
        assertTrue(ball.contains("dup"));
        assertFalse(ball.contains("unique"));
        assertEquals(2, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        passed++;
    }
    @Test
    public void testInsert_t3() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup2");
        ball.insert("dup3");
        ball.insert("dup3");
        ball.remove("dup2");
        assertEquals(4, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        passed++;
    }

    @Test
    public void testRemove() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        int dups = ball.remove("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        assertEquals(1, dups);
        passed++;
    }
    @Test
    public void testRemove_t1() {
        ball.insert("uni1");
        int dups = ball.remove("uni1");
        assertEquals(0, ball.getSize());
        assertEquals(0, dups);
        assertFalse(ball.contains("uni1"));
        passed++;
    }
    @Test
    public void testRemove_t2() {
        ball.remove("uni1");
        ball.insert("uni1");
        ball.remove("uni");
        assertEquals(1, ball.getSize());
        assertTrue(ball.contains("uni1"));
        ball.insert("uni2");
        ball.insert("uni3");
        ball.remove("uni1");
        assertEquals(2, ball.getSize());
        assertFalse(ball.contains("uni1"));
        passed++;
    }
    @Test
    public void testRemove_t3() {
        ball.insert("dup1");
        ball.insert("dup1");
        ball.insert("dup2");
        ball.insert("dup2");
        ball.insert("uni1");
        ball.remove("uni1");
        assertEquals(4, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        ball.insert("uni2");
        ball.insert("uni3");
        int dups = ball.remove("dup1");
        assertEquals(1, dups);
        dups = ball.remove("dup1");
        assertEquals(0, dups);
        assertEquals(4, ball.getSize());
        assertEquals(3, ball.getUniqueSize());
        assertFalse(ball.contains("dup1"));
        passed++;
    }

    @Test
    public void testRemoveAll() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(3, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        ball.removeAll("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        passed++;
    }
    @Test
    public void testRemoveAll_t1() {
        ball.removeAll("uni1");
        ball.insert("uni1");
        ball.removeAll("uni1");
        assertEquals(0, ball.getSize());
        assertFalse(ball.contains("uni1"));
        passed++;
    }
    @Test
    public void testRemoveAll_t2() {
        ball.insert("uni1");
        ball.insert("uni2");
        ball.insert("uni3");
        ball.removeAll("uni1");
        ball.removeAll("uni2");
        assertEquals(1, ball.getSize());
        assertFalse(ball.contains("uni1"));
        passed++;
    }
    @Test
    public void testRemoveAll_t3() {
        ball.insert("dup1");
        ball.insert("dup1");
        ball.insert("dup2");
        ball.insert("dup2");
        ball.insert("uni1");
        ball.removeAll("dup1");
        assertEquals(3, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        assertFalse(ball.contains("dup1"));
        ball.removeAll("dup2");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        ball.removeAll("uni1");
        assertEquals(0, ball.getSize());
        assertEquals(0, ball.getUniqueSize());
        passed++;
    }

    @Test
    public void testCount() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(2, ball.count("dup"));
        assertEquals(1, ball.count("unique"));
        assertEquals(0, ball.count("forneymon"));
        passed++;
    }
    @Test
    public void testCount_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(3, ball.count("dup"));
        ball.removeAll("dup");
        assertEquals(0, ball.count("dup"));
        passed++;
    }
    @Test
    public void testCount_t2() {
        ball.insert("dup");
        ball.insert("dup2");
        ball.insert("dup");
        ball.insert("dup2");
        assertEquals(2, ball.count("dup"));
        assertEquals(2, ball.count("dup2"));
        ball.removeAll("dup");
        assertEquals(0, ball.count("dup"));
        ball.remove("dup2");
        assertEquals(1, ball.count("dup2"));
        passed++;
    }

    @Test
    public void testContains() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));
        passed++;
    }
    // This is tested pretty much everywhere so...


    @Test
    public void testGetMostCommon() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals("dup", ball.getMostCommon());
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));
        passed++;
    }
    @Test
    public void testGetMostCommon_t1() {
        assertEquals(null, ball.getMostCommon());
        passed++;
    }
    @Test
    public void testGetMostCommon_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("a") || mc.equals("b") || mc.equals("c"));
        passed++;
    }

    // Iterator Tests
    // -------------------------------------------------
    @Test
    public void testIteratorBasics() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        LinkedYarn.Iterator it = ball.getIterator();

        // Test next()
        LinkedYarn dolly = new LinkedYarn(ball);
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasNext());
        
        // Test prev()
        dolly = new LinkedYarn(ball);
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasPrev()) {it.prev();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasPrev());
        
        int countOfReplaced = ball.count(it.getString());
        it.replaceAll("replaced!");
        assertEquals(countOfReplaced, ball.count("replaced!"));
        assertTrue(it.isValid());
        
        ball.insert("c");
        assertFalse(it.isValid());
        
        passed++;
    }
    @Test
    public void testIteratorBasics_t1() {
        try {
            LinkedYarn.Iterator it = ball.getIterator();
        } catch (Exception e) {
            if (!(e instanceof IllegalStateException)) {
                fail();
            }
        }
        passed++;
    }
    
    @Test
    public void testIteratorValidity() {
        ball.insert("a");
        LinkedYarn.Iterator it = ball.getIterator();
        ball.insert("a");
        assertFalse(it.isValid());
        passed++;
    }
    @Test
    public void testIteratorValidity_t1() {
        ball.insert("a");
        LinkedYarn.Iterator it = ball.getIterator();
        ball.remove("a");
        assertFalse(it.isValid());
        passed++;
    }
    @Test
    public void testIteratorValidity_t2() {
        ball.insert("a");
        LinkedYarn.Iterator it1 = ball.getIterator();
        ball.insert("b");
        LinkedYarn.Iterator it2 = ball.getIterator();
        ball.remove("b");
        assertFalse(it1.isValid());
        assertFalse(it2.isValid());
        passed++;
    }
    @Test
    public void testIteratorValidity_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("a");
        LinkedYarn.Iterator it1 = ball.getIterator();
        LinkedYarn.Iterator it2 = y2.getIterator();
        ball.swap(y2);
        assertFalse(it1.isValid());
        assertFalse(it2.isValid());
        passed++;
    }
    
    @Test
    public void testIteratorNext() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        LinkedYarn dolly = new LinkedYarn(ball);
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasNext());
        passed++;
    }
    @Test
    public void testIteratorNext_t1() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        LinkedYarn dolly = new LinkedYarn(ball);
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasNext());
        passed++;
    }
    @Test
    public void testIteratorNext_t2() {
        try {
            ball.insert("a");
            LinkedYarn.Iterator it = ball.getIterator();
            it.next();
            it.next();
        } catch (Exception e) {
            if (!(e instanceof NoSuchElementException)) {
                fail();
            }
        }
        passed++;
    }
    
    @Test
    public void testIteratorPrev() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        LinkedYarn dolly = new LinkedYarn(ball);
        while (it.hasNext()) {it.next();}
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasPrev()) {it.prev();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasPrev());
        passed++;
    }
    @Test
    public void testIteratorPrev_t1() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        LinkedYarn dolly = new LinkedYarn(ball);
        while (it.hasNext()) {it.next();}
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasPrev()) {it.prev();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasPrev());
        passed++;
    }
    @Test
    public void testIteratorPrev_t2() {
        try {
            ball.insert("a");
            ball.insert("a");
            ball.insert("a");
            LinkedYarn.Iterator it = ball.getIterator();
            it.next();
            it.next();
            it.prev();
            it.prev();
            it.prev();
        } catch (Exception e) {
            if (!(e instanceof NoSuchElementException)) {
                fail();
            }
        }
        passed++;
    }
    @Test
    public void testIteratorPrev_t3() {
        try {
            ball.insert("a");
            LinkedYarn.Iterator it = ball.getIterator();
            ball.insert("a");
            it.prev();
        } catch (Exception e) {
            if (!(e instanceof IllegalStateException)) {
                fail();
            }
        }
        passed++;
    }
    
    // Small helper function to test replaceAll
    public static void advanceIteratorTo (String toFind, LinkedYarn.Iterator it) {
        while (it.hasNext()) {
            if (it.getString().equals(toFind)) {
                return;
            }
            it.next();
        }
    }
    
    @Test
    public void testIteratorReplaceAll() {
        ball.insert("a");
        LinkedYarn.Iterator it = ball.getIterator();
        it.replaceAll("b");
        assertTrue(it.isValid());
        assertEquals(1, ball.count("b"));
        assertEquals(0, ball.count("a"));
        passed++;
    }
    @Test
    public void testIteratorReplaceAll_t1() {
        ball.insert("a");
        ball.insert("a");
        LinkedYarn.Iterator it = ball.getIterator();
        it.replaceAll("b");
        assertTrue(it.isValid());
        assertEquals(2, ball.count("b"));
        assertEquals(0, ball.count("a"));
        passed++;
    }
    @Test
    public void testIteratorReplaceAll_t2() {
        ball.insert("a");
        ball.insert("c");
        ball.insert("c");
        ball.insert("a");
        ball.insert("d");
        LinkedYarn.Iterator it = ball.getIterator();
        advanceIteratorTo("c", it);
        it.replaceAll("b");
        assertTrue(it.isValid());
        assertEquals(2, ball.count("b"));
        assertEquals(0, ball.count("c"));
        passed++;
    }
    @Test
    public void testIteratorReplaceAll_t3() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        advanceIteratorTo("a", it);
        it.replaceAll("a");
        assertTrue(it.isValid());
        assertEquals(2, ball.count("a"));
        assertEquals(2, ball.count("b"));
        passed++;
    }
    @Test
    public void testIteratorReplaceAll_t4() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        advanceIteratorTo("b", it);
        it.replaceAll("a");
        assertTrue(it.isValid());
        assertEquals(4, ball.count("a"));
        assertEquals(0, ball.count("b"));
        passed++;
    }
    @Test
    public void testIteratorReplaceAll_t5() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        advanceIteratorTo("a", it);
        it.replaceAll("c");
        assertTrue(it.isValid());
        assertEquals(0, ball.count("a"));
        assertEquals(3, ball.count("c"));
        it = ball.getIterator();
        LinkedYarn dolly = new LinkedYarn(ball);
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        passed++;
    }
    
    @Test
    public void testIteratorLinks() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("c");
        ball.insert("b");
        ball.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        while (it.hasNext()) { it.next(); }
        while (it.hasPrev()) { it.prev(); }
        passed++;
    }
    @Test
    public void testIteratorLinks_t1() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("c");
        ball.insert("d");
        ball.insert("d");
        ball.insert("b");
        ball.insert("c");
        ball.insert("d");
        ball.removeAll("d");
        LinkedYarn.Iterator it = ball.getIterator();
        while (it.hasNext()) { it.next(); }
        while (it.hasPrev()) { it.prev(); }
        passed++;
    }
    @Test
    public void testIteratorLinks_t2() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("c");
        ball.insert("d");
        ball.insert("d");
        ball.insert("b");
        ball.insert("c");
        ball.insert("d");
        ball.remove("b");
        LinkedYarn.Iterator it = ball.getIterator();
        while (it.hasNext()) { it.next(); }
        while (it.hasPrev()) { it.prev(); }
        passed++;
    }
    
    // Inter-LinkedYarn Tests
    // -------------------------------------------------
    @Test
    public void testCopy() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        LinkedYarn dolly = new LinkedYarn(ball);
        assertEquals(2, dolly.count("dup"));
        assertEquals(1, dolly.count("unique"));
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
        passed++;
    }
    @Test
    public void testCopy_t1() {
        LinkedYarn dolly = new LinkedYarn(ball);
        ball.insert("a");
        assertFalse(dolly.contains("a"));
        passed++;
    }
    @Test
    public void testCopy_t2() {
        ball.insert("a");
        LinkedYarn dolly = new LinkedYarn(ball);
        dolly.insert("b");
        LinkedYarn superDolly = new LinkedYarn(dolly);
        superDolly.insert("c");
        assertTrue(superDolly.contains("a"));
        assertTrue(superDolly.contains("b"));
        assertFalse(dolly.contains("c"));
        passed++;
    }
    @Test
    public void testCopy_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.insert("a");
        LinkedYarn dolly = new LinkedYarn(ball);
        assertEquals(2, dolly.count("a"));
        assertEquals(1, dolly.count("b"));
        assertEquals(1, dolly.count("c"));
        passed++;
    }

    @Test
    public void testSwap() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));
        passed++;
    }
    @Test
    public void testSwap_t1() {
        LinkedYarn y2 = new LinkedYarn();
        ball.insert("a");
        y2.swap(ball);
        assertTrue(ball.isEmpty());
        assertFalse(y2.isEmpty());
        passed++;
    }
    @Test
    public void testSwap_t2() {
        LinkedYarn y2 = new LinkedYarn();
        LinkedYarn y3 = new LinkedYarn();
        y2.insert("a");
        ball.insert("b");
        y2.swap(ball);
        y3.swap(y2);
        assertTrue(y2.isEmpty());
        assertTrue(ball.contains("a"));
        assertTrue(y3.contains("b"));
        ball.insert("c");
        assertFalse(y2.contains("c"));
        passed++;
    }
    @Test
    public void testSwap_t3() {
        ball.insert("a");
        ball.swap(ball);
        assertTrue(ball.contains("a"));
        assertEquals(1, ball.getSize());
        passed++;
    }
    @Test
    public void testSwap_t4() {
        ball.insert("a");
        ball.insert("b");
        LinkedYarn dolly = new LinkedYarn(ball);
        dolly.insert("c");
        LinkedYarn.Iterator it = ball.getIterator();
        ball.swap(dolly);
        assertFalse(it.isValid());
        passed++;
    }

    // Static Method Tests
    // -------------------------------------------------
    @Test
    public void testKnit() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.knit(y1, y2);
        assertEquals(3, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertEquals(1, y3.count("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        passed++;
    }
    @Test
    public void testKnit_t1() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("b");
        LinkedYarn y3 = LinkedYarn.knit(ball, y1);
        assertEquals(2, y3.getSize());
        assertEquals(2, y3.getUniqueSize());
        passed++;
    }
    @Test
    public void testKnit_t2() {
        ball.insert("a");
        ball.insert("a");
        ball = LinkedYarn.knit(ball, ball);
        assertEquals(4, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        passed++;
    }
    @Test
    public void testKnit_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.insert("a");
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("b");
        y1.insert("d");
        LinkedYarn result = LinkedYarn.knit(ball, y1);
        assertEquals(3, result.count("a"));
        assertEquals(2, result.count("b"));
        assertEquals(1, result.count("c"));
        assertEquals(1, result.count("d"));
        passed++;
    }

    @Test
    public void testTear() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.tear(y1, y2);
        assertEquals(1, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        passed++;
    }
    @Test
    public void testTear_t1() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("b");
        LinkedYarn y2 = LinkedYarn.tear(ball, y1);
        assertEquals(0, y2.getSize());
        assertFalse(y2.contains("a"));
        passed++;
    }
    @Test
    public void testTear_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("a");
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("a");
        LinkedYarn y2 = LinkedYarn.tear(ball, y1);
        assertEquals(1, y2.getSize());
        assertFalse(y2.contains("a"));
        passed++;
    }
    @Test
    public void testTear_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.insert("a");
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("a");
        y1.insert("b");
        y1.insert("d");
        LinkedYarn result = LinkedYarn.tear(ball, y1);
        assertEquals(1, result.count("a"));
        assertEquals(0, result.count("b"));
        assertEquals(1, result.count("c"));
        assertEquals(0, result.count("d"));
        passed++;
    }
    @Test
    public void testTear_t4() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.insert("a");
        LinkedYarn result = LinkedYarn.tear(ball, ball);
        assertTrue(result.isEmpty());
        assertFalse(ball.isEmpty());
        passed++;
    }

    @Test
    public void testSameYarn() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(LinkedYarn.sameYarn(y1, y2));
        assertTrue(LinkedYarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(LinkedYarn.sameYarn(y1, y2));
        passed++;
    }
    @Test
    public void testSameYarn_t1() {
        LinkedYarn y1 = new LinkedYarn();
        assertTrue(LinkedYarn.sameYarn(ball, y1));
        assertTrue(LinkedYarn.sameYarn(y1, y1));
        y1.insert("a");
        assertTrue(LinkedYarn.sameYarn(y1, y1));
        passed++;
    }
    @Test
    public void testSameYarn_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("b");
        y1.insert("a");
        assertFalse(LinkedYarn.sameYarn(ball, y1));
        ball.removeAll("b");
        y1.removeAll("b");
        assertTrue(LinkedYarn.sameYarn(ball, y1));
        passed++;
    }
    @Test
    public void testSameYarn_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        ball.insert("c");
        ball.insert("a");
        ball.insert("d");
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("b");
        y1.insert("a");
        y1.insert("b");
        y1.insert("a");
        y1.insert("c");
        assertFalse(LinkedYarn.sameYarn(ball, y1));
        ball.removeAll("d");
        assertTrue(LinkedYarn.sameYarn(ball, y1));
        passed++;
    }
    @Test
    public void testSameYarn_t4() {
        LinkedYarn y1 = new LinkedYarn();
        assertTrue(LinkedYarn.sameYarn(ball, y1));
        assertTrue(LinkedYarn.sameYarn(y1, ball));
        passed++;
    }

}
