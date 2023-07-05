package com.example.bookshelf.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookshelf.Dao.FavouredBookDao;
import com.example.bookshelf.Database.BookShelfDataBase;
import com.example.bookshelf.Database.FavouredBookRepository;
import com.example.bookshelf.Entities.FavouredBook;
import com.example.bookshelf.services.SessionManager;

import java.util.List;

import kotlinx.coroutines.Dispatchers;

public class FavouredBookViewModel extends ViewModel {

    private FavouredBookRepository repository;

    public FavouredBookViewModel() {
        repository = FavouredBookRepository.getInstance();
    }

    public void insert(FavouredBook book) {
        repository.insert(book);
    }

    public void delete(FavouredBook book) {
        repository.delete(book);
    }

    public LiveData<List<FavouredBook>> getFavouredBooks(String username) {
        return repository.getFavouredBooks(username);
    }
}
