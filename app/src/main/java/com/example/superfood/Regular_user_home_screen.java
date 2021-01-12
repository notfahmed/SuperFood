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

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class Regular_user_home_screen extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.superfood.EXTRA_TEXT";
    private String text;
    private TextView user_welcome, points_user;
    private CircleImageView ProfileImage;
    private Button profile_image_change, volunteer, profile, donate, leaderboard;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user_home_screen);

        user_welcome = findViewById(R.id.name);
        ProfileImage = findViewById(R.id.image);
        volunteer = findViewById(R.id.volunteer);
        donate = findViewById(R.id.donate);
        profile = findViewById(R.id.profilebtn);
        leaderboard = findViewById(R.id.leaderboardbtn);
        points_user = findViewById(R.id.user_points_main_screen);

        for(int i = 0; i < ProfileInformation.getSize(); i++){
            if(ProfileInformation.getName(i).equals(text)){
                points_user.setText("Points: " + ProfileInformation.getPoint(i));
            }
        }

        Intent intent = getIntent();
        text = intent.getStringExtra(LoginActivity.EXTRA_TEXT);
        change_user_name(text);

        if(ProfileInformation.getUri() != null){
            ProfileImage.setImageURI(ProfileInformation.getUri());
        }


        profile_image_change = findViewById(R.id.imageButton);
        profile_image_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regular_user_home_screen.this, Regular_user_volunteer.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regular_user_home_screen.this, Regular_user_donate.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regular_user_home_screen.this, Regular_user_profile.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regular_user_home_screen.this, Regular_user_leaderboard.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            ProfileImage.setImageURI(imageUri);
            ProfileInformation.setUri(imageUri);
        }
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private void change_user_name(String userName){
        if(userName.equals("Admin")){
            user_welcome.setText(userName);
        }
        else{
            user_welcome.setText(userName);
        }

    }
}
