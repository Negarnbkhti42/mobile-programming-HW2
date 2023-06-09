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

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity implements PasswordChangeDialog.PasswordChangeDialogListener {

    private final HomeFragment homeFragment = HomeFragment.newInstance();
    private final FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
    private final CartFragment cartFragment = CartFragment.newInstance();
    private final ProfileFragment profileFragment = ProfileFragment.newInstance();
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        selectedFragment = homeFragment;

        fragmentManager.beginTransaction().add(R.id.home_layout, favoriteFragment, "favorite").hide(favoriteFragment).commit();
        fragmentManager.beginTransaction().add(R.id.home_layout, cartFragment, "cart").hide(cartFragment).commit();
        fragmentManager.beginTransaction().add(R.id.home_layout, profileFragment, "profile").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.home_layout, homeFragment, "home").commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println(Arrays.toString(fragmentManager.getFragments().toArray()));
                int itemId = item.getItemId();
                System.out.println(selectedFragment);
                if (itemId == R.id.action_home) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(homeFragment).commit();
                    selectedFragment = homeFragment;
                    return true;
                } else if (itemId == R.id.action_cart) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(cartFragment).commit();
                    selectedFragment = cartFragment;
                    return true;
                } else if (itemId == R.id.action_favorite) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(favoriteFragment).commit();
                    selectedFragment = favoriteFragment;
                    return true;
                } else if (itemId == R.id.action_profile) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(profileFragment).commit();
                    selectedFragment = profileFragment;
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