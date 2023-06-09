package com.example.bookshelf.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookshelf.Entities.Comment;

import java.util.List;

@Dao
public interface CommentDao {
    @Insert
    void insert(Comment comment);

    @Query("Select * from Comment where book_id = :bookId")
    List<Comment> findCommentsOfABook(Integer bookId);

    @Query("SELECT SUM(rate) FROM Comment GROUP BY book_id having book_id = :bookId")
    Double getRateAverage(String bookId);
}
