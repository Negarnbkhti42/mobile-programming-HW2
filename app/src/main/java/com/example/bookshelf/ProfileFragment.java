package com.example.bookshelf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class ProfileFragment extends Fragment {

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

        Button editPasswordButton = (Button) view.findViewById(R.id.edit_password_button);
        Button submitPasswordEdit = (Button) view.findViewById(R.id.submit_password_edit_button);

        EditText newPasswordInput = (EditText) view.findViewById(R.id.new_password_input);

        LinearLayout editPasswordField = (LinearLayout) view.findViewById(R.id.password_edit_field);

        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPasswordField.setVisibility(View.VISIBLE);
                editPasswordButton.setVisibility(View.GONE);
            }
        });

        submitPasswordEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPasswordInput.setText("");
                editPasswordField.setVisibility(View.GONE);
                editPasswordButton.setVisibility(View.VISIBLE);
            }
        });
    }
}