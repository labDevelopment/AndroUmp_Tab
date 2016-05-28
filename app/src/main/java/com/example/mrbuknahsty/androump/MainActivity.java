package com.example.mrbuknahsty.androump;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button plusHS, minusHS, plusAS, minusAS, plusInning, minusInning, plusBall, minusBall, plusStrike, minusStrike, plusOut, minusOut, single, duble, triple;
    TextView HS, AS, innings, balls, strikes, outs;
    CheckBox home, first, second, third;

    int MAX_VALUE_BALL = 4;
    int MAX_VALUE_STRIKE = 3;
    int MAX_VALUE_OUT = 3;

    int ball = 0;
    int strike = 0;
    int out = 0;
    float inning = 1;
    int homeScore = 0;
    int awayScore = 0;
    int outCounter = 0;

    boolean up;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SET BUTTONS
        plusHS = (Button)findViewById(R.id.plusHomeScoreBtn);
        minusHS = (Button)findViewById(R.id.minusHomeScoreBtn);
        plusAS = (Button)findViewById(R.id.plusAwayScoreBtn);
        minusAS = (Button)findViewById(R.id.minusAwayScoreBtn);
        plusInning = (Button)findViewById(R.id.plusInningBtn);
        minusInning = (Button)findViewById(R.id.minusInningBtn);
        plusBall = (Button)findViewById(R.id.plusBallsBtn);
        minusBall = (Button)findViewById(R.id.minusBallsBtn);
        plusStrike = (Button)findViewById(R.id.plusStrikeBtn);
        minusStrike = (Button)findViewById(R.id.minusStrikeBtn);
        plusOut = (Button)findViewById(R.id.plusOutsBtn);
        minusOut = (Button)findViewById(R.id.minusOutsBtn);
        single = (Button)findViewById(R.id.singleBtn);
        duble = (Button)findViewById(R.id.doubleBtn);
        triple = (Button)findViewById(R.id.tripleBtn);

        //SETS TEXTVIEWS
        HS = (TextView)findViewById(R.id.homeScoreTV);
        AS = (TextView)findViewById(R.id.awayScoreTV);
        innings = (TextView)findViewById(R.id.inningNumTV);
        balls = (TextView)findViewById(R.id.ballsNumTV);
        strikes = (TextView)findViewById(R.id.strikesNumTV);
        outs = (TextView)findViewById(R.id.outsNumTV);

        //SET CHECKBOXS
        first = (CheckBox)findViewById(R.id.firstCB);
        second = (CheckBox)findViewById(R.id.secondCB);
        third = (CheckBox)findViewById(R.id.thirdCB);
        home = (CheckBox)findViewById(R.id.homeCB);

        HS.setText(String.valueOf(homeScore));
        AS.setText(String.valueOf(awayScore));
        balls.setText("0");
        strikes.setText("0");
        outs.setText("0");

        innings.setText(String.valueOf(inning));


        addToHomeScore();
        minusToHomeScore();
        addToAwayScore();
        minusToAwayScore();
        addInning();
        minusInning();
        addToBalls();
        minusBall();
        addToStrikes();
        minusStrike();

        plusInning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInning();
                alertToClearBases();
            }
        });

        minusInning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusInning();
                alertToClearBases();
            }
        });

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseHit();
                clearBallsStrikes();
            }
        });

        duble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duble();
                clearBallsStrikes();
            }
        });

        triple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triple();
                clearBallsStrikes();
            }
        });

        plusOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOut();
            }
        });

        minusOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusOut();
            }
        });



    }

    public void addToHomeScore()
    {
        plusHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScore += 1;
                HS.setText(homeScore + "");
            }
        });
    }

    public void minusToHomeScore()
    {
        minusHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeScore > 0) {
                    homeScore -= 1;
                    HS.setText(homeScore + "");
                }
            }
        });
    }

    public void addToAwayScore()
    {
        plusAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                awayScore += 1;
                AS.setText(awayScore + "");
            }
        });
    }

    public void minusToAwayScore()
    {
        minusAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awayScore > 0) {
                    awayScore -= 1;
                    AS.setText(awayScore + "");
                }
            }
        });
    }

    public void addInning()
    {
        inning += 0.5;
        innings.setText(inning + "");
      changeDisplay();
    }

    public void minusInning()
    {
        if(inning > 1) {
            inning -= 0.5;
            innings.setText(inning + "");
        }
        changeDisplay();
    }

    public void baseHit()
    {
        checkCkeckedBoxWalk();
    }

    public void duble()
    {
        checkCkeckedBoxDouble();
    }

    public void triple()
    {
        checkCkeckedBoxTriple();
    }

    public void addToBalls()
    {
        plusBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ball < 4) {
                    ball += 1;
                    balls.setText(ball + "");
                        if(ball == 4)
                        {
                            clearBallsStrikes();
                            checkCkeckedBoxWalk();
                        }
                }
            }
        });
    }

    public void minusBall()
    {
        minusBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ball > 0) {
                    ball -= 1;
                    balls.setText(ball + "");
                }
            }
        });
    }

    public void addToStrikes()
    {
        plusStrike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strike < 3) {
                    strike += 1;
                    strikes.setText(strike + "");
                        if(strike == 3)
                        {
                            addOut();
                            clearBallsStrikes();
                        }
                }
            }
        });
    }

    public void minusStrike()
    {
        minusStrike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(strike > 0) {
                    strike -= 1;
                    strikes.setText(strike + "");
                }
            }
        });
    }

    public void addOut()
    {
        outCounter +=1;
        if(out < 3) {
            out += 1;
            outs.setText(out + "");
                if(out == 3)
                {
                    addInning();
                    clearAll();
                }
        }
    }

    public void minusOut()
    {
        outCounter-=1;
        if(out > 0) {
            out -= 1;
            outs.setText(out + "");
        }

    }

    public void changeDisplay()
    {
        if(inning % 1 != 0)
        {
            HS.setBackgroundColor(Color.DKGRAY);
            HS.setTextColor(0xFFFFFFFF);
            AS.setBackgroundColor(0xFFFFFFFF);
            AS.setTextColor(0xFF000000);
        }
        else{
            AS.setBackgroundColor(Color.DKGRAY);
            AS.setTextColor(0xFFFFFFFF);
            HS.setBackgroundColor(0xFFFFFFFF);//white
            HS.setTextColor(0xFF000000);//black
        }
    }

    public void checkCkeckedBoxWalk()
    {
        if(!first.isChecked())
        {
            first.setChecked(true);
        }
        else if(first.isChecked() && second.isChecked() && third.isChecked())
        {
            if(inning % 1 != 0)
            {
                homeScore += 1;
                HS.setText(homeScore + "");
            }
            else
            {
                awayScore +=1;
                AS.setText(awayScore + "");
            }
        }
        else if(first.isChecked() && second.isChecked() && !third.isChecked())
        {
            third.setChecked(true);
        }
        else
        {
            second.setChecked(true);
        }

    }

    public void checkCkeckedBoxDouble()
    {
        if(first.isChecked()&& !second.isChecked() && !third.isChecked())
        {
            first.setChecked(false);
            second.setChecked(true);
            third.setChecked(true);
        }
        else if(first.isChecked() && second.isChecked() && !third.isChecked()) {
            if (inning % 1 != 0) {
                first.setChecked(false);
                third.setChecked(true);
                homeScore += 1;
                HS.setText(homeScore + "");
            } else {
                first.setChecked(false);
                third.setChecked(true);
                awayScore += 1;
                AS.setText(awayScore + "");
            }
        }
        else if(first.isChecked() && second.isChecked() && third.isChecked())
        {
            if(inning % 1 != 0)
            {
                first.setChecked(false);
                homeScore += 2;
                HS.setText(homeScore + "");
            }
            else
            {
                first.setChecked(false);
                awayScore +=2;
                AS.setText(awayScore + "");
            }
        }
        else if(first.isChecked() && third.isChecked() && !second.isChecked())
        {
            if(inning % 1 != 0)
            {
                first.setChecked(false);
                second.setChecked(true);
                homeScore += 1;
                HS.setText(homeScore + "");
            }
            else
            {
                first.setChecked(false);
                second.setChecked(true);
                awayScore +=1;
                AS.setText(awayScore + "");
            }
        }
        else if(!first.isChecked() && second.isChecked() && third.isChecked())
        {

            alertForDouble();
        }
        else if(!first.isChecked() && !third.isChecked() && second.isChecked())
        {

            if(inning % 1 != 0)
            {
                second.setChecked(true);
                homeScore += 1;
                HS.setText(homeScore + "");
            }
            else
            {
                second.setChecked(true);
                awayScore +=1;
                AS.setText(awayScore + "");
            }
        }
        else if(!first.isChecked() && !second.isChecked() && third.isChecked())
        {

            if(inning % 1 != 0)
            {
                second.setChecked(true);
                third.setChecked(false);
                homeScore += 1;
                HS.setText(homeScore + "");
            }
            else
            {
                second.setChecked(true);
                third.setChecked(false);
                awayScore +=1;
                AS.setText(awayScore + "");
            }
        }
        else
        {
            second.setChecked(true);
        }

    }

    public void checkCkeckedBoxTriple()
    {
        if(first.isChecked()&& !second.isChecked() && !third.isChecked())
        {
            if (inning % 1 != 0) {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                homeScore += 1;
                HS.setText(homeScore + "");
            } else {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                awayScore += 1;
                AS.setText(awayScore + "");
            }
        }
        else if(first.isChecked() && second.isChecked() && !third.isChecked()) {
            if (inning % 1 != 0) {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                homeScore += 2;
                HS.setText(homeScore + "");
            } else {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                awayScore += 2;
                AS.setText(awayScore + "");
            }
        }
        else if(first.isChecked() && second.isChecked() && third.isChecked())
        {
            if (inning % 1 != 0) {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                homeScore += 3;
                HS.setText(homeScore + "");
            } else {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                awayScore += 3;
                AS.setText(awayScore + "");
            }
        }
        else if(first.isChecked() && third.isChecked() && !second.isChecked())
        {
            if (inning % 1 != 0) {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                homeScore += 2;
                HS.setText(homeScore + "");
            } else {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                awayScore += 2;
                AS.setText(awayScore + "");
            }
        }
        else if(!first.isChecked() && second.isChecked() && third.isChecked())
        {

            if (inning % 1 != 0) {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                homeScore += 2;
                HS.setText(homeScore + "");
            } else {
                first.setChecked(false);
                second.setChecked(false);
                third.setChecked(true);
                awayScore += 2;
                AS.setText(awayScore + "");
            }
        }
        else if(!first.isChecked() && !second.isChecked() && third.isChecked())
        {

            if(inning % 1 != 0)
            {
                third.setChecked(true);
                homeScore += 1;
                HS.setText(homeScore + "");
            }
            else
            {
                third.setChecked(true);
                awayScore +=1;
                AS.setText(awayScore + "");
            }
        }
        else if(!first.isChecked() && !third.isChecked() && second.isChecked())
        {

            if(inning % 1 != 0)
            {
                second.setChecked(false);
                third.setChecked(true);
                homeScore += 1;
                HS.setText(homeScore + "");
            }
            else
            {
                second.setChecked(false);
                third.setChecked(true);
                awayScore +=1;
                AS.setText(awayScore + "");
            }
        }
        else
        {
            third.setChecked(true);
        }

    }

    public void alertToClearBases()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Clear...");

        // Setting Dialog Message
        alertDialog.setMessage("Would you like to clear the bases?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                // Write your code here to invoke YES event
                clearChecked();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public void alertForDouble()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Double...");

        // Setting Dialog Message
        alertDialog.setMessage("Did 2 Runs Score on That Double?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                // Write your code here to invoke YES event
                if(inning % 1 != 0)
                {
                    second.setChecked(true);
                    third.setChecked(false);
                    homeScore += 2;
                    HS.setText(homeScore + "");
                }
                else
                {
                    second.setChecked(true);
                    third.setChecked(false);
                    awayScore +=2;
                    AS.setText(awayScore + "");
                }
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                if(inning % 1 != 0)
                {
                    second.setChecked(true);
                    homeScore += 1;
                    HS.setText(homeScore + "");
                }
                else
                {
                    second.setChecked(true);
                    awayScore +=1;
                    AS.setText(awayScore + "");
                }
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public void clearChecked()
    {
        first.setChecked(false);
        second.setChecked(false);
        third.setChecked(false);
    }

    public void clearBallsStrikes()
    {
        ball = 0;
        balls.setText("0");
        strike = 0;
        strikes.setText("0");
    }

    public void clearAll()
    {
        first.setChecked(false);
        second.setChecked(false);
        third.setChecked(false);

        ball = 0;
        balls.setText("0");
        strike = 0;
        strikes.setText("0");
        out = 0;
        outs.setText("0");
    }

}
