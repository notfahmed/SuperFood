package com.example.superfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private SharedPreferences myPreferences;
    private TextView password_invalid_message;
    private SharedPreferences.Editor myEditor;
    private EditText Name, Password, Email, confrimPassword, Number, Birthday;
    private Button cancel, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        cancel =  findViewById(R.id.cancelbtn);
        login = findViewById(R.id.loginbtn);
        Name = findViewById(R.id.fullname);
        Password = findViewById(R.id.password);
        Email = findViewById(R.id.email);
        confrimPassword = findViewById(R.id.confirmpass);
        Number = findViewById(R.id.phonenum);
        Birthday = findViewById(R.id.bday);
        password_invalid_message = findViewById(R.id.password_warning);
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myEditor = myPreferences.edit();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(Password.getText().toString().length() > 8)
                        || !(isValidPassword(Password.getText().toString()))
                        || !(isEmailValid(Email.getText().toString()))
                        || !(samePasswords(Password.getText().toString(), confrimPassword.getText().toString()))){
                    if(!(Password.getText().toString().length() > 8)){
                        password_invalid_message.setText("Password is too short.");
                    }
                    else if(!(isValidPassword(Password.getText().toString()))){
                        password_invalid_message.setText("Read asterisk.");
                    }
                    else if(!(samePasswords(Password.getText().toString(), confrimPassword.getText().toString()))){
                        password_invalid_message.setText("Passwords are not the same.");
                    }
                    else{
                        password_invalid_message.setText("Email is invaild.");
                    }
                }
                else if(Name.getText().toString().length() == 0){
                    password_invalid_message.setText("Name is invaild.");
                }
                else if(Number.getText().toString().length() != 10){
                    password_invalid_message.setText("Number is invaild.");
                }
                else if(isBdayValid(Birthday.getText().toString())){
                    password_invalid_message.setText("Bday is invaild.");
                }
                else{
                    ProfileInformation.setBirthday(Birthday.getText().toString());
                    ProfileInformation.setName(Name.getText().toString());
                    ProfileInformation.setNumber(Number.getText().toString());
                    ProfileInformation.setPassword(Password.getText().toString());
                    ProfileInformation.setEmail(Email.getText().toString());
                    ProfileInformation.setDonation();
                    ProfileInformation.setVolunteer();
                    Intent intent = new Intent(SignUp.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    public static boolean isBdayValid(String Bday){
        String split[] = Bday.split("/");
        if((split[0].equals("0")) || (split[0].compareTo("12") < 0)){
            System.out.println("ERROR 1");
            return false;
        }
        else if((split[1].equals("0")) || (split[1].compareTo("31") < 0)){
            System.out.println("ERROR 2");
            return false;
        }
        else if(split[2].equals("0")){
            System.out.println("ERROR 3");
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isValidPassword(final String userPassword){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(userPassword);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean samePasswords(final String password, final String confirm){
        return password.equals(confirm);
    }
}
