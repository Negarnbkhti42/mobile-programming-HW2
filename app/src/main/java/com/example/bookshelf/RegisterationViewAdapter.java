package com.example.bookshelf;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class RegisterationViewAdapter extends FragmentStateAdapter {

    private Activity activity;

    public RegisterationViewAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Activity activity) {
        super(fragmentManager, lifecycle);
        this.activity = activity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 1) {
            return  new SignupTabFragment(this.activity);
        }
        return new LoginTabFragment(this.activity);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
