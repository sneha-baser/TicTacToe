package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity<btn2> extends AppCompatActivity  implements View.OnClickListener{
    ArrayList<List<Button>> board = new ArrayList<List<Button>>(); //declaring board array

    int board_status[][]=new int[3][3];
    boolean player1=true;//if player1 represent X then it is true else false
    int turncount=0;//counter to check if there is draw
    Button btn1 ;
    Button btn2 ;
    Button btn3 ;
    Button btn4 ;
    Button btn5 ;
    Button btn6 ;
    Button btn7 ;
    Button btn8 ;
    Button btn9 ;
    Button rstbtn;
    TextView txtup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btn1 = (Button) findViewById(R.id.btn1);
         btn2 = (Button) findViewById(R.id.btn2);
         btn3 = (Button) findViewById(R.id.btn3);
         btn4 = (Button) findViewById(R.id.btn4);
         btn5 = (Button) findViewById(R.id.btn5);
         btn6 = (Button) findViewById(R.id.btn6);
         btn7 = (Button) findViewById(R.id.btn7);
         btn8 = (Button) findViewById(R.id.btn8);
         btn9 = (Button) findViewById(R.id.btn9);
         rstbtn = (Button)findViewById(R.id.rstbtn);
        txtup = (TextView) findViewById(R.id.turntxtv);


    //initialising board array -> which is 2D array of buttons
        board.add(Arrays.asList(btn1,btn2,btn3));
        board.add(Arrays.asList(btn4,btn5,btn6));
        board.add(Arrays.asList(btn7,btn8,btn9));


//        board.add(new ArrayList<Button>(Arrays.asList(btn1, btn2, btn3)));
//        board.add(new ArrayList<Button>(Arrays.asList(btn4, btn5, btn6)));
//        board.add(new ArrayList<Button>(Arrays.asList(btn7, btn8, btn9)));
        //for each loop to traverse each button
        //fun to initalize board status
        initialzeBoardStatus();
        for(List<Button> i : board){
            for(Button button : i){
                button.setOnClickListener(this);
            }

        }
        rstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turncount=0;
                player1=true;//again set player X
                // call this fun as we have to again reset all
                initialzeBoardStatus();
                updateDisplay("Player X turn");

            }


        });








    }

    public void initialzeBoardStatus() {
        int i,j;
        for(i=0;i<=2;i++){
            for(j=0;j<=2;j++){
                board_status[i][j]=-1;
                //enable all buttons and set text as empty
                board.get(i).get(j).setEnabled(true);
                board.get(i).get(j).setText(" ");
            }


        }
    }

    //all clicks of board button handle here
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1 :
                updateOnClick(0,0,player1);
                break;
            case R.id.btn2 :
                updateOnClick(0,1,player1);
                break;
            case R.id.btn3 :
                updateOnClick(0,2,player1);
                break;
            case R.id.btn4 :
                updateOnClick(1,0,player1);
                break;
            case R.id.btn5 :
                updateOnClick(1,1,player1);
                break;
            case R.id.btn6 :
                updateOnClick(1,2,player1);
                break;
            case R.id.btn7 :
                updateOnClick(2,0,player1);
                break;
            case R.id.btn8 :
                updateOnClick(2,1,player1);
                break;
            case R.id.btn9 :
                updateOnClick(2,2,player1);
                break;
        }
        player1=!player1;
        turncount=turncount+1;
        if(player1){

            updateDisplay("Player X Turn");
        }
        else{

            updateDisplay("Player O Turn");
        }
        if(turncount==9){
            updateDisplay("GAME DRAW");
        }
        checkWinner();
    }

    private void checkWinner() {
        int i;
        //to check if horizontal match for winner
        for(i=0;i<=2;i++){
            if(board_status[i][0]==board_status[i][1] && board_status[i][1]==board_status[i][2]){
                if(board_status[i][0]==1) {
                    updateDisplay("Player X win");
                    disablefun();
                    break;
                }
                else if(board_status[i][0]==0) {
                    updateDisplay("Player O win");
                    disablefun();
                    break;
                }
            }
        }
        //to check if vertical match for winner
        for(i=0;i<=2;i++){
            if(board_status[0][i]==board_status[1][i] && board_status[1][i]==board_status[2][i]){
                if(board_status[0][i]==1) {
                    updateDisplay("Player X win");
                    disablefun();
                    break;
                }
                else if(board_status[0][i]==0) {
                    updateDisplay("Player O win");
                    disablefun();
                    break;
                }
            }
        }
        //to check if diagonally matches
        if(board_status[0][0]==board_status[1][1] && board_status[1][1]==board_status[2][2]) {
            if(board_status[0][0]==1) {
                updateDisplay("Player X win");
                disablefun();

            }
            else if(board_status[0][0]==0) {
                updateDisplay("Player O win");
                disablefun();


            }

        }
        if(board_status[0][2]==board_status[1][1] && board_status[1][1]==board_status[2][0]) {
            if(board_status[0][2]==1) {
                updateDisplay("Player X win");
                disablefun();

            }
            else if(board_status[0][2]==0) {
                updateDisplay("Player O win");
                disablefun();


            }

        }

    }

    private void disablefun() {
        int i,j;
        for(i=0;i<=2;i++){
            for(j=0;j<=2;j++){

                //enable all buttons and set text as empty
                board.get(i).get(j).setEnabled(false);

            }


        }

    }

    private void updateDisplay(String turn) {
        txtup.setText(turn);
    }

    private void updateOnClick(int i, int i1, Boolean player1) {
        String val;
        if(player1) {
            val = "X";
            board_status[i][i1] = 1;
        }
        else {
            val = "O";
            board_status[i][i1] = 0;
        }
        board.get(i).get(i1).setText(val);
        board.get(i).get(i1).setEnabled(false);


    }




}
