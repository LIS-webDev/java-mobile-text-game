package ru.samsung.itschool.hello.javaproject;

import android.widget.Button;

public class Game {
    private Gladiator player1;
    private Gladiator player2;

    private Button attackBtn;
    private Button blockBtn;
    private Button magicBtn;

    public Game(Gladiator player1, Gladiator player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startBattle() {
        while (player1.isAlive() && player2.isAlive()) {
            player1.attack(player2);
            if (player2.isAlive()) {
                player2.attack(player1);
            }
        }
        if (player1.isAlive()) {
            System.out.println(player1.getName() + " победил!");
        } else {
            System.out.println(player2.getName() + " победил!");
        }
    }

}