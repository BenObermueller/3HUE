package net.htlgrieskirchen.pos3.streams;

import java.util.Objects;

public class Weapon {
    
    private final String name;
    private final CombatType combatType;
    private final DamageType damageType;
    private final int damage;
    private final int speed;
    private final int minStrength;
    private int value;

    public Weapon(String name, CombatType combatType, DamageType damageType, int damage, int speed, int minStrength, int value) {
        //implement this
    }

    public String getName() {
        //implement this
    }
    
    public CombatType getCombatType() {
        //implement this
    }

    public DamageType getDamageType() {
        //implement this
    }

    public int getDamage() {
        //implement this
    }

    public int getSpeed() {
        //implement this
    }

    public int getMinStrength() {
        //implement this
    }
    
    public int getValue() {
        //implement this
    }
    
    public void setValue(int value) {
        //implement this
    }

    @Override
    public int hashCode() {
        //implement this
    }

    @Override
    public boolean equals(Object obj) {
        //implement this
    }

    @Override
    public String toString() {
        return "Weapon{" + "name=" + name + ", combatType=" + combatType + ", damageType=" + damageType + ", damage=" + damage + ", speed=" + speed + ", minStrength=" + minStrength + ", value=" + value + '}';
    }

}
