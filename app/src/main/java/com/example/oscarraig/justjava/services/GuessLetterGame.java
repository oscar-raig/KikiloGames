package com.example.oscarraig.justjava.services;


import com.example.oscarraig.justjava.domain.GameStatus;

public class GuessLetterGame {
    private static final String A = "a";
    private RandomStrategy randomStrategy;
    private String winningLetter;

    public GameStatus getState() {
        return state;
    }

    private GameStatus state;

    public GuessLetterGame(RandomStrategy randomstrategy) {
        this.randomStrategy = randomstrategy;
    }


    public String getWinningLetter() {
        return winningLetter;
    }


    public void play() {
        selectWinningLetter();
        state = GameStatus.STARTED;
    }

    private void selectWinningLetter() {
        this.winningLetter = randomLetter();
    }

    private String randomLetter() {
        return randomStrategy.get();
    }

    public void tryLetter(String winningLetter) {
        if( winningLetter.equals(this.getWinningLetter())) {
            state = GameStatus.ENDED;
        }
    }

    public void stop() {

    }
}
