package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int score1=0;
    int score2=0,roundCount=0;
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2,2};
    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img=(ImageView)view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(gameState[tappedImage]==2&& gameActive){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                String name2 = getIntent().getStringExtra("name2");
                status.setText(String.format("%s%s", name2, getString(R.string.twos)));
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                String name1 = getIntent().getStringExtra("name1");
                status.setText(String.format("%s%s", name1, getString(R.string.ones)));
            }
            img.animate().translationYBy(1000f).rotation(360*10).setDuration(1000);
            roundCount++;
        }
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    TextView status = findViewById(R.id.status);
                    String name1 = getIntent().getStringExtra("name1");
                    status.setText(String.format("%s WON", name1));
                    score1++;
                    textView=findViewById(R.id.textView);
                    textView.setText(String.format(getString(R.string.ssd), getString(R.string.player_1), name1,score1));
                }
                else{
                    TextView status = findViewById(R.id.status);
                    String name2 = getIntent().getStringExtra("name2");
                    status.setText(String.format("%s WON", name2));
                    score2++;
                    textView2=findViewById(R.id.textView2);
                    textView2.setText(String.format(getString(R.string.ssd), getString(R.string.player_2), name2,score2));
                }
                Button playAgain= (Button) findViewById(R.id.playAgain);
                playAgain.setVisibility(View.VISIBLE);
            }
            else if(roundCount==9){
                TextView status = findViewById(R.id.status);
                status.setText(R.string.draw);
                gameActive=false;
                Button playAgain= (Button) findViewById(R.id.playAgain);
                playAgain.setVisibility(View.VISIBLE);
            }
        }
        Button playAgain= (Button) findViewById(R.id.playAgain);
        playAgain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gameReset(v);
                        Button playAgain= (Button) findViewById(R.id.playAgain);
                        playAgain.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        roundCount=0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        String name1 = getIntent().getStringExtra("name1");
        status.setText(String.format("%s%s", name1, getString(R.string.ones)));

    }
    TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gameReset(v);
                    }
                }
        );
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        String name1 = getIntent().getStringExtra("name1");
        String name2 = getIntent().getStringExtra("name2");
        textView.setText(String.format("%s%s", getString(R.string.player_1), name1));
        textView2.setText(String.format("%s%s", getString(R.string.player_2), name2));
    }
}