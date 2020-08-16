package com.example.fishgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Flyingfishview extends View {

    private Bitmap fish[] = new Bitmap[2];          //CREATING AN ARRAY OF FISH

    private Bitmap backgroundimage;                 //CREATING BACKGROUND BITMAP

    private Paint scorepaint = new Paint();         //USING PAINT CLASS

    private boolean touch = false;                  //INITALLY TOUCH FALSE BETWEEN FISH AND BALL

    private Bitmap life[] = new Bitmap[2];          //BITMAP FOR TWO LIFE

    private int fishX = 10;
    private int fishY;
    private int fishspeed;

    private int canvasWidth, canvasHeight;

    private int score,lifeCounter;

    private int yellowx, yellowy, yellowspeed = 11;
    private Paint yellowPaint = new Paint();

    private int greenx, greeny, greenspeed = 18;
    private Paint greenpaint = new Paint();

    private int redx, redy, redspeed = 18;
    private Paint redpaint = new Paint();


    public Flyingfishview(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        backgroundimage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        scorepaint.setColor(Color.YELLOW);
        scorepaint.setTextSize(40);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);

        score = 0;
        lifeCounter=3;

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);

        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);


        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
        fishY = 550;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        canvas.drawBitmap(backgroundimage, 0, 0, null);

        canvas.drawText("score : " + score, 20, 60, scorepaint);

        //FISH POSITIONING AND LOGIC--------------------------------------------------------

        int minFishy = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;

        fishY = fishY + fishspeed;

        if (fishY < minFishy) {
            fishY = minFishy;
        }
        if (fishY > maxFishY) {
            fishY = maxFishY;
        }
        fishspeed = fishspeed + 2;

        if (touch) {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        } else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }
//-----------------------------------------------------------------------------------------------------


        //RED BALL LOGIC


        redx = redx - redspeed;

        if (hitballChecker(redx, redy)) {
            redx = -100;
            lifeCounter--;

            if(lifeCounter==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent gameover=new Intent(getContext(),GameOverActivity.class);
                gameover.putExtra("myscore",score);
                gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(gameover);
            }
        }

//-----------------------------------------------------------------------------------------

        if (redx < 0) {
            redx = canvasWidth + 21;
            redy = (int) Math.floor(Math.random() * (maxFishY - minFishy) + minFishy);
        }
        canvas.drawCircle(redx, redy, 25, redpaint);

        for(int i=0;i<3;i++){
            int x=(int)(320 +life[0].getWidth()*1.5*i);
            int y=30;
            if(i<lifeCounter)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else{
                canvas.drawBitmap(life[1],x,y,null);
            }
        }

//---------------------------------------------------------------------------------------
        //YELLOW BALL LOGIC

        yellowx = yellowx - yellowspeed;

        if (hitballChecker(yellowx, yellowy)) {
            score = score + 10;
            yellowx = -100;
        }


        if (yellowx < 0) {
            yellowx = canvasWidth + 21;
            yellowy = (int) Math.floor(Math.random() * (maxFishY - minFishy) + minFishy);
        }
        canvas.drawCircle(yellowx, yellowy, 22, yellowPaint);

        //GREEN BALL LOGIC ----------------------------------------------------------------------

        greenx = greenx - greenspeed;

        if (hitballChecker(greenx, greeny)) {
            score = score + 30;
            greenx = -100;
        }


        if (greenx < 0) {
            greenx = canvasWidth + 21;
            greeny = (int) Math.floor(Math.random() * (maxFishY - minFishy) + minFishy);
        }
        canvas.drawCircle(greenx, greeny, 20, greenpaint);

    }

// Hit ball logic------------------------------------------------------------------------------------

    public boolean hitballChecker(int x, int y) {
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            fishspeed = -22;
        }
        return true;
    }
}
