package com.example.bookshelf.Database;

import android.os.AsyncTask;

import com.example.bookshelf.Dao.UserDao;
import com.example.bookshelf.Entities.User;

public class UserRepository {

    private UserDao dao;

    public UserRepository(UserDao dao) {
        this.dao = dao;
    }

    public void insert(User user) {
        new InsertAsyncTask(dao).execute(user);
    }


    public void delete(User user) {
        new DeleteAsyncTask(dao).execute(user);
    }

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncTaskDao;

        InsertAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncTaskDao;

        DeleteAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
