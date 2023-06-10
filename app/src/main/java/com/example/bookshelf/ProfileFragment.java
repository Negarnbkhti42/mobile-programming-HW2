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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bookshelf.services.SessionManager;


public class ProfileFragment extends Fragment {

    private SessionManager sessionManager;


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Activity activity = getActivity();
        this.sessionManager = new SessionManager(activity);

        TextView editPasswordButton = (TextView) view.findViewById(R.id.edit_password_button);
        ImageButton settingButton = (ImageButton) view.findViewById(R.id.btn_view_settings);

        TextView logoutButton = (TextView) view.findViewById(R.id.logout_button);
        TextView deleteAccountButton = (TextView) view.findViewById(R.id.delete_account_button);

        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordChangeDialog passwordChangeDialog = new PasswordChangeDialog();
                passwordChangeDialog.show(getChildFragmentManager(), "password change dialog");
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SettingsActivity.class);
                activity.startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.clearSession();
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.clearSession();
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
    }

}