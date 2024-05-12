package ru.samsung.itschool.hello.javaproject;

public class Gladiator {
    private String name;
    private int health;
    private int attack;
    private int armor;
    private int mana;


    public Gladiator(String name, int health, int attack, int armor, int mana) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        this.mana = mana;
    }

    public void attack(Gladiator opponent) {
        int damage = calculateDamage();
        opponent.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int calculateDamage() {
        return this.attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

}