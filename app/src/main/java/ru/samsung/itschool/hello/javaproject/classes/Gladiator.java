package ru.samsung.itschool.hello.javaproject.classes;

import java.util.Objects;
import java.util.Random;

public class Gladiator {
    private String name;
    private String leftHand;
    private String rightHand;
    private int health;
    private int attack;
    private int armor;
    private int evasion;
    private boolean evadeStatus = false;
    private boolean blockStatus = false;
    private String move = "";

    public Gladiator(String name, int health, int attack, int armor, int evasion) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        this.evasion = evasion;
    }

    public void attack(Gladiator opponent) {
        opponent.evadeStatus = false;
        opponent.blockStatus = false;
        int damage = calculateDamage(opponent);
        opponent.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        this.armor -= damage;
        if (this.armor < 0) {
            this.health += this.armor;
            this.armor = 0;
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int calculateDamage(Gladiator opponent) {
        int newAttack = this.attack;
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        if (Objects.equals(this.leftHand, "shield") || Objects.equals(this.rightHand, "shield")) {
            int opponentEvasion = opponent.evasion + 20;
            if (opponentEvasion >= randomNum) {
                opponent.blockStatus = true;
            }
        } else if (opponent.evasion >= randomNum) {
            opponent.evadeStatus = true;
        }
        if (opponent.evaded() || opponent.blocked()) {
            return 0;
        }
        return newAttack;
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

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public boolean evaded() {
        return this.evadeStatus;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public void evade() {
        this.move = "evade";
    }

    public boolean blocked() {
        return this.blockStatus;
    }
    public void block() {
        this.move = "block";
    }

    public String getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(String leftHand) {
        this.leftHand = leftHand;
        if (Objects.equals(leftHand, "weapon")) {
            this.attack *= (int)(this.attack * 1.5);
        }
    }

    public String getRightHand() {
        return rightHand;
    }

    public void setRightHand(String rightHand) {
        this.rightHand = rightHand;
        if (Objects.equals(rightHand, "weapon")) {
            this.attack *= (int)(this.attack * 1.5);
        }
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void addArmor(int armor) {
        this.armor += armor;
    }
}