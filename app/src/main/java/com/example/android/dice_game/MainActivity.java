package com.example.android.dice_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView tvUserScore,tvUser2Score,tvStatus;
    private ImageView imDiceface;
    private Button bRoll,bHold,bReset,bRoll2,bHold2;

    private int[] diceFaceImages = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };
    private int userOverallScore = 0, userTurnScore = 0;
    private int user2OverallScore = 0,user2TurnScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkAllViews();

        bRoll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                rollButtonClick();
            }
            });
        bRoll2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                roll2ButtonClick();
            }
        });
        bHold2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                hold2ButtonClick();
            }
        });
        bHold.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View view){
                holdButtonClick();
        }
        });

        bReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resetButtonClick();
            }
        });


    }

    private void roll2ButtonClick() {
        int rolledNumber2 = rollDice();
        imDiceface.setImageResource(diceFaceImages[rolledNumber2]);
        rolledNumber2++;

        if (rolledNumber2 == 1) {
            user2TurnScore = 0;
            tvStatus.setText("You rolled a '1',Player 1 turn");
            enableButton2(false);
            enableButton1(true);
        }
        else{
            user2TurnScore+=rolledNumber2;
            tvStatus.setText("Your turn score is "+user2TurnScore);
            checkWinner();
        }
    }
    private void hold2ButtonClick(){
        user2OverallScore+=user2TurnScore;
        user2TurnScore = 0;
        tvUser2Score.setText(String.format("Your Score: %d", user2OverallScore));
        tvStatus.setText("Player 1 turn");
        enableButton2(false);
        enableButton1(true);
    }

    private int rollDice(){
        Random random = new Random();
        return random.nextInt(6);
    }
    private void resetButtonClick() {
        userOverallScore = 0;
        userTurnScore = 0;
        user2OverallScore = 0;
        user2TurnScore = 0;
        tvUserScore.setText("Your Score: 0");
        tvStatus.setText("Status: Game Started");
        tvUser2Score.setText("Computer Score: 0");

        Log.d("RESET", "yes it was reset");
    }

    private void holdButtonClick() {
        userOverallScore+=userTurnScore;
        userTurnScore = 0;
        tvUserScore.setText(String.format("Your Score: %d", userOverallScore));
        tvStatus.setText("Player 2 turn");
        enableButton1(false);
        enableButton2(true);
    }

    private void rollButtonClick(){
        int rolledNumber1 = rollDice();
        imDiceface.setImageResource(diceFaceImages[rolledNumber1]);
        rolledNumber1++;

        if (rolledNumber1 == 1) {
            userTurnScore = 0;
            tvStatus.setText("You rolled a '1',Player 2 turn");
            enableButton1(false);
            enableButton2(true);
        }
        else{
            userTurnScore+=rolledNumber1;
            tvStatus.setText("Your turn score is "+userTurnScore);
            checkWinner();
        }
    }
    private void enableButton1(boolean isEnabled){
        bRoll.setEnabled(isEnabled);
        bHold.setEnabled(isEnabled);
    }
    private void enableButton2(boolean isEnabled){
        bRoll2.setEnabled(isEnabled);
        bHold2.setEnabled(isEnabled);
    }
    /*private void computerTurn(){
        enableButton(false);
        while(true){
            int computerRolledNumber = rollDice();
            imDiceface.setImageResource(diceFaceImages[computerRolledNumber]);
            computerRolledNumber++;

            if(computerRolledNumber == 1){
                computerTurnScore = 0;
                tvStatus.setText("Computer rolled a '1'");
                tvStatus.setText("Now Players turn");
                enableButton(true);
                break;
            }
            else {
                computerTurnScore += computerRolledNumber;
                tvStatus.setText(String.format("Computer turn score is %d",computerTurnScore));
            }

            if(computerTurnScore >20){
                computerOverallScore += computerTurnScore;
                computerTurnScore = 0;
                tvComputerScore.setText(String.format("Computer Score: %d",computerOverallScore));
                tvStatus.setText("Now Player's turn");
                enableButton(true);
            }
        }
        checkWinner();
    }*/
    public boolean checkWinner(){
        if(userOverallScore >=100){
            Toast.makeText(getApplicationContext(),"Player 1 Wins!",Toast.LENGTH_SHORT).show();
            resetButtonClick();
            return true;
        }
        else if(user2OverallScore >= 100){
            Toast.makeText((getApplicationContext()),"Player 2 Wins!",Toast.LENGTH_SHORT).show();
            resetButtonClick();
            return true;
        }

        return  false;
    }
    private void linkAllViews(){
        tvUserScore = (TextView)findViewById(R.id.user_score);
        tvUser2Score = (TextView)findViewById(R.id.user2_score);
        tvStatus = (TextView)findViewById(R.id.game_status);
        imDiceface = (ImageView)findViewById(R.id.dice_face);
        bRoll = (Button)findViewById(R.id.roll_button);
        bHold = (Button)findViewById(R.id.hold_button);
        bReset = (Button)findViewById(R.id.reset_button);
        bRoll2 = (Button)findViewById(R.id.roll2_button);
        bHold2 = (Button)findViewById(R.id.hold2_button);
    }

}
