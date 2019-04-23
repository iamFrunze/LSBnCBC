package com.example.lsb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);
    }

    public void goToLSB(View view) {
        Intent intent = new Intent(this, LSBActivity.class);
        startActivity(intent);
    }
}
