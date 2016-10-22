package com.example.oscarraig.justjava.services;


import java.util.Random;

public class RandomStrategyImpl implements RandomStrategy {
    String[] letters = {"A", "B"};



    @Override
    public String get() {
        return letters[new Random().nextInt(2)];
    }
}
