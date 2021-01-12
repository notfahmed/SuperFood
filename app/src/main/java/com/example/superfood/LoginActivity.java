package com.example.superfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.superfood.EXTRA_TEXT";
    private EditText email, password;
    private TextView Info;
    private Button sign_up, login_user, guest;
    private int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email_btn);
        password = findViewById(R.id.password_btn);
        Info = findViewById(R.id.inc_attempts);
        login_user = findViewById(R.id.loginbtn);
        sign_up = findViewById(R.id.sign_up_btn);
        guest = findViewById(R.id.guest_btn);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString());
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Regular_user_home_screen.class);
                intent.putExtra(EXTRA_TEXT, "Guest");
                startActivity(intent);
            }
        });
    }

    private void validate(String userName, String userPassword){
        for(int i = 0; i < ProfileInformation.getSize(); i++){
            if(ProfileInformation.getEmail(i).equals(userName) && ProfileInformation.getPassword(i).equals(userPassword)){
                String text = ProfileInformation.getName(i);
                Intent intent = new Intent(LoginActivity.this, Regular_user_home_screen.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);

                i = ProfileInformation.getSize() + 1;
            }
        }
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            openHomeScreen();
        }
        else{
            counter--;
            Info.setText("# of Incorrect Attempts: " + String.valueOf(counter));
            if(counter == 0){
                login_user.setEnabled(false);
            }
        }
    }
    public void openHomeScreen(){
        EditText editText1 = findViewById(R.id.email_btn);
        String text = editText1.getText().toString();
//        String text = "Fawaz";
        Intent intent = new Intent(LoginActivity.this, Regular_user_home_screen.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }

}
