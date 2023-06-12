package com.example.bookshelf.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.preference.PreferenceManager;

import java.security.SecureRandom;

public class SessionManager {
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NICKNAME = "userNickname";
    private static final String KEY_AUTH_TOKEN = "authToken";

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private Context context;

    private boolean shouldSessionBeKept;

    public SessionManager(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public void createSession(String userId, String nickname, boolean keepSession) {
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NICKNAME, nickname);
        String authToken = generateAuthKey();
        editor.putString(KEY_AUTH_TOKEN, authToken);
        shouldSessionBeKept = keepSession;
        editor.apply();
    }

    public boolean isLoggedIn() {
        return preferences.getString(KEY_USER_ID, null) != null;
    }

    public String getUserId() {
        return preferences.getString(KEY_USER_ID, null);
    }

public String getUserNickname() {
        return preferences.getString(KEY_USER_NICKNAME, null);
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
        return authToken;
    }
}

