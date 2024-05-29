package ru.samsung.itschool.hello.javaproject.classes;

import java.util.ArrayList;
import java.util.List;

public class GladiatorList
{
    private final List<Gladiator> gladiators;

    public GladiatorList() {
        this.gladiators = new ArrayList<>();
    }

    public void addGladiator(Gladiator gladiator) {
        gladiators.add(gladiator);
    }

    public List<Gladiator> getGladiators() {
        return gladiators;
    }

    public Gladiator getGladiator(int index) {
        return gladiators.get(index);
    }
}
