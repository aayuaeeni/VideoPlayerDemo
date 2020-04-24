package com.raju.vpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button btnPlayer1,btnPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActions();
    }

    private void initActions()
    {
        btnPlayer1.setOnClickListener(this);
        btnPlayer2.setOnClickListener(this);
    }

    private void initViews()
    {
        btnPlayer1 = findViewById(R.id.btnPlayer1);
        btnPlayer2 = findViewById(R.id.btnPlayer2);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnPlayer1:
                startActivity(new Intent(MainActivity.this,Plater1Activity.class));
                break;
            case R.id.btnPlayer2:
                startActivity(new Intent(MainActivity.this,Player2Activity.class));
                break;
        }
    }
}
