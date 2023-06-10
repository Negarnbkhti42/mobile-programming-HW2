package com.example.bookshelf.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.bookshelf.Dao.FavouredBookDao;
import com.example.bookshelf.Database.BookShelfDataBase;
import com.example.bookshelf.Entities.FavouredBook;

import java.util.List;

public class FavouredBookRepository {

    private FavouredBookDao favouredBookDao;
    private LiveData<List<FavouredBook>> favouredBooks;

    public FavouredBookRepository(Application application) {

        BookShelfDataBase dataBase = BookShelfDataBase.getDatabase(application);
//        favouredBookDao = dataBase.favouredBookDao();
        favouredBooks = favouredBookDao.find("hi");
    }

    public void insert() {

    }

    public void delete() {

    }
}
