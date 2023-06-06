package com.example.bookshelf.Dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookshelf.Entities.User;

import java.util.List;

public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE nickname = nickname ")
    public User findUser(String nickname);
}
