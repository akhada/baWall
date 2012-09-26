package com.github.mobileartisans.bawall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mobileartisans.bawall.component.ProgressAsyncTask;
import com.github.mobileartisans.bawall.domain.*;

import java.util.List;

public class IssueViewActivity extends Activity {
    public static final String ISSUE_KEY = "issueKey";
    private String issueKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue);
        issueKey = getIntent().getExtras().getString(ISSUE_KEY);
        setTitle("Loading " + issueKey + " details");
        new IssueDetailsTask(this, "Loading " + issueKey + " details").execute(issueKey);
        bindBackButton();
    }

    public class IssueDetailsTask extends ProgressAsyncTask<String, Void, Issue> {

        protected IssueDetailsTask(Context context, String message) {
            super(context, message);
        }

        @Override
        protected Issue process(String... issueKeys) {
            UserPreference.Preference preference = new UserPreference(IssueViewActivity.this).getPreference();
            return new AkhadaClient(preference).getIssue(issueKeys[0]);
        }

        @Override
        protected void onSuccess(final Issue issue) {
            Button issueAssignee = (Button) findViewById(R.id.issueAssignee);
            TextView issueSummary = (TextView) findViewById(R.id.issueSummary);
            TextView issueTitle = (TextView) findViewById(R.id.issueTitle);
            Button issueTransitions = (Button) findViewById(R.id.issueTransitions);
            issueTransitions.setText(issue.getStatus());
            issueTransitions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(IssueViewActivity.this);
                    builder.setTitle("Transition to...");
                    builder.setItems(issue.getTransitionNames(), new UpdateIssueStatus(issue.getTransitions())).create().show();
                }
            });
            issueAssignee.setText(issue.getAssignee());
            issueSummary.setText(issue.getSummary());
            issueTitle.setText(issue.getKey());
        }

        @Override
        protected void onNotFound() {
            Toast.makeText(context, "Issue not found", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(IssueViewActivity.this, HomeActivity.class));
        }
    }

    public void onListAssignee(View view) {
        new IssueAssigneeTask(this).execute(issueKey);
    }

    public class IssueAssigneeTask extends ProgressAsyncTask<String, Void, Assignees> {

        protected IssueAssigneeTask(Context context) {
            super(context, "Loading assignees");
        }

        @Override
        protected Assignees process(String... issueKeys) {
            UserPreference.Preference preference = new UserPreference(IssueViewActivity.this).getPreference();
            return new AkhadaClient(preference).getAssignees(issueKeys[0]);
        }

        @Override
        protected void onSuccess(Assignees assignees) {
            AlertDialog.Builder builder = new AlertDialog.Builder(IssueViewActivity.this);
            builder.setTitle("Change assignee to ...");
            builder.setItems(assignees.getUserNames(), new UpdateIssueAssignee(assignees)).create().show();
        }
    }

    private class UpdateIssueAssignee implements DialogInterface.OnClickListener {

        private Assignees assignees;

        public UpdateIssueAssignee(Assignees assignees) {
            this.assignees = assignees;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int index) {
            User user = assignees.getUsers().get(index);
            Button assignee = (Button) findViewById(R.id.issueAssignee);
            assignee.setText(user.getDisplayName());
            new UpdateIssueAssigneeTask(IssueViewActivity.this).execute(user.getName());
        }
    }

    private class UpdateIssueAssigneeTask extends ProgressAsyncTask<String, Void, Void> {

        protected UpdateIssueAssigneeTask(Context context) {
            super(context, "Updating assignee");
        }

        @Override
        protected Void process(String... assignees) {
            UserPreference.Preference preference = new UserPreference(IssueViewActivity.this).getPreference();
            new AkhadaClient(preference).updateAssignee(issueKey, assignees[0]);
            return null;
        }
    }

    private class UpdateIssueStatus implements DialogInterface.OnClickListener {

        private List<Transition> transitions;

        public UpdateIssueStatus(List<Transition> transitions) {
            this.transitions = transitions;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int position) {
            Transition transition = transitions.get(position);
            new UpdateIssueTask(IssueViewActivity.this).execute(transition);
            Toast.makeText(IssueViewActivity.this, transition.getName(), Toast.LENGTH_SHORT).show();

        }
    }

    private class UpdateIssueTask extends ProgressAsyncTask<Transition, Void, Void> {
        protected UpdateIssueTask(Context context) {
            super(context, "Updating state");
        }

        @Override
        protected Void process(Transition... status) {
            UserPreference.Preference preference = new UserPreference(IssueViewActivity.this).getPreference();
            new AkhadaClient(preference).updateStatus(issueKey, status[0]);
            return null;
        }
    }

    private void bindBackButton() {
        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IssueViewActivity.this.finish();
            }
        });
    }
}
