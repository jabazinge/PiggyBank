package com.example.piggybankchildrensapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Bank extends AppCompatActivity {
    final double quarters = .25;
    final double dimes = .10;
    final double nickels = .05;
    final double pennies = .01;
    double account;
    double total = 0;
    boolean save = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
//        final Spinner group = (Spinner) findViewById(R.id.txtGroup);
        final EditText quarterTxt = (EditText) findViewById(R.id.quarters);
        final EditText dimeTxt = (EditText) findViewById(R.id.dimes);
        final EditText nickelTxt = (EditText) findViewById(R.id.nickels);
        final EditText pennieTxt = (EditText) findViewById(R.id.pennies);
        final TextView account = (TextView) findViewById(R.id.account);
        final Button saveButton = (Button)  findViewById(R.id.action_btn);
        Spinner bankActionSpinner = (Spinner) findViewById(R.id.txtGroup);
        bankActionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String selected =     bankActionSpinner.getSelectedItem().toString();
               Log.d("Selected38", selected);
                if(selected.equals("Deposit")){
                    Log.d("Debugging 5: ", String.valueOf(save));
                    save = true;
                    saveButton.setText("Deposit");
                    saveButton.setEnabled(true);
                    Log.d("Selected: ", selected);
                    Log.d("Save: ", String.valueOf(save));

                }else if(selected.equals("Withdrawal")){
                    save = false;
                    saveButton.setText("Withdraw");
                    saveButton.setEnabled(true);
                    Log.d("Selected: ", selected);
                    Log.d("Save: ", String.valueOf(save));
                }else{
                    saveButton.setText("Deposit");
                    saveButton.setEnabled(false);
                }

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Total 1: ",quarterTxt.getText().toString());
                        double quaterValue = 0;
                        double  dimeValue = 0;
                        double nickelValue = 0;
                        double  pennyValue = 0;
                        DecimalFormat currency = new DecimalFormat("$###,###.##");
                            if(!quarterTxt.getText().toString().isEmpty()){
                                quaterValue = multiplier(quarters, quarterTxt.getText().toString());
                                Log.d("Total 1: ",quarterTxt.getText().toString());


                            }
                        if(!dimeTxt.getText().toString().isEmpty()){
                            dimeValue = multiplier(dimes, dimeTxt.getText().toString());
                            Log.d("Dime: ",dimeTxt.getText().toString());


                        }
                        if(!nickelTxt.getText().toString().isEmpty()){
                            nickelValue = multiplier(nickels, nickelTxt.getText().toString());
                            Log.d("Nickel: ",nickelTxt.getText().toString());


                        }
                        if(!pennieTxt.getText().toString().isEmpty()){
                            pennyValue = multiplier(pennies, pennieTxt.getText().toString());
                            Log.d("Total 1: ",pennieTxt.getText().toString());


                        }
                        if (save) {
                            deposit(nickelValue);
                            deposit(dimeValue);
                            deposit(pennyValue);
                            deposit(quaterValue);
                            account.setText("Your Balance: "  +currency.format(total));
                            Log.d("Your Total is: ", String.valueOf(total));

                        }else {
                            withdraw(quaterValue);
                            withdraw(nickelValue);
                            withdraw(dimeValue);
                            withdraw(pennyValue);

                            account.setText("Your Balance: "  +currency.format(total));
                            Log.d("You Total is: " , String.valueOf(total));
                        }
                    }
                });




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public  double multiplier(double _currency, String nums){
        double numValue = Double.parseDouble(nums);
        return numValue*_currency;
    }
    public  void deposit(double value){
        total = total+value;
    }

    public void withdraw (double value){
        if(value > total) return;
        total = total-value;
    }
}