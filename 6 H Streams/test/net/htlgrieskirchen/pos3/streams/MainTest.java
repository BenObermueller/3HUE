/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.streams;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ben Obermüller
 */
public class MainTest {

    final String FILE_NAME = "weapons.csv";

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sortListByDamage method, of class Main.
     */
    @Test
    public void testSortListByDamage() {
        System.out.println("sortListByDamage");
        Main m = new Main();
        boolean expr = true;
        List<Weapon> myList = m.sortListByDamage(m.loadList(FILE_NAME));
        for (int i = 0, j = 1; j < myList.size(); i++, j++) {
            if (myList.get(i).getDamage() < myList.get(j).getDamage()) {
                assertEquals(expr, false);
            }
        }
        assertEquals(expr, true);
    }

    /**
     * Test of sortListByLetters method, of class Main.
     */
    @Test
    public void testSortListByLetters() {
        System.out.println("sortListByLetters");
        Main m = new Main();
        boolean expr = true;
        List<Weapon> myList = m.sortListByLetters(m.loadList(FILE_NAME));
        for (int i = 0, j = 1; j < myList.size(); i++, j++) {
            if (myList.get(i).getCombatType().toString().compareTo(myList.get(j).getCombatType().toString()) > 0) {
                if (myList.get(i).getDamageType().toString().compareTo(myList.get(j).getDamageType().toString()) > 0) {
                    if (myList.get(i).getName().compareTo(myList.get(j).getName()) > 0) {
                        assertEquals(expr, false);
                    }
                }
            }
        }
        assertEquals(expr, true);
    }
}
