package com.example.bookshelf.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.bookshelf.Dao.FavouredBookDao;
import com.example.bookshelf.Entities.FavouredBook;

import java.util.List;

public class FavouredBookRepository {
    private final FavouredBookDao dao;

    private static FavouredBookRepository favouredBookRepository;

    public static FavouredBookRepository getInstance() {
        return favouredBookRepository;
    }

    public static void setInstance(FavouredBookRepository instance) {
        if (favouredBookRepository == null)
            favouredBookRepository = instance;
    }


    public FavouredBookRepository(Application application) {
        BookShelfDataBase bookShelfDatabase = BookShelfDataBase.getDatabase(application);
        dao = bookShelfDatabase.favouredBookDao();
    }

    public void insert(FavouredBook book) {
        new InsertAsyncTask(dao).execute(book);
    }

    public void delete(FavouredBook book) {
        new DeleteAsyncTask(dao).execute(book);
    }

    public LiveData<List<FavouredBook>> getFavouredBooks(String username) {
        return dao.getUserFavoured(username);
    }

    public FavouredBook find(String username, String bookId) {
        try {
            return new FindAsyncTask(dao).execute(new FavouredBook(null, bookId, username)).get();
        } catch (Exception e) {
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<FavouredBook, Void, Void> {
        private final FavouredBookDao asyncTaskDao;

        InsertAsyncTask(FavouredBookDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavouredBook... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<FavouredBook, Void, Void> {
        private final FavouredBookDao asyncTaskDao;

        DeleteAsyncTask(FavouredBookDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavouredBook... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class FindAsyncTask extends AsyncTask<FavouredBook, Void, FavouredBook> {
        private final FavouredBookDao asyncTaskDao;

        FindAsyncTask(FavouredBookDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected FavouredBook doInBackground(final FavouredBook... params) {
            return asyncTaskDao.find(params[0].getUsername(), params[0].getBookId());
        }
    }

}
