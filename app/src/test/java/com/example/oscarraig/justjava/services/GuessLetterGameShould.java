package com.example.oscarraig.justjava.services;


import com.example.oscarraig.justjava.domain.GameStatus;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


public class GuessLetterGameShould {

    private static final String EXPECTED_LETTER = "a";
    public static final String A_NO_WINNING_LETTER = "b";
    private  static RandomStrategy randomStrategy;

    @BeforeClass
    public static void setUp(){

        randomStrategy = Mockito.mock(RandomStrategy.class);
    }

    @Test
    public void startGameAndGenenerateAWinningLetter() {

        GuessLetterGame guessLetterGame = new GuessLetterGame(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        guessLetterGame.play();
        assertThat(guessLetterGame.getWinningLetter(),is(EXPECTED_LETTER));

    }
    @Test
    public void tryLetterWithWinningLetterEndGame() {
        GuessLetterGame guessLetterGame = new GuessLetterGame(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        guessLetterGame.play();
        String winningLetter = EXPECTED_LETTER;
        guessLetterGame.tryLetter(winningLetter);
        assertThat(guessLetterGame.getState(),is(GameStatus.ENDED));
    }

    @Test
    public void tryLetterWithNoWinningLetterNotEndGame() {
        GuessLetterGame guessLetterGame = new GuessLetterGame(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        guessLetterGame.play();
        String winningLetter = A_NO_WINNING_LETTER;
        guessLetterGame.tryLetter(winningLetter);
        assertThat(guessLetterGame.getState(),is(GameStatus.STARTED));
    }
}