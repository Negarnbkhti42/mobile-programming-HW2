package com.example.bookshelf.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookshelf.Dao.UserDao;
import com.example.bookshelf.Database.BookShelfDataBase;
import com.example.bookshelf.Entities.User;

public class UserViewModel extends ViewModel {
    private UserDao userDao;
    private LiveData<User> userLiveData;

    public UserViewModel(Context context) {
        // Obtain an instance of the UserDao
        userDao = BookShelfDataBase.getDatabase(context).userDao();
    }

    public LiveData<User> getUserByUsername(String username, String password) {
        if (userLiveData == null) {
            // Execute the query asynchronously and receive the LiveData object
            userLiveData = userDao.findUser(username, password);
        }
        return userLiveData;
    }
}

