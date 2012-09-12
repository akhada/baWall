package com.github.mobileartisans.bawall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.example.R;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onLogin(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText projectSite = (EditText) findViewById(R.id.projectSite);
        new UserPreference(this).save(username.getText().toString(), password.getText().toString(), projectSite.getText().toString());
        startActivity(new Intent(this, HomeActivity.class));
    }
}