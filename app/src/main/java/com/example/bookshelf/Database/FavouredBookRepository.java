package com.example.bookshelf.Database;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.bookshelf.Dao.FavouredBookDao;
import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.Google.Book;

import java.util.List;

public class FavouredBookRepository {

    private LiveData<List<FavouredBook>> favouredBooks;
    private FavouredBookDao dao;


    public FavouredBookRepository(FavouredBookDao dao, String username) {
        this.dao = dao;
        favouredBooks = dao.getUserFavoured(username);
    }

    public void insert(FavouredBook book) {
        new InsertAsyncTask(dao).execute(book);
    }

    public void delete(FavouredBook book) {
        new DeleteAsyncTask(dao).execute(book);
    }

    public LiveData<List<FavouredBook>> getFavouredBooks() {
        return favouredBooks;
    }

    public FavouredBook find(String username, String bookId) {
        try {
            return new FindAsyncTask(dao).execute(new FavouredBook(null, bookId, username)).get();
        } catch (Exception e) {
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<FavouredBook, Void, Void> {
        private FavouredBookDao asyncTaskDao;

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
        private FavouredBookDao asyncTaskDao;

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
        private FavouredBookDao asyncTaskDao;

        FindAsyncTask(FavouredBookDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected FavouredBook doInBackground(final FavouredBook... params) {
            return asyncTaskDao.find(params[0].getUsername(), params[0].getBookId());
        }
    }

}
