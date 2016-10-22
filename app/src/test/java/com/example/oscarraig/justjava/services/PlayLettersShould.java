package com.example.oscarraig.justjava.services;


import com.example.oscarraig.justjava.domain.GameStatus;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


public class PlayLettersShould {

    private static final String EXPECTED_LETTER = "a";
    public static final String A_NO_WINNING_LETTER = "b";
    private  static RandomStrategy randomStrategy;

    @BeforeClass
    public static void setUp(){

        randomStrategy = Mockito.mock(RandomStrategy.class);

    }

    @Test
    public void startGameAndGnenerateAWinningLetter() {

        PlayLetters playLetters = new PlayLetters(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        playLetters.start();
        assertThat(playLetters.getWinningLetter(),is(EXPECTED_LETTER));

    }
    @Test
    public void intentWinningLetterEndGame() {
        PlayLetters playLetters = new PlayLetters(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        playLetters.start();
        String winningLetter = EXPECTED_LETTER;
        playLetters.intent(winningLetter);
        assertThat(playLetters.getState(),is(GameStatus.ENDED));
    }

    @Test
    public void intentNoWinningLetterNotEndGame() {
        PlayLetters playLetters = new PlayLetters(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        playLetters.start();
        String winningLetter = A_NO_WINNING_LETTER;
        playLetters.intent(winningLetter);
        assertThat(playLetters.getState(),is(GameStatus.STARTED));
    }
}