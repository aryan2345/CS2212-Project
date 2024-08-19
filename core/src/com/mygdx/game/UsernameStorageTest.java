package com.mygdx.game;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for UserStorage class
 *
 * @author Johnathan
 * @version 1.0
 */
class UsernameStorageTest {

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
     * Test of getUsername method, of class UsernameStorage
     */
    @Test
    void getUsername1() {
        System.out.println("getUsername() 1");
        UsernameStorage instance = new UsernameStorage("woop");
        String expResult = "woop";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class UsernameStorage
     */
    @Test
    void getUsername2() {
        System.out.println("getUsername() 2");
        UsernameStorage instance = new UsernameStorage("fun");
        String expResult = "fun";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class UsernameStorage
     */
    @Test
    void getUsername3() {
        System.out.println("getUsername() 3");
        UsernameStorage instance = new UsernameStorage("look");
        String expResult = "look";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class UsernameStorage
     */
    @Test
    void setUsername1() {
        System.out.println("setUsername() 1");
        UsernameStorage instance = new UsernameStorage("look");
        instance.setUsername("miss");
        String expResult = "miss";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class UsernameStorage
     */
    @Test
    void setUsername2() {
        System.out.println("setUsername() 2");
        UsernameStorage instance = new UsernameStorage("duck");
        instance.setUsername("rock");
        String expResult = "rock";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class UsernameStorage
     */
    @Test
    void setUsername3() {
        System.out.println("setUsername() 3");
        UsernameStorage instance = new UsernameStorage("look");
        instance.setUsername("look");
        String expResult = "look";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }
}