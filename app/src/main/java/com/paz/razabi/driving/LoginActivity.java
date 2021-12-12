package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences mPrefrences;
    private SharedPreferences.Editor mEditor;
    private EditText etUsername , etPassword;
    private Button bSignIn , bSignUp;
    private CheckBox cnRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}