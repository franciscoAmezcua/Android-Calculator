package com.franciscoamezcua.calculatorassignment;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    private Button nextActivity;


    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    TextView result;
    TextView name, age;
    EditText number1, number2;
    Button add, subtract, divide, multiply, factorial;

    double result_num;
    int num1, num2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextActivity = (Button) findViewById(R.id.next);

        result =  (TextView) findViewById(R.id.result);
        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        number1 = (EditText) findViewById(R.id.num1);
        number2 = (EditText) findViewById(R.id.num2);

        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.subtract);
        divide = (Button) findViewById(R.id.divide);
        multiply = (Button) findViewById(R.id.multiply);
        factorial = (Button) findViewById(R.id.factorial);

        // Capture the layout's TextView and set the string as its text


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((number1.getText().length()>0) && (number2.getText().length()>0))
                {
                   double num1 = Double.parseDouble(number1.getText().toString());
                   double num2 = Double.parseDouble(number2.getText().toString());
                    result_num = num1 + num2;
                    result.setText(String.valueOf(result_num));
                }
                else{
                    Toast toast= Toast.makeText(MainActivity.this,"Enter The Required Numbers",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((number1.getText().length()>0) && (number2.getText().length()>0))
                {
                    double num1 = Double.parseDouble(number1.getText().toString());
                    double num2 = Double.parseDouble(number2.getText().toString());
                    result_num = num1 - num2;
                    result.setText(String.valueOf(result_num));
                }
                else{
                    Toast toast= Toast.makeText(MainActivity.this,"Enter The Required Numbers",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((number1.getText().length()>0) && (number2.getText().length()>0))
                {
                    double num1 = Double.parseDouble(number1.getText().toString());
                    double num2 = Double.parseDouble(number2.getText().toString());
                    result_num = num1 / num2;
                    result.setText(String.valueOf(result_num));
                }
                else{
                    Toast toast= Toast.makeText(MainActivity.this,"Enter The Required Numbers",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((number1.getText().length()>0) && (number2.getText().length()>0))
                {
                    double num1 = Double.parseDouble(number1.getText().toString());
                    double num2 = Double.parseDouble(number2.getText().toString());
                    result_num = num1 * num2;
                    result.setText(String.valueOf(result_num));
                }
                else{
                    Toast toast= Toast.makeText(MainActivity.this,"Enter The Required Numbers",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((number1.getText().length()>0))
                {
                    double num1 = Double.parseDouble(number1.getText().toString());
                    result.setText("" + String.format(Locale.getDefault(),"%.0f",factorialcalc(num1)));
                }
                else{
                    Toast toast= Toast.makeText(MainActivity.this,"Can't accept null or negative values on First Number",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivityForResult(new Intent(getApplicationContext(), DisplaySecondActivity.class), 999);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999 && resultCode == 1) {
            name.setText(data.getStringExtra("name"));
            age.setText(data.getStringExtra("age"));
        }
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Intent created to bind two components
        Intent intent = new Intent(this, DisplaySecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.name);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    private double factorialcalc (double factnum){
        if(factnum < 2){
            return 1;
        }else{
            return factnum * factorialcalc(factnum - 1);
        }
    }

}
