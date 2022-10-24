package com.example.tic_tac_to;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 for o ,1 for x, 2 for empty
    int PlayerActive=0;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    public static int count =0;
    boolean gameActive=true;
    int[][] winningPossible={{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};

    public void onImageTap(View view){
        ImageView imageView=(ImageView)view;
        imageView.setTranslationY(-1000);
        int imageTapped=Integer.parseInt(imageView.getTag().toString());

        if (gameState[imageTapped] == 2 && gameActive)
        {
            count++;
            if (count==9){
                gameActive=false;
            }

        gameState[imageTapped]=PlayerActive;
        Log.i("ImageTap", String.valueOf(imageTapped));
        if (PlayerActive==0){
        imageView.setImageResource(R.drawable.o);
        PlayerActive=1;
        TextView status=findViewById(R.id.textView2);
        status.setText("X's turn");
        }else {
            imageView.setImageResource(R.drawable.x);
            PlayerActive=0;
            TextView status=findViewById(R.id.textView2);
            status.setText("0's turn");
        }
        imageView.animate().translationYBy(1000).setDuration(200);
        int draw =1;
        for (int[] element : winningPossible){
            if (gameState[element[0]]==gameState[element[1]] && gameState[element[1]]==gameState[element[2]]
            && gameState[element[0]] != 2){
                draw=0;
                String winner;
                if (gameState[element[0]]==0){
                    winner="0 has won";
                }else {
                    winner="X has won ";
                }
                TextView status=findViewById(R.id.textView2);
                status.setText(winner);

                 Button playAgain=findViewById(R.id.PlayAgain);
                 playAgain.setVisibility(View.VISIBLE);
            }
          }
        // Draw condition
            if (count==9 && draw==1){
                TextView status=findViewById(R.id.textView2);
                status.setText("Match Draw");

                Button playAgain=findViewById(R.id.PlayAgain);
                playAgain.setVisibility(View.VISIBLE);
            }
        }

    }
    public void OnClickButton(View view){
        Button playAgain=findViewById(R.id.PlayAgain);
        playAgain.setVisibility(View.INVISIBLE);

        TextView status=findViewById(R.id.textView2);
        status.setText("0's turn");

        gameActive=true;
        PlayerActive=0;
        count=0;
        // Setting gameState to empty
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView1)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView2)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView3)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView4)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView5)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView6)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView7)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.imageView8)).setImageDrawable(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playAgain=findViewById(R.id.PlayAgain);
        playAgain.setVisibility(View.INVISIBLE);
    }
}