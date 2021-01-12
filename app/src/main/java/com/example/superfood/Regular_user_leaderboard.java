package com.example.superfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


public class Regular_user_leaderboard extends AppCompatActivity {

    private Button backButton;
    public static final String EXTRA_TEXT = "com.example.superfood.EXTRA_TEXT";
    private CircleImageView profileImage;
    private String text;
    private TextView pointsText, hoursText, donationText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user_leaderboard);

        Intent intent = getIntent();
        text = intent.getStringExtra(LoginActivity.EXTRA_TEXT);
        backButton = findViewById(R.id.button);
        profileImage = findViewById(R.id.image);
        pointsText = findViewById(R.id.points_text);
        hoursText = findViewById(R.id.voluteered_hours_text);
        donationText = findViewById(R.id.donated_text);

        for(int i = 0; i < ProfileInformation.getSize(); i++){
            if(ProfileInformation.getName(i).equals(text));{
                hoursText.setText(String.valueOf(ProfileInformation.getVolunteer(i)));
                donationText.setText(String.valueOf(ProfileInformation.getDonation(i)));
                pointsText.setText(String.valueOf(ProfileInformation.getPoint(i)));
            }
        }

        if(ProfileInformation.getUri() != null){
            profileImage.setImageURI(ProfileInformation.getUri());
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regular_user_leaderboard.this, Regular_user_home_screen.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout = findViewById(R.id.put_text_here);
        TextView[] textView = new TextView[ProfileInformation.getSize()];
        for(int i = 0; i < ProfileInformation.getSize(); i++){
            textView[i] = new TextView(this);
            textView[i].setText("Name: " + ProfileInformation.getName(i)
                    + "                    Points: " + ProfileInformation.getPoint(i));
            textView[i].setTextSize(20);
            textView[i].setTextColor(Color.WHITE);
            textView[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(textView[i]);
        }



    }



}
