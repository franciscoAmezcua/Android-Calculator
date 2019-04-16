package com.franciscoamezcua.calculatorassignment;

/**
 * Created by famezcua on 2018-01-26.
 */

import android.app.DatePickerDialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Calendar;

import java.io.FileInputStream;



public class DisplaySecondActivity extends AppCompatActivity {

    private static final String TAG = "DisplaySecondActivity";
    private static  final String FILE_NAME = "sample.txt";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText editText;
    private TextView textView2;
    private TextView ageView;

    TextView mEditText;
    TextView mEditTextAge;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        textView2 = (TextView) findViewById(R.id.textView2);
        ageView = (TextView) findViewById(R.id.ageView);

        mDisplayDate = (TextView) findViewById(R.id.tvBirthDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month =  cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        DisplaySecondActivity.this,
                        android.R.style.Theme_Holo_Dialog,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDataSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
                String date =  year + "/" + month + "/" + day;

                int ageYear = year;
                int currentYear = 2018;
                int result_ageCalc;
                result_ageCalc = currentYear - ageYear;
                ageView.setText(String.valueOf(result_ageCalc));
                mDisplayDate.setText(date);

            }
        };


        mEditText = findViewById(R.id.textView2);
        mEditTextAge = findViewById(R.id.ageView);


    }

    public void save(View v) {
        String text = mEditText.getText().toString();
        String text2 = mEditTextAge.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().equals(null);
            mEditTextAge.getText().equals(null);
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            mEditText.setText(sb.toString());
            mEditTextAge.setText(sb.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {


        Intent i = new Intent();
        i.putExtra("name", textView2.getText().toString());
        i.putExtra("age", ageView.getText().toString());
        setResult(1, i);

        finish();
    }
}
