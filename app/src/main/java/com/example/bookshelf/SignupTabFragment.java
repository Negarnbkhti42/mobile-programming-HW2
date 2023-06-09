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
import android.widget.EditText;

public class SignupTabFragment extends Fragment {

    private Activity activity;

    private EditText username;
    private EditText nickname;
    private EditText password;
    private EditText confirmPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = getActivity();

        username = (EditText) view.findViewById(R.id.signup_username);
        nickname = (EditText) view.findViewById(R.id.signup_nickname);
        password = (EditText) view.findViewById(R.id.signup_password);
        confirmPassword = (EditText) view.findViewById(R.id.signup_password_confirm);

        Button signupButton = (Button) view.findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignup();
            }
        });
    }

    private void handleSignup() {
        String usernameText = username.getText().toString();
        String nicknameText = nickname.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirmPassword.getText().toString();

        if (usernameText.isEmpty()) {
            username.setError("Username is required");
            username.requestFocus();
            return;
        }

        if (usernameText.length() < 6 || usernameText.length() > 30) {
            username.setError("Username must be at least 6 characters and at most 30 characters");
            username.requestFocus();
            return;
        }

        if (nicknameText.isEmpty()) {
            nickname.setError("Nickname is required");
            nickname.requestFocus();
            return;
        }

        if (nicknameText.length() < 6 || nicknameText.length() > 30) {
            nickname.setError("Nickname must be at least 6 characters and at most 30 characters");
            nickname.requestFocus();
            return;
        }

        if (passwordText.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if (passwordText.length() < 6 || passwordText.length() > 30) {
            password.setError("Password must be at least 6 characters and at most 30 characters");
            password.requestFocus();
            return;
        }

        if (!passwordText.equals(confirmPasswordText)) {
            confirmPassword.setError("Password and confirm password must be the same");
            confirmPassword.requestFocus();
            return;
        }



        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        activity.finish();
    }
}