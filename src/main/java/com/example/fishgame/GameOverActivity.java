package com.example.fishgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView gameresult;
    private String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        score=getIntent().getExtras().get("myscore").toString();
        gameresult=findViewById(R.id.gameresult);
        gameresult.setText("Your Score: "+score);
    }

    public void playAgain(View view){
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(100);
                }
                catch ( Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent playagainintent=new Intent(GameOverActivity.this,MainActivity.class);
                    startActivity(playagainintent);
                }
            }
        };
        thread.start();
    }

    public void menu(View v){
        Intent i =new Intent(GameOverActivity.this,gameMenu.class);
        startActivity(i);
    }

}
