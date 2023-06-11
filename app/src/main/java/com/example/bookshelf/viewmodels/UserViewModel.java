package com.example.bookshelf.viewmodels;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookshelf.Dao.UserDao;
import com.example.bookshelf.Database.BookShelfDataBase;
import com.example.bookshelf.Entities.User;

public class UserViewModel extends ViewModel {
    private UserDao userDao;

    public UserViewModel(Context context) {
        // Obtain an instance of the UserDao
        userDao = BookShelfDataBase.getDatabase(context).userDao();
    }

    public void insert(User user) {
        new InsertAsyncTask(userDao).execute(user);
    }

    public User findUser(String username, String password) {
        try {
            return new FindUserAsyncTask(userDao).execute(username, password).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        new UpdateAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteAsyncTask(userDao).execute(user);
    }

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class FindUserAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;

        public FindUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.findUser(strings[0], strings[1]);
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public UpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public DeleteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

}

