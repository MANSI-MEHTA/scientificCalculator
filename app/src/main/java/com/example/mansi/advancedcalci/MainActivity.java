package com.example.mansi.advancedcalci;

import android.content.pm.ActivityInfo;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNumber;
    Button btnSquare, btnSquareRoot, btnCube;
    TextView tvAnswer;


    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);

        etNumber = (EditText) findViewById(R.id.etNumber);
        btnSquare = (Button) findViewById(R.id.btnSquare);
        btnSquareRoot = (Button) findViewById(R.id.btnSquareRoot);
        btnCube = (Button) findViewById(R.id.btnCube);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);

        btnSquare.setOnClickListener(this);
        btnSquareRoot.setOnClickListener(this);
        btnCube.setOnClickListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }
    public void onClick(View v) {

        if (etNumber.getText().toString().length() == 0) {
            Snackbar.make(v, "Please enter a number", Snackbar.LENGTH_LONG).show();
            etNumber.requestFocus();
            return;
        }

        double number;

        switch (v.getId()) {

            case R.id.btnSquare:
                number = Double.parseDouble(etNumber.getText().toString());
                double square = number * number;
                tvAnswer.setText("Square of " + number + " is: " + square);
                break;

            case R.id.btnSquareRoot:
                number = Double.parseDouble(etNumber.getText().toString());
                double squareRoot = Math.sqrt(number);
                tvAnswer.setText("Square root of " + number + " is: " + squareRoot);
                break;

            case R.id.btnCube:
                number = Double.parseDouble(etNumber.getText().toString());
                double cube = number * number * number;
                tvAnswer.setText("Cube of " + number + " is: " + cube);
                break;

            case R.id.fab:
                String toSpeak = tvAnswer.getText().toString();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.email:
                Snackbar.make(findViewById(android.R.id.content),"Clicked Email", Snackbar.LENGTH_LONG ).show();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"help@kc.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Need Help");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "I want help regarding ");
                startActivity(emailIntent);
                break;

            case R.id.contact:
                Snackbar.make(findViewById(android.R.id.content),"Clicked Contact", Snackbar.LENGTH_LONG ).show();
                Intent c = new Intent(Intent.ACTION_DIAL);
                c.setData(Uri.parse("tel:" + "9022272186"));
                startActivity(c);
                break;

            case R.id.about:
                Snackbar.make(findViewById(android.R.id.content),"Calculator Developed by KC", Snackbar.LENGTH_LONG ).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}

