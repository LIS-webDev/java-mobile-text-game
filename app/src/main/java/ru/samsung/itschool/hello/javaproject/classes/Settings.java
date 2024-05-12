package ru.samsung.itschool.hello.javaproject.classes;

import java.io.Serializable;

public class Settings implements Serializable
{
    private int stage = 0;

    public Settings() {}

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return stage;
    }
}
