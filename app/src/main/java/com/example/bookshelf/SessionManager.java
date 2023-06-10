package com.example.bookshelf;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.security.SecureRandom;

public class SessionManager {
    private static final String PREF_NAME = "Session";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_AUTH_TOKEN = "authToken";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void createSession(String userId) {
        editor.putString(KEY_USER_ID, userId);
        String authToken = generateAuthKey();
        editor.putString(KEY_AUTH_TOKEN, authToken);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return preferences.getString(KEY_USER_ID, null) != null;
    }

    public String getUserId() {
        return preferences.getString(KEY_USER_ID, null);
    }

    public String getAuthToken() {
        return preferences.getString(KEY_AUTH_TOKEN, null);
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }

    private String generateAuthKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        String authToken = Base64.encodeToString(randomBytes, Base64.DEFAULT);
        return  authToken;
    }
}

