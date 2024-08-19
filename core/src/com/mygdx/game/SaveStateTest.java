package com.mygdx.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SaveState class
 *
 * @author Johnathan
 * @version 1.0
 */
class SaveStateTest {
    public static Save save1;
    public static Save save2;
    public static Save save3;
    private static SaveState instance1;
    private static SaveState instance2;
    private static SaveState instance3;
    @BeforeAll
    public static void setupClass()
    {
        save1 = new Save ("easy", 2, 1000);
        save2 = new Save ("medium", 1, 5000);
        save3 = new Save ("hard", 3, 239000);
        instance1 = new SaveState ("rock", save1);
        instance2 = new SaveState ("mice", save2);
        instance3 = new SaveState ("croc", save3);
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
     * Test of getUsername method, of class SaveState
     */
    @Test
    public void getUsername1() {
        System.out.println("getUsername() 1");
        String expResult = "rock";
        String result = instance1.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class SaveState
     */
    @Test
    public void getUsername2() {
        System.out.println("getUsername() 2");
        String expResult = "mice";
        String result = instance2.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class SaveState
     */
    @Test
    public void getUsername3() {
        System.out.println("getUsername() 3");
        String expResult = "croc";
        String result = instance3.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSave method, of class SaveState
     */
    @Test
    public void getSave1() {
        System.out.println("getSave() 1");
        Save expResult = save1;
        Save result = instance1.getSave();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSave method, of class SaveState
     */
    @Test
    public void getSave2() {
        System.out.println("getSave() 2");
        Save expResult = save2;
        Save result = instance2.getSave();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSave method, of class SaveState
     */
    @Test
    public void getSave3() {
        System.out.println("getSave() 3");
        Save expResult = save3;
        Save result = instance3.getSave();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class SaveState
     */
    @Test
    public void setUsername1() {
        System.out.println("setUsername() 1");
        String expResult = "spel";
        SaveState instance = new SaveState ("wiza",save1);
        instance.setUsername("spel");
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class SaveState
     */
    @Test
    public void setUsername2() {
        System.out.println("setUsername() 2");
        String expResult = "132";
        SaveState instance = new SaveState ("woop",save2);
        instance.setUsername("132");
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class SaveState
     */
    @Test
    public void setUsername3() {
        System.out.println("setUsername() 3");
        String expResult = "2op";
        SaveState instance = new SaveState ("wrap",save3);
        instance.setUsername("2op");
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSave method, of class SaveState
     */
    @Test
    public void setSave1() {
        System.out.println("setSave() 1");
        Save expResult = new Save ("medium", 2, 3000);
        SaveState instance = new SaveState ("wiza", save1);
        instance.setSave(expResult);
        Save result = instance.getSave();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSave method, of class SaveState
     */
    @Test
    public void setSave2() {
        System.out.println("setSave() 2");
        Save expResult = new Save ("easy", 3, 479);
        SaveState instance = new SaveState ("wiza",save2);
        instance.setSave(expResult);
        Save result = instance.getSave();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSave method, of class SaveState
     */
    @Test
    public void setSave3() {
        System.out.println("setSave() 3");
        Save expResult = new Save ("medium", 1, 1132);
        SaveState instance = new SaveState ("wiza",save3);
        instance.setSave(expResult);
        Save result = instance.getSave();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class SaveState
     */
    @Test
    public void getDiff1() {
        System.out.println("getDiff() 1");
        String expResult = "easy";
        String result = instance1.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class SaveState
     */
    @Test
    public void getDiff2() {
        System.out.println("getDiff() 2");
        String expResult = "medium";
        String result = instance2.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiff method, of class SaveState
     */
    @Test
    public void getDiff3() {
        System.out.println("getDiff() 3");
        String expResult = "hard";
        String result = instance3.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLevel method, of class SaveState
     */
    @Test
    public void getLevel1() {
        System.out.println("getLevel() 1");
        int expResult = save1.getLevel();
        int result = instance1.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLevel method, of class SaveState
     */
    @Test
    public void getLevel2() {
        System.out.println("getLevel() 2");
        int expResult = save2.getLevel();
        int result = instance2.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLevel method, of class SaveState
     */
    @Test
    public void getLevel3() {
        System.out.println("getLevel() 3");
        int expResult = save3.getLevel();
        int result = instance3.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class SaveState
     */
    @Test
    public void getScore1() {
        System.out.println("getScore() 1");
        int expResult = save1.getScore();
        int result = instance1.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class SaveState
     */
    @Test
    public void getScore2() {
        System.out.println("getScore() 2");
        int expResult = save2.getScore();
        int result = instance2.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class SaveState
     */
    @Test
    public void getScore3() {
        System.out.println("getScore() 3");
        int expResult = save3.getScore();
        int result = instance3.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class SaveState
     */
    @Test
    public void setDiff1() {
        System.out.println("setDiff() 1");
        Save save = new Save ("medium", 2, 3000);
        SaveState instance = new SaveState ("wiza",save);
        instance.setDiff("hard");
        String expResult = "hard";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class SaveState
     */
    @Test
    public void setDiff2() {
        System.out.println("setDiff() 2");
        Save save = new Save ("easy", 3, 1300);
        SaveState instance = new SaveState ("wiza",save);
        instance.setDiff("medium");
        String expResult = "medium";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiff method, of class SaveState
     */
    @Test
    public void setDiff3() {
        System.out.println("setDiff() 3");
        Save save = new Save ("hard", 2, 67000);
        SaveState instance = new SaveState ("wiza",save);
        instance.setDiff("hard");
        String expResult = "hard";
        String result = instance.getDiff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLevel method, of class SaveState
     */
    @Test
    public void setLevel1() {
        System.out.println("setLevel() 1");
        Save save = new Save ("medium", 2, 1000);
        SaveState instance = new SaveState ("wiza",save);
        instance.setLevel(1);
        int expResult = 1;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLevel method, of class SaveState
     */
    @Test
    public void setLevel2() {
        System.out.println("setLevel() 2");
        Save save = new Save ("easy", 1, 8900);
        SaveState instance = new SaveState ("wiza",save);
        instance.setLevel(3);
        int expResult = 3;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLevel method, of class SaveState
     */
    @Test
    public void setLevel3() {
        System.out.println("setLevel() 3");
        Save save = new Save ("medium", 3, 6780);
        SaveState instance = new SaveState ("wiza",save);
        instance.setLevel(3);
        int expResult = 3;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class SaveState
     */
    @Test
    public void setScore1() {
        System.out.println("setScore() 1");
        Save save = new Save ("medium", 2, 3000);
        SaveState instance = new SaveState ("wiza",save);
        instance.setScore(2000);
        int result = instance.getScore();
        int expResult = 2000;
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class SaveState
     */
    @Test
    public void setScore2() {
        System.out.println("setScore() 2");
        Save save = new Save ("easy", 1, 3000);
        SaveState instance = new SaveState ("wiza",save);
        instance.setScore(5087);
        int result = instance.getScore();
        int expResult = 5087;
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class SaveState
     */
    @Test
    public void setScore3() {
        System.out.println("setScore() 3");
        Save save = new Save ("hard", 1, 1324);
        SaveState instance = new SaveState ("wiza",save);
        instance.setScore(1567);
        int result = instance.getScore();
        int expResult = 1567;
        assertEquals(expResult, result);
    }

    /**
     * Test of testToString method, of class SaveState
     */
    @Test
    public void testToString1() {
        System.out.println("testToString1() 1");
        String expResult = "SaveState{" +
                "username='" + "woopa" + '\'' +
                ", save=" + save1 +
                '}';
        SaveState instance = new SaveState ("woopa",save1);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of testToString method, of class SaveState
     */
    @Test
    public void testToString2() {
        System.out.println("testToString1() 2");
        String expResult = "SaveState{" +
                "username='" + "blam" + '\'' +
                ", save=" + save2 +
                '}';
        SaveState instance = new SaveState ("blam",save2);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of testToString method, of class SaveState
     */
    @Test
    public void testToString3() {
        System.out.println("testToString1() 3");
        String expResult = "SaveState{" +
                "username='" + "hero" + '\'' +
                ", save=" + save3 +
                '}';
        SaveState instance = new SaveState ("hero",save3);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}