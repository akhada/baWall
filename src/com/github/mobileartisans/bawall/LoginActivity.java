package com.github.mobileartisans.bawall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.github.mobileartisans.bawall.domain.UserPreference;

public class LoginActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onLogin(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText projectSite = (EditText) findViewById(R.id.projectSite);
        boolean isValid = validate(username, password, projectSite);
        if (isValid) {
            UserPreference.Preference preference = new UserPreference.Preference(username.getText().toString(), password.getText().toString(), projectSite.getText().toString());
            new UserPreference(this).save(preference);
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    private boolean validate(EditText... requiredFields) {
        boolean isValid = true;
        for (EditText field : requiredFields) {
            if (field.getText().toString().trim().length() == 0) {
                field.setError("value is required");
                isValid = false;
            }
        }
        return isValid;
    }

}