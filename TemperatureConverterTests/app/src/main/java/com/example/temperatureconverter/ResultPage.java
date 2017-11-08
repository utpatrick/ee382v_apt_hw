package com.example.temperatureconverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Patrick on 11/7/2017.
 */

public class ResultPage extends Activity{
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        text = (TextView) findViewById(R.id.result);
        Bundle inputData = getIntent().getExtras();
        String input = inputData.getString("INPUT");
        String input_type = inputData.getString("INPUT_TYPE");
        String result = inputData.getString("RESULT");
        String result_type = inputData.getString("RESULT_TYPE");
        text.setText(input + input_type + " is "+ result + result_type);
    }

    public void goToMain(View view){
        Intent intent = new Intent(ResultPage.this, MainActivity.class);
        startActivity(intent);
    }
}
