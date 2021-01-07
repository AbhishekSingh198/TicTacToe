package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button playNow;
    EditText player1name,player2name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        playNow=findViewById(R.id.playNow);
        player1name=findViewById(R.id.player1name);
        player2name=findViewById(R.id.player2name);
        playNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name2 = player2name.getText().toString();
                if (player2name.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter the field", Toast.LENGTH_SHORT).show();
                } else {
                    String name1 = player1name.getText().toString().trim();
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    intent.putExtra("name1", name1);
                    intent.putExtra("name2",name2);
                    startActivity(intent);
                }

            }
        });
    }
}