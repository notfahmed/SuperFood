package com.example.superfood;

import android.net.Uri;
import java.util.ArrayList;

public class ProfileInformation{
    private static int x = 0;
    private static ArrayList<String> name = new ArrayList<>(x);
    private static ArrayList<String> email = new ArrayList<>(x);
    private static ArrayList<String> password = new ArrayList<>(x);
    private static ArrayList<String> bDay = new ArrayList<>(x);
    private static ArrayList<String> number = new ArrayList<>(x);
    private static Uri uri;
    private static ArrayList<Integer> donation = new ArrayList<>(x);
    private static ArrayList<Integer> volunteer = new ArrayList<>(x);


    public static String getName(int x){
        return name.get(x);
    }
    public static String getPassword(int x){
        return password.get(x);
    }
    public static String getBirthday(int x){
        return bDay.get(x);
    }
    public static String getNumber(int x){
        return number.get(x);
    }
    public static String getEmail(int x){
        return email.get(x);
    }
    public static int getSize(){
        return name.size();
    }
    public static int getDonation(int x){
        return donation.get(x);
    }
    public static int getVolunteer(int x){
        return volunteer.get(x);
    }
    public static int getPoint(int x){
        return (donation.get(x) + (volunteer.get(x)*24));
    }
    public static Uri getUri(){
        return uri;
    }

    public static void setName(String newItem){
        name.add(newItem);
    }
    public static void setEmail(String newItem){
        email.add(newItem);
    }
    public static void setPassword(String newItem){
        password.add(newItem);
    }
    public static void setBirthday(String newItem){
        bDay.add(newItem);
    }
    public static void setNumber(String newItem){
        number.add(newItem);
    }
    public static void setDonation(){
        donation.add(0);
    }
    public static void setVolunteer(){
        volunteer.add(0);
    }
    public static void setUri(Uri imageUri){
        uri = imageUri;
    }

    public static void setName_at_i(String newItem , int i){
        name.set(i, newItem);
    }
    public static void setPassword_at_i(String newItem, int i){
        password.set(i, newItem);
    }
    public static void setBirthday_at_i(String newItem, int i){
        bDay.set(i, newItem);
    }
    public static void setNumber_at_i(String newItem, int i){
        number.set(i, newItem);
    }
    public static void setDonation_at_i(int newItem, int i){
        newItem += donation.get(i);
        donation.set(i, newItem);

    }
    public static void setVolunteer_at_i(int newItem, int i){
        newItem += volunteer.get(i);
        volunteer.set(i, newItem);
    }




}
