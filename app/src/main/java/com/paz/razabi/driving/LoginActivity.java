package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText etEmail , etPassword;
    private Button bSignIn , bSignUp;
    private CheckBox cbRemember;
    private final String EMPTY_OR_MISSING_SP = "";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        bSignIn = findViewById(R.id.bSignIn);
        bSignUp = findViewById(R.id.bSignUp);

        cbRemember = findViewById(R.id.cbRemember);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        // If possible, restore saved credentials.
        restoreCreds();

        // Else, continue with sign in/up

    }

    private void restoreCreds() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);

        etEmail.setText(mPreferences.getString("saved_email", EMPTY_OR_MISSING_SP));
        etPassword.setText(mPreferences.getString("saved_password", EMPTY_OR_MISSING_SP));
        cbRemember.setChecked(mPreferences.getBoolean("saved_creds", false));
    }

    public void signUp(View view) {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);

        if (cbRemember.isChecked()) {
            mEditor.putString("saved_email", etEmail.getText().toString());
            mEditor.putString("saved_password", etPassword.getText().toString());
            mEditor.putBoolean("save_creds", true);
        } else {
            mEditor.putString("saved_email", EMPTY_OR_MISSING_SP);
            mEditor.putString("saved_password", EMPTY_OR_MISSING_SP);
            mEditor.putBoolean("save_creds", false);
        }
        mEditor.apply();
    }

    public void signIn(View view) {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        if (cbRemember.isChecked()) {
            mEditor.putString("saved_email", etEmail.getText().toString());
            mEditor.putString("saved_password", etPassword.getText().toString());
            mEditor.putBoolean("save_creds", true);
        } else {
            mEditor.putString("saved_email", EMPTY_OR_MISSING_SP);
            mEditor.putString("saved_password", EMPTY_OR_MISSING_SP);
            mEditor.putBoolean("save_creds", false);
        }
        mEditor.apply();
    }
}