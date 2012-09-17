package com.github.mobileartisans.bawall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.*;
import com.github.mobileartisans.bawall.domain.AkhadaClient;
import com.github.mobileartisans.bawall.domain.Issue;
import com.github.mobileartisans.bawall.domain.UserPreference;

public class IssueViewActivity extends Activity {
    public static final String ISSUE_KEY = "issueKey";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue);
        String issueKey = getIntent().getExtras().getString(ISSUE_KEY);
        setTitle(issueKey);
        new IssueDetailsTask().execute(issueKey);
    }

    public class IssueDetailsTask extends AsyncTask<String, Void, Issue> {
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(IssueViewActivity.this);
            dialog.show();
        }

        @Override
        protected Issue doInBackground(String... issueKeys) {
            UserPreference.Preference preference = new UserPreference(IssueViewActivity.this).getPreference();
            return new AkhadaClient(preference).getIssue(issueKeys[0]);
        }

        @Override
        protected void onPostExecute(Issue issue) {
            super.onPostExecute(issue);
            Button issueAssignee = (Button) findViewById(R.id.issueAssignee);
            TextView issueSummary = (TextView) findViewById(R.id.issueSummary);
            Spinner issueTransitions = (Spinner) findViewById(R.id.issueTransitions);
            issueAssignee.setText(issue.getAssignee());
            issueSummary.setText(issue.getSummary());
            SpinnerAdapter adapter = new ArrayAdapter<String>(IssueViewActivity.this, android.R.layout.simple_spinner_dropdown_item, issue.getTransitions());
            issueTransitions.setAdapter(adapter);
            dialog.dismiss();
        }
    }

}
