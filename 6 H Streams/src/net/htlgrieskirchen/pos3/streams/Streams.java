package net.htlgrieskirchen.pos3.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    private double ergForAve = 0;

    public double average(int[] numbers) {

        Arrays.stream(numbers).forEach((int value) -> {
            ergForAve += value;
        });
        return ergForAve / numbers.length;
    }

    public List<String> upperCase(String[] strings) {
        List<String> l = new ArrayList<>();
        Arrays.stream(strings).forEach((t) -> {
            l.add(t.toUpperCase());
        });
        return l;
    }

    public Weapon findWeaponWithLowestDamage(List<Weapon> weapons) {
        if (weapons.isEmpty() != true) {
            return weapons.stream().min((o1, o2) -> {
                return o1.getDamage() - o2.getDamage();
            }).get();
        }else{
            return null;
        }
    }

    public Weapon findWeaponWithHighestStrength(List<Weapon> weapons) {
        if (weapons.isEmpty() != true) {
            return weapons.stream().min((o1, o2) -> {
                return o2.getMinStrength() - o1.getMinStrength();
            }).get();
        }
        return null;
    }

    public List<Weapon> collectMissileWeapons(List<Weapon> weapons) {
        List<Weapon> l = new ArrayList<>();
        weapons.stream().forEach((t) -> {
            if (t.getDamageType() == DamageType.MISSILE) {
                l.add(t);
            }
        });
        return l;
    }
    private Weapon longestN;

    public Weapon findWeaponWithLongestName(List<Weapon> weapons) {
        if (weapons.isEmpty() != true) {
            longestN = weapons.get(0);
            weapons.stream().forEach((t) -> {
                if (t.getName() != null) {
                    if (longestN.getName().length() < t.getName().length()) {
                        longestN = t;
                    }
                }
            });
        } else {
            this.longestN = null;
        }

        return this.longestN;
    }

    public List<String> toNameList(List<Weapon> weapons) {
        List<String> l = new ArrayList<>();
        weapons.stream().forEach((t) -> {
            l.add(t.getName());
        });
        return l;
    }

    private int countForSpeedArr = 0;

    public int[] toSpeedArray(List<Weapon> weapons) {
        int[] arr = new int[weapons.size()];
        weapons.stream().forEach((t) -> {
            //arr[weapons.indexOf(t)] = t.getSpeed();
            arr[this.countForSpeedArr] = t.getSpeed();
            this.countForSpeedArr++;
        });
        return arr;
    }
    private int sumUpValues = 0;

    public int sumUpValues(List<Weapon> weapons) {
        weapons.stream().forEach((t) -> {
            sumUpValues += t.getValue();
        });
        return sumUpValues;
    }
    private int sumUpHashC = 0;

    public long sumUpHashCodes(List<Weapon> weapons) {
        weapons.stream().forEach((t) -> {
            sumUpHashC += t.hashCode();
        });
        return sumUpHashC;
    }

    public List<Weapon> removeDuplicates(List<Weapon> weapons) {
        weapons.stream().forEach((t) -> {
            int count = 0;
            for(Weapon w : weapons){
                if(w.equals(t)){
                    count++;
                }
            }
            if(count>1){
                count--;
                for(int i = 1; i < count; i++){
                    weapons.remove(t);
                }
            }
        });
        return weapons;
    }

    public int increaseValuesByTenPercent(List<Weapon> weapons) {
        return (int) (sumUpValues(weapons) * 1.1);
    }
}
