package com.example.bookshelf.Database;

import android.app.Application;

import com.example.bookshelf.Dao.CommentDao;

public class BookRepository {
    private static BookRepository bookRepository;
    private BookShelfDataBase bookShelfDatabase;
    private CommentDao commentDao;

    public static BookRepository getInstance() {
        return bookRepository;
    }

    public static void setInstance(BookRepository repository){
        bookRepository = repository;
    }

    public BookRepository(Application application) {
        bookShelfDatabase = BookShelfDataBase.getDatabase(application);
        commentDao = bookShelfDatabase.commentDao();
    }

    public Double getAverageRate(String bookId) {
        return commentDao.getRateAverage(bookId);
    }
}
