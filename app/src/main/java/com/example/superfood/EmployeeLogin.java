package com.example.superfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EmployeeLogin extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.superfood.EXTRA_TEXT";
    private EditText email, password;
    private Spinner Company_validate;
    private TextView Info;
    private Button sign_up,  login_user;
    private int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);

        email = findViewById(R.id.email_btn);
        password = findViewById(R.id.password_btn);
        Info = findViewById(R.id.inc_attempts);
        login_user = findViewById(R.id.loginbtn);
        sign_up = findViewById(R.id.sign_up_btn);
        Company_validate.findViewById(R.id.company_spinner);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeLogin.this, SignUp.class);
                startActivity(intent);
            }
        });

        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString(), Company_validate.getSelectedItem().toString());
            }
        });
    }

    private void validate(String userName, String userPassword, String company){
        if((userName.equals("Klyne.Smith@utdallas.edu")) && (userPassword.equals("1234")) && (company.equals("UTD"))){
            Intent intent = new Intent(EmployeeLogin.this, Regular_user_home_screen.class);;
            startActivity(intent);
        }
        else{
            counter--;
            Info.setText("# of Incorrect Attempts: " + String.valueOf(counter));
            if(counter == 0){
                login_user.setEnabled(false);
            }
        }
    }
}
