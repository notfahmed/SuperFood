package com.example.superfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Regular_user_donate extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.superfood.EXTRA_TEXT";
    private Button cancel, confirm;
    private String userWelcome;
    private EditText donation, card, month, year, cvv;
    private TextView errormsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user_donate);

        cancel = findViewById(R.id.cancel);
        confirm = findViewById(R.id.confirm);
        donation = findViewById(R.id.donation_amount);
        errormsg = findViewById(R.id.error_message);
        card = findViewById(R.id.cardnumber);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        cvv = findViewById(R.id.cvv);

                cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                userWelcome = intent.getStringExtra(Regular_user_home_screen.EXTRA_TEXT);
                Intent intent2 = new Intent(Regular_user_donate.this, Regular_user_home_screen.class);
                intent2.putExtra(EXTRA_TEXT, userWelcome);
                startActivity(intent2);

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                userWelcome = intent.getStringExtra(Regular_user_home_screen.EXTRA_TEXT);
                if(validDonation(donation.getText().toString()) || donation.getText().toString().contains(":")){
                    if(valid_cardNumber(card.getText().toString())){
                        if(validMonth(month.getText().toString())){
                            if(validYear(year.getText().toString())){
                                if(validCVV(cvv.getText().toString())){
                                    for(int i = 0; i < ProfileInformation.getSize(); i++){
                                        if(ProfileInformation.getName(i).equals(userWelcome)){
                                            ProfileInformation.setDonation_at_i(Integer.parseInt(donation.getText().toString()), i);
                                        }
                                    }
                                    Intent intent2 = new Intent(Regular_user_donate.this, Regular_user_home_screen.class);
                                    intent2.putExtra(EXTRA_TEXT, userWelcome);
                                    startActivity(intent2);
                                }
                                else{
                                    errormsg.setText("INVALID CVV");
                                }
                            }
                            else{
                                errormsg.setText("INVALID YEAR");
                            }
                        }
                        else{
                            errormsg.setText("INVALID MONTH");
                        }
                    }
                    else{
                        errormsg.setText("INVALID CARD NUMBER");
                    }

                }
                else{
                    errormsg.setText("INVALID DONATION AMOUNT");
                }

            }
        });
    }
    public static boolean validDonation(String donationAmount){
        return !(donationAmount.equals("0"));
    }
    public static boolean valid_cardNumber(String cardNumber){
        return (cardNumber.length() == 16);
    }
    public static boolean validMonth(String month){
        if(month.equals("0") || (month.compareTo("12") > 0)){
            return false;
        }
        else{
            return true;
        }
    }
    public static boolean validYear(String year){
        if(year.equals(0) || (year.length() == 3)){
            return false;
        }
        else{
            return true;
        }
    }
    public static boolean validCVV(String cvv){
        if(cvv.length() == 3){
            return true;
        }
        else{
            return false;
        }
    }
}
