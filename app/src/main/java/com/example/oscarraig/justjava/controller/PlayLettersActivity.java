package com.example.oscarraig.justjava.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
        Button button = (Button)findViewById(view.getId());
        String intent =  button.getText().toString();
        playLetters.intent(intent);
        if( playLetters.getState() == GameStatus.ENDED) {
            view.setBackgroundColor(Color.GREEN);
        } else {
            view.setBackgroundColor(Color.RED);
        }
    }
    private void setWinningLetter(){
        String winningLetter = playLetters.getWinningLetter();
        TextView textWinningLetter = (TextView)findViewById(R.id.winning_letter);

        textWinningLetter.setText(winningLetter);

        initializeColorForIntentButtons();

    }
    public void initializeColorForIntentButtons() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.groupIntentButtons);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View button = layout.getChildAt(i);
            if (button instanceof Button) {
                button.setBackgroundColor(Color.GRAY);
            }
        }
    }
}
