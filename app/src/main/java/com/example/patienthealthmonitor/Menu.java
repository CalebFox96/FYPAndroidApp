package com.example.patienthealthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button Hist, Graph, Live, Logout,btn,graph,history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn = (Button) findViewById(R.id.btn);
        Live =(Button)findViewById(R.id.button3);
        Logout =(Button)findViewById(R.id.button7);
        graph =(Button)findViewById(R.id.button5);
        history =(Button)findViewById(R.id.button4);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),History.class);
                startActivity(startIntent);
            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),Graph.class);
                startActivity(startIntent);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),Video.class);
                startActivity(startIntent);
            }
        });

        Live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),UserEntry.class);
                startActivity(startIntent);
            }
        });


    }
}
