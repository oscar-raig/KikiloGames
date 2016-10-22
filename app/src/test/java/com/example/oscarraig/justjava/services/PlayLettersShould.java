package com.example.oscarraig.justjava.services;


import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


public class PlayLettersShould {

    private static final String EXPECTED_LETTER = "a";
    private  static RandomStrategy randomStrategy;

    @BeforeClass
    public static void setUp(){

        randomStrategy = Mockito.mock(RandomStrategy.class);

    }

    @Test
    public void startGame() {

        PlayLetters playLetters = new PlayLetters(randomStrategy);
        when(randomStrategy.get()).thenReturn(EXPECTED_LETTER);
        playLetters.start();
        assertThat(playLetters.getWinningLetter(),is(EXPECTED_LETTER));

    }
    @Test
    public void endGame() {

    }

}