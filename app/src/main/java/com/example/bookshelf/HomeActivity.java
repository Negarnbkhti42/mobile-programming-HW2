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

    private static final String SELECTED_FRAGMENT_TAG_KEY = "selected_fragment_tag";
    private String selectedFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if (savedInstanceState == null) {
            // Only add the fragments if this is the initial creation of the activity
            selectedFragment = homeFragment;
            selectedFragmentTag = "home";

            fragmentManager.beginTransaction().add(R.id.home_layout, favoriteFragment, "favorite").hide(favoriteFragment).commit();
            fragmentManager.beginTransaction().add(R.id.home_layout, cartFragment, "cart").hide(cartFragment).commit();
            fragmentManager.beginTransaction().add(R.id.home_layout, profileFragment, "profile").hide(profileFragment).commit();
            fragmentManager.beginTransaction().add(R.id.home_layout, homeFragment, "home").commit();
        } else {
            // If the activity is being recreated, retrieve the selected fragment tag from the saved state
            selectedFragmentTag = savedInstanceState.getString(SELECTED_FRAGMENT_TAG_KEY);

            // Retrieve the selected fragment from the fragment manager using the tag
            selectedFragment = fragmentManager.findFragmentByTag(selectedFragmentTag);
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println(Arrays.toString(fragmentManager.getFragments().toArray()));
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(homeFragment).commit();
                    selectedFragment = homeFragment;
                    selectedFragmentTag = "home";
                    return true;
                } else if (itemId == R.id.action_cart) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(cartFragment).commit();
                    selectedFragment = cartFragment;
                    selectedFragmentTag = "cart";
                    return true;
                } else if (itemId == R.id.action_favorite) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(favoriteFragment).commit();
                    selectedFragment = favoriteFragment;
                    selectedFragmentTag = "favorite";
                    return true;
                } else if (itemId == R.id.action_profile) {
                    fragmentManager.beginTransaction().hide(selectedFragment).show(profileFragment).commit();
                    selectedFragment = profileFragment;
                    selectedFragmentTag = "profile";
                    return true;
                }

                System.out.println(selectedFragment);
                return false;
            }
        });

    }


    @Override
    public void onDialogPositiveClick(String newPassword) {
        System.out.println(newPassword);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the selected fragment's tag in the saved state
        outState.putString(SELECTED_FRAGMENT_TAG_KEY, selectedFragmentTag);
    }
}