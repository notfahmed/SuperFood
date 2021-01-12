package com.example.superfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Regular_user_volunteer extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.superfood.EXTRA_TEXT";
    private String userWelcome;
    private Spinner time_am, time_pm;
    private EditText time_from, time_to;
    private Button confirm, cancel;
    private TextView errormsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user_volunteer);

        time_am = findViewById(R.id.time_spinner_to);
        time_pm = findViewById(R.id.time_spinner_from);
        time_from = findViewById(R.id.from_time);
        time_to = findViewById(R.id.to_time);
        confirm = findViewById(R.id.confirm);
        errormsg = findViewById(R.id.error_message);
        cancel = findViewById(R.id.cancel);

        Intent intent = getIntent();
        userWelcome = intent.getStringExtra(Regular_user_home_screen.EXTRA_TEXT);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateTimes(time_from.getText().toString(), time_am.getSelectedItem().toString(), time_to.getText().toString(), time_pm.getSelectedItem().toString())){
                    Intent intent = getIntent();
                    userWelcome = intent.getStringExtra(Regular_user_home_screen.EXTRA_TEXT);

                    for(int i = 0; i < ProfileInformation.getSize(); i++){
                        if(ProfileInformation.getName(i).equals(userWelcome)){
                            ProfileInformation.setVolunteer_at_i(
                                    differenceTimes(time_from.getText().toString(),
                                                    time_am.getSelectedItem().toString(),
                                                    time_to.getText().toString(),
                                                    time_pm.getSelectedItem().toString()), i);
                            i = ProfileInformation.getSize() + 1;
                        }
                    }

                    Intent intent2 = new Intent(Regular_user_volunteer.this, Regular_user_home_screen.class);
                    intent2.putExtra(EXTRA_TEXT, userWelcome);
                    startActivity(intent2);
                }
                else{
                    errormsg.setText("INVALID TIMINGS");
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Regular_user_volunteer.this, Regular_user_home_screen.class);
                intent2.putExtra(EXTRA_TEXT, userWelcome);
                startActivity(intent2);
            }
        });
    }
    public static boolean validateTimes(String fromTime, String am_pm1, String toTime, String am_pm2){
        if((am_pm1.equals("AM") && am_pm2.equals("AM")) || (am_pm1.equals("PM") && am_pm2.equals("PM"))){
            if((fromTime.equals(toTime)) || fromTime.compareTo(toTime) < 0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return true;
        }
    }
    public static int differenceTimes(String fromTime, String am_pm1,String toTime, String am_pm2){
        char split_c = ':';
        String split_s = ":";
        String newFromTime = "";
        String newToTime = "";
        if(fromTime.contains(split_s)){
            for(int i = 0; i < fromTime.length(); i++){
                char c = fromTime.charAt(i);
                if(c != split_c){
                    newFromTime += c;
                }
                else{
                    i = fromTime.length()+1;
                }
            }
        }
        else{
            newFromTime = fromTime;
        }
        if(toTime.contains(split_s)){
            for(int i = 0; i < toTime.length(); i++){
                char c = toTime.charAt(i);
                if(c != split_c){
                    newToTime += c;
                }
                else{
                    i = toTime.length()+1;
                }
            }
        }
        else{
            newToTime = toTime;
        }

        if(((am_pm1.equals("AM") && am_pm2.equals("AM")) || (am_pm1.equals("PM") && am_pm2.equals("PM")))){
            int fromTime_int = Integer.parseInt(newFromTime);
            int toTime_int = Integer.parseInt(newToTime);
            return toTime_int - fromTime_int;
        }
        else{
            if(am_pm1.equals("AM") && am_pm2.equals("PM")){
                int fromTime_int = Integer.parseInt(newFromTime);
                int toTime_int = Integer.parseInt(newToTime) + 12;
                return toTime_int - fromTime_int;
            }
            else{
                int fromTime_int = Integer.parseInt(newFromTime);
                int toTime_int = Integer.parseInt(newToTime);
                return (fromTime_int - 12) + ((toTime_int));
            }
        }

    }
}
