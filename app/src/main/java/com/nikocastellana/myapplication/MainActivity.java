package com.nikocastellana.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected String phoneNumber = "";
    protected Button button1;
    protected Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            startActivityForResult(i,1);
        });

        button2 = (Button) findViewById(R.id.button2);
    }

    protected void onActivityResult(int code, int result_code, Intent i) {
        super.onActivityResult(code, result_code, i);

        // Extract phone number from intent
        phoneNumber = i.getStringExtra("Number");

        // Check the result code from the second activty
        if(result_code == Activity.RESULT_OK){
            button2.setOnClickListener(v->{
                Intent dialActivity = new Intent(Intent.ACTION_DIAL);

                dialActivity.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(dialActivity);
            });
        }
        else if(result_code == Activity.RESULT_CANCELED){
            Log.i("Toast number is", phoneNumber);
            button2.setOnClickListener(v->{
                Toast.makeText(MainActivity.this, "Error wrong number: " + phoneNumber, Toast.LENGTH_SHORT).show();
            });
        }
    }
}