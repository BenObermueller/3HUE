/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        final String FILE_NAME ="weapons.csv";
        
        List<Weapon> myList = m.loadList(FILE_NAME);
        
        System.out.println("1...sort List by Damage");
        System.out.println("2...sort List by Damage");
        System.out.println("3...print List in Tabelle");

        String ausw = sc.nextLine();
        if (ausw.equals("1")) {
            myList = m.sortListByDamage(myList);
        } else if (ausw.equals("2")) {
            myList = m.sortListByLetters(myList);
        }else if(ausw.equals("3")){
            m.printListWithInterfaceInTabelle(myList);
        }else {
            System.out.println("Fehler bei der Eingabe!");
        }
        //m.printListWithInterface(myList);
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
    
    private void printListWithInterfaceInTabelle(List<Weapon> list){
        Printable printable = w -> System.out.println(w.getName() + " [" + w.getDamageType() + " = " + w.getDamage() + "]");
        for (int i = 0; i < list.size(); i++) {
            String outLine ="+";
            for(int j=0; j< list.get(i).getName().length(); j++){
                outLine +="-";
            }
            outLine +="+";
            for(int j=0; j< list.get(i).getDamageType().toString().length(); j++){
                outLine +="-";
            }
            outLine +="+";
            if(list.get(i).getDamage()>=10){
                for(int j=0; j< 4; j++){
                outLine +="-";
                }
            }else{
                for(int j=0; j< 3; j++){
                outLine +="-";
            }
            }
            outLine +="+";
            System.out.println(outLine);
            printable.print(list.get(i));
        }
    }
}
