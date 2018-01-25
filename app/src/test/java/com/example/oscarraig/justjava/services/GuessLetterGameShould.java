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
    public void startGameAndGenenerateAWinningLetter() {

        PlayLetters playLetters = new PlayLetters(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        playLetters.play();
        assertThat(playLetters.getWinningLetter(),is(EXPECTED_LETTER));

    }
    @Test
    public void tryLetterWithWinningLetterEndGame() {
        PlayLetters playLetters = new PlayLetters(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        playLetters.play();
        String winningLetter = EXPECTED_LETTER;
        playLetters.tryLetter(winningLetter);
        assertThat(playLetters.getState(),is(GameStatus.ENDED));
    }

    @Test
    public void tryLetterWithNoWinningLetterNotEndGame() {
        PlayLetters playLetters = new PlayLetters(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        playLetters.play();
        String winningLetter = A_NO_WINNING_LETTER;
        playLetters.tryLetter(winningLetter);
        assertThat(playLetters.getState(),is(GameStatus.STARTED));
    }
}