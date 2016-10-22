package com.example.oscarraig.justjava.services;


public class PlayLetters {
    private static final String A = "a";
    private RandomStrategy randomStrategy;
    private String winningLetter;

    public PlayLetters(RandomStrategy randomstrategy) {
        this.randomStrategy = randomstrategy;
    }


    public String getWinningLetter() {
        return winningLetter;
    }


    public void start() {
        selectWinningLetter();
    }

    private void selectWinningLetter() {
        this.winningLetter = randomLetter();
    }

    private String randomLetter() {
        return randomStrategy.get();
    }
}
