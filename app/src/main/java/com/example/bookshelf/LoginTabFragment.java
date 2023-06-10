package com.example.bookshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bookshelf.services.SessionManager;

public class LoginTabFragment extends Fragment {

    private Activity activity;
    private SessionManager sessionManager;

    EditText username;
    EditText password;
    CheckBox rememberMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = getActivity();
        this.sessionManager = new SessionManager(activity);

        username = (EditText) view.findViewById(R.id.login_username);
        password = (EditText) view.findViewById(R.id.login_password);
        rememberMe = (CheckBox) view.findViewById(R.id.login_remember);

        Button signupButton = (Button) view.findViewById(R.id.login_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginIsValid()) {
                    String usernameText = username.getText().toString();
                    sessionManager.createSession(usernameText, rememberMe.isChecked());

                    Intent intent = new Intent(activity, HomeActivity.class);
                    startActivity(intent);
                    activity.finish();                }
            }
        });
    }

    private boolean loginIsValid() {
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        boolean rememberMeChecked = rememberMe.isChecked();

        if (usernameText.isEmpty()) {
            username.setError("Username is required");
            username.requestFocus();
            return false;
        }

        if (passwordText.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return false;
        }



        return  true;

    }

}