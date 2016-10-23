package com.example.oscarraig.justjava.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

import com.example.oscarraig.justjava.R;
import com.example.oscarraig.justjava.domain.GameStatus;
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

    public void intent(View view) {
        if( playLetters.getState() == GameStatus.ENDED) {
            return;
        }
        String intent = "A";
        switch (view.getId()) {
            case R.id.intent_A:
                intent = "A";
                break;
            case R.id.intent_B:
                intent = "B";
                break;
            case R.id.intent_C:
                intent = "C";
                break;
        }
        playLetters.intent(intent);
        if( playLetters.getState() == GameStatus.ENDED) {
            view.setBackgroundColor(Color.GREEN);
        } else {
            view.setBackgroundColor(Color.RED);
        }
    }
    private void setWinningLetter(){
        String winningLetter = playLetters.getWinningLetter();
        TextView text = (TextView)findViewById(R.id.winning_letter);

        text.setText(winningLetter);


        Button intentA = (Button)findViewById(R.id.intent_A);
        intentA.setBackgroundColor(Color.GRAY);

        Button intentB = (Button)findViewById(R.id.intent_B);
        intentB.setBackgroundColor(Color.GRAY);

        Button intentC = (Button)findViewById(R.id.intent_C);
        intentC.setBackgroundColor(Color.GRAY);


    }
}
