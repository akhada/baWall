package com.github.mobileartisans.bawall;

import android.app.Activity;
import android.os.Bundle;
import com.example.R;

public class IssueViewActivity extends Activity {
    public static final String ISSUE_KEY = "issueKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue);
        setTitle(getIntent().getExtras().getString(ISSUE_KEY));
    }
}
