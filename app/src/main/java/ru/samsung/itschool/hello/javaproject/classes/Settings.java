package ru.samsung.itschool.hello.javaproject.classes;

import java.io.Serializable;

public class Settings implements Serializable
{
    private int stage = 0;
    private Gladiator character;

    public Settings() {}

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return stage;
    }

    public void nextStage() {this.stage++;}

    public Gladiator getCharacter() {
        return character;
    }

    public void setCharacter(Gladiator character) {
        this.character = character;
    }
}
