package com.example.superfood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class Regular_user_profile extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.superfood.EXTRA_TEXT";
    private static final int PICK_IMAGE = 100;
    private Button back, confirm, imageEditbtn;
    private EditText name, bday, number, passwordTEXT;
    private TextView email, invalidmsg;
    private String text, emailSave;
    private CircleImageView image;
    private Uri newUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user_profile);

        back = findViewById(R.id.backbtn);
        confirm = findViewById(R.id.acceptbtn);
        name = findViewById(R.id.nametext);
        bday = findViewById(R.id.bDaytext2);
        number = findViewById(R.id.numbertext3);
        email = findViewById(R.id.emailtextnochange);
        passwordTEXT = findViewById(R.id.passwordtext);
        invalidmsg = findViewById(R.id.invalid_message);
        imageEditbtn = findViewById(R.id.imageeditbtn);
        image = findViewById(R.id.image);

        Intent intent = getIntent();
        text = intent.getStringExtra(LoginActivity.EXTRA_TEXT);

        for(int i = 0; i < ProfileInformation.getSize(); i++){
            if(text.equals(ProfileInformation.getName(i))){
                name.setHint(ProfileInformation.getName(i));
                name.setHintTextColor(getResources().getColor(R.color.white));
                bday.setHint(ProfileInformation.getBirthday(i));
                bday.setHintTextColor(getResources().getColor(R.color.white));
                number.setHint(ProfileInformation.getNumber(i));
                number.setHintTextColor(getResources().getColor(R.color.white));
                email.setText(ProfileInformation.getEmail(i));
                email.setHintTextColor(getResources().getColor(R.color.white));
                emailSave = ProfileInformation.getEmail(i);
                passwordTEXT.setHint(ProfileInformation.getPassword(i));
                passwordTEXT.setHintTextColor(getResources().getColor(R.color.white));
                image.setImageURI(ProfileInformation.getUri());
            }
        }

        imageEditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regular_user_profile.this, Regular_user_home_screen.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(passwordTEXT.getText().toString().length() > 8)
                        || !(isValidPassword(passwordTEXT.getText().toString()))
                        || !(isEmailValid(email.getText().toString()))){
                    if(!(passwordTEXT.getText().toString().length() > 8)){
                        invalidmsg.setText("Password is too short.");
                    }
                    else if(!(isValidPassword(passwordTEXT.getText().toString()))){
                        invalidmsg.setText("Password needs 8 characters, with at least one uppercase, lowercase, special characters, and number.");
                    }
                    else{
                        invalidmsg.setText("Email is invaild.");
                    }
                }
                else if(name.getText().toString().length() == 0){
                    invalidmsg.setText("Name is invaild.");
                }
                else if(number.getText().toString().length() != 10){
                    invalidmsg.setText("Number is invaild.");
                }
                else if(isBdayValid(bday.getText().toString())){
                    invalidmsg.setText("Bday is invaild.");
                }
                else{
                    for(int i = 0; i < ProfileInformation.getSize(); i++){
                        if(emailSave.equals(ProfileInformation.getEmail(i))){
                            ProfileInformation.setBirthday_at_i(bday.getText().toString(), i);
                            ProfileInformation.setName_at_i(name.getText().toString(), i);
                            ProfileInformation.setNumber_at_i(number.getText().toString(), i);
                            ProfileInformation.setPassword_at_i(passwordTEXT.getText().toString(), i);
                            text = ProfileInformation.getName(i);
                            i = ProfileInformation.getSize() + 1;
                        }
                    }
                }
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            for(int i = 0; i < ProfileInformation.getSize(); i++){
                if(text.equals(ProfileInformation.getName(i))){
                    newUri = data.getData();
                    ProfileInformation.setUri(newUri);
                    image.setImageURI(newUri);
                }
            }
        }
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

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


}
