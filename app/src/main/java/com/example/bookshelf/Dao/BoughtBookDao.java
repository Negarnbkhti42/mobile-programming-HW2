package com.example.bookshelf.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookshelf.Entities.BoughtBook;

import java.util.List;

@Dao
public interface BoughtBookDao {
    @Insert
    void insert(BoughtBook boughtBook);

    @Query("Select * from boughtbook where nickname = :nickname")
    List<BoughtBook> find(String nickname);
}
