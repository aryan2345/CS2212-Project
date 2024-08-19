package com.mygdx.game;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for JSONHandeler
 *
 * @author Jack Scott
 * @version 1.0
 */

class JSONHandelerTest {

    public static File tester;
    public static SaveState saveState1;
    public static SaveState saveState2;
    public static SaveState saveState3;
    public static JSONHandeler instance;


    @BeforeAll
    public static void setUpClass(){
        tester = new File(System.getProperty("user.dir") + "/other/jsons/tester.json");
        try {
            if (tester.createNewFile()) {
                System.out.println("tester created");
            }
        } catch (IOException e) {
            System.out.println("failed to create tester");
        }

        saveState1 = new SaveState("ABBY", new Save("easy", 0, 0));
        saveState2 = new SaveState("JOHN", new Save("medium", 1, 24000));
        saveState3 = new SaveState("MART", new Save("hard", 2, 40000));
    }

    @AfterAll
    public static void tearDownClass(){
        if (tester.delete()) {
            System.out.println("tester deleted");
        } else {
            System.out.println("failed to delete tester");
        }
    }

    @BeforeEach
    public void setUp(){
        instance = new JSONHandeler();
        System.out.println("setUp()");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("tearDown()");
    }


    /**
     * 1st Test saveGame method with paramaters String username and Save of JsonHandeler
     */
    @Test
    void saveGameA1() {
        System.out.println("saveGame() A 1");
        instance.saveGame(saveState1);
        String line4 = "";
        try {
            line4 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(4);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        String uname = saveState1.getUsername();
        assertTrue(line4.contains(uname));
    }

    /**
     * 2nd Test saveGame method with paramaters String username and Save of JsonHandeler
     */
    @Test
    void saveGameA2(){
        System.out.println("saveGame() A 2");
        instance.saveGame(saveState2);
        String line15 = "";
        try {
            line15 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(15);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        String diff = saveState1.getDiff();
        assertTrue(line15.contains(diff));
    }

    /**
     * 3rd Test saveGame method with paramaters String username and Save of JsonHandeler
     */
    @Test
    void saveGameA3(){
        System.out.println("saveGame() A 3");
        instance.saveGame(saveState3);
        String line25 = "";
        try {
            line25 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(25);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        int level = saveState1.getLevel();
        assertTrue(line25.contains(String.valueOf(level)));
    }

    /**
     * 4th Test saveGame method with paramaters String username and Save of JsonHandeler
     */
    @Test
    void saveGameA4(){
        System.out.println("saveGame() A 4");
        String line4 = "";
        String line5 = "";
        String line6 = "";
        String line7 = "";
        String line8 = "";
        try {
            line4 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(4);
            line5 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(5);
            line6 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(6);
            line7 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(7);
            line8 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(8);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        boolean out = line4.contains("username") && line5.contains("save") && line6.contains("diff") && line7.contains("level") && line8.contains("score");
        assertTrue(out);
    }

    /**
     * 1st Test saveGame method with paramater SaveState of JsonHandeler
     */
    @Test
    void saveGameB1() {
        System.out.println("saveGame() B 1");
        instance.saveGame(saveState1.getUsername(), new Save(saveState1.getDiff(), saveState1.getLevel(), saveState1.getScore()));
        String line31 = "";
        try {
            line31 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(31);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        String uname = saveState1.getUsername();
        assertTrue(line31.contains(uname));
    }

    /**
     * 2nd Test saveGame method with paramater SaveState of JsonHandeler
     */
    @Test
    void saveGameB2(){
        System.out.println("saveGame() B 2");
        instance.saveGame(saveState2);
        String line40 = "";
        try {
            line40 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(40);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        String diff = saveState1.getDiff();
        assertTrue(line40.contains(diff));
    }

    /**
     * 3rd Test saveGame method with paramater SaveState of JsonHandeler
     */
    @Test
    void saveGameB3(){
        System.out.println("saveGame() B 3");
        instance.saveGame(saveState3);
        String line53 = "";
        try {
            line53 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(53);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        int score = saveState1.getScore();
        assertTrue(line53.contains(String.valueOf(score)));
    }

    /**
     * 4th Test saveGame method with paramater SaveState of JsonHandeler
     */
    @Test
    void saveGameB4(){
        System.out.println("saveGame() B 4");
        String line31 = "";
        String line32 = "";
        String line33 = "";
        String line34 = "";
        String line35 = "";
        try {
            line31 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(31);
            line32 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(32);
            line33 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(33);
            line34 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(34);
            line35 = Files.readAllLines(Paths.get(tester.getAbsolutePath())).get(35);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        boolean out = line31.contains("username") && line32.contains("save") && line33.contains("diff") && line34.contains("level") && line35.contains("score");
        assertTrue(out);
    }

    /**
     * 1st Test getSave method of JsonHandeler
     */
    @Test
    void getSave1() {
        System.out.println("getSave() 1");
        Save result = instance.getSave(saveState1.getUsername());
        assertTrue((Objects.equals(saveState1.getDiff(), result.getDiff()) && saveState1.getLevel() == result.getLevel() && saveState1.getScore() == result.getLevel()));
    }

    /**
     * 2nd Test getSave method of JsonHandeler
     */
    @Test
    void getSave2() {
        System.out.println("getSave() 2");
        Save result = instance.getSave(saveState2.getUsername());
        assertTrue((Objects.equals(saveState2.getDiff(), result.getDiff()) && saveState2.getLevel() == result.getLevel() && saveState2.getScore() == result.getLevel()));
    }

    /**
     * 3rd Test getSave method of JsonHandeler
     */
    @Test
    void getSave3() {
        System.out.println("getSave() 3");
        Save result = instance.getSave(saveState3.getUsername());
        assertTrue((Objects.equals(saveState3.getDiff(), result.getDiff()) && saveState3.getLevel() == result.getLevel() && saveState3.getScore() == result.getLevel()));
    }

    /**
     * Test getSaveState method of JsonHandeler
     */
    @Test
    void getSaveState() {
        System.out.println("getSaveState()");
        SaveState result = instance.getSaveState(saveState1.getUsername());
        assertTrue((Objects.equals(saveState1.getDiff(), result.getDiff()) && saveState1.getLevel() == result.getLevel() && saveState1.getScore() == result.getLevel()));
    }

    /**
     * 1st Test getSortedScores method of JsonHandeler
     */
    @Test
    void getSortedScores1() {
        System.out.println("getSortedScores() 1");
        ArrayList<SaveState> result = instance.getSortedScores();
        assertTrue((Objects.equals(saveState3.getUsername(), result.get(0).getUsername()) && Objects.equals(saveState3.getDiff(), result.get(0).getDiff()) && saveState3.getLevel() == result.get(0).getLevel() && saveState3.getScore() == result.get(0).getLevel()));
    }

    /**
     * 2nd Test getSortedScores method of JsonHandeler
     */
    @Test
    void getSortedScores2() {
        System.out.println("getSortedScores() 2");
        ArrayList<SaveState> result = instance.getSortedScores();
        assertEquals(6, result.size());
    }

    /**
     * 1st Test checkUsername method of JsonHandeler
     */
    @Test
    void checkUsername1() {
        System.out.println("checkUsername() 1");
        assertTrue(instance.checkUsername(saveState1.getUsername()));
    }

    /**
     * 2nd Test checkUsername method of JsonHandeler
     */
    @Test
    void checkUsername2() {
        System.out.println("checkUsername() 2");
        assertFalse(instance.checkUsername("Dans"));
    }

    /**
     * 3rd Test checkUsername method of JsonHandeler
     */
    @Test
    void checkUsername3() {
        System.out.println("checkUsername() 3");
        assertFalse(instance.checkUsername(saveState1.getUsername().toLowerCase()));
    }
}