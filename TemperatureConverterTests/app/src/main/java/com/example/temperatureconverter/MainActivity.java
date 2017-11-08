package com.example.temperatureconverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.inputValue);

    }

    // this method is called at button click because we assigned the name to the
    // "OnClick" property of the button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                RadioButton celsiusButton = (RadioButton) findViewById(R.id.radio0);
                RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radio1);
                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                float inputValue = Float.parseFloat(text.getText().toString());
                if (celsiusButton.isChecked()) {
                    text.setText(String
                            .valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                    celsiusButton.setChecked(false);
                    fahrenheitButton.setChecked(true);
                    goToResult(String.valueOf(inputValue), "F", String
                            .valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)), "C");
                } else {
                    text.setText(String
                            .valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                    fahrenheitButton.setChecked(false);
                    celsiusButton.setChecked(true);
                    goToResult(String.valueOf(inputValue), "C", String
                            .valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)), "F");
                }
                break;
        }
    }

    public void goToResult(String input, String in_type, String result, String res_type){
        Intent intent = new Intent(MainActivity.this, ResultPage.class);
        intent.putExtra("INPUT", input);
        intent.putExtra("INPUT_TYPE", in_type);
        intent.putExtra("RESULT", result);
        intent.putExtra("RESULT_TYPE", res_type);
        startActivity(intent);
    }
}