package com.mygdx.game;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Save class
 *
 * @author Johnathan
 * @version 1.0
 */
class SaveTest {
    @BeforeAll
    public static void setupClass()
    {
        System.out.println("setupClass()");
    }

    @AfterAll
    public static void tearDownClass()
    {
        System.out.println("tearDownClass()");
    }
    @BeforeEach
    public void setUp() {
        System.out.println("setUp()");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("tearDown()");
    }

    /**
     * Test of getLevel method, of class Save
     */
    @Test
    void getLevel1() {
        System.out.println("getLevel() 1");
        Save instance = new Save("easy", 1, 5000);
        int expResult = 1;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLevel method, of class Save
     */
    @Test
    void getLevel2() {
        System.out.println("getLevel() 2");
        Save instance = new Save("medium", 3, 18000);
        int expResult = 3;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLevel method, of class Save
     */
    @Test
    void getLevel3() {
        System.out.println("getLevel() 3");
        Save instance = new Save("hard", 2, 5000);
        int expResult = 2;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Save
     */
    @Test
    void getScore1() {
        System.out.println("getLevel() 1");
        Save instance = new Save("hard", 2, 5000);
        int expResult = 5000;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Save
     */
    @Test
    void getScore2() {
        System.out.println("getLevel() 2");
        Save instance = new Save("medium", 3, 18000);
        int expResult = 18000;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Save
     */
    @Test
    void getScore3() {
        System.out.println("getScore() 3");
        Save instance = new Save("easy", 1, 5000);
        int expResult = 5000;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class Save
     */
    @Test
    void getDiff1() {
        System.out.println("getDiff() 1");
        Save instance = new Save("easy", 1, 5000);
        String expResult = "easy";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class Save
     */
    @Test
    void getDiff2() {
        System.out.println("getDiff() 2");
        Save instance = new Save("medium", 3, 18000);
        String expResult = "medium";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class Save
     */
    @Test
    void getDiff3() {
        System.out.println("getDiff() 3");
        Save instance = new Save("hard", 2, 5000);
        String expResult = "hard";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLevel method, of class Save
     */
    @Test
    void setLevel1() {
        System.out.println("setLevel() 1");
        Save instance = new Save("hard", 2, 5000);
        instance.setLevel(1);
        int expResult = 1;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLevel method, of class Save
     */
    @Test
    void setLevel2() {
        System.out.println("setLevel() 2");
        Save instance = new Save("easy", 1, 98000);
        instance.setLevel(2);
        int expResult = 2;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLevel method, of class Save
     */
    @Test
    void setLevel3() {
        System.out.println("setLevel() 3");
        Save instance = new Save("medium", 3, 17000);
        instance.setLevel(3);
        int expResult = 3;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class Save
     */
    @Test
    void setDiff1() {
        System.out.println("setLevel() 1");
        Save instance = new Save("medium", 2, 13000);
        instance.setDiff("easy");
        String expResult = "easy";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class Save
     */
    @Test
    void setDiff2() {
        System.out.println("setLevel() 2");
        Save instance = new Save("easy", 1, 17000);
        instance.setDiff("hard");
        String expResult = "hard";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class Save
     */
    @Test
    void setDiff3() {
        System.out.println("setLevel() 3");
        Save instance = new Save("medium", 3, 12000);
        instance.setDiff("medium");
        String expResult = "medium";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class Save
     */
    @Test
    void setScore1() {
        System.out.println("setScore() 1");
        Save instance = new Save("medium", 3, 12000);
        instance.setScore(1000);
        int expResult = 1000;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class Save
     */
    @Test
    void setScore2() {
        System.out.println("setScore() 2");
        Save instance = new Save("easy", 1, 14000);
        instance.setScore(45000);
        int expResult = 45000;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class Save
     */
    @Test
    void setScore3() {
        System.out.println("setScore() 3");
        Save instance = new Save("hard", 2, 4000);
        instance.setScore(4000);
        int expResult = 4000;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of testToString method, of class Save
     */
    @Test
    void testToString1() {
        System.out.println("testToString() 1");
        Save instance = new Save("easy", 2, 3000);
        String expResult = "Save{" +
                "diff='" + "easy" + '\'' +
                ", level=" + 2 +
                ", score=" + 3000 +
                '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of testToString method, of class Save
     */
    @Test
    void testToString2() {
        System.out.println("testToString() 2");
        Save instance = new Save("hard", 1, 9000);
        String expResult = "Save{" +
                "diff='" + "hard" + '\'' +
                ", level=" + 1 +
                ", score=" + 9000 +
                '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of testToString method, of class Save
     */
    @Test
    void testToString3() {
        System.out.println("testToString() 3");
        Save instance = new Save("medium", 2, 97888);
        String expResult = "Save{" +
                "diff='" + "medium" + '\'' +
                ", level=" + 2 +
                ", score=" + 97888 +
                '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}