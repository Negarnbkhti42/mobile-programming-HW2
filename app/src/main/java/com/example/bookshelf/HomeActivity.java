package com.example.bookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity implements PasswordChangeDialog.PasswordChangeDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final HomeFragment homeFragment = HomeFragment.newInstance();
        final FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
        final CartFragment cartFragment = CartFragment.newInstance();
        final ProfileFragment profileFragment = ProfileFragment.newInstance();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment[] selectedFragment = {homeFragment};

        fragmentManager.beginTransaction().add(R.id.home_layout, favoriteFragment, "favorite").hide(favoriteFragment).commit();
        fragmentManager.beginTransaction().add(R.id.home_layout, cartFragment, "cart").hide(cartFragment).commit();
        fragmentManager.beginTransaction().add(R.id.home_layout, profileFragment, "profile").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.home_layout, homeFragment, "home").commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    fragmentManager.beginTransaction().hide(selectedFragment[0]).show(homeFragment).commit();
                    selectedFragment[0] = homeFragment;
                    return true;
                } else if (itemId == R.id.action_cart) {
                    fragmentManager.beginTransaction().hide(selectedFragment[0]).show(cartFragment).commit();
                    selectedFragment[0] = cartFragment;
                    return true;
                } else if (itemId == R.id.action_favorite) {
                    fragmentManager.beginTransaction().hide(selectedFragment[0]).show(favoriteFragment).commit();
                    selectedFragment[0] = favoriteFragment;
                    return true;
                } else if (itemId == R.id.action_profile) {
                    fragmentManager.beginTransaction().hide(selectedFragment[0]).show(profileFragment).commit();
                    selectedFragment[0] = profileFragment;
                    return true;
                }

                return false;
            }
        });

    }

    @Override
    public void onDialogPositiveClick(String newPassword) {
        System.out.println(newPassword);

    }
}