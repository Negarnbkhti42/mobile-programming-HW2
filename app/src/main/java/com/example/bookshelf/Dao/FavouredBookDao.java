package com.example.bookshelf.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookshelf.Entities.BoughtBook;
import com.example.bookshelf.Entities.FavouredBook;

import java.util.List;

@Dao
public interface FavouredBookDao {
    @Insert
    void insert(FavouredBook favouredBook);

    @Query("Select * from favouredbook where username = :username")
    List<FavouredBook> find(String username);

    @Delete
    void delete(FavouredBook favouredBook);
}
