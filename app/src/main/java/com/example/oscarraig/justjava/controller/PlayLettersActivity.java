package com.example.oscarraig.justjava.controller;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.VideoView;

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
            playVideo();
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
        MediaController mc = new MediaController(this);
        mVideoView.setMediaController(mc);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.we_win_mp4));
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
