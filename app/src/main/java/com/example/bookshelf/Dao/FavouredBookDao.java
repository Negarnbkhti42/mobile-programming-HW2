package com.example.bookshelf.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookshelf.Entities.FavouredBook;

import java.util.List;

@Dao
public interface FavouredBookDao {
    @Insert
    void insert(FavouredBook favouredBook);

    @Query("Select * from favouredbook where username = :username")
    LiveData<List<FavouredBook>> getUserFavoured(String username);

    @Query("Select * from favouredbook where username = :username and book_id = :bookId")
    FavouredBook find(String username, String bookId);

    @Delete
    void delete(FavouredBook favouredBook);
}
