package com.example.oscarraig.justjava.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.oscarraig.justjava.R;
import com.example.oscarraig.justjava.services.PlayLetters;
import com.example.oscarraig.justjava.services.RandomStrategyImpl;

public class PlayLettersActivity extends AppCompatActivity {

    PlayLetters playLetters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playletters);
        playLetters = new PlayLetters(new RandomStrategyImpl());
        playLetters.play();
        setWinningLetter();
    }

    public void returnToMenu(View view) {
        this.finish();
    }

    public void play(View view) {
        playLetters.stop();
        playLetters.play();
        setWinningLetter();
    }

    private void setWinningLetter(){
        String winningLetter = playLetters.getWinningLetter();
        TextView text = (TextView)findViewById(R.id.winning_letter);
        text.setText(winningLetter);
    }
}
