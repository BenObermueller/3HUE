package net.htlgrieskirchen.pos3.streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StreamsTest {

    private List<Weapon> weapons;

    public StreamsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        weapons = readFile("weapons.csv");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of average method, of class Streams.
     */
    @Test
    public void testAverage() {
        System.out.println("average");
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        Streams instance = new Streams();
        double expResult = 50.5;
        double result = instance.average(numbers);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of upperCase method, of class Streams.
     */
    @Test
    public void testUpperCase() {
        System.out.println("upperCase");
        String[] strings = new String[]{"foo", "bar"};
        Streams instance = new Streams();
        List<String> expResult = Arrays.asList("FOO", "BAR");
        List<String> result = instance.upperCase(strings);
        assertEquals(expResult, result);
    }

    /**
     * Test of findWeaponWithLowestDamage method, of class Streams.
     */
    @Test
    public void testFindWeaponWithLowestDamage() {
        System.out.println("findWeaponWithLowestDamage");
        Streams instance = new Streams();
        Weapon expResult = weapons.get(3);
        Weapon result = instance.findWeaponWithLowestDamage(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of findWeaponWithHighestStrength method, of class Streams.
     */
    @Test
    public void testFindWeaponWithHighestStrength() {
        System.out.println("findWeaponWithHighestStrength");
        Streams instance = new Streams();
        Weapon expResult = weapons.get(2);
        Weapon result = instance.findWeaponWithHighestStrength(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of collectMissileWeapons method, of class Streams.
     */
    @Test
    public void testCollectMissileWeapons() {
        System.out.println("collectMissileWeapons");
        Streams instance = new Streams();
        List<Weapon> expResult = new LinkedList<>();
        expResult.add(weapons.get(1));
        expResult.add(weapons.get(8));
        expResult.add(weapons.get(9));
        List<Weapon> result = instance.collectMissileWeapons(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of findWeaponWithLongestName method, of class Streams.
     */
    @Test
    public void testFindWeaponWithLongestName() {
        System.out.println("findWeaponWithLongestName");
        Streams instance = new Streams();
        Weapon expResult = weapons.get(11);
        Weapon result = instance.findWeaponWithLongestName(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of toNameList method, of class Streams.
     */
    @Test
    public void testToNameList() {
        System.out.println("toNameList");
        Streams instance = new Streams();
        List<String> expResult = new LinkedList<>();
        expResult.add("Varscona");
        expResult.add("Tuigan Bow");
        expResult.add("Crom Faeyr");
        expResult.add("Broken Weapon");
        expResult.add("Carsomyr");
        expResult.add("Flail of Ages");
        expResult.add("Boomerang Dagger");
        expResult.add("Dagger of Venom");
        expResult.add("Heartseeker");
        expResult.add("Strong Arm");
        expResult.add("Kundane");
        expResult.add("The Burning Earth");
        expResult.add("Daystar");
        expResult.add("Long Sword +1");
        expResult.add("Long Sword +1");
        expResult.add("Long Sword +1");
        List<String> result = instance.toNameList(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of toSpeedArray method, of class Streams.
     */
    @Test
    public void testToSpeedArray() {
        System.out.println("toSpeedArray");
        Streams instance = new Streams();
        int[] expResult = new int[]{3, 5, 1, 0, 5, 4, 1, 0, 4, 5, 0, 4, 3, 4, 4, 4};
        int[] result = instance.toSpeedArray(weapons);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of sumUpValues method, of class Streams.
     */
    @Test
    public void testSumUpValues() {
        System.out.println("sumUpValues");
        Streams instance = new Streams();
        int expResult = 97050;
        int result = instance.sumUpValues(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of sumUpHashCodes method, of class Streams.
     */
    @Test
    public void testSumUpHashCodes() {
        System.out.println("sumUpHashCodes");
        Streams instance = new Streams();
        int expResult = 0;
        for (Weapon w : weapons) {
            expResult += w.hashCode();
        }
        long result = instance.sumUpHashCodes(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeDuplicates method, of class Streams.
     */
    @Test
    public void testRemoveDuplicates() {
        System.out.println("removeDuplicates");
        Streams instance = new Streams();
        List<Weapon> expResult = weapons;
        expResult.remove(expResult.size() - 1);
        expResult.remove(expResult.size() - 1);
        List<Weapon> result = instance.removeDuplicates(weapons);
        assertEquals(expResult, result);
    }

    /**
     * Test of increaseValuesByTenPercent method, of class Streams.
     */
    @Test
    public void testIncreaseValuesByTenPercent() throws IOException {
        System.out.println("increaseValuesByTenPercent");
        Streams instance = new Streams();
        int expResult = 106755;
        instance.increaseValuesByTenPercent(weapons);
        int result = 0;
        result = weapons.stream()
                .map((w) -> w.getValue())
                .reduce(result, Integer::sum);
        assertEquals(expResult, result);
    }

    private List<Weapon> readFile(String file) throws IOException {
        return Files.lines(new File(file).toPath())
                .skip(1)
                .map(s -> s.split(";"))
                .map(s -> new Weapon(
                        s[0],
                        CombatType.valueOf(s[1]),
                        DamageType.valueOf(s[2]),
                        Integer.parseInt(s[3]),
                        Integer.parseInt(s[4]),
                        Integer.parseInt(s[5]),
                        Integer.parseInt(s[6])
                ))
                .collect(Collectors.toList());
    }

    @Test(timeout = 4000)
    public void test00()  throws Throwable  {
        Streams streams0 = new Streams();
        String[] stringArray0 = new String[0];
        List<String> list0 = streams0.upperCase(stringArray0);
        assertEquals(0, list0.size());
    }

    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        Streams streams0 = new Streams();
        String[] stringArray0 = new String[6];
        stringArray0[0] = "^t\"1N`D/NyL0u`.";
        stringArray0[1] = "";
        stringArray0[2] = "]D5$k";
        stringArray0[3] = "";
        stringArray0[4] = "cM0a)=%`7G0#JYd=v#";
        stringArray0[5] = "";
        List<String> list0 = streams0.upperCase(stringArray0);
        assertFalse(list0.contains("]D5$k"));
    }

    @Test(timeout = 4000)
    public void test02()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.PIERCING;
        Weapon weapon0 = new Weapon("k:|8,hxR", combatType0, damageType0, 5060, 5060, 5060, 5);
        linkedList0.add(weapon0);
        int[] intArray0 = streams0.toSpeedArray(linkedList0);
        assertEquals(1, intArray0.length);
    }

    @Test(timeout = 4000)
    public void test03()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.PIERCING;
        Weapon weapon0 = new Weapon("k:|8,hxR", combatType0, damageType0, 5060, 5060, 5060, 5);
        linkedList0.add(weapon0);
        List<String> list0 = streams0.toNameList(linkedList0);
        assertFalse(list0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test04()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.PIERCING;
        Weapon weapon0 = new Weapon("k:|8,hxR", combatType0, damageType0, 5060, 5060, 5060, 5);
        linkedList0.add(weapon0);
        int int0 = streams0.sumUpValues(linkedList0);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test05()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.MISSILE;
        Weapon weapon0 = new Weapon("", combatType0, damageType0, 1, 1, 0, (-4052));
        linkedList0.add(weapon0);
        int int0 = streams0.sumUpValues(linkedList0);
        assertEquals((-4052), int0);
    }

    @Test(timeout = 4000)
    public void test06()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.SLASHING;
        Weapon weapon0 = new Weapon("%\"]N)f5", combatType0, damageType0, (-1), (-1), (-1), 1155);
        linkedList0.add(weapon0);
        long long0 = streams0.sumUpHashCodes(linkedList0);
        //  // Unstable assertion: assertEquals(612071032L, long0);
    }

    @Test(timeout = 4000)
    public void test07()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.NONE;
        DamageType damageType0 = DamageType.MISSILE;
        Weapon weapon0 = new Weapon("net.htlgrieskirchen.pos3.streams.CombatType", combatType0, damageType0, 0, (-252), (-252), (-1));
        linkedList0.add(0, weapon0);
        long long0 = streams0.sumUpHashCodes(linkedList0);
        //  // Unstable assertion: assertEquals((-1678356734L), long0);
    }

    @Test(timeout = 4000)
    public void test08()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.PIERCING;
        Weapon weapon0 = new Weapon("k:|8,hxR", combatType0, damageType0, 5060, 5060, 5060, 5);
        linkedList0.add(weapon0);
        List<Weapon> list0 = streams0.removeDuplicates(linkedList0);
        assertTrue(list0.contains(weapon0));
    }

    @Test(timeout = 4000)
    public void test09()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.MISSILE;
        Weapon weapon0 = new Weapon("SUF4x}5T9QGT#L~", combatType0, damageType0, (-2151), (-2151), (-2151), 0);
        weapon0.setValue((-2891));
        linkedList0.offerFirst(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLowestDamage(linkedList0);
        assertEquals(CombatType.MELEE, weapon1.getCombatType());
    }

    @Test(timeout = 4000)
    public void test10()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.NONE;
        DamageType damageType0 = DamageType.NONE;
        Weapon weapon0 = new Weapon("", combatType0, damageType0, 0, 4, 0, 0);
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLowestDamage(linkedList0);
        assertEquals(0, weapon1.getValue());
    }

    @Test(timeout = 4000)
    public void test11()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.NONE;
        Weapon weapon0 = new Weapon((String) null, combatType0, damageType0, 0, 0, 0, 0);
        linkedList0.addLast(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLowestDamage(linkedList0);
        assertEquals(DamageType.NONE, weapon1.getDamageType());
    }

    @Test(timeout = 4000)
    public void test12()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.RANGED;
        DamageType damageType0 = DamageType.NONE;
        Weapon weapon0 = new Weapon("9", combatType0, damageType0, 451, 0, 451, 2237);
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLowestDamage(linkedList0);
        assertEquals(0, weapon1.getSpeed());
    }

    @Test(timeout = 4000)
    public void test13()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.NONE;
        Weapon weapon0 = new Weapon("", combatType0, damageType0, 1552, 0, 1552, 0);
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLongestName(linkedList0);
        assertEquals(1552, weapon1.getMinStrength());
    }

    @Test(timeout = 4000)
    public void test14()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.SLASHING;
        Weapon weapon0 = new Weapon(",rGJ>c]?,i", combatType0, damageType0, 0, (-1), 0, (-1743));
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLongestName(linkedList0);
        assertEquals(CombatType.MELEE, weapon1.getCombatType());
    }

    @Test(timeout = 4000)
    public void test15()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.MISSILE;
        Weapon weapon0 = new Weapon("", combatType0, damageType0, 0, 0, (-1), 439);
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLongestName(linkedList0);
        assertEquals(0, weapon1.getDamage());
    }

    @Test(timeout = 4000)
    public void test16()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.PIERCING;
        Weapon weapon0 = new Weapon("k:|8,hxR", combatType0, damageType0, 5060, 5060, 5060, 5);
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLongestName(linkedList0);
        assertTrue(linkedList0.contains(weapon1));
    }

    @Test(timeout = 4000)
    public void test17()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.NONE;
        DamageType damageType0 = DamageType.PIERCING;
        Weapon weapon0 = new Weapon((String) null, combatType0, damageType0, (-1116), 1, 1, (-1116));
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithLongestName(linkedList0);
        assertEquals(1, weapon1.getMinStrength());
    }

    @Test(timeout = 4000)
    public void test18()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.NONE;
        DamageType damageType0 = DamageType.MISSILE;
        Weapon weapon0 = new Weapon("net.htlgrieskirchen.pos3.streams.CombatType", combatType0, damageType0, 0, (-252), (-252), (-1));
        linkedList0.add(0, weapon0);
        Weapon weapon1 = streams0.findWeaponWithHighestStrength(linkedList0);
        assertEquals((-252), weapon1.getSpeed());
    }

    @Test(timeout = 4000)
    public void test19()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.MELEE;
        DamageType damageType0 = DamageType.BLUNT;
        Weapon weapon0 = new Weapon("", combatType0, damageType0, 1193, 1193, 1193, 1);
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithHighestStrength(linkedList0);
        assertEquals(DamageType.BLUNT, weapon1.getDamageType());
    }

    @Test(timeout = 4000)
    public void test20()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.NONE;
        DamageType damageType0 = DamageType.BLUNT;
        Weapon weapon0 = new Weapon((String) null, combatType0, damageType0, (-1), 0, 0, 0);
        linkedList0.add(weapon0);
        Weapon weapon1 = streams0.findWeaponWithHighestStrength(linkedList0);
        assertNull(weapon1.getName());
    }

    @Test(timeout = 4000)
    public void test21()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        CombatType combatType0 = CombatType.NONE;
        DamageType damageType0 = DamageType.MISSILE;
        Weapon weapon0 = new Weapon("net.htlgrieskirchen.pos3.streams.CombatType", combatType0, damageType0, 0, (-252), (-252), (-1));
        linkedList0.add(0, weapon0);
        List<Weapon> list0 = streams0.collectMissileWeapons(linkedList0);
        assertTrue(list0.contains(weapon0));
    }

    @Test(timeout = 4000)
    public void test22()  throws Throwable  {
        Streams streams0 = new Streams();
        int[] intArray0 = new int[1];
        intArray0[0] = 1;
        double double0 = streams0.average(intArray0);
        assertEquals(1.0, double0, 0.01);
    }

    @Test(timeout = 4000)
    public void test23()  throws Throwable  {
        Streams streams0 = new Streams();
        int[] intArray0 = new int[3];
        intArray0[0] = (-4052);
        double double0 = streams0.average(intArray0);
        assertEquals((-1350.6666666666667), double0, 0.01);
    }


    @Test(timeout = 4000)
    public void test36()  throws Throwable  {
        Streams streams0 = new Streams();
        // Undeclared exception!
        try {
            streams0.average((int[]) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
        }
    }

    @Test(timeout = 4000)
    public void test38()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        List<Weapon> list0 = streams0.collectMissileWeapons(linkedList0);
        assertTrue(list0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test39()  throws Throwable  {
        Streams streams0 = new Streams();
        int[] intArray0 = new int[1];
        double double0 = streams0.average(intArray0);
        assertEquals(0.0, double0, 0.01);
    }

    @Test(timeout = 4000)
    public void test40()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        streams0.increaseValuesByTenPercent(linkedList0);
        assertEquals(0, linkedList0.size());
    }

    @Test(timeout = 4000)
    public void test41()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        List<Weapon> list0 = streams0.removeDuplicates(linkedList0);
        assertTrue(list0.isEmpty());
    }

    @Test(timeout = 4000)
    public void test42()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        int int0 = streams0.sumUpValues(linkedList0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test43()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        List<String> list0 = streams0.toNameList(linkedList0);
        assertEquals(0, list0.size());
    }

    @Test(timeout = 4000)
    public void test44()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        int[] intArray0 = streams0.toSpeedArray(linkedList0);
        assertEquals(0, intArray0.length);
    }

    @Test(timeout = 4000)
    public void test45()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        Weapon weapon0 = streams0.findWeaponWithHighestStrength(linkedList0);
        assertNull(weapon0);
    }

    @Test(timeout = 4000)
    public void test46()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        Weapon weapon0 = streams0.findWeaponWithLongestName(linkedList0);
        assertNull(weapon0);
    }

    @Test(timeout = 4000)
    public void test47()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        long long0 = streams0.sumUpHashCodes(linkedList0);
        assertEquals(0L, long0);
    }

    @Test(timeout = 4000)
    public void test48()  throws Throwable  {
        Streams streams0 = new Streams();
        LinkedList<Weapon> linkedList0 = new LinkedList<Weapon>();
        Weapon weapon0 = streams0.findWeaponWithLowestDamage(linkedList0);
        assertNull(weapon0);
    }
    
}
