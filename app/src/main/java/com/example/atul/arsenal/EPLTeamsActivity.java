package com.example.atul.arsenal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class EPLTeamsActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eplteams);
        textView = findViewById(R.id.sample1);
        String s = getIntent().getStringExtra("teamPlayers");
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}
