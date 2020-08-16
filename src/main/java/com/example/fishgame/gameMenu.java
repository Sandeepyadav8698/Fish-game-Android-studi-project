package com.example.fishgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class gameMenu extends AppCompatActivity {

    Button startgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        startgame=findViewById(R.id.button1);
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startgameintent=new Intent(gameMenu.this,MainActivity.class);
                startActivity(startgameintent);
            }
        });
    }

    public void exitapp(View v)
    {
        finish();
        System.exit(0);

    }
    public void help(View v){
        Intent startgameintent=new Intent(gameMenu.this,helpActivity.class);
        startActivity(startgameintent);
    }

    public void sound(View v){
        Toast.makeText(this,"Sound is already off",Toast.LENGTH_SHORT).show();
    }
}
