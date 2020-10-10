/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.streams;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Ben Obermüller
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
        Scanner sc = new Scanner(System.in);
        System.out.println("Die 1 und 2 Aufgabe kann im getested werden");
        System.out.println("3...Aufgabe 3");
        System.out.println("4...Aufgabe 4");
        
        switch(sc.nextLine()){
            case "3":
                m.runThirdA();
                break;
            case "4":
                m.runForthA();
                break;
            default:
                System.out.println("Falsche Eingabe!");
        }
    }

    public void runFirstA() {
        Main m = new Main();
        final String FILE_NAME = "weapons.csv";
        Scanner sc = new Scanner(System.in);

        List<Weapon> myList = m.loadList(FILE_NAME);

        System.out.println("1...sort List by Damage");
        System.out.println("2...sort List by Letters");

        String ausw = sc.nextLine();
        if (ausw.equals("1")) {
            myList = m.sortListByDamage(myList);
        } else if (ausw.equals("2")) {
            myList = m.sortListByLetters(myList);
        } else {
            System.out.println("Fehler bei der Eingabe!");
        }

        System.out.println("1...print List with Interface");
        System.out.println("2...print List into Tabelle");

        ausw = sc.nextLine();

        if (ausw.equals("1")) {
            m.printListWithInterface(myList);
        } else if (ausw.equals("2")) {
            m.printListWithInterfaceInTabelle(myList);
        } else {
            System.out.println("Fehler bei der Eingabe!");
        }
    }

    public List<Weapon> loadList(String csvFile) {
        List<Weapon> list = new ArrayList<>();
        try {
            list = Files.lines(new File("weapons.csv").toPath())
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
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Weapon> sortListByDamage(List<Weapon> myList) {
        System.out.println("Sort via Damage");
        List<Weapon> mL = myList;
        mL.sort(new Comparator<Weapon>() {
            @Override
            public int compare(Weapon o1, Weapon o2) {
                return Integer.compare(o2.getDamage(), o1.getDamage());
            }
        });
        return mL;
    }

    public List<Weapon> sortListByLetters(List<Weapon> myList) {
        System.out.println("Sort via Letters");
        List<Weapon> mL = myList;
        mL.sort(new Comparator<Weapon>() {
            @Override
            public int compare(Weapon o1, Weapon o2) {
                if (o1.getCombatType().toString().compareTo(o2.getCombatType().toString()) == 0) {
                    if (o1.getDamageType().toString().compareTo(o2.getDamageType().toString()) == 0) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return o1.getDamageType().toString().compareTo(o2.getDamageType().toString());
                    }
                } else {
                    return o1.getCombatType().toString().compareTo(o2.getCombatType().toString());
                }
            }
        });
        return mL;
    }

    private void printListWithInterface(List<Weapon> list) {
        Printable printable = w -> System.out.println(w.getName() + " [" + w.getDamageType() + " = " + w.getDamage() + "]");
        for (int i = 0; i < list.size(); i++) {
            printable.print(list.get(i));
        }
    }

    private void printListWithInterfaceInTabelle(List<Weapon> list) {
        Printable printable = w -> System.out.println(w.getName() + " [" + w.getDamageType() + " = " + w.getDamage() + "]");
        for (int i = 0; i < list.size(); i++) {
            String outLine = "+";
            for (int j = 0; j < list.get(i).getName().length(); j++) {
                outLine += "-";
            }
            outLine += "+";
            for (int j = 0; j < list.get(i).getDamageType().toString().length(); j++) {
                outLine += "-";
            }
            outLine += "+";
            if (list.get(i).getDamage() >= 10) {
                for (int j = 0; j < 4; j++) {
                    outLine += "-";
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    outLine += "-";
                }
            }
            outLine += "+";
            System.out.println(outLine);
            printable.print(list.get(i));
        }
    }

    public void runSecondA() {
        Main m = new Main();
        Streams s = new Streams();

//        int[] arr = m.fillIntArr();
//        System.out.println(s.average(arr));
        List<String> list = s.upperCase(m.fillStringList());
        for (String str : list) {
            System.out.println(str);
        }
    }

    public int[] fillIntArr() {
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100) + 1;
        }
        return arr;
    }

    public String[] fillStringList() {
        String[] arr = new String[10];
        for (int j = 0; j < arr.length; j++) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String generatedString = buffer.toString();

            arr[j] = generatedString;
        }

        return arr;
    }

    public void runThirdA() {

        Main m = new Main();

        final Predicate<Integer> isEven = (t) -> {
            if (t != null) {
                if (t % 2 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        };

        final Predicate<Integer> isPositive = (t) -> {
            if (t != null) {
                if (t >= 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        };

        final Predicate<Integer> isZero = (t) -> {
            if (t != null) {
                if (t == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        };

        final Predicate<Integer> isNull = (t) -> {
            if (t == null) {
                return true;
            } else {
                return false;
            }
        };

        final Predicate<String> isShortWord = (t) -> {
            if (t != null) {
                if (t.length() < 4) {
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }
        };

        List<Integer> intList = m.fillListForA3();
        List<String> stringList = m.fillStringListforA3();
        
        System.out.println("isEven:");
        intList.stream().filter(isEven).forEach(System.out::println);

        System.out.println("\nisPositve:");
        intList.stream().filter(isPositive).forEach(System.out::println);

        System.out.println("\nisZero:");
        intList.stream().filter(isZero).forEach(System.out::println);

        System.out.println("\nisNull:");
        intList.stream().filter(isNull).forEach(System.out::println);

        System.out.println("\nisEven and Positive:");
        intList.stream().filter(isEven.and(isPositive)).forEach(System.out::println);

        System.out.println("\nisUneven and Positive:");
        intList.stream().filter(isEven.negate().and(isPositive)).forEach(System.out::println);
        
        System.out.println("\nis short Word:");
        stringList.stream().filter(isShortWord).forEach(System.out::println);
    }

    private List<Integer> fillListForA3() {
        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(5);
        l.add(2);
        l.add(-10);
        l.add(-5);
        l.add(0);
        l.add(null);
        return l;
    }

    private List<String> fillStringListforA3() {
        List<String> l = new ArrayList<>();
        l.add("Hallo");
        l.add("HI");
        l.add(null);
        return l;
    }

    public void runForthA() {
        final int erg = IntStream.of(1,2,3,4,5,6,7,8,9,10).filter((value) -> {
            if(value%2 == 0){
                return true;
            }else{
                return false;
            }
        }).map(Integer::valueOf).reduce(0, (z1, z2) -> z1+z2);
        System.out.println(erg);
    }
}
