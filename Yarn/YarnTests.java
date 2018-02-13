package yarn;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.Timeout;

public class YarnGradingTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Grade record-keeping
    static int possible = 0, passed = 0;
    
    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    // Used as the basic empty Yarn to test; the @Before
    // method is run before every @Test
    Yarn ball;
    @Before
    public void init () {
        possible++;
        ball = new Yarn();
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
        assertEquals(ball.getSize(), 2);
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        passed++;
    }
    @Test
    public void testGetSize_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 3);
        passed++;
    }
    @Test
    public void testGetSize_t2() {
        ball.insert("u1");
        ball.insert("u2");
        ball.insert("u3");
        assertEquals(ball.getSize(), 3);
        passed++;
    }

    @Test
    public void testGetUniqueSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
        ball.insert("unique");
        assertEquals(ball.getUniqueSize(), 2);
        passed++;
    }
    public void testGetUniqueSize_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
        passed++;
    }
    @Test
    public void testGetUniqueSize_t2() {
        ball.insert("u1");
        ball.insert("u2");
        ball.insert("u3");
        assertEquals(ball.getUniqueSize(), 3);
        passed++;
    }
    @Test
    public void testGetUniqueSize_t3() {
        ball.insert("u1");
        ball.insert("u2");
        ball.remove("u1");
        assertEquals(ball.getUniqueSize(), 1);
        ball.remove("u2");
        assertEquals(ball.getUniqueSize(), 0);
        passed++;
    }

    // Yarn Manipulation Tests
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
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
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
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
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
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 2);
        passed++;
    }
    @Test
    public void testInsert_t4() {
        for (int i = 0; i < 105; i++) {
            ball.insert("" + i);
        }
        assertEquals(ball.getSize(), 100);
        assertEquals(ball.getUniqueSize(), 100);
        passed++;
    }
    @Test
    public void testInsert_t5() {
        for (int i = 0; i < 101; i++) {
            ball.insert("SAMESIES");
        }
        assertEquals(ball.getSize(), 101);
        assertEquals(ball.getUniqueSize(), 1);
        passed++;
    }

    @Test
    public void testRemove() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
        int dups = ball.remove("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        assertEquals(dups, 1);
        passed++;
    }
    @Test
    public void testRemove_t1() {
        ball.insert("uni1");
        int dups = ball.remove("uni1");
        assertEquals(ball.getSize(), 0);
        assertEquals(dups, 0);
        assertFalse(ball.contains("uni1"));
        passed++;
    }
    @Test
    public void testRemove_t2() {
        ball.remove("uni1");
        ball.insert("uni1");
        ball.remove("uni");
        assertEquals(ball.getSize(), 1);
        assertTrue(ball.contains("uni1"));
        ball.insert("uni2");
        ball.insert("uni3");
        ball.remove("uni1");
        assertEquals(ball.getSize(), 2);
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
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 2);
        ball.insert("uni2");
        ball.insert("uni3");
        int dups = ball.remove("dup1");
        assertEquals(dups, 1);
        dups = ball.remove("dup1");
        assertEquals(dups, 0);
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 3);
        assertFalse(ball.contains("dup1"));
        passed++;
    }

    @Test
    public void testRemoveAll() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        ball.removeAll("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        passed++;
    }
    @Test
    public void testRemoveAll_t1() {
        ball.removeAll("uni1");
        ball.insert("uni1");
        ball.removeAll("uni1");
        assertEquals(ball.getSize(), 0);
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
        assertEquals(ball.getSize(), 1);
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
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        assertFalse(ball.contains("dup1"));
        assertTrue(ball.contains("uni1"));
        ball.removeAll("dup2");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        assertFalse(ball.contains("dup2"));
        ball.removeAll("uni1");
        assertEquals(ball.getSize(), 0);
        assertEquals(ball.getUniqueSize(), 0);
        passed++;
    }

    @Test
    public void testCount() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("unique"), 1);
        assertEquals(ball.count("forneymon"), 0);
        passed++;
    }
    @Test
    public void testCount_t1() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.count("dup"), 3);
        ball.removeAll("dup");
        assertEquals(ball.count("dup"), 0);
        passed++;
    }
    @Test
    public void testCount_t2() {
        ball.insert("dup");
        ball.insert("dup2");
        ball.insert("dup");
        ball.insert("dup2");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("dup2"), 2);
        ball.removeAll("dup");
        assertEquals(ball.count("dup"), 0);
        ball.remove("dup2");
        assertEquals(ball.count("dup2"), 1);
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
    public void testGetNth() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        // To avoid double jeopardy with copy:
        Yarn c = new Yarn();
        c.insert("dup");
        c.insert("dup");
        c.insert("unique");
        c.insert("cool");
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(c.contains(gotten));
            c.remove(gotten);
        }
        passed++;
    }
    @Test
    public void testGetNth_t1() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        Yarn c = new Yarn(ball);
        c.insert("a");
        c.insert("b");
        c.insert("c");
        c.insert("a");
        c.insert("b");
        c.insert("c");
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(c.contains(gotten));
            c.remove(gotten);
        }
        passed++;
    }
    @Test
    public void testGetNth_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.remove("a");
        ball.removeAll("b");
        Yarn comparison = new Yarn(ball);
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(comparison.contains(gotten));
            comparison.remove(gotten);
        }
        passed++;
    }

    @Test
    public void testGetMostCommon() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getMostCommon(), "dup");
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));
        passed++;
    }
    @Test
    public void testGetMostCommon_t1() {
        assertEquals(ball.getMostCommon(), null);
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
    @Test
    public void testGetMostCommon_t3() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        ball.insert("d");
        ball.insert("b");
        ball.insert("d");
        ball.insert("c");
        ball.insert("d");
        ball.insert("c");
        ball.insert("d");
        ball.insert("c");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("d"));
        passed++;
    }
    
    @Test
    public void testToString() {
        Yarn y1 = new Yarn();
        assertTrue(y1.toString().equals("{  }"));
        passed++;
    }
    @Test
    public void testToString_t1() {
        Yarn y1 = new Yarn();
        y1.insert("unique");
        assertTrue(y1.toString().equals("{ \"unique\": 1 }"));
        passed++;
    }
    @Test
    public void testToString_t2() {
        Yarn y1 = new Yarn();
        y1.insert("unique");
        y1.insert("dup");
        y1.insert("dup");
        assertTrue(y1.toString().equals("{ \"unique\": 1, \"dup\": 2 }") ||
                   y1.toString().equals("{ \"dup\": 2, \"unique\": 1 }"));
        passed++;
    }

    // Inter-yarn Tests
    // -------------------------------------------------
    @Test
    public void testCopy() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        Yarn dolly = new Yarn(ball);
        assertEquals(dolly.count("dup"), 2);
        assertEquals(dolly.count("unique"), 1);
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
        passed++;
    }
    @Test
    public void testCopy_t1() {
        Yarn dolly = new Yarn(ball);
        ball.insert("a");
        assertFalse(dolly.contains("a"));
        passed++;
    }
    @Test
    public void testCopy_t2() {
        ball.insert("a");
        Yarn dolly = new Yarn(ball);
        dolly.insert("b");
        Yarn superDolly = new Yarn(dolly);
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
        ball.insert("b");
        ball.insert("c");
        Yarn dolly = new Yarn(ball);
        dolly.insert("c");
        dolly.removeAll("a");
        assertTrue(ball.contains("a"));
        assertEquals(2, dolly.count("b"));
        assertEquals(3, dolly.count("c"));
        assertEquals(2, ball.count("c"));
        passed++;
    }

    @Test
    public void testSwap() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
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
        Yarn y2 = new Yarn();
        ball.insert("a");
        ball.insert("b");
        y2.swap(ball);
        assertTrue(ball.isEmpty());
        assertFalse(y2.isEmpty());
        passed++;
    }
    @Test
    public void testSwap_t2() {
        Yarn y2 = new Yarn();
        Yarn y3 = new Yarn();
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
        assertEquals(ball.getSize(), 1);
        passed++;
    }

    // Static Method Tests
    // -------------------------------------------------
    @Test
    public void testKnit() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.knit(y1, y2);
        assertEquals(y3.count("dup"), 3);
        assertEquals(y3.count("unique"), 1);
        assertEquals(y3.count("cool"), 1);
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        passed++;
    }
    @Test
    public void testKnit_t1() {
        Yarn y1 = new Yarn();
        y1.insert("a");
        y1.insert("b");
        Yarn y3 = Yarn.knit(ball, y1);
        assertEquals(y3.getSize(), 2);
        assertEquals(y3.getUniqueSize(), 2);
        passed++;
    }
    @Test
    public void testKnit_t2() {
        ball.insert("a");
        ball.insert("a");
        ball = Yarn.knit(ball, ball);
        assertEquals(ball.getSize(), 4);
        assertEquals(ball.getUniqueSize(), 1);
        passed++;
    }
    @Test
    public void testKnit_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("c");
        ball.insert("c");
        ball = Yarn.knit(ball, ball);
        assertEquals(ball.getSize(), 8);
        assertEquals(ball.getUniqueSize(), 3);
        passed++;
    }

    @Test
    public void testTear() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.tear(y1, y2);
        assertEquals(y3.count("dup"), 1);
        assertEquals(y3.count("unique"), 1);
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        passed++;
    }
    @Test
    public void testTear_t1() {
        Yarn y1 = new Yarn();
        y1.insert("a");
        y1.insert("b");
        Yarn y2 = Yarn.tear(ball, y1);
        assertEquals(y2.getSize(), 0);
        assertFalse(y2.contains("a"));
        passed++;
    }
    @Test
    public void testTear_t2() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("a");
        ball.insert("c");
        ball.insert("c");
        Yarn y1 = new Yarn();
        y1.insert("a");
        y1.insert("a");
        y1.insert("b");
        y1.insert("c");
        Yarn y2 = Yarn.tear(ball, y1);
        assertEquals(y2.getSize(), 1);
        assertFalse(y2.contains("a"));
        assertFalse(y2.contains("b"));
        passed++;
    }
    @Test
    public void testTear_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        Yarn y2 = Yarn.tear(ball, ball);
        assertEquals(y2.getSize(), 0);
        assertFalse(y2.contains("a"));
        passed++;
    }

    @Test
    public void testSameYarn() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(Yarn.sameYarn(y1, y2));
        assertTrue(Yarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(Yarn.sameYarn(y1, y2));
        passed++;
    }
    @Test
    public void testSameYarn_t1() {
        Yarn y1 = new Yarn();
        assertTrue(Yarn.sameYarn(ball, y1));
        passed++;
    }
    @Test
    public void testSameYarn_t2() {
        Yarn y1 = new Yarn();
        assertTrue(Yarn.sameYarn(y1, y1));
        y1.insert("a");
        y1.insert("b");
        assertTrue(Yarn.sameYarn(y1, y1));
        assertEquals(2, y1.getSize());
        assertEquals(2, y1.getUniqueSize());
        passed++;
    }
    @Test
    public void testSameYarn_t3() {
        ball.insert("a");
        ball.insert("b");
        ball.insert("b");
        Yarn y1 = new Yarn();
        y1.insert("b");
        y1.insert("a");
        assertFalse(Yarn.sameYarn(ball, y1));
        ball.removeAll("b");
        y1.removeAll("b");
        assertTrue(Yarn.sameYarn(ball, y1));
        passed++;
    }

}
