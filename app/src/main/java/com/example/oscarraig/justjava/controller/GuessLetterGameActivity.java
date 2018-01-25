package com.example.oscarraig.justjava.controller;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.VideoView;

import com.example.oscarraig.justjava.R;
import com.example.oscarraig.justjava.domain.GameStatus;
import com.example.oscarraig.justjava.services.GuessLetterGame;
import com.example.oscarraig.justjava.services.RandomStrategyImpl;

public class GuessLetterGameActivity extends AppCompatActivity {

    GuessLetterGame guessLetterGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playletters);
        guessLetterGame = new GuessLetterGame(new RandomStrategyImpl());
        guessLetterGame.play();
        setWinningLetter();
    }

    public void play(View view) {
        guessLetterGame.stop();
        guessLetterGame.play();
        setWinningLetter();
    }


    public void guessLetter(View view) {

        if( guessLetterGame.getState() == GameStatus.ENDED) {
            return;
        }
        Button button = (Button)findViewById(view.getId());
        String intent =  button.getText().toString();
        guessLetterGame.tryLetter(intent);
        if( guessLetterGame.getState() == GameStatus.ENDED) {
            view.setBackgroundColor(Color.GREEN);
            playVideo();
        } else {
            view.setBackgroundColor(Color.RED);
        }
    }

    private void setWinningLetter(){
        String winningLetter = guessLetterGame.getWinningLetter();
        TextView textWinningLetter = (TextView)findViewById(R.id.winning_letter);

        textWinningLetter.setText(winningLetter);

        initializeColorForIntentButtons();

    }
    private void initializeColorForIntentButtons() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.groupIntentButtons);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View button = layout.getChildAt(i);
            if (button instanceof Button) {
                button.setBackgroundColor(Color.GRAY);
            }
        }
    }

    private void playVideo(){
        VideoView mVideoView = (VideoView)findViewById(R.id.videoview);
        mVideoView.setVisibility(View.VISIBLE);
        mVideoView.setMediaController(null);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.we_win));
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

            @Override
            public void onCompletion(MediaPlayer mp) {
                VideoView mVideoView = (VideoView)findViewById(R.id.videoview);
                mVideoView.setVisibility(View.INVISIBLE);
            }
        });
        mVideoView.start();
    }


}
