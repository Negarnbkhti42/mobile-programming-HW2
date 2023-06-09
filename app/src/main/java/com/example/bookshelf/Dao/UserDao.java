package com.example.bookshelf.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookshelf.Entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("select * From User where nickname = :nickname")
    User findUser(String nickname);
}
