package com.example.hotelmanagementapp;

public class DatabaseHelper {
    String textUsername, textEmailAddress, textPassword, textConfirmPassword;
    public DatabaseHelper() {
    }

    public String getTextInputEditText() {
        return textUsername;
    }

    public void setTextInputEditText(String textInputEditText) {
        this.textUsername = textInputEditText;
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

    public DatabaseHelper(String textInputEditText, String textEmailAddress, String textPassword, String textConfirmPassword) {
        this.textUsername = textInputEditText;
        this.textEmailAddress = textEmailAddress;
        this.textPassword = textPassword;
        this.textConfirmPassword = textConfirmPassword;
    }
}
