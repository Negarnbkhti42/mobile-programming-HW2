package com.example.bookshelf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PasswordChangeDialog extends DialogFragment {

    private PasswordChangeDialogListener listener;
    private EditText newPasswordEditor;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.edit_password_dialog, null);

        newPasswordEditor = view.findViewById(R.id.new_password);

        builder.setView(view)
                .setTitle("edit password")
                .setPositiveButton("set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogPositiveClick(newPasswordEditor.getText().toString());
                    }
                })
                .setNegativeButton("cancel", null);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (PasswordChangeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement PasswordChangeDialogListener");
        }
    }

    public interface PasswordChangeDialogListener {
        void onDialogPositiveClick(String newPassword);
    }
}
