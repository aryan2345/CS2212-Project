package com.mygdx.game;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Difficulty Storage Test class
 *
 * @author Johnathan
 * @version 1.0
 */
class DifficultyStorageTest {

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
     * Test of getDiff method, of class DifficultyStorage
     */
    @Test
    void getDiff1() {
        System.out.println("getDiff() 1");
        DifficultyStorage instance = new DifficultyStorage("easy");
        String expResult = "easy";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class DifficultyStorage
     */
    @Test
    void getDiff2() {
        System.out.println("getDiff() 2");
        DifficultyStorage instance = new DifficultyStorage("medium");
        String expResult = "medium";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class DifficultyStorage
     */
    @Test
    void getDiff3() {
        System.out.println("getDiff() 3");
        DifficultyStorage instance = new DifficultyStorage("hard");
        String expResult = "hard";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class DifficultyStorage
     */
    @Test
    void setDiff1() {
        System.out.println("setDiff() 1");
        DifficultyStorage instance = new DifficultyStorage("hard");
        instance.setDiff("easy");
        String expResult = "easy";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class DifficultyStorage
     */
    @Test
    void setDiff2() {
        System.out.println("setDiff() 2");
        DifficultyStorage instance = new DifficultyStorage("easy");
        instance.setDiff("medium");
        String expResult = "medium";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class DifficultyStorage
     */
    @Test
    void setDiff3() {
        System.out.println("setDiff() 3");
        DifficultyStorage instance = new DifficultyStorage("medium");
        instance.setDiff("hard");
        String expResult = "hard";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }
}