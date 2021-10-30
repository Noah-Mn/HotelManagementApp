package com.example.hotelmanagementapp;

public class DatabaseHelper {
    String textUsername, textEmailAddress, textPassword, textConfirmPassword;
    public DatabaseHelper() {
    }

    public String getTextUsername() {
        return textUsername;
    }

    public void setTextUsername(String textUsername) {
        this.textUsername = textUsername;
    }

    public String getTextEmailAddress() {
        return textEmailAddress;
    }

    public void setTextEmailAddress(String textEmailAddress) {
        this.textEmailAddress = textEmailAddress;
    }

    public String getTextPassword() {
        return textPassword;
    }

    public void setTextPassword(String textPassword) {
        this.textPassword = textPassword;
    }

    public String getTextConfirmPassword() {
        return textConfirmPassword;
    }

    public void setTextConfirmPassword(String textConfirmPassword) {
        this.textConfirmPassword = textConfirmPassword;
    }

    public DatabaseHelper(String textUsername, String textEmailAddress, String textPassword, String textConfirmPassword) {
        this.textUsername = textUsername;
        this.textEmailAddress = textEmailAddress;
        this.textPassword = textPassword;
        this.textConfirmPassword = textConfirmPassword;
    }
}
