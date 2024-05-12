package ru.samsung.itschool.hello.javaproject.classes;

import java.util.ArrayList;
import java.util.List;

public class ConclusionList
{
    private final List<Conclusion> conclusions;

    public ConclusionList() {
        this.conclusions = new ArrayList<>();
    }

    public void addConclusion(String title, String text) {
        Conclusion conclusion = new Conclusion(title, text);
        conclusions.add(conclusion);
    }

    public List<Conclusion> getConclusions() {
        return conclusions;
    }
}
