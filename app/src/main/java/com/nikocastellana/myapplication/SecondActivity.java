package com.nikocastellana.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondActivity extends Activity {

    protected EditText textField;
    protected String phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textField = (EditText) findViewById(R.id.enterNumber);
        textField.setOnEditorActionListener(numListener);
    }

    public TextView.OnEditorActionListener numListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            /* Phone number formats*/
            Pattern format1 = Pattern.compile("[0-9]{3}[0-9]{3}[0-9]{3}");
            Pattern format2 = Pattern.compile("[0-9]{3} [0-9]{3} [0-9]{3}");
            Pattern format3 = Pattern.compile("[(][0-9]{3}[)][-]??\\s??[0-9]{3}[-]??\\s??[0-9]{3}");

            // Get phone number from text view and pass through intent
            phoneNumber = v.getText().toString();
            Intent result = new Intent(SecondActivity.this, MainActivity.class);
            result.putExtra("Number", phoneNumber);

            // Check if number is correct format
            if(phoneNumber.matches(format1.pattern())){
                setResult(Activity.RESULT_OK, result);
            }
            else if(phoneNumber.matches(format2.pattern())){
                setResult(Activity.RESULT_OK, result);
            }
            else if(phoneNumber.matches(format3.pattern())){
                setResult(Activity.RESULT_OK, result);
            }
            else{
                setResult(Activity.RESULT_CANCELED, result);
            }
            finish();

            return true;
        }
    };

}