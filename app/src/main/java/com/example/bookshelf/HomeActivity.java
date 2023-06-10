package com.example.bookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookshelf.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity implements PasswordChangeDialog.PasswordChangeDialogListener {

    private final HomeFragment homeFragment = HomeFragment.newInstance();
    private final FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
    private final CartFragment cartFragment = CartFragment.newInstance();
    private final ProfileFragment profileFragment = ProfileFragment.newInstance();
    private String lastFragment;

    private final String HOME_FRAGMENT_KEY = "home";
    private final String FAVORITE_FRAGMENT_KEY = "favorite";
    private final String CART_FRAGMENT_KEY = "cart";
    private final String PROFILE_FRAGMENT_KEY = "profile";
    private final String LAST_FRAGMENT_KEY = "lastFragment";

    ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lastFragment = savedInstanceState == null ? HOME_FRAGMENT_KEY : savedInstanceState.getString(LAST_FRAGMENT_KEY, HOME_FRAGMENT_KEY);
        Fragment firstDisplayFragment = homeFragment;

        switch (lastFragment) {
            case "cart":
                firstDisplayFragment = cartFragment;
                break;
            case "favorite":
                firstDisplayFragment = favoriteFragment;
                break;
            case "profile":
                firstDisplayFragment = profileFragment;
                break;
        }

        replaceFragment(firstDisplayFragment, lastFragment);
//        binding.navigation.setBackground(null);

        binding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    replaceFragment(homeFragment, HOME_FRAGMENT_KEY);
                    break;
                case R.id.action_cart:
                    replaceFragment(cartFragment, CART_FRAGMENT_KEY);
                    break;
                case R.id.action_favorite:
                    replaceFragment(favoriteFragment, FAVORITE_FRAGMENT_KEY);
                    break;
                case R.id.action_profile:
                    replaceFragment(profileFragment, PROFILE_FRAGMENT_KEY);
                    break;
            }
            return true;
        });

    }


    @Override
    public void onDialogPositiveClick(String newPassword) {
        System.out.println(newPassword);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_FRAGMENT_KEY, lastFragment);
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_layout, fragment, tag);
        lastFragment = tag;
        fragmentTransaction.commit();
    }
}